package collections;

import java.util.Arrays;

public class AddArray {
public static void main(String[] args) {
	int[] input= {1,2,3,4,5,6,7,8,9,10};//n*n+1/2
	method1(input);
	//method2(input);
}

private static void method1(int[] ar) {
	int sum=0;
    sum=Arrays.stream(ar).reduce(0,(a,b)->a+b);
    System.out.println(sum);	
}

private void method2(int[] ar) {
	// TODO Auto-generated method stub

}
}
