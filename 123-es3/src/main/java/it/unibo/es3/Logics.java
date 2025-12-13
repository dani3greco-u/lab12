package it.unibo.es3;

import java.util.Set;

/**
 * Interface defining the logic of application.
 */
public interface Logics {

    /**
     * Active the Neighbors.
     *
     */
    void activeNeighbors();

    /**
     * True if it is time to quit.
     *
     * @return whether it is time to quit
     */
    boolean toQuit();

    /**
     * The current position for every slot.
     * 
     * @return the postion of each slot
     */
    Set<Pair<Integer, Integer>> getActive();
}
