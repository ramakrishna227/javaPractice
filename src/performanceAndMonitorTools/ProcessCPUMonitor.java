package performanceAndMonitorTools;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.management.OperatingSystemMXBean;

public class ProcessCPUMonitor {
	private ProcessCPUMonitor instance;
	private ThreadMXBean threadMXBean;
	private OperatingSystemMXBean operatingSystemMxBean;
	private Timer timer;
	private final Map<Long, Long> oldThreadIDToCPUUsageMap = new ConcurrentHashMap<>();
	private final Map<Long, Long> newThreadIDToCPUUsageMap = new ConcurrentHashMap<>();
	private double processCPULoad;
	private String threadDump;
	private ThreadMonitor threadMonitor;
	Instant old, now;

	public ProcessCPUMonitor getInstance() {
		if (instance == null) {
			synchronized (this) {
				if (instance == null) {
					instance = new ProcessCPUMonitor();
				}
			}
		}
		return instance;
	}

	private void startMonitoring() {
		threadMXBean = ManagementFactory.getThreadMXBean();
		if (ManagementFactory.getOperatingSystemMXBean() instanceof OperatingSystemMXBean) {
			operatingSystemMxBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		}
		threadMonitor = new ThreadMonitor(true);
		old = now = Instant.now();
		startTimerThread();

	}

	private void startTimerThread() {
		if (timer == null) {
			timer = new Timer();
		}
		schedule(5000L);// schedule every 5 seconds which is 5000milli seconds
	}

	private void schedule(long delay) {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				checkProcessCPU();
			}

		}, delay);
	}

	private void checkProcessCPU() {
		captureCurrentThreadCPUUsage();
		processCPULoad = operatingSystemMxBean.getProcessCpuLoad() * 100;
		// if process cpu load crosses 60
		if (processCPULoad > 60) {
			setCPUUsageDetails();
			threadDump = threadMonitor.getThreadDump();
			System.out.println(threadDump);
		}

	}

	private void setCPUUsageDetails() {
		threadMonitor.setDuration(Duration.between(old, now).toMillis());
		threadMonitor.setOldThreadIDToCPUUsageMap(oldThreadIDToCPUUsageMap);
		threadMonitor.setNewThreadIDToCPUUsageMap(newThreadIDToCPUUsageMap);

	}

	private void captureCurrentThreadCPUUsage() {
		old = now;
		now = Instant.now();
		oldThreadIDToCPUUsageMap.clear();
		oldThreadIDToCPUUsageMap.putAll(newThreadIDToCPUUsageMap);
		newThreadIDToCPUUsageMap.clear();
		long[] threadIDs = threadMXBean.getAllThreadIds();
		for (long id : threadIDs) {
			long threadUsage = -1;
			threadUsage = threadMXBean.getThreadCpuTime(id);
			if (threadUsage != -1) {
				newThreadIDToCPUUsageMap.put(id, threadUsage);

			}
		}

	}
}
