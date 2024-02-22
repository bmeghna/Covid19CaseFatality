package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The test class for the A Singly-Linked List
 * 
 * @author Alex Natt
 * @version 2021.3.20
 *
 */
public class LinkedListTest extends student.TestCase {

    private LinkedList<String> emptyListA;
    private LinkedList<String> emptyListB;
    private LinkedList<String> smallListA;
    private LinkedList<String> bigListA;
    private Iterator<String> iter;

    /**
     * Initializes 2 empty lists, 2 lists with a small number of items, and 2
     * lists with a large number of items
     */
    public void setUp() {
        emptyListA = new LinkedList<String>();
        emptyListB = new LinkedList<String>();

        smallListA = new LinkedList<String>();

        smallListA.add("soccer");
        smallListA.add("swimming");
        smallListA.add("gymnastics");

        bigListA = new LinkedList<String>();

        for (int i = 0; i < 100; i++) {
            bigListA.add("sport" + i);
        }

        iter = emptyListA.iterator();
    }


    /**
     * ensures the size is properly updated
     */
    public void testSize() {
        assertEquals(3, smallListA.size());
        smallListA.add("3");
        smallListA.add(2, "3456");
        smallListA.add(1, "239");
        assertEquals(6, smallListA.size());
        smallListA.remove("3456");
        smallListA.remove(2);
        assertEquals(4, smallListA.size());

    }


    /**
     * ensures add throws the right exception at the right moment
     */
    public void testAddException() {
        Exception ex = null;
        Exception ex2 = null;

        try {
            bigListA.add(101, null);
        }
        catch (Exception e) {
            ex = e;
        }
        assertTrue("add() is throwing the wrong type of exceptions",
            ex instanceof IllegalArgumentException);

        try {
            bigListA.add(101, "Miller");
        }
        catch (Exception e) {
            ex2 = e;
        }
        assertTrue("add() is throwing the wrong type of exceptions",
            ex2 instanceof IndexOutOfBoundsException);
        ex2 = null;
        try {
            bigListA.add(-1, "Miller");
        }
        catch (Exception e) {
            ex2 = e;
        }
        assertTrue("add() is throwing the wrong type of exceptions",
            ex2 instanceof IndexOutOfBoundsException);
        ex = null;
        try {
            bigListA.add(null);
        }
        catch (Exception e) {
            ex = e;
        }
        assertTrue("add() is throwing the wrong type of exceptions",
            ex instanceof IllegalArgumentException);
    }


    /**
     * ensures add works in all situation
     */
    public void testAdd() {
        emptyListA.add("kendrick");
        assertEquals("kendrick", emptyListA.get(0));

        emptyListA.add("kendrick2");
        emptyListA.add("kendrick3");
        assertEquals("kendrick2", emptyListA.get(1));
        assertEquals("kendrick3", emptyListA.get(2));

        emptyListB.add(0, "kendrick1");
        emptyListB.add(1, "kendrick4");
        emptyListB.add(1, "kendrick3");
        emptyListB.add(1, "kendrick2");
        emptyListB.add(0, "kendrick0");
        assertEquals("kendrick0", emptyListB.get(0));
        assertEquals("kendrick1", emptyListB.get(1));
        assertEquals("kendrick2", emptyListB.get(2));
        assertEquals("kendrick3", emptyListB.get(3));
        assertEquals("kendrick4", emptyListB.get(4));
    }


    /**
     * Ensures the list knows when its empty
     */
    public void testIsEmpty() {
        assertFalse(smallListA.isEmpty());
        assertTrue(emptyListA.isEmpty());
        smallListA.clear();
        assertTrue(smallListA.isEmpty());
    }


    /**
     * ensures removed throws an exception at the right moment
     */
    public void testRemoveException() {
        Exception ex = null;
        Exception ex2 = null;
        try {
            bigListA.remove(-1);
        }
        catch (Exception e) {
            ex = e;
        }
        assertTrue("remove() is throwing the wrong type of exceptions",
            ex instanceof IndexOutOfBoundsException);

        try {
            bigListA.remove(101);
        }
        catch (Exception e) {
            ex2 = e;
        }
        assertTrue("remove() is throwing the wrong type of exceptions",
            ex2 instanceof IndexOutOfBoundsException);
        ex2 = null;
        try {
            emptyListA.remove(0);
        }
        catch (Exception e) {
            ex2 = e;
        }
        assertTrue("remove() is throwing the wrong type of exceptions",
            ex2 instanceof IndexOutOfBoundsException);
    }


    /**
     * ensures remove works in every situation
     */
    public void testRemove() {
        emptyListA.add("kendrick");
        assertTrue(emptyListA.remove(0));
        assertTrue(emptyListA.isEmpty());

        emptyListA.add(0, "kendrick");
        assertTrue(emptyListA.remove("kendrick"));
        assertTrue(emptyListA.isEmpty());

        emptyListB.add(0, "kendrick");
        emptyListB.add(1, "kendrick4");
        emptyListB.add(1, "kendrick3");
        emptyListB.add(1, "kendrick2");
        assertFalse(emptyListB.remove("dababy"));
        assertTrue(emptyListB.remove(1));
        assertTrue(emptyListB.remove(1));
        assertEquals("kendrick", emptyListB.get(0));
        assertEquals("kendrick4", emptyListB.get(1));
        assertTrue(emptyListB.remove("kendrick4"));
        emptyListB.remove(0);
        assertTrue(emptyListB.isEmpty());
    }


    /**
     * Ensures get throws the correct exception
     */
    public void testGetException() {
        Exception ex = null;
        Exception ex2 = null;
        try {
            bigListA.get(-1);
        }
        catch (Exception e) {
            ex = e;
        }
        assertTrue("get() is throwing the wrong type of exceptions",
            ex instanceof IndexOutOfBoundsException);

        try {
            bigListA.get(101);
        }
        catch (Exception e) {
            ex2 = e;
        }
        assertTrue("get() is throwing the wrong type of exceptions",
            ex2 instanceof IndexOutOfBoundsException);
    }


    /**
     * ensures get can get the right object
     */
    public void testGet() {
        for (int i = 0; i < 100; i++) {
            assertEquals("sport" + i, bigListA.get(i));
        }
    }


    /**
     * ensures the list knows when it contaIns a certain object
     */
    public void testContains() {
        emptyListB.add(0, "kendrick1");
        emptyListB.add(1, "kendrick4");
        emptyListB.add(1, "kendrick3");
        emptyListB.add(1, "kendrick2");
        emptyListB.add(0, "kendrick0");

        assertFalse(emptyListB.contains("kendrick"));
        assertTrue(emptyListB.contains("kendrick3"));
    }


    /**
     * Ensures the correct index is returned
     */
    public void testLastIndexOf() {
        emptyListB.add(0, "kendrick1");
        emptyListB.add(1, "kendrick4");
        emptyListB.add(1, "kendrick3");
        emptyListB.add(1, "kendrick2");
        emptyListB.add(0, "kendrick0");
        emptyListB.add(0, "kendrick4");

        assertEquals(5, emptyListB.lastIndexOf("kendrick4"));
        assertEquals(-1, emptyListB.lastIndexOf("kendrick"));
    }


    /**
     * Ensures the list is actually cleared when clear is called
     */
    public void testClear() {
        assertEquals(3, smallListA.size());
        smallListA.clear();
        assertTrue(smallListA.isEmpty());
        smallListA.clear();
        assertTrue(smallListA.isEmpty());
    }


    /**
     * Tests the equals method against an object thats not a LList, against a
     * LList that's null, against a LList that points to the same initial LList
     * object, against a LList of different size, and against a LList of same
     * size with equals and unequal contents
     */
    public void testEquals() {
        LinkedList<String> samelist = smallListA;
        LinkedList<String> nullList = null;
        Object notList = new Object();
        LinkedList<String> similarList = new LinkedList<String>();

        similarList.add("soccer");
        similarList.add("swimming");
        similarList.add("gymnastics");

        assertFalse(smallListA.equals(notList));
        assertFalse(smallListA.equals(nullList));
        assertTrue(smallListA.equals(similarList));
        assertTrue(smallListA.equals(samelist));

        similarList.add("basketball");
        assertFalse(smallListA.equals(similarList));

        similarList.remove(0);
        similarList.remove(0);
        similarList.add("tennis");

        assertFalse(smallListA.equals(similarList));
    }


    /**
     * Ensures toString returns the correct output
     */
    public void testToString() {
        emptyListA.add("Jim");
        emptyListA.add("Jimothy");
        emptyListA.add("Jimmy");

        assertEquals("{Jim, Jimothy, Jimmy}", emptyListA.toString());

        assertEquals("{}", emptyListB.toString());
    }


    /**
     * Ensures the array verion of this list is built correctly
     */
    public void testToArray() {
        Object[] testArray = bigListA.toArray();

        for (int i = 0; i < testArray.length; i++) {
            assertEquals("sport" + i, testArray[i]);
        }
    }


    /**
     * Ensures the list is properly sorted
     */
    public void testInsertionSort() {
        emptyListA.add("C");
        emptyListA.add("D");
        emptyListA.add("E");
        emptyListA.add("F");
        emptyListA.add("A");
        emptyListA.add("B");
        emptyListA.insertionSort(String.CASE_INSENSITIVE_ORDER);
        assertEquals("{A, B, C, D, E, F}", emptyListA.toString());

        emptyListB.add("Jimmy");
        emptyListB.insertionSort(String.CASE_INSENSITIVE_ORDER);

        assertEquals("{Jimmy}", emptyListB.toString());
    }


    /**
     * Tests the iterator to makes sure every method in it work
     */
    public void testIterator() {
        Exception exception = null;

        try {
            iter.next();
            fail("next() is not throwing an exception when it should");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("next() is throwing the wrong type of exceptions",
            exception instanceof NoSuchElementException);

        emptyListA.add("Forrest");

        try {
            iter.remove();
            fail("next() is not throwing an exception when it should");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue("next() is throwing the wrong type of exceptions",
            exception instanceof IllegalStateException);

        emptyListA.add("Forrest2");
        emptyListA.add("Forrest3");

        assertTrue(iter.hasNext());
        assertEquals("Forrest", iter.next());
        assertEquals("Forrest2", iter.next());
        iter.remove();
        assertEquals(2, emptyListA.size());
        assertTrue(iter.hasNext());
        assertEquals("Forrest3", iter.next());

    }
}
