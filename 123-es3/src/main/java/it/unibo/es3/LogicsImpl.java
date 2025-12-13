package it.unibo.es3;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private static final int ACTIVE_CELL_ON_START = 3;
    private static final List<Pair<Integer, Integer>> OFFSETS = List.of(
        new Pair<>(-1, -1), new Pair<>(-1, 0), new Pair<>(-1, 1),
        new Pair<>(0, -1), new Pair<>(0, 1), new Pair<>(1, -1), 
        new Pair<>(1, 0), new Pair<>(1, 1)
    );
    private final int gridSize;
    private final Set<Pair<Integer, Integer>> active = new LinkedHashSet<>();

    /**
     * Constructor.
     * 
     * @param size the size of the grid
     */
    @SuppressFBWarnings (
        "DMI_RANDOM_USED_ONLY_ONCE"
    )
    public LogicsImpl(final int size) {
        this.gridSize = size;
        while (active.size() < ACTIVE_CELL_ON_START) {
            final Random rand = new Random();
            active.add(new Pair<>(rand.nextInt(0, size), rand.nextInt(0, size)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pair<Integer, Integer>> getActive() {
        return Collections.unmodifiableSet(active);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activeNeighbors() {
        final Set<Pair<Integer, Integer>> copy = new LinkedHashSet<>(this.active);
        for (final Pair<Integer, Integer> pair : copy) {
            for (final var offset : OFFSETS) {
                final int x = pair.x() + offset.x();
                final int y = pair.y() + offset.y();
                final boolean validRow = x >= 0 && x < gridSize;
                final boolean validColumn = y >= 0 && y < gridSize;
                if (validRow && validColumn) {
                    active.add(new Pair<>(x, y));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return active.size() == (int) Math.pow(this.gridSize, 2);
    }

}
