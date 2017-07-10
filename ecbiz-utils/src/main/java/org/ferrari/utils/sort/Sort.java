package org.ferrari.utils.sort;

import java.util.Comparator;
/**
 * @author kevin
 * @param <E>
 */
public abstract class Sort<E extends Comparable<E>>{
	/*** 默认比较器*/
	public final Comparator<E> DEFAULT_ORDER = new DefaultComparator();
	/*** 反向比较器*/
	public final Comparator<E> REVERSE_ORDER = new ReverseComparator();
	/**
	 * 
	 * <h1>排序抽象方法</h1>
	 * @param array
	 * @param from 对数组中元素排序起始位置
	 * @param end 结束位置
	 * @param c 排序比较器 DEFAULT_ORDER：默认比较器，REVERSE_ORDER：反向比较器
	 * @return
	 * @author kevin
	 */
	public abstract void sort(E[] array, int from, int end, Comparator<E> c);
	
	public void sort(int from, int length, E[] array, Comparator<E> c){
		sort(array, 0, array.length - 1, c);
	}
	
	public final void sort(E[] array, Comparator<E> c){
		sort(0, array.length, array, c);
	}
	/**
	 * 
	 * <h1>数组排序，采用默认比较器</h1>
	 * @param array
	 * @author kevin
	 */
	public final void sort(E[] array){
		sort(0, array.length, array, this.DEFAULT_ORDER);
	}
	/**
	 * <h1>交换数组中两个元素的位置</h1>
	 * @param array
	 * @param from
	 * @param to
	 * @author kevin
	 */
	protected final void swap(E[] array, int from, int to) {
		E tmp = array[from];
		array[from] = array[to];
		array[to] = tmp;
	}
	/**
	 * <h1>数组元素后移</h1>
	 * @param array
	 * @param start
	 * @param end
	 * @author kevin
	 */
	protected final void move(E[] array, int start, int end){
		for (int i = end; i >= start; i--) {
			array[i + 1] = array[i];
		}
	}
	/**
	 * <h1>数组元素后移,步长指定元素间隔</h1>
	 * @param array
	 * @param start
	 * @param end
	 * @param step
	 * @author kevin
	 */
	protected final void move(E[] array, int start, int end, int step){
		for (int i = end; i >= start; i -= step) {
			array[i + step] = array[i];
		}
	}
	/**
	 * 排序比较器：升序
	 * @author kevin
	 */
	protected class DefaultComparator implements Comparator<E>{
		public int compare(E a, E b) {
			return a.compareTo(b);
		}
	}
	/**
	 * 排序比较器：降序
	 * @author kevin
	 */
	protected class ReverseComparator implements Comparator<E>{
		public int compare(E a, E b) {
			return b.compareTo(a);
		}
	}
}
