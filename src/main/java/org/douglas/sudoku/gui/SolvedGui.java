/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.douglas.sudoku.gui;

import org.apache.commons.lang3.StringUtils;
import org.douglas.sudoku.grid.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Douglas SIX
 */
final class SolvedGui extends AbstractGui {
    private List<ResetListener> listeners = new ArrayList<>();

    SolvedGui(Grid grid) {
        super(grid);
    }

    void addListener(ResetListener toAdd) {
        listeners.add(toAdd);
    }

    protected JComponent fillQuadrantCell(Grid grid, int currentX, int currentY) {
        JComponent component;
        if (grid.getValue(currentX, currentY) != Integer.MIN_VALUE) {
            JLabel label = new JLabel(grid.getCellValue(currentX, currentY));
            if (grid.isInitialValue(currentX, currentY)) {
                label.setForeground(Color.RED);
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            component = label;
        } else {
            JPanel possPanel = new JPanel(new GridLayout(3, 3));
            Collection<String> values = grid.getCellPossibleValues(currentX, currentY);
            for (int value = Grid.LOWER_BOUND; value < Grid.HIGHER_BOUND; value++) {
                JLabel label;
                if (values.contains(String.valueOf(value + 1))) {
                    label = new JLabel(String.valueOf(value + 1));
                } else {
                    label = new JLabel(StringUtils.EMPTY);
                }
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setForeground(Color.GRAY);
                possPanel.add(label);
            }
            component = possPanel;
        }
        return component;
    }

    protected void addActionButton() {
        JButton actionButton = new JButton("Reset");
        actionButton.addActionListener(e -> {
            for (ResetListener listener : listeners) {
                listener.resetDone();
            }
        });

        buttonsPanel.add(actionButton);
    }
}
