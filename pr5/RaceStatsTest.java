package prj5;

/**
 * Test Class for a RaceStats object
 * 
 * @author Alex Natt
 * @version 2021.4.22
 */
public class RaceStatsTest extends student.TestCase {

    private RaceStats stats;
    private RaceStats similarStats;
    private RaceStats notSameStats;

    /**
    * 
    */
    public void setUp() {
        stats = new RaceStats("latin", 23472, 1482);
        similarStats = new RaceStats("latin", 23472, 1482);
        notSameStats = new RaceStats("african", 23472, 1482);
    }


    /**
     * Tests all of the getters for the racestats class
     */
    public void testGetters() {
        assertEquals("latin", stats.getRace());
        assertEquals(23472, stats.getCases());
        assertEquals(1482, stats.getDeaths());
        assertEquals(6.3, stats.getCFR(), 0.1);

        RaceStats invalid = new RaceStats("african", -1, 1482);
        RaceStats invalid2 = new RaceStats("african", 3452, -1);

        assertEquals(-1, invalid.getCFR(), 0.1);
        assertEquals(-1, invalid2.getCFR(), 0.1);
    }


    /**
     * Tests all possible variations of compareTo
     */
    public void testCompareTo() {
        RaceStats largerName = new RaceStats("mongolian", 23472, 1482);

        assertEquals(11, stats.compareTo(notSameStats));
        assertEquals(0, stats.compareTo(similarStats));
        assertEquals(-1, stats.compareTo(largerName));
    }


    /**
     * Tests the equals method against an object thats not a RaceStats, against
     * a
     * RaceStats that's null, against a RaceStats that points to the same
     * initial RaceStats
     * object, against a RaceStats of different size, and against a RaceStats of
     * same
     * size with equals and unequal contents
     */
    public void testEquals() {
        RaceStats same = stats;
        Object obj = new Object();
        RaceStats nullstats = null;
        RaceStats wrongDeaths = new RaceStats("latin", 23472, 305);
        RaceStats wrongCases = new RaceStats("latin", 2000, 305);
        RaceStats wrongName = new RaceStats("african", 2000, 305);

        assertTrue(stats.equals(same));
        assertFalse(stats.equals(nullstats));
        assertFalse(stats.equals(obj));
        assertTrue(stats.equals(similarStats));
        assertFalse(stats.equals(wrongDeaths));
        assertFalse(stats.equals(wrongCases));
        assertFalse(stats.equals(wrongName));
    }


    /**
     * Ensures toString() returns the right formated string
     */
    public void testToString() {
        assertEquals("latin: 23472 cases, 6.3% CFR", stats.toString());
    }
}
