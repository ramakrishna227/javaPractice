package threadsPractice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SingleThreadExecutorPractice {
	ExecutorService executor=Executors.newSingleThreadExecutor();
	
public static void main(String[] args) throws InterruptedException, ExecutionException {
	
	SingleThreadExecutorPractice obj=new SingleThreadExecutorPractice();
	Future<Integer> future = obj.squareCalculator(100);
	while(!future.isDone()) {
		System.out.println("calculating...");
		Thread.sleep(200);
		future.cancel(true);
	}
	if(!future.isCancelled()) {
	System.out.println(future.get());
	}else {
		System.out.println("Future is cancelled");
	}
	
}

public Future<Integer> squareCalculator(Integer n){
	return executor.submit(new Callable<Integer>(){
		
		public Integer call() {
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			long i=0;
			while(i<1000000000) {;
			i++;
			}
			return n*n;
		}
		
	});
}
}
