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

package org.douglas.sudoku.grid;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Collection;

/**
 * @author Douglas SIX
 */
public class Gui extends JPanel {
    private Grid grid;

    public Gui(Grid grid) {
        super(new GridLayout(3, 3));
        this.grid = grid;
        Border thickBlackLine = BorderFactory.createLineBorder(Color.BLACK, 3);
        Border thinBlackLine = BorderFactory.createLineBorder(Color.GRAY, 1);

        setBorder(thickBlackLine);

        for (int qx = Grid.QUADRANT_LOW_BOUND; qx <= Grid.QUADRANT_HIGH_BOUND; qx++) {
            for (int qy = Grid.QUADRANT_LOW_BOUND; qy <= Grid.QUADRANT_HIGH_BOUND; qy++) {
                JPanel innerPossibilityPanel = new JPanel(new GridLayout(3, 3));
                innerPossibilityPanel.setBorder(thinBlackLine);

                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        int currentX = x + (3 * qx);
                        int currentY = y + (3 * qy);

                        if (grid.getValue(currentX, currentY) != Integer.MIN_VALUE) {
                            JLabel label = new JLabel(grid.getCellValue(currentX, currentY));
                            if (grid.isInitialValue(currentX, currentY)) {
                                label.setForeground(Color.RED);
                            }
                            label.setHorizontalAlignment(JLabel.CENTER);
                            addCompForBorder(thinBlackLine, label, innerPossibilityPanel);
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
                            addCompForBorder(thinBlackLine, possPanel, innerPossibilityPanel);
                        }
                    }
                }
                add(innerPossibilityPanel);
            }
        }
    }

    private void addCompForBorder(Border border, JComponent component, Container container) {
        JPanel comp = new JPanel(new GridLayout(1, 1), false);
        comp.add(component);
        comp.setBorder(border);
        container.add(comp);
    }
}
