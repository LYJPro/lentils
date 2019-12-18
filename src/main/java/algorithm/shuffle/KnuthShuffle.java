package algorithm.shuffle;

import java.util.Random;

/**
 * Knuth-Shuffle
 */
public class KnuthShuffle {
	/**
	 * 打乱顺序算法，髙纳德的打乱顺序算法
	 * @param arr 待打乱顺序的数组
	 * @see java.util.Collections#shuffle()
	 */
	public static void knuthShuffle(int[] arr) {
		Random random = new Random();
		for (int i = arr.length - 1; i > 0; i--) {
			int j = random.nextInt(arr.length);
			swap(arr, i, j);
		}
	}
	
	/**
	 * 交换
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
