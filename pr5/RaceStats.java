package prj5;

import java.text.DecimalFormat;

/**
 * Represents the covid statistics for a certain race of a given state
 * 
 * @author Alex Natt
 * @version 2021.4.19
 */
public class RaceStats implements Comparable<RaceStats> {
    private String race;
    private int cases; // Note that N/A for cases or deaths is represented
    private int deaths; // by a -1

    /**
     * Initializes the RaceStats object for its name, cases, and deaths
     * 
     * @param raceName
     *            is the name of the race
     * @param numCases
     *            is the number of COVID cases for this race
     * @param numDeaths
     *            is the number of deaths by COVID for this race
     */
    public RaceStats(String raceName, int numCases, int numDeaths) {
        race = raceName;
        cases = numCases;
        deaths = numDeaths;
    }


    /**
     * returns the race
     * 
     * @return the race
     */
    public String getRace() {
        return race;
    }


    /**
     * returns the number of cases
     * 
     * @return the number of cases
     */
    public int getCases() {
        return cases;
    }


    /**
     * returns the number of deaths
     * 
     * @return the number of deaths
     */
    public int getDeaths() {
        return deaths;
    }


    /**
     * Gives the case fatality rate for this race, which is the percent of
     * people with covid cases who died from covid, or -1 if not applicable
     * 
     * @return the case fatality rate for this race
     */
    public double getCFR() {
        if (deaths == -1 || cases == -1) {
            return -1;
        }
        return (((double)deaths / cases) * 100);
    }


    /**
     * Sees if another RaceStats is lesser than or not to this object.
     * 
     * @param other
     *            is the other stats for a race to be compared against
     * @return a negative integer if this CFR is lower, or in the event of equal
     *         CFR, if this is alphabetically first. A positive integer for the
     *         opposite case
     */
    @Override
    public int compareTo(RaceStats other) {
        return race.compareTo(other.race);
    }


    /**
     * Returns true if the two stats objects have same race, number of cases,
     * and number of deaths
     * 
     * @param other
     *            is the other pbject to be compared against
     * @return true if the other object is a RaceStats object with the same
     *         name, cases, and deaths
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() == other.getClass()) {
            RaceStats o = (RaceStats)other;
            return (this.getRace().equals(o.getRace()) && this.getCases() == (o
                .getCases()) && this.getDeaths() == (o.getDeaths()));
        }
        return false;
    }


    /**
     * Gives a string representation of the object
     * 
     * @return the race, cases, and CFR for the RaceStats object
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.#");
        StringBuilder builder = new StringBuilder();
        builder.append(race);
        builder.append(": ");
        builder.append(cases);
        builder.append(" cases, ");
        builder.append(df.format(getCFR()));
        builder.append("% CFR");
        return builder.toString();

    }
}
