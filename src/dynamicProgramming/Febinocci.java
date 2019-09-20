package dynamicProgramming;

import java.time.Duration;
import java.time.Instant;

public class Febinocci {
	public static void main(String[] args) {
		Instant recursiveStart = Instant.now();
		System.out.println(recursiveFebinocci(45));
		Instant recursiveEnd=Instant.now();
		System.out.println("Time took for recursive calculation is "+Duration.between(recursiveStart, recursiveEnd).toMillis()+" millis");
		
		Instant dynamicStart=Instant.now();
		System.out.println(dynamicFebinocci(45));
		Instant dynamicEnd=Instant.now();
		System.out.println("Time took for dynamic calculation is "+Duration.between(dynamicStart, dynamicEnd).toMillis()+" millis");
		
	}

	private static int recursiveFebinocci(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		return recursiveFebinocci(n - 1) + recursiveFebinocci(n - 2);
	}
	
	private static int dynamicFebinocci(int n) {
		int previous=1;
		int result=1;
		for(int i=2; i<=n;i++) {
			int temp=result;
			result=result+previous;
			previous=temp;
		}
		return result;
	}
}
