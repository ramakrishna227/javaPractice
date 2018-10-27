package threadsPractice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutorPractice2 {
ExecutorService executor=Executors.newSingleThreadExecutor();

public static void main(String[] args) throws InterruptedException, ExecutionException {
	SingleThreadExecutorPractice2 obj=new SingleThreadExecutorPractice2();
	
	Future future1 = obj.numberDoubler(100);
	Future future2 = obj.numberDoubler(200);
while(!(future1.isDone()&& future2.isDone())) {
	Thread.sleep(200);
	String future1Status=future1.isDone()?" future 1 done":"future1 not done";
		String future2Status = future2.isDone()?"future2 done": "future2 not done";
	System.out.println(future1Status+" "+future2Status);
}

System.out.println(future1.get() +"         "+future2.get());
}

public Future<Integer> numberDoubler(Integer number){
	return executor.submit(()->{
		Thread.sleep(1000);
		return number*2;
	});
}
}
