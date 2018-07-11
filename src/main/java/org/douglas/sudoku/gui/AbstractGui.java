package org.douglas.sudoku.gui;

import org.douglas.sudoku.grid.Grid;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class AbstractGui extends JPanel {
    protected final Grid grid;
    protected JPanel buttonsPanel = new JPanel();

    AbstractGui(final Grid grid) {
        super(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        this.grid = grid;
        Border thickBlackLine = BorderFactory.createLineBorder(Color.BLACK, 3);
        Border thinBlackLine = BorderFactory.createLineBorder(Color.GRAY, 1);

        gridPanel.setBorder(thickBlackLine);

        addQuadrantToPanel(grid, gridPanel, thinBlackLine);

        add(gridPanel, BorderLayout.CENTER);

        addActionButton();

        addCancelButton();

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    protected abstract void addActionButton();

    private void addQuadrantToPanel(Grid grid, JPanel gridPanel, Border thinBlackLine) {
        for (int quadrantX = Grid.QUADRANT_LOW_BOUND; quadrantX <= Grid.QUADRANT_HIGH_BOUND; quadrantX++) {
            for (int quadrantY = Grid.QUADRANT_LOW_BOUND; quadrantY <= Grid.QUADRANT_HIGH_BOUND; quadrantY++) {
                JPanel innerPossibilityPanel = new JPanel(new GridLayout(3, 3));
                innerPossibilityPanel.setBorder(thinBlackLine);

                addFigureCellsToQuadrant(grid, thinBlackLine, quadrantX, quadrantY, innerPossibilityPanel);
                gridPanel.add(innerPossibilityPanel);
            }
        }
    }

    private void addFigureCellsToQuadrant(Grid grid, Border thinBlackLine, int quadrantX, int quadrantY, JPanel innerPossibilityPanel) {
        for (int cptX = Grid.QUADRANT_LOW_BOUND; cptX <= Grid.QUADRANT_HIGH_BOUND; cptX++) {
            for (int cptY = Grid.QUADRANT_LOW_BOUND; cptY <= Grid.QUADRANT_HIGH_BOUND; cptY++) {
                final int currentX = cptX + (3 * quadrantX);
                final int currentY = cptY + (3 * quadrantY);

                JComponent textField = fillQuadrantCell(grid, currentX, currentY);
                addCompForBorder(thinBlackLine, textField, innerPossibilityPanel);
            }
        }
    }

    private void addCompForBorder(Border border, JComponent component, Container container) {
        JPanel comp = new JPanel(new GridLayout(1, 1), false);
        comp.add(component);
        comp.setBorder(border);
        container.add(comp);
    }

    protected abstract JComponent fillQuadrantCell(Grid grid, int currentX, int currentY);

    protected void addCancelButton() {
        JButton cancelButton = new JButton("Exit");
        cancelButton.addActionListener(e -> {
            System.out.println(e.getActionCommand());
            System.out.println(e.getSource().toString());
            System.out.println(e.toString());
            System.exit(0);
        });

        buttonsPanel.add(cancelButton);
    }

}
