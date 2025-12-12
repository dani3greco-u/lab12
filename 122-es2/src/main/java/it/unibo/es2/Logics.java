package it.unibo.es2;

import java.util.List;

/**
 * Interface defining the logic of application.
 */
public interface Logics {

    /**
     * The number of slots.
     *
     * @return the number of slots
     */
    int size();

    /**
     * The current values for every slot.
     *
     * @return ordered list of the integers in each slot
     */
    List<List<Boolean>> values();

    /**
     * Change the value of the specified ceil.
     *
     * @param position the position of the ceil
     * @return the new value a button should show after being pressed
     */
    boolean hit(Pair<Integer, Integer> position);

    /**
     * True if it is time to quit.
     *
     * @return whether it is time to quit
     */
    boolean toQuit();
}

