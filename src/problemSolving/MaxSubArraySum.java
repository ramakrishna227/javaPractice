package problemSolving;

//Kadanes algorithm
public class MaxSubArraySum {
public static void main(String[] args) {
	int result=0;
	int maxSum=0;
	int currentSum=0;
	int[] input= {-2, -5, 6, -2, -3, 1, 5, -6};
	for(int i=0; i<input.length;i++) {
		currentSum=Math.max(currentSum+input[i], 0);
		maxSum=Math.max(maxSum, currentSum);
	}
	System.out.println(maxSum);
}
}
