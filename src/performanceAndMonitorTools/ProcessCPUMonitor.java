package performanceAndMonitorTools;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;

public class ProcessCPUMonitor {
	private ProcessCPUMonitor instance;
	private ThreadMXBean threadMXBean;
	private OperatingSystemMXBean operatingSystemMxBean;

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
		operatingSystemMxBean = ManagementFactory.getOperatingSystemMXBean();
		if (threadMXBean.isCurrentThreadCpuTimeSupported()) {

		}

	}
}
