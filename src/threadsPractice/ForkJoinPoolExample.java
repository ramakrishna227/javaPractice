package threadsPractice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample {
	ForkJoinPool forkJoinPool = new ForkJoinPool();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPoolExample obj = new ForkJoinPoolExample();

		FactorialSquareCalculator cal = new FactorialSquareCalculator(4);
		obj.forkJoinPool.execute(cal);
		System.out.println(cal.get());
	}

}

class FactorialSquareCalculator extends RecursiveTask<Integer> {
	Integer n;

	public FactorialSquareCalculator(Integer n) {
		this.n = n;
	}

	@Override
	protected Integer compute() {
		if(n<1) {
			return n;
		}
		FactorialSquareCalculator calculator=new FactorialSquareCalculator(n-1);
		calculator.fork();
		return n * n+calculator.join();
	}
}