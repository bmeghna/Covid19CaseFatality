package prj5;

import java.util.Comparator;

/**
 * Comparator for the RaceStats object based on CFR
 * 
 * @author Alex Natt
 * @version 2021.4.22
 */
public class CompareByCFR implements Comparator<RaceStats> {
    /**
     * Compare 2 RaceStats objects based on CFR
     * 
     * @param first
     *            is the first stat we will compare
     * @param other
     *            is the other stat we compare first against
     * @return -1 if the first CFR is higher, 1 if the first CFR is lower, or 0
     *         if CFRs are equal
     */
    @Override
    public int compare(RaceStats first, RaceStats other) {

        if (first.getCFR() > other.getCFR()) {
            return -1;
        }
        if (first.getCFR() < other.getCFR()) {
            return 1;
        }
        return (first.getRace().compareTo(other.getRace()));
    }

}
