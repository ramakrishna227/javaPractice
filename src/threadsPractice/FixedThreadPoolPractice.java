package threadsPractice;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPoolPractice {

	ExecutorService executor = Executors.newFixedThreadPool(2);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FixedThreadPoolPractice obj = new FixedThreadPoolPractice();
		Integer[] array1 = obj.randomArrayGenerator(100);
		Integer[] array2 = obj.randomArrayGenerator(100);

		Future<Integer[]> future1 = obj.bubbleSort(array1);
		Future<Integer[]> future2 = obj.bubbleSort(array2);
		while (!(future1.isDone() && future2.isDone())) {
			Thread.sleep(100);
			String future1Status = future1.isDone() ? " future 1 done" : "future1 not done";
			String future2Status = future2.isDone() ? "future2 done" : "future2 not done";
			System.out.println(future1Status + " " + future2Status);
		}

		System.out.println(Arrays.toString(future1.get()));
		System.out.println(Arrays.toString(future2.get()));

	}

	private Integer[] randomArrayGenerator(int n) {
		Integer[] array = new Integer[n];
		Random generator = new Random();
		for (int i = 0; i < n; i++) {
			array[i] = generator.nextInt(n);
		}
		return array;
	}

	Future<Integer[]> bubbleSort(Integer[] arr) {
		
		return executor.submit(() -> {
			System.out.println("sorting..");
			Thread.sleep(1000);
			for (int i = 0; i < arr.length - 1; i++) {
				for (int j = 0; j < arr.length - 1 - i; j++) {
					if (arr[j] > arr[j + 1]) {
						swap(arr, j);
					}
				}
			}
			return arr;
		});
	}

	private void swap(Integer[] arr, int j) {
		int temp = arr[j];
		arr[j] = arr[j + 1];
		arr[j + 1] = temp;
	}

}
