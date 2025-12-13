package it.unibo.es2;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */

public class LogicsImpl implements Logics {

    private static List<List<Boolean>> list = new ArrayList<>(); 

    /**
     * Constructor.
     *
     * @param size the size of the logics
     * 
     */
    public LogicsImpl(final int size) {
        for (int i = 0; i < size; i++) {
            final List<Boolean> row = new ArrayList<>();
            list.add(row);
            for (int j = 0; j < size; j++) {
                row.add(false);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final Pair<Integer, Integer> position) {
        final boolean value = !this.getCell(position.x(), position.y());
        list.get(position.x()).set(position.y(), value);
        return value;
    }

    /**
     * The value of the cell.
     * 
     * @param row the row of the matrix
     * @param col the column of the matrix
     * @return the value of the ceil
     */
    private boolean getCell(final int row, final int col) {
        return list.get(row).get(col);
    }

    /**
     * Check if the row is full.
     * 
     * @param row the row to check
     * @return true if the row is full, false otherwise
     */
    private boolean checkRow(final int row) {
        for (int col = 0; col < list.size(); col++) {
            if (!list.get(row).get(col)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the column is full.
     * 
     * @param col the column to check
     * @return true if the column is full, false otherwise
     */
    private boolean checkColumn(final int col) {
        for (final List<Boolean> row : list) {
            if (!row.get(col)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        for (int i = 0; i < list.size(); i++) {
            if (checkRow(i) || checkColumn(i)) {
            return true;
            }
        }
        return false;
    }

}



