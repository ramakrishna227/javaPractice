package performanceAndMonitorTools;

import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ThreadMonitor {
	String CLASS_NAME = ThreadMonitor.class.getName();
	ThreadMXBean threadMXBean;

	/**
	 * default - JDK 6+ VM for JDK 5, method name will be
	 * "findMonitorDeadlockedThreads" and for this details about synchronizers and
	 * locks cannot be dumped.()
	 * 
	 * Can be found by scanning MBeanOperationInfo array by getting it from
	 * MBeanServerConnection.getMBeanInfo(new
	 * ObjectName(ManagementFactory.THREAD_MXBEAN_NAME)).getOperations();
	 */
	private String findDeadlocksMethodName = "findDeadlockedThreads";

	// Map to hold thread Id to initial CPU usage in milliseconds
	private final Map<Long, Long> oldThreadIDtoCPUUsageMap = new ConcurrentHashMap<>();
	// Map to hold thread Id to current CPU usage in milliseconds
	private final Map<Long, Long> currentThreadIDtoCPUUsageMap = new ConcurrentHashMap<>();
	// flag to show/hide thread CPU usage details in thread dump
	private boolean isShowThreadCPUUsage = false;

	// duration for which thread CPU time is calculated
	private long duration;

	// total available cores in system
	private int totalCores;

	public ThreadMonitor() {
		threadMXBean = ManagementFactory.getThreadMXBean();
	}

	public ThreadMonitor(boolean isShowThreadCPUUsage) {
		this();
		this.isShowThreadCPUUsage = isShowThreadCPUUsage;
		totalCores = Runtime.getRuntime().availableProcessors();
	}

	public String getThreadDump() {
		StringBuilder sb = new StringBuilder();
		sb.append(getThreadDumpWithNoDeadlockInfo());
		sb.append(getDeadlockInfo());
		return sb.toString();
	}

	private String getThreadDumpWithNoDeadlockInfo() {
		System.out.println("Getting smart stack trace");
		StringBuilder sb = new StringBuilder();
		if (threadMXBean.isObjectMonitorUsageSupported() && threadMXBean.isSynchronizerUsageSupported()) {
			sb.append(getDumpThreadInfoWithLocks());
		} else {
			sb.append(dumpThreadInfo());
		}
		System.out.println("Smart stack trace task got completed");
		return sb.toString();
	}

	private String dumpThreadInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nFull java thred dump");
		long[] tids = threadMXBean.getAllThreadIds();
		ThreadInfo[] tinfos = threadMXBean.getThreadInfo(tids);
		for (ThreadInfo ti : tinfos) {
			sb.append(getThreadInfoString(ti));
		}
		return sb.toString();
	}

	private String getDeadlockInfo() {
		StringBuilder sb = new StringBuilder();
		System.out.println("Trying to get deadlock information if any");
		long startTime = 0;
		long endTime = 0;
		long[] tids;
		if (findDeadlocksMethodName.equals("findDeadlockedThreads") && threadMXBean.isSynchronizerUsageSupported()) {
			startTime = System.currentTimeMillis();
			tids = threadMXBean.findDeadlockedThreads();
			endTime = System.currentTimeMillis();
			if (tids == null) {
				sb.append("\nSummary: No deadlock found. Time taken to detect dead locks: ");
				sb.append(endTime - startTime);
				sb.append("ms\n");
				return sb.toString();
			}
			sb.append("Deadlock found :-\n");
			ThreadInfo[] infos = threadMXBean.getThreadInfo(tids, true, true);

			for (ThreadInfo ti : infos) {
				sb.append(getThreadInfoString(ti));
				sb.append(getPrintLockInfo(ti.getLockedSynchronizers()));
				sb.append("\n");
			}
		} else {
			startTime = System.currentTimeMillis();
			tids = threadMXBean.findMonitorDeadlockedThreads();
			endTime = System.currentTimeMillis();
			if (tids == null) {
				sb.append("\nSummary: No deadlock found. Time taken to detect dead locks: ");
				sb.append(endTime - startTime);
				sb.append("ms\n");
				return sb.toString();
			}
			sb.append("Monitored deadlock found:-\n");
			ThreadInfo[] infos = threadMXBean.getThreadInfo(tids, Integer.MAX_VALUE);
			for (ThreadInfo ti : infos) {
				sb.append(getThreadInfoString(ti));
				sb.append("\n");
			}
		}
		sb.append("\nSummary: Detected Deadlocks(s). Time taken to detect dead locks: ");
		sb.append(startTime - endTime);
		sb.append("ms\n");
		System.out.println("Deadlock fetching task got completed");
		return sb.toString();
	}

	// prints threadDump with locks info
	private String getDumpThreadInfoWithLocks() {
		StringBuilder sb = new StringBuilder();
		ThreadInfo[] tinfos = threadMXBean.dumpAllThreads(true, true);
		if (tinfos != null && tinfos.length > 0) {
			sb.append(System.getProperty("line.separator")).append("Full thread dump with locks info: ");// This string
			for (ThreadInfo ti : tinfos) {
				sb.append(getThreadInfoString(ti));
				LockInfo[] syncs = ti.getLockedSynchronizers();
				sb.append(getPrintLockInfo(syncs));

			}

		}

		return sb.toString();
	}

	private Object getThreadInfoString(ThreadInfo ti) {
		StringBuilder sb = new StringBuilder();
		sb.append(getPrintThreadText(ti));
		StackTraceElement[] stackTrace = ti.getStackTrace();
		MonitorInfo[] monitors = ti.getLockedMonitors();
		for (int i = 0; i < stackTrace.length; i++) {
			{
				StackTraceElement ste = stackTrace[i];
				sb.append("\n" + "    " + "at " + ste.toString());
				for (MonitorInfo mi : monitors) {
					if (mi.getLockedStackDepth() == i) {
						sb.append("\n" + "    " + "  -locked " + mi);
					}
				}
			}
		}
		return sb.toString();
	}

	private Object getPrintThreadText(ThreadInfo ti) {
		StringBuilder sb = new StringBuilder();
		// To log hex thread id - "0X" + Long.toHexString(ti.getThreadId());
		String threadId = String.valueOf(ti.getThreadId());
		sb.append(System.getProperty("line.separator"));
		sb.append("\"");
		sb.append(ti.getThreadName());
		sb.append("\"");
		sb.append(" prio=0 tid=");
		sb.append(threadId);
		sb.append(" nid=0 ");
		appendThreadCPUUsageInfo(ti, sb);
		if (Thread.State.NEW.equals(ti.getThreadState()) || Thread.State.RUNNABLE.equals(ti.getThreadState())
				|| Thread.State.TERMINATED.equals(ti.getThreadState())) {
			sb.append(ti.getThreadState().toString().toLowerCase());
			sb.append(System.getProperty("line.separator"));
			sb.append("java.lang.Thread.State: ");
			sb.append(ti.getThreadState());
		} else if (Thread.State.BLOCKED.equals(ti.getThreadState())) {
			sb.append("waiting for monitor entry [on lock ");
			sb.append(ti.getLockName());
			sb.append("]");
			sb.append(System.getProperty("line.separator"));
			sb.append("java.lang.Thread.State: ");
			sb.append(ti.getThreadState());
			sb.append(" (on object monitor)");
		} else if (Thread.State.WAITING.equals(ti.getThreadState())
				|| Thread.State.TIMED_WAITING.equals(ti.getThreadState())) {
			if ("java.lang.Object.wait(Native Method)".equalsIgnoreCase(ti.getStackTrace()[0].toString())) {
				sb.append("in Object.wait() [on lock ");
				sb.append(ti.getLockName());
				sb.append("]");
				sb.append(System.getProperty("line.separator"));
				sb.append("java.lang.Thread.State: ");
				sb.append(ti.getThreadState());
				sb.append(" (on object monitor)");
			} else if ("sun.misc.Unsafe.park(Native Method)".equalsIgnoreCase(ti.getStackTrace()[0].toString())) {
				sb.append("waiting on condition [on lock ");
				sb.append(ti.getLockName());
				sb.append("]");
				sb.append("java.lang.Thread.State: ");
				sb.append(ti.getThreadState());
				sb.append(" (parking)");
			} else if ("java.lang.Thread.sleep(Native Method)".equalsIgnoreCase(ti.getStackTrace()[0].toString())) {
				sb.append("waiting on condition [in lock ");
				sb.append(ti.getLockName());
				sb.append("]");
				sb.append(System.getProperty("line.separator"));
				sb.append("java.lang.Thread.State: ");
				sb.append(ti.getThreadState());
				sb.append(" (sleeping)");
			}

		}

		return sb.toString();
	}

	private Object getPrintLockInfo(LockInfo[] syncs) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n" + "    " + "Locked synchronizers: count = " + syncs.length);
		for (LockInfo li : syncs) {
			sb.append("\n" + "    " + "  - " + li);
		}
		sb.append("\n");

		return sb.toString();
	}

	private void appendThreadCPUUsageInfo(ThreadInfo ti, StringBuilder sb) {
		if (isShowThreadCPUUsage) {
			long netThreadCPUTime = findThreadCPUTime(ti);
			sb.append("cpuTime=");
			sb.append(netThreadCPUTime);
			sb.append("ms");
			sb.append(" cpuPerc=");
			sb.append(getCPUPerc(netThreadCPUTime));
			sb.append(" ");
		}
	}

	private long findThreadCPUTime(ThreadInfo ti) {
		long threadId = ti.getThreadId();
		Long lastThreadCPUTimeInMillis = oldThreadIDtoCPUUsageMap.get(threadId);
		long netThreadCPUTime = 0;
		Long currentThreadCPUTimeInMillis = currentThreadIDtoCPUUsageMap.get(threadId);
		try {
			if (currentThreadCPUTimeInMillis == null) {
				long threadUsage = threadMXBean.getThreadCpuTime(threadId);
				if (threadUsage != -1) {
					currentThreadCPUTimeInMillis = TimeUnit.NANOSECONDS.toMillis(threadUsage);
				}
			}
			if (lastThreadCPUTimeInMillis != null && currentThreadCPUTimeInMillis != null) {
				netThreadCPUTime = currentThreadCPUTimeInMillis - lastThreadCPUTimeInMillis;
			} else if (currentThreadCPUTimeInMillis != null) {
				netThreadCPUTime = currentThreadCPUTimeInMillis;
			}
		} catch (Exception e) {

		}

		return netThreadCPUTime;
	}

	private double getCPUPerc(long totalThreadCPUTime) {
		double cpuPercentage = 0;
		if (duration > 0) {
			long totalAvailableCPUTime = duration * totalCores;
			cpuPercentage = ((double) totalThreadCPUTime * 100) / (double) totalAvailableCPUTime;
		}
		return cpuPercentage;
	}

}
