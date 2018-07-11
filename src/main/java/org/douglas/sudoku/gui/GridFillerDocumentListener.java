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

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * @author Douglas SIX
 */
@Log4j2
final class GridFillerDocumentListener implements DocumentListener {
    private final int currentX;
    private final int currentY;
    private final Grid grid;

    GridFillerDocumentListener(final int currentX, final int currentY, final Grid grid){
        this.currentX = currentX;
        this.currentY = currentY;
        this.grid = grid;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        int value = 0;
        try {
            value = Integer.valueOf(e.getDocument().getText(0, 1));
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
            value = Integer.valueOf(e.getDocument().getText(0, 1));
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
            value = Integer.valueOf(e.getDocument().getText(0, 1));
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
        log.debug("e { " + currentX + ", " + currentY + ", " + value + " }");
        grid.setCellValue(currentX, currentY, value);
    }}
