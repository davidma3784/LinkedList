package listClasses;

import java.util.Comparator;
/**
 * This program e
 * @author Yanzhuo Ma
 * This program extends BasicLinkedList, and sorts elements before anding them to the list 
 * @param <T>
 */
public class SortedLinkedList<T> extends BasicLinkedList<T> {
	/**
	 * constructor
	 * @param comparator
	 */
	public SortedLinkedList(Comparator<T> comparator) {
		super(comparator);
	}
	/**
	 * adds elements into the SortedLinkedList by sorting them before in descending order based on comparator 
	 * @param element
	 * @return
	 */
	public SortedLinkedList<T> add(T element){
		Node<T> newItem = new Node<T>(element);
		Node<T> temp = head;
		cursor = head;
		if ((head == null) || comparator.compare(element, cursor.data) <= 0) {
			newItem.next = head;
			head = newItem;
		} else {
			boolean notAdded = true;
			while (cursor != null && notAdded) {
				if (comparator.compare(element, cursor.data) > 0) {
					if (cursor.next == null) {
						cursor.next = newItem;
						notAdded = false;
					} else {
						temp = cursor;
						cursor = cursor.next;
					}
				} else {
					newItem.next = cursor;
					temp.next = newItem;
					notAdded = false;
				}
			}
		}
		return this;
	}
	/**
	 * calls parent class function
	 * @param targetData
	 * @return
	 */
	public BasicLinkedList<T> remove(T targetData) {
		return super.remove(targetData, comparator);
	}
	/**
	 * calls parent class function
	 */
	public BasicLinkedList<T> addToEnd(T data) {
		throw new UnsupportedOperationException(
				"Invalid operation for sorted list.");
	}
	/**
	 * calls parent class function
	 */
	public BasicLinkedList<T> addToFront(T data) {
		throw new UnsupportedOperationException(
				"Invalid operation for sorted list.");
	}
}