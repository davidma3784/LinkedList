package listClasses;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Yanzhuo Ma This program implements the BasicLinkedList class, which
 *         creates a linked list object of type Generic T which is unordered.
 * @param <T>
 */
public class BasicLinkedList<T> implements Iterable<T>,
		Comparable<BasicLinkedList<T>> {

	public Comparator<T> comparator;
	protected Node<T> cursor;
	protected Node<T> head;
	private int size = 0;

	/**
	 * 
	 * @author David creates a node, that is a part of the LinkedList
	 */
	protected class Node<D> {
		D data;
		Node<D> next;

		/**
		 * default constructor
		 */
		public Node() {
			data = null;
			next = null;
		}

		/**
		 * constructor that stores the given data
		 * 
		 * @param data
		 */
		public Node(D data) {
			this.data = data;
			next = null;
		}

		/**
		 * copy constructor
		 * 
		 * @param D
		 */
		public Node(Node<D> D) {
			this.data = D.data;
			this.next = D.next;
		}
	}

	/**
	 * default constructor
	 */
	public BasicLinkedList() {
		head = null;
	}

	/**
	 * constructor that takes in a comparator
	 * 
	 * @param comparator
	 */
	public BasicLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
		head = null;
	}

	/**
	 * returns the size of the LinkedList
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * returns the LinkedList with an element added to the end
	 * 
	 * @param data
	 * @return
	 */
	public BasicLinkedList<T> addToEnd(T data) {
		Node<T> newEle = new Node<T>(data);
		if (head == null) {
			newEle.next = head;
			head = newEle;
		} else {
			cursor = head;
			while (cursor.next != null) {
				cursor = cursor.next;
			}
			cursor.next = newEle;
		}
		size++;
		return this;
	}

	/**
	 * returns the LinkedList with an element added to the front
	 * 
	 * @param data
	 * @return
	 */
	public BasicLinkedList<T> addToFront(T data) {
		Node<T> newEle = new Node<T>(data);
		newEle.next = head;
		head = newEle;
		size++;
		return this;
	}

	/**
	 * returns the first element in the LinkedList
	 * 
	 * @return
	 */
	public T getFirst() throws NullPointerException {
		return head.data;
	}

	/**
	 * returns the last element in the LinkedList
	 */
	public T getLast() throws NullPointerException{
		cursor = head;
		while (cursor.next != null) {
			cursor = cursor.next;
		}
		return cursor.data;
	}

	/**
	 * returns the first element in the LinkedList, and removes it, returns the
	 * LinkedList
	 * 
	 * @return
	 */
	public T retrieveFirstElement() throws NullPointerException {
		Node<T> temp = new Node<T>(head.data);
		cursor = head;
		head = cursor.next;
		size--;
		return temp.data;
	}

	/**
	 * returns the last element in the LinkedList, and removes it, returns the
	 * LinkedList
	 * 
	 * @return
	 */
	public T retrieveLastElement() throws NullPointerException {
		if (this.getSize() == 1) {
			return this.retrieveFirstElement();
		} else {
			cursor = head;
			while ((cursor.next).next != null) {
				cursor = cursor.next;
			}
			T temp = cursor.next.data;
			cursor.next = null;
			size--;
			return temp;
		}

	}

	/**
	 * returns a LinkedList that removes all instances of targetData, based on
	 * comparator
	 * 
	 * @param targetData
	 * @param comparator
	 * @return
	 */
	public BasicLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		cursor = head;
		BasicLinkedList<T> tempList = new BasicLinkedList<T>();
		while (cursor != null) {
			if (comparator.compare(cursor.data, targetData) == 0) {
				size--;
			} else {
				tempList.addToEnd(cursor.data);
			}
			cursor = cursor.next;
		}
		head = tempList.head;
		return tempList;
	}

	@Override
	/**
	 * returns an iterator for BasicLinkedList
	 */
	public Iterator<T> iterator() {
		return new IteratorT();

	}

	public class IteratorT implements Iterator<T> {
		Node<T> current = head;

		/**
		 * checks if the the LinkedList has a next element
		 */
		public boolean hasNext() {
			return current!= null;
		}

		/**
		 * returns the next element in the list
		 */
		public T next() throws NoSuchElementException {
			Node<T> temp = current;
			if (hasNext()) {
				current = current.next;
				return temp.data;
			} else {
				throw new NoSuchElementException();
			}
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * determines whether two elements are equal or not
	 */
	public int compareTo(BasicLinkedList<T> otherList) {
		cursor = head;
		Node<T> other = otherList.head;
		while (cursor != null && other != null) {
			if (comparator.compare(cursor.data, other.data) < 0) {
				return -1;
			} else if ((comparator).compare(cursor.data, other.data) > 0) {
				return 1;
			} else {
				cursor = cursor.next;
				other = other.next;
			}
		}
		if (cursor == null && other!= null) {
			return -1;
		} else if (other == null && cursor != null) {
			return 1;
		} else
			return 0;
	}
}
