package org.ferrari.utils.sort;

import java.util.Comparator;
/**
 * 选择排序
 * @author kevin
 * @param <E>
 */
public class SelectSort<E extends Comparable<E>> extends Sort<E> {

	public void sort(E[] array, int from, int end, Comparator<E> c) {
		int min;// 最小索引
		for (int i = from; i <= end; i++) {
			min = i;
			for (int j = i + 1; j <= end; j++) {
				//发现比当前array[min]更小的元素，记录下来
				if (c.compare(array[j], array[min]) < 0) {
					min = j;
				}
			}
			swap(array, i, min);
		}
	}

	public static void main(String[] args) {
		Integer[] arrs = { 7, 2, 4, 3, 12, 1, 9, 6, 8, 5, 11, 10 };
		Sort<Integer> sort = new SelectSort<Integer>();
		sort.sort(arrs, 0, arrs.length - 1, sort.REVERSE_ORDER);
		for (int i = 0; i < arrs.length; i++) {
			System.out.println(arrs[i]);
		}
	}
}
