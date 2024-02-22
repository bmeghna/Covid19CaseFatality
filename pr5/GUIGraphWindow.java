package prj5;

import java.awt.Color;
import java.text.DecimalFormat;
import cs2.Button;
import cs2.*;
/**
 * GUIGraphWindow creates the front end of the COVID-19 visualization
 * window displays the CFR of each race in a state in the form of a Bar
 * Graph.
 * 
 * @author Alex Natt, Austin Kirkbride, Meghna Banerjee
 * @version 04/30/2021
 */
public class GUIGraphWindow {
    private static int barWidth = 20;
    private LinkedList<State> stateList;
    private State state;
    private Window window;
    private Button sortByAlpha;
    private Button quitButton;
    private Button sortByCFR;
    private Button dcButton;
    private Button gaButton;
    private Button mdButton;
    private Button ncButton;
    private Button tnButton;
    private Button vaButton;
    private Color maroon;
    private Color orange;
    
    /**
     * New GUIGraphWindow object
     * @param states
     *      The LinkedList of states
     */
    public GUIGraphWindow(LinkedList<State> states) {
        window = new Window();
        window.setTitle("COVID-19 Visualization");
        
        orange = new Color(255, 140, 0);
        maroon = new Color(128, 0, 0);
        sortByAlpha = new Button("Sort By Alpha");
        sortByAlpha.onClick(this, "clickedSortByAlpha");
        quitButton = new Button("Quit");
        quitButton.onClick(this, "clickedQuit");
        sortByCFR = new Button("Sort By CFR");
        sortByCFR.onClick(this, "clickedSortByCFR");
        dcButton = new Button("Represent DC");
        dcButton.onClick(this, "clickedDC");
        gaButton = new Button("Represent GA");
        gaButton.onClick(this, "clickedGA");
        mdButton = new Button("Represent MD");
        mdButton.onClick(this, "clickedMD");
        ncButton = new Button("Represent NC"); 
        ncButton.onClick(this, "clickedNC");
        tnButton = new Button("Represent TN");
        tnButton.onClick(this, "clickedTN");
        vaButton = new Button("Represent VA");
        vaButton.onClick(this, "clickedVA");
        
        stateList = states;
        
        window.addButton(sortByAlpha, WindowSide.NORTH);
        window.addButton(quitButton, WindowSide.NORTH);
        window.addButton(sortByCFR, WindowSide.NORTH);
        window.addButton(dcButton, WindowSide.SOUTH);
        window.addButton(gaButton, WindowSide.SOUTH);
        window.addButton(mdButton, WindowSide.SOUTH);
        window.addButton(ncButton, WindowSide.SOUTH);
        window.addButton(tnButton, WindowSide.SOUTH);
        window.addButton(vaButton, WindowSide.SOUTH);
    }
    
    /**
     * Handles the DC button in the window
     * @param button
     *      The button to click DC
     */
    public void clickedDC(Button button) {
        state = stateList.get(0);
        update();
    }
    
    /**
     * Handles the GA button in the window
     * @param button
     *      The button to click GA
     */
    public void clickedGA(Button button) {
        state = stateList.get(1);
        update();
    }
    
    /**
     * Handles the MD button in the window
     * @param button
     *      The button to click MD
     */
    public void clickedMD(Button button) {
        state = stateList.get(2);
        update();
    }
    
    /**
     * Handles the NC button in the window
     * @param button
     *      The button to click NC
     */
    public void clickedNC(Button button) {
        state = stateList.get(3);
        update();
    }
    
    /**
     * Handles the TN button in the window
     * @param button
     *      The button to click TN
     */
    public void clickedTN(Button button) {
        state = stateList.get(4);
        update();
    }
    /**
     * Handles the VA button in the window
     * @param button
     *      The button to click VA
     */
    public void clickedVA(Button button) {
        state = stateList.get(5);
        update();
    }
    
    /**
     * Handles the quit method
     * 
     * @param button
     *      The button to click quit
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }
    
    /**
     * Handles the SortByAlpha method
     * 
     * @param button
     *      The button to click sortByAlpha
     */
    public void clickedSortByAlpha(Button button) {
        for (State sta: stateList) {
            sta.sortAlpha();
        }
        update();
    }
    
    /**
     * Handles the SortByCFR method
     * 
     * @param button
     *      The button to click sortByCFR
     */
    public void clickedSortByCFR(Button button) {
        for (State sta: stateList) {
            sta.sortCFR();
        }
        update();
    }
    
    /**
     * Create the CFR by Race Graph
     */
    public void createGraph(State sta) {
        int width = window.getGraphPanelWidth();
        int desheight = window.getGraphPanelHeight() - 50;
        int shapeX = width / 6;
        int colorch = 0;
        for (RaceStats next: sta.getRaces()) {
            int cfr = (int) next.getCFR() * width / 30;
            Shape s;
            if (colorch % 2 == 1) {
                s = new Shape(shapeX, desheight - cfr, barWidth, cfr, orange);
            }
            else {
                s = new Shape(shapeX, desheight - cfr, barWidth, cfr, maroon);
            }
            TextShape t = new TextShape(shapeX - 10, desheight + 10, next.getRace());
            DecimalFormat df = new DecimalFormat("#.#");
            String str = df.format(next.getCFR());
            if (str.equals("-1")) {
                str = "NA";
            }
            TextShape d = new TextShape(shapeX, desheight + 30, str);
            shapeX = shapeX + width / 6;
            colorch++;
            window.addShape(s);
            window.addShape(t);
            window.addShape(d);
        }
    }
    
    
    /**
     * Private helper method that updates the graph based on the state 
     * clicked.
     */
    private void update() {
        window.removeAllShapes();
        createGraph(state);
        String title = state.getName()
            + " Case Fatality Ratios by Race";
        TextShape titleShape = new TextShape(window.getGraphPanelWidth() 
            / 2,  3, title);
        titleShape.move(-titleShape.getWidth()/2, 0);
        window.addShape(titleShape);
    }
}
