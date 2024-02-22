package prj5;

/**
 * This class tests the state class methods
 *
 * @author Austin Kirkbride, Alex Natt, Meghna Banerjee
 * @version 4.23.2021
 */
public class StateTest extends student.TestCase {

    private State state;
    private RaceStats white;
    private RaceStats african;
    private LinkedList<RaceStats> races;

    /**
     * Initializes new States for us to test
     */
    public void setUp() {
        white = new RaceStats("White", 5, 3);
        african = new RaceStats("African American", 3, 1);
        races = new LinkedList<RaceStats>();
        races.add(african);
        races.add(white);
        state = new State("North Carolina", races);
    }


    /**
     * tests get name
     */
    public void testGetName() {
        assertEquals("North Carolina", state.getName());
    }


    /**
     * tests get race
     */
    public void testGetRace() {
        assertEquals(white, state.getRace("White"));
        assertEquals(african, state.getRace("African American"));
        assertNull(state.getRace("Black"));
    }


    /**
     * tests get races
     */
    public void testGetRaces() {
        assertEquals(races, state.getRaces());
    }


    /**
     * tests the get deaths method
     */
    public void testGetDeaths() {
        assertEquals(3, state.getDeaths("White"));
        assertEquals(1, state.getDeaths("African American"));
    }


    /**
     * tests the get cases method
     **/
    public void testGetCases() {
        assertEquals(5, state.getCases("White"));
        assertEquals(3, state.getCases("African American"));
    }


    /**
     * Tests the alphabetical sorting method
     */
    public void testSortAlpha() {
        state.sortAlpha();
        assertEquals(african, state.getRaces().get(0));
        assertEquals(white, state.getRaces().get(1));
    }


    /**
     * Tests the CFR sorting method
     */
    public void testSortCFR() {
        RaceStats latin = new RaceStats("Latin", 6, 1);
        RaceStats asian = new RaceStats("Asian", 6, 1);
        state.getRaces().add(latin);
        state.getRaces().add(asian);
        state.sortCFR();
        assertEquals(white, state.getRaces().get(0));
        assertEquals(african, state.getRaces().get(1));
        assertEquals(latin, state.getRaces().get(3));
        assertEquals(asian, state.getRaces().get(2));
    }
}
