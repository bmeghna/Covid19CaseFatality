package prj5;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Reads an input file, and creates a new Linked List of states accordingly
 *
 * @author Austin Kirkbride, Alex Natt, Meghna Banerjee
 * @version 4/23/2021
 */
public class CovidReader {
    private LinkedList<State> states;

    /**
     * Calls private method readFile()
     * 
     * @param fileName
     *            name of file
     * @throws FileNotFoundException
     *             if the filename is invalid
     */
    public CovidReader(String fileName) throws FileNotFoundException {
        readFile(fileName);
    }

    /**
     * returns a list of states
     */
    public LinkedList<State> getStates() {
        return states;
    }
    
    
    /**
     * Reads from a file and creates a Linked List of states from the
     * information in the file
     * 
     * @param fileName
     *            name of file
     * @throws FileNotFoundException
     *             throws if file not found
     */
    private void readFile(String fileName) throws FileNotFoundException {

        Scanner file = new Scanner(new File(fileName));
        states = new LinkedList<State>();
        file.nextLine();

        while (file.hasNextLine()) {
            String str = file.nextLine();
            String[] state = str.split(","); // String array state info

            for (int i = 0; i < state.length - 1; i++) {
                if (state[i].equals("NA")) {
                    state[i] = "-1";
                }
            }

            RaceStats white = new RaceStats("White", Integer.valueOf(state[1]),
                Integer.valueOf(state[6]));
            RaceStats black = new RaceStats("Black", Integer.valueOf(state[2]),
                Integer.valueOf(state[7]));
            RaceStats latinX = new RaceStats("LatinX", Integer.valueOf(
                state[3]), Integer.valueOf(state[8]));
            RaceStats asian = new RaceStats("Asian", Integer.valueOf(state[4]),
                Integer.valueOf(state[9]));
            RaceStats other = new RaceStats("Other", Integer.valueOf(state[5]),
                Integer.valueOf(state[10]));

            LinkedList<RaceStats> races = new LinkedList<RaceStats>();

            races.add(white);
            races.add(black);
            races.add(latinX);
            races.add(asian);
            races.add(other);

            State newState = new State(state[0], races);

            states.add(newState);

        }
    }


    /**
     * Prints the output of each state sorted alphabetically and then by CFR
     */
    public void print() {
        for (State i : states) {

            i.sortAlpha();
            System.out.println(i.getName());
            for (RaceStats j : i.getRaces()) {
                System.out.println(j.toString());
            }
            
            System.out.println("=====");

            i.sortCFR();
            for (RaceStats k : i.getRaces()) {
                System.out.println(k.toString());
            }
            System.out.println("=====");
        }
    }
}
