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

import lombok.extern.log4j.Log4j2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Douglas SIX
 */
@Log4j2
public class EmptyGui extends JPanel {
    private final Grid grid;
    private List<InitializationListener> listeners = new ArrayList<InitializationListener>();

    public EmptyGui(Grid grid) {
        super(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        this.grid = grid;
        Border thickBlackLine = BorderFactory.createLineBorder(Color.BLACK, 3);
        Border thinBlackLine = BorderFactory.createLineBorder(Color.GRAY, 1);

        gridPanel.setBorder(thickBlackLine);

        for (int quadrantX = Grid.QUADRANT_LOW_BOUND; quadrantX <= Grid.QUADRANT_HIGH_BOUND; quadrantX++) {
            for (int quadrantY = Grid.QUADRANT_LOW_BOUND; quadrantY <= Grid.QUADRANT_HIGH_BOUND; quadrantY++) {
                JPanel innerPossibilityPanel = new JPanel(new GridLayout(3, 3));
                innerPossibilityPanel.setBorder(thinBlackLine);

                for (int cptX = 0; cptX < 3; cptX++) {
                    for (int cptY = 0; cptY < 3; cptY++) {
                        final int currentX = cptX + (3 * quadrantX);
                        final int currentY = cptY + (3 * quadrantY);

                        JTextField textField = new JTextField();
                        Border border = BorderFactory.createLineBorder(Color.BLUE, 0);
                        textField.setBorder(border);
                        textField.setDocument(new JTextFieldLimit(1));
                        textField.setHorizontalAlignment(JTextField.CENTER);
                        textField.getDocument().addDocumentListener(new DocumentListener() {
                            @Override
                            public void insertUpdate(DocumentEvent e) {
                                int value = 0;
                                try {
                                    value = Integer.valueOf(e.getDocument().getText(0, 1).toString());
                                } catch (BadLocationException e1) {
                                    e1.printStackTrace();
                                }
                                log.debug("e { " + currentX + ", " + currentY + ", " + value + " }");
                                grid.setCellValue(currentX, currentY, value);
                            }

                            @Override
                            public void removeUpdate(DocumentEvent e) {
                                int value = 0;
                                try {
                                    value = Integer.valueOf(e.getDocument().getText(0, 1).toString());
                                } catch (BadLocationException e1) {
                                    e1.printStackTrace();
                                }
                                log.debug("e { " + currentX + ", " + currentY + ", " + value + " }");
                                grid.setCellValue(currentX, currentY, value);
                            }

                            @Override
                            public void changedUpdate(DocumentEvent e) {
                                int value = 0;
                                try {
                                    value = Integer.valueOf(e.getDocument().getText(0, 1).toString());
                                } catch (BadLocationException e1) {
                                    e1.printStackTrace();
                                }
                                log.debug("e { " + currentX + ", " + currentY + ", " + value + " }");
                                grid.setCellValue(currentX, currentY, value);
                            }
                        });
                        addCompForBorder(thinBlackLine, textField, innerPossibilityPanel);
                    }
                }
                gridPanel.add(innerPossibilityPanel);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> {
            this.grid.stopInitialisation();
            for (InitializationListener listener : listeners) {
                listener.initializationDone();
            }
        });
        buttonsPanel.add(solveButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            System.out.println(e.getActionCommand());
            System.out.println(e.getSource().toString());
            System.out.println(e.toString());
            System.exit(0);
        });
        buttonsPanel.add(cancelButton);

        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void addCompForBorder(Border border, JComponent component, Container container) {
        JPanel comp = new JPanel(new GridLayout(1, 1), false);
        comp.add(component);
        comp.setBorder(border);
        container.add(comp);
    }

    public void addListener(InitializationListener toAdd) {
        listeners.add(toAdd);
    }

    private class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) return;

            if (!str.matches("[1-9]{1}")) return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
