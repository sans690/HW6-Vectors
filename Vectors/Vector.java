// Name: Saniyah Smith
// Computing ID: xjk8nm@virginia.edu
// Assignment Name: HW06 Vectors
// Resource: ChatGPT September 25 version help with figuring out the difference between itemArray.length and size, I tried using itemArray.length in my conditions but that was incorrect, help checking if full/ throw exception, help with a bit of the logic in the methods (I had the general idea for what needed to be done but small parts need fixes or additions to work)
package vector;

import java.util.Arrays;
import java.util.NoSuchElementException;

@SuppressWarnings({ "unchecked" })
public class Vector<T> implements SimpleList<T> {

	private T[] itemArray;
	private int size = 0;
	private static final int INITIAL_CAPACITY = 100;

	public Vector() {
		this(INITIAL_CAPACITY); // calls the other constructor that takes an int parameter
	}

	public Vector(int capacity) {
		this.itemArray = (T[]) new Object[capacity];
		this.size = 0;
	}

	public int capacity() {
		return this.itemArray.length;
	}

	/**
	 * When the underlying array fills up, we need to somehow resize it to
	 * accommodate the
	 * Vectors's elements.
	 * Ignores the request if the requested new capacity is too small to fit the
	 * elements
	 * already in the Vector
	 */
	public void resize(int newCapacity) {
		// condition
		if (newCapacity > itemArray.length) {
			// new instance of T array that hold object type with specific capacity
			T[] newItemArray = (T[]) new Object[newCapacity];
			// condition loop
			for (int i = 0; i < size; i++) {
				// for every iteration set the i to the new array
				newItemArray[i] = itemArray[i];
			}
			// once loop stops this code is executed
			itemArray = newItemArray;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		// condition loop
		for (int i = 0; i < size; i++) {
			// set of current i to null
			itemArray[i] = null;
		}
		// set of size to zero
		size = 0;

	}

	@Override
	public void insertAtTail(T item) {

		// ChatGPT helped with this, forgot that if full check needed to be done
		if (size == itemArray.length) {
			int newCapacity = (itemArray.length * 3) / 2 + 1;
			resize(newCapacity);
		}

		// setting to the size plus 1
		int newSize1 = size + 1;
		// makes new array based on the new size
		T[] newItemArray2 = (T[]) new Object[newSize1];
		// loop
		for (int i = 0; i < size; i++) {
			// setting current i to the current i in the new array
			newItemArray2[i] = itemArray[i];
		}
		newItemArray2[newSize1 - 1] = item;
		itemArray = newItemArray2;
		size = newSize1;
	}

	@Override
	public void insertAtHead(T item) {
		// ChatGPT helped with this, forgot that if full check needed to be done
		if (size == itemArray.length) {
			int newCapacity = (itemArray.length * 3) / 2 + 1;
			resize(newCapacity);
		}
		// setting of new size
		int newSize2 = size + 1;
		// // makes new array based on the new size
		T[] newItemArray3 = (T[]) new Object[newSize2];
		// inserts item at index 0 of new array
		for (int i = 0; i < size; i++) {
			// allows you to add the items from the og array after the new item is inserted
			// at the head
			newItemArray3[i + 1] = itemArray[i];
		}
		// setting of the 0 index to the item
		newItemArray3[0] = item;

		itemArray = newItemArray3;
		size = newSize2;

	}

	@Override
	public void insertAt(int index, T item) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		// ChatGPT helped with this, forgot that if full check needed to be done
		if (size == itemArray.length) {
			int newCapacity = (itemArray.length * 3) / 2 + 1;
			resize(newCapacity);
		}
		// setting of new size
		int newSize4 = size + 1;
		// makes new array based on the new size
		T[] newItemArray4 = (T[]) new Object[newSize4];
		// inserts all the elements before the index
		for (int i = 0; i < index; i++) {
			newItemArray4[i] = itemArray[i];
		}
		// the current index is where the item is inserted
		newItemArray4[index] = item;
		// loop that adds the remaining item of the og array after the index
		for (int i = index; i < size; i++) {
			newItemArray4[i + 1] = itemArray[i];
		}
		itemArray = newItemArray4;
		size = newSize4;
	}

	@Override
	public T removeAtTail() {
		if (size == 0) {
			throw new NoSuchElementException("The array is empty");
		}
		// set new size to og size minus 1
		int newSize5 = size - 1;
		// makes new array based on the new size
		T[] newItemArray5 = (T[]) new Object[newSize5];
		T removedItem = itemArray[size - 1];
		// loop
		for (int i = 0; i < newSize5; i++) {
			// setting of current index of new array to the og array index
			newItemArray5[i] = itemArray[i];
		}
		itemArray = newItemArray5;
		size = newSize5;

		return removedItem;
	}

	@Override
	public T removeAtHead() {
		if (size == 0) {
			throw new NoSuchElementException("The array is empty");
		}
		// setting of new size
		int newSize6 = size - 1;
		// what items is removed
		T removedItem = itemArray[0];
		// makes new array based on the new size
		T[] newItemArray6 = (T[]) new Object[newSize6];
		// loop
		for (int i = 1; i < size; i++) {
			// setting of current index of new array minus 1 to the og array index
			newItemArray6[i - 1] = itemArray[i];
		}
		size = newSize6;
		itemArray = newItemArray6;

		return removedItem;
	}

	@Override
	public int find(T item) {
		// loop
		for (int i = 0; i < size; i++) {
			// condidtion
			if (itemArray[i] != null && itemArray[i].equals(item)) {
				return i;
			}
		}
		// item is not in theh item array
		return -1;
	}

	@Override
	public T get(int index) {
		// condition
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Not allowed, this is out of bounds");
		}
		// if condition is passed, if there is an item to get
		return (T) itemArray[index];

	}

	/*
	 * This toString() method allow you to print a nicely formatted version of your
	 * Vector. E.g.
	 * System.out.println( myVector );
	 * It uses utility methods in the Java Arrays library.
	 */
	@Override
	public String toString() {
		// return Arrays.toString(this.itemArray); // prints entire array from 0 to
		// capacity-1
		return Arrays.toString(Arrays.copyOfRange(this.itemArray, 0, this.size)); // prints from 0 to size-1
	}

}
