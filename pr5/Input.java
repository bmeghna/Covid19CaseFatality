package prj5;

import java.io.FileNotFoundException;

/**
 * Reads a file using the covid reader class
 * 
 * @author Alex Natt, Austin Kirkbride, Meghna Banerjee
 * @version 2021.4.23
 */
public class Input {
    /**
     * Reads from a file, parses it, then prints the data nicely formatted
     * 
     * @param args
     *            is an input file of our choice
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 1) {
            CovidReader reader = new CovidReader(args[0]);
            reader.print();
        }
        else {
            CovidReader reader = new CovidReader(
                "Cases_and_Deaths_by_race_CRDT_Sep2020.csv");
            reader.print();
        }
    }
}
