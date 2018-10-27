package sorting;

import java.util.Arrays;

public class BubbleSort {
public static void main(String[] args) {
//	int[] arr= {10,5,2,12,3,4,2,45,3};
	int[] arr= {9,8,7,6,5,4,3,2,1};
	int size=arr.length-1;
	for(int i=0;i<size;i++) {
		boolean swapped=false;
		for(int j=0;j<size-i; j++) {
			
			if(arr[j]>arr[j+1]) {
				swapped=true;
				int temp=arr[j];
				arr[j]=arr[j+1];
				arr[j+1]=temp;
			}
			
		}
		if(swapped==false) {
			break;
		}
		System.out.println(Arrays.toString(arr));
	}
	System.out.println("Final output "+Arrays.toString(arr));
}
}
