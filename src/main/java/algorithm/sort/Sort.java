package algorithm.sort;

import java.util.Random;

import org.junit.Test;

public class Sort<E extends Comparable<E>> {
	
	/**
	 * 插入排序
	 * @param arr
	 */
	public void insertSort(E[] arr) {
		for (int i = 0; i < arr.length; i++) {
			E cur = arr[i];
			
			int j;
			for (j = i; j > 0 && arr[j - 1].compareTo(cur) > 0; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = cur;
		}
	}
	
	/**
	 * 选择排序
	 * @param arr
	 */
	public void sort(E[] arr) {
		for (int i = 0; i < arr.length; i++) {
			E minVal = arr[i];
			for (int j = i; j < arr.length; j++) {
				if (minVal.compareTo(arr[j]) > 0) {
					arr[i] = arr[j];
					arr[j] = minVal;
					minVal = arr[i];
				}
			}
		}
	}

	@Test
	public static void main(String[] args) {
		int[] arr = new int[10];
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			arr[i] = random.nextInt(20);
		}
		mergeSort(arr);
	}
	
	/**
	 * 归并排序
	 */
	public static void mergeSort(int[] arr) {
		divide(arr, 0, arr.length - 1);
	}

	private static void divide(int[] arr, int l, int r) {
		int mid = (l + r)/2;
		
		if (l >= mid) {
			return;
		}
		
		divide(arr, l, mid);
		divide(arr, mid, r);
		merge(arr, l, mid, r);
	}

	public static void merge(int[] arr, int l, int mid, int r) {
	}
}
