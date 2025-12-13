package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private final Map<JButton, Pair<Integer, Integer>> cells = new LinkedHashMap<>();

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        final Logics logic = new LogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JPanel main = new JPanel(new BorderLayout());
        final JPanel panel = new JPanel(new GridLayout(width, width));
        main.add(panel, BorderLayout.CENTER);
        // Create buttons and add them to the panel
        final JButton play = new JButton(">");
        main.add(play, BorderLayout.SOUTH);
        this.getContentPane().add(main);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final var pos = new Pair<>(j, i);
                final JButton button = new JButton(" ");
                button.setEnabled(false);
                this.cells.put(button, pos);
                panel.add(button);
            }
        }
        play.addActionListener(e -> {
            logic.activeNeighbors();
            this.updateView(logic);
            if (logic.toQuit()) {
                dispose();
            }
        });

        this.updateView(logic);
        pack();
        this.setVisible(true);
    }

    /**
     * Update the view, adding the neighbors.
     * 
     * @param logic the logic of application
     */
    private void updateView(final Logics logic) {
        final Set<Pair<Integer, Integer>> active = logic.getActive();
        cells.forEach((b, p) -> b.setText(active.contains(p) ? "*" : " "));
    }
}
