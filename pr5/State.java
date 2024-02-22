package prj5;

/**
 * This provides definitions for all of the methods necessary for a list
 * implementation
 *
 * @author Meghna Banerjee, Alex Natt, Austin Kirkbride
 *         The class that you want it to store
 * @version 4/14/2015, 10/21/2015, 10/15/2016
 */
public class State {

    private String name;
    private LinkedList<RaceStats> races;
    private CompareAlpha compareAlpha;
    private CompareByCFR compareCFR;

    /**
     * Initializes the name, a races list for this object
     * 
     * @param name
     *            is the name of the state
     * @param races
     *            is the list of the stats for a each race
     */
    public State(String name, LinkedList<RaceStats> races) {
        this.name = name;
        this.races = races;
        compareAlpha = new CompareAlpha();
        compareCFR = new CompareByCFR();
    }


    /**
     * returns name of state
     * 
     * @return name of state
     */
    public String getName() {
        return name;
    }


    /**
     * gets the race given a certain string
     * 
     * @param str
     *            is the name of the race we want
     * @return the RaceStats object given a string, or null if no object matches
     *         that string
     */
    public RaceStats getRace(String str) {
        for (RaceStats next : races) {
            if (next.getRace().equals(str)) {
                return next;
            }
        }
        return null;
    }


    /**
     * returns the races list
     * 
     * @return the races list
     */
    public LinkedList<RaceStats> getRaces() {
        return races;
    }


    /**
     * returns the deaths for a certain race
     * 
     * @param str
     *            is the race we want
     * @return the deaths for a certain race
     */
    public int getDeaths(String str) {
        RaceStats race = getRace(str);
        return race.getDeaths();
    }


    /**
     * returns the cases for a certain race
     * 
     * @param str
     *            is the race we want
     * @return the cases for a certain race
     */
    public int getCases(String str) {
        RaceStats race = getRace(str);
        return race.getCases();
    }


    /**
     * Sorts the list in ascending alphabetical order
     */
    public void sortAlpha() {
        races.insertionSort(compareAlpha);
    }


    /**
     * Sorts the list in descending order by CFR
     */
    public void sortCFR() {
        races.insertionSort(compareCFR);
    }

}
