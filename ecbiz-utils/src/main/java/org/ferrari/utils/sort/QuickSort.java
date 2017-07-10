package org.ferrari.utils.sort;

import java.util.Comparator;

/**
 * 快速排序
 * 
 * @author keivn
 * @param <E>
 */
public class QuickSort<E extends Comparable<E>> extends Sort<E> {

	public void sort(E[] array, int from, int end, Comparator<E> c) {
		this.quickSort(array, from, end, c);
	}

	protected void quickSort(E[] array, int low, int high, Comparator<E> c) {
		if (low < high) {
			int stand = this.partition(array, low, high, c);
			quickSort(array, low, stand - 1, c);
			quickSort(array, stand + 1, high - 1, c);
		}
	}

	protected int partition(E[] array, int low, int high, Comparator<E> c) {
		E standElem = array[low];// 定义参照元素
		int border = low;
		for (int i = low + 1; i <= high; i++) {
			if (c.compare(array[i], standElem) < 0) {
				swap(array, ++border, i);
			}
		}
		swap(array, border, low);
		return border;
	}

	public static void main(String[] args) {
		Integer[] arrs = { 7, 2, 4, 3, 12, 1, 9, 6, 8, 5, 11, 10 };
		Sort<Integer> sort = new QuickSort<Integer>();
		sort.sort(arrs, 0, 11, sort.DEFAULT_ORDER);
		for (int i = 0; i < arrs.length; i++) {
			System.out.println(arrs[i]);
		}
	}
}
