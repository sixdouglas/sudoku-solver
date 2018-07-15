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

import lombok.extern.log4j.Log4j2;
import org.douglas.sudoku.grid.Grid;

import javax.swing.*;
import javax.swing.border.Border;
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
final class EmptyGui extends AbstractGui {
    private List<InitializationListener> listeners = new ArrayList<>();

    EmptyGui(final Grid grid) {
        super(grid);
    }

    void addListener(InitializationListener toAdd) {
        listeners.add(toAdd);
    }

    @Override
    protected JComponent fillQuadrantCell(Grid grid, int currentX, int currentY) {
        JTextField textField = new JTextField();
        Border border = BorderFactory.createLineBorder(Color.BLUE, 0);
        textField.setBorder(border);
        textField.setDocument(new JTextFieldLimit(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.getDocument().addDocumentListener(new GridFillerDocumentListener(currentX, currentY, grid));
        return textField;
    }

    protected JButton addActionButton() {
        JButton actionButton = new JButton("Solve");
        actionButton.addActionListener(e -> {
            this.grid.stopInitialisation();
            for (InitializationListener listener : listeners) {
                listener.initializationDone();
            }
        });

        return actionButton;
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
