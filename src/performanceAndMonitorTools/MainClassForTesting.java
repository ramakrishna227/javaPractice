package performanceAndMonitorTools;

public class MainClassForTesting {
public static void main(String[] args) {
	ThreadMonitor tm=new ThreadMonitor();
	
	System.out.println(tm.getThreadDump());
	
	
	
	System.out.println("-----------22222222222222222222222-----------------------------");
System.out.println(new ThreadMonitor(true).getThreadDump());

}
}
