package org.ferrari.utils.sort;

import java.util.Comparator;

/**
 * 冒泡排序
 * 
 * @author keivn
 * @param <E>
 */
public class BubbleSort<E extends Comparable<E>> extends Sort<E> {

	public void sort(E[] array, int from, int end, Comparator<E> c) {
		for (int k = 1; k < end - from + 1; k++) {
			for (int i = end - from; i >= k; i--) {
				if (c.compare(array[i], array[i - 1]) < 0) {
					swap(array, i, i - 1);
				}
			}
		}
	}

	public static void main(String[] args) {
		Integer[] arrs = { 7, 2, 4, 3, 12, 1, 9, 6, 8, 5, 11, 10 };
		Sort<Integer> sort = new BubbleSort<Integer>();
		sort.sort(arrs, 0, 11, sort.REVERSE_ORDER);
		for (int i = 0; i < arrs.length; i++) {
			System.out.println(arrs[i]);
		}
	}
}
