package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    //private static final String ERROR_MESSAGE = "Unimplemented method";
    private static final int STEP = 1;
    private static final int INITIAL_STATE = 0;
    private final List<Integer> list;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     * 
     */
    public LogicsImpl(final int size) {
        list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(INITIAL_STATE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return list.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return Collections.unmodifiableList(list);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        final List<Boolean> lBoolean = new ArrayList<>();
        final Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            lBoolean.add(!it.next().equals(list.size()));
        }
        return Collections.unmodifiableList(lBoolean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        list.set(elem, list.get(elem) + STEP);
        return list.get(elem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        final StringBuilder sb = new StringBuilder("<<");
        for (int i = 0; i < list.size() - 1; i++) {
            sb.append(list.get(i).toString()).append('|');
        }
        sb.append(list.get(list.size() - 1).toString()).append(">>");
        return sb.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        final int firstValue = list.get(0);
        final Iterator<Integer> it = this.values().iterator();
        while (it.hasNext()) {
            if (!it.next().equals(firstValue)) {
                return false;
            }
        }
        return true;
    }
}
