package org.ferrari.utils.sort;

import java.util.Comparator;
/**
 * 插入排序
 * @author kevin
 * @param <E>
 */
public class InsertSort<E extends Comparable<E>> extends Sort<E> {

	public void sort(E[] array, int from, int end, Comparator<E> c) {
		for (int i = from + 1; i <= end; i++) {
			for (int j = 0; j < i; j++) {
				E elem = array[i];
				if (c.compare(array[j], elem) > 0) {
					move(array, j, i - 1);
					array[j] = elem;
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		Integer[] arrs = { 7, 2, 4, 3, 12, 1, 9, 6, 8, 5, 11, 10 };
		Sort<Integer> sort = new InsertSort<Integer>();
		sort.sort(arrs, 0, 11, sort.DEFAULT_ORDER);
		for (int i = 0; i < arrs.length; i++) {
			System.out.println(arrs[i]);
		}
	}
}
