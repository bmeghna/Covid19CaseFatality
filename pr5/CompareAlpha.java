package prj5;

import java.util.Comparator;

/**
 * Comparator for the RaceStats object based on name
 * 
 * @author Alex Natt
 * @version 2021.4.22
 */
public class CompareAlpha implements Comparator<RaceStats> {
    /**
     * Compare 2 RaceStats objects based on name
     * 
     * @param first
     *            is the first stat we will compare
     * @param other
     *            is the other stat we compare first against
     * @return -1 if the first name is lower, 1 if the first name is lower, or 0
     *         if name are equal
     */
    @Override
    public int compare(RaceStats first, RaceStats other) {
        return first.getRace().compareTo(other.getRace());
    }

}
