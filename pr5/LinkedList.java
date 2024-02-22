package prj5;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * Creates a LinkedList data type
 *
 * @author Austin Kirkbride, Alex Natt, Meghna Banerjee
 * @version 4.23.2021
 * @param <T>
 *            is the generic type parameter
 */
public class LinkedList<T> implements LList<T>, Iterable<T> {

    /**
     * Node is used to store data in linkedlist
     * and point to other nodes in the linkedlist
     *
     * @author Austin Kirkbride, Alex Natt, Meghna Banerjee
     * @version 4.23.2021
     * @param <T>
     *            is the generic type parameter
     */
    public static class Node<T> {

        // The data element stored in the node.
        private T data;

        // The next node in the sequence.
        private Node<T> next;

        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         */
        public Node(T d) {
            data = d;
        }


        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         * @param nextNode
         *            is the node after this one
         */
        public Node(T d, Node<T> nextNode) {
            data = d;
            next = nextNode;
        }


        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node<T> n) {
            next = n;
        }


        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<T> next() {
            return next;
        }


        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public T getData() {
            return data;
        }
    } // end Node class

    private Node<T> head; // **this is a SENTINEL node**
    private int size;

    /**
     * Creates a new LinkedList object
     */
    public LinkedList() {
        head = new Node<T>(null); // **SENTINEL NODE**
        size = 0;
    }


    /**
     * Returns the number of items in this list
     * 
     * @return the size of this list
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Adds the object to the position in the list
     *
     * @precondition obj cannot be null
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    @Override
    public void add(int index, T obj) {
        // check if the object is null
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }

        // check if the index is out of bounds
        if ((index < 0) || (index > this.size)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node<T> current = head.next;
        // empty list case
        if (isEmpty()) {
            head.setNext(new Node<T>(obj));
        }
        else if (index == 0) { // adding to the front of the list
            Node<T> newNode = new Node<T>(obj);
            newNode.setNext(head.next);
            head.setNext(newNode);
        }
        else { // adding anywhere else
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<T>(obj, current.next);
            current.setNext(newNode);
        }
        size++;
    }


    /**
     * Adds the object to the end of the list.
     *
     * @precondition obj cannot be null
     * @param obj
     *            the object to add
     * @throws IllegalArgumentException
     *             if obj is null
     */
    @Override
    public void add(T obj) {
        // check if the object is null
        if (obj == null) {
            throw new IllegalArgumentException("Object is null");
        }

        Node<T> current = head.next;

        // empty stack case
        if (isEmpty()) {
            head.setNext(new Node<T>(obj));
        }
        else {
            while (current.next != null) {
                current = current.next;
            }
            current.setNext(new Node<T>(obj));
        }
        size++;
    }


    /**
     * Checks if the list is empty
     *
     * @return true if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return head.next == null;
    }


    /**
     * Removes the first instance of the given object from the list
     *
     * @param obj
     *            the object to remove
     * @return true if successful
     */
    @Override
    public boolean remove(T obj) {
        Node<T> current = head.next;
        Node<T> runner = head;

        while (current != null) {
            if (current.getData().equals(obj)) {
                runner.setNext(current.next);
                size--;
                return true;
            }
            runner = current;
            current = current.next();
        }

        return false;
    }


    /**
     * Removes the object at the given position
     *
     * @param index
     *            the position of the object
     * @return true if the removal was successful
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     */
    @Override
    public boolean remove(int index) {
        if ((index < 0) || (index >= this.size)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }

        Node<T> current = head; // this represents the node before the one we
                                // want to remove

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.setNext(current.next.next);
        size--;
        return true;
    }


    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if no node at the given index
     */
    @Override
    public T get(int index) {
        Node<T> current = head.next;
        int currentIndex = 0;
        T data = null;
        while (current != null) {
            if (currentIndex == index) {
                data = current.data;
            }
            currentIndex++;
            current = current.next;
        }

        // check if the data was null...
        if (data == null) {
            // ... if so throw an exception
            throw new IndexOutOfBoundsException("Index exceeds the size.");
        }
        return data;
    }


    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    @Override
    public boolean contains(T obj) {
        Node<T> current = head.next;
        while (current != null) {
            if (obj.equals(current.data)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    /**
     * Resets the list by nulling out the first item
     */
    @Override
    public void clear() {
        if (head.next != null) {
            head.setNext(null);
        }
        size = 0;
    }


    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    @Override
    public int lastIndexOf(T obj) {
        int lastIndex = -1;
        Node<T> current = head.next;
        int currentIndex = 0;
        while (current != null) {
            if (obj.equals(current.data)) {
                lastIndex = currentIndex;
            }
            currentIndex++;
            current = current.next;

        }
        return lastIndex;
    }


    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node<T> currNode = head.next;
            while (currNode != null) {
                T element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != null) {
                    builder.append(", ");
                }
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }


    /**
     * Returns an array representation of the list If a list contains A, B, and
     * C, the following should be returned {A, B, C}, If a list
     * contains A, B, C, and C the following should be returned {A, B, C, C}
     *
     * @return an array representing the list
     */
    public Object[] toArray() {

        Object[] array = new Object[this.size()];

        Node<T> current = head.next;
        int count = 0;
        while (current != null) {
            array[count] = current.getData();
            current = current.next;
            count++;
        }

        return array;
    }


    /**
     * Returns true if both lists have the exact same contents
     * in the exact same order
     *
     * @return a boolean of whether two lists have the same contents,
     *         item per item and in the same order
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            @SuppressWarnings("unchecked")
            LinkedList<T> other = ((LinkedList<T>)obj);
            if (other.size() == this.size()) {
                Node<T> current = head.next;
                Node<T> otherCurrent = other.head.next;
                while (current != null) {
                    if (!current.getData().equals(otherCurrent.getData())) {
                        return false;
                    }
                    current = current.next();
                    otherCurrent = otherCurrent.next();
                }
                return true;
            }
        }

        return false;
    }

    /**
     * Iterator for our Double linked list
     * 
     * @author Alex Natt
     * @version 2021.3.25
     *
     * @param <T>
     */
    private class SLListIterator<E> implements Iterator<T> {

        private Node<T> curr;
        private boolean calledNext = false;

        /**
         * Creates a new SLListIterator
         */
        public SLListIterator() {
            curr = head;
        }


        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return curr.next != null;
        }


        /**
         * Gets the next value in the list
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public T next() {
            if (hasNext()) {
                curr = curr.next;
                calledNext = true;
                return curr.data;
            }
            else {
                throw new NoSuchElementException("There are no nodes left.");
            }
        }


        /**
         * Removes the last object returned with next() from the list
         *
         * @throws IllegalStateException
         *             if next has not been called yet
         *             and if the element has already been removed
         */
        @Override
        public void remove() {
            if (!calledNext) {
                throw new IllegalStateException("Did not call next().");
            }
            else {
                LinkedList.this.remove(curr.data);
                calledNext = false;
            }
        }
    }

    /**
     * creates an iterator for us to iterate over the list.
     */
    @Override
    public Iterator<T> iterator() {
        return new SLListIterator<T>();
    }


    /**
     * Performs an insertion sort by splitting the list into a sorted and an
     * unsorted portion and then iterates through the unsorted portion to
     * insert each node one-by-one into the sorted portion.
     * 
     * @param comp
     *            is the comparator we use to compare the objects
     */
    public void insertionSort(Comparator<? super T> comp) {
        if (this.size() > 1) {
            Node<T> unsorted = head.next.next; // second node
            Node<T> sorted = head.next; // first node
            sorted.setNext(null);

            while (unsorted != null) {
                Node<T> theInsertion = unsorted;
                unsorted = unsorted.next;
                insertIntoSorted(theInsertion, comp);
            }
        }
    }


    /**
     * Helper method to insert a node into its proper location in a sorted
     * linked chain.
     * 
     * @param nodeToInsert
     *            node to add to sorted section of list
     * @param comp
     *            is the comparator we use to compare the objects
     */
    private void insertIntoSorted(
        Node<T> nodeToInsert,
        Comparator<? super T> comp) {
        Node<T> insertionPoint = head.next;
        Node<T> nodeBefore = head;
        T insertion = nodeToInsert.getData();

        while (insertionPoint != null && (comp.compare(insertion, insertionPoint
            .getData())) > 0) {
            nodeBefore = insertionPoint;
            insertionPoint = insertionPoint.next;
        }

        if (nodeBefore == head) {
            nodeToInsert.setNext(head.next);
            head.next = nodeToInsert;
        }
        else {
            nodeBefore.setNext(nodeToInsert);
            nodeToInsert.setNext(insertionPoint);

        }
    }
}
