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

package org.douglas.sudoku;

import lombok.extern.log4j.Log4j2;
import org.douglas.sudoku.grid.Grid;
import org.douglas.sudoku.grid.MainGui;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Douglas SIX
 */
@Log4j2
public class Application implements Runnable {

    private Application(){
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    @Override
    public void run() {
        invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new MainGui();
        frame.setVisible(true);
    }


    private void initEasyGrid(Grid grid) {
        grid.setCellValue(0, 0, 5);
        grid.setCellValue(0, 1, 3);
        grid.setCellValue(0, 2, 4);
        grid.setCellValue(0, 3, 6);
        grid.setCellValue(0, 4, 7);
        grid.setCellValue(0, 5, 8);
        grid.setCellValue(0, 6, 9);
        grid.setCellValue(0, 7, 1);
        grid.setCellValue(0, 8, 2);

        grid.setCellValue(1, 0, 6);
//        grid.setCellValue(1, 1, 7);
        grid.setCellValue(1, 2, 2);
        grid.setCellValue(1, 3, 1);
        grid.setCellValue(1, 4, 9);
        grid.setCellValue(1, 5, 5);
        grid.setCellValue(1, 6, 3);
        grid.setCellValue(1, 7, 4);
        grid.setCellValue(1, 8, 8);

        grid.setCellValue(2, 0, 1);
        grid.setCellValue(2, 1, 9);
        grid.setCellValue(2, 2, 8);
        grid.setCellValue(2, 3, 3);
        grid.setCellValue(2, 4, 4);
        grid.setCellValue(2, 5, 2);
        grid.setCellValue(2, 6, 5);
        grid.setCellValue(2, 7, 6);
        grid.setCellValue(2, 8, 7);

        grid.setCellValue(3, 0, 8);
        grid.setCellValue(3, 1, 5);
        grid.setCellValue(3, 2, 9);
        grid.setCellValue(3, 3, 7);
        grid.setCellValue(3, 4, 6);
        grid.setCellValue(3, 5, 1);
        grid.setCellValue(3, 6, 4);
        grid.setCellValue(3, 7, 2);
        grid.setCellValue(3, 8, 3);

        grid.setCellValue(4, 0, 4);
        grid.setCellValue(4, 1, 2);
        grid.setCellValue(4, 2, 6);
        grid.setCellValue(4, 3, 8);
        grid.setCellValue(4, 4, 5);
        grid.setCellValue(4, 5, 3);
        grid.setCellValue(4, 6, 7);
        grid.setCellValue(4, 7, 9);
        grid.setCellValue(4, 8, 1);

        grid.setCellValue(5, 0, 7);
        grid.setCellValue(5, 1, 1);
        grid.setCellValue(5, 2, 3);
        grid.setCellValue(5, 3, 9);
        grid.setCellValue(5, 4, 2);
        grid.setCellValue(5, 5, 4);
        grid.setCellValue(5, 6, 8);
        grid.setCellValue(5, 7, 5);
        grid.setCellValue(5, 8, 6);

        grid.setCellValue(6, 0, 9);
        grid.setCellValue(6, 1, 6);
        grid.setCellValue(6, 2, 1);
        grid.setCellValue(6, 3, 5);
        grid.setCellValue(6, 4, 3);
        grid.setCellValue(6, 5, 7);
        grid.setCellValue(6, 6, 2);
        grid.setCellValue(6, 7, 8);
        grid.setCellValue(6, 8, 4);

        grid.setCellValue(7, 0, 2);
        grid.setCellValue(7, 1, 8);
        grid.setCellValue(7, 2, 7);
        grid.setCellValue(7, 3, 4);
        grid.setCellValue(7, 4, 1);
        grid.setCellValue(7, 5, 9);
        grid.setCellValue(7, 6, 6);
        grid.setCellValue(7, 7, 3);
        grid.setCellValue(7, 8, 5);

        grid.setCellValue(8, 0, 3);
        grid.setCellValue(8, 1, 4);
        grid.setCellValue(8, 2, 5);
        grid.setCellValue(8, 3, 2);
        grid.setCellValue(8, 4, 8);
        grid.setCellValue(8, 5, 6);
        grid.setCellValue(8, 6, 1);
        grid.setCellValue(8, 7, 7);
        grid.setCellValue(8, 8, 9);

        grid.stopInitialisation();
    }

    private void initSecondGrid(Grid grid) {
        grid.setCellValue(0, 0, 4);
        grid.setCellValue(0, 1, 7);
        grid.setCellValue(0, 2, 9);
//        grid.setCellValue(0, 3, 6);
        grid.setCellValue(0, 4, 1);
        grid.setCellValue(0, 5, 2);
//        grid.setCellValue(0, 6, 9);
//        grid.setCellValue(0, 7, 1);
//        grid.setCellValue(0, 8, 2);

//        grid.setCellValue(1, 0, 6);
        grid.setCellValue(1, 1, 3);
//        grid.setCellValue(1, 2, 2);
        grid.setCellValue(1, 3, 6);
        grid.setCellValue(1, 4, 7);
//        grid.setCellValue(1, 5, 5);
//        grid.setCellValue(1, 6, 3);
        grid.setCellValue(1, 7, 1);
//        grid.setCellValue(1, 8, 8);

        grid.setCellValue(2, 0, 1);
//        grid.setCellValue(2, 1, 9);
        grid.setCellValue(2, 2, 2);
        grid.setCellValue(2, 3, 9);
//        grid.setCellValue(2, 4, 4);
//        grid.setCellValue(2, 5, 2);
        grid.setCellValue(2, 6, 7);
//        grid.setCellValue(2, 7, 6);
        grid.setCellValue(2, 8, 4);

//        grid.setCellValue(3, 0, 6);
//        grid.setCellValue(3, 1, 8);
//        grid.setCellValue(3, 2, 9);
//        grid.setCellValue(3, 3, 7);
        grid.setCellValue(3, 4, 4);
//        grid.setCellValue(3, 5, 1);
        grid.setCellValue(3, 6, 5);
        grid.setCellValue(3, 7, 6);
        grid.setCellValue(3, 8, 8);

        grid.setCellValue(4, 0, 6);
        grid.setCellValue(4, 1, 8);
//        grid.setCellValue(4, 2, 6);
//        grid.setCellValue(4, 3, 8);
//        grid.setCellValue(4, 4, 6);
//        grid.setCellValue(4, 5, 3);
//        grid.setCellValue(4, 6, 7);
//        grid.setCellValue(4, 7, 9);
        grid.setCellValue(4, 8, 2);

        grid.setCellValue(5, 0, 2);
//        grid.setCellValue(5, 1, 1);
//        grid.setCellValue(5, 2, 3);
        grid.setCellValue(5, 3, 8);
        grid.setCellValue(5, 4, 6);
        grid.setCellValue(5, 5, 3);
//        grid.setCellValue(5, 6, 8);
        grid.setCellValue(5, 7, 9);
//        grid.setCellValue(5, 8, 6);

        grid.setCellValue(6, 0, 3);
        grid.setCellValue(6, 1, 4);
//        grid.setCellValue(6, 2, 1);
//        grid.setCellValue(6, 3, 5);
        grid.setCellValue(6, 4, 8);
//        grid.setCellValue(6, 5, 7);
        grid.setCellValue(6, 6, 9);
//        grid.setCellValue(6, 7, 8);
//        grid.setCellValue(6, 8, 4);

//        grid.setCellValue(7, 0, 2);
        grid.setCellValue(7, 1, 2);
//        grid.setCellValue(7, 2, 7);
        grid.setCellValue(7, 3, 4);
//        grid.setCellValue(7, 4, 1);
//        grid.setCellValue(7, 5, 9);
        grid.setCellValue(7, 6, 8);
//        grid.setCellValue(7, 7, 3);
//        grid.setCellValue(7, 8, 5);

        grid.setCellValue(8, 0, 8);
//        grid.setCellValue(8, 1, 4);
        grid.setCellValue(8, 2, 1);
        grid.setCellValue(8, 3, 5);
        grid.setCellValue(8, 4, 2);
//        grid.setCellValue(8, 5, 6);
        grid.setCellValue(8, 6, 4);
//        grid.setCellValue(8, 7, 7);
//        grid.setCellValue(8, 8, 9);

        grid.stopInitialisation();
    }

    private void initThirdGrid(Grid grid) {
        grid.setCellValue(0, 1, 4);

        grid.setCellValue(1, 0, 8);
        grid.setCellValue(1, 5, 4);
        grid.setCellValue(1, 6, 5);
        grid.setCellValue(1, 8, 7);

        grid.setCellValue(2, 0, 6);
        grid.setCellValue(2, 1, 7);
        grid.setCellValue(2, 2, 2);
        grid.setCellValue(2, 5, 3);
        grid.setCellValue(2, 6, 8);
        grid.setCellValue(2, 7, 9);

        grid.setCellValue(3, 6, 3);

        grid.setCellValue(4, 0, 3);
        grid.setCellValue(4, 1, 9);
        grid.setCellValue(4, 7, 7);

        grid.setCellValue(5, 1, 8);
        grid.setCellValue(5, 2, 4);
        grid.setCellValue(5, 3, 3);
        grid.setCellValue(5, 4, 7);
        grid.setCellValue(5, 5, 5);
        grid.setCellValue(5, 7, 1);

        grid.setCellValue(6, 2, 5);
        grid.setCellValue(6, 4, 9);
        grid.setCellValue(6, 5, 6);
        grid.setCellValue(6, 8, 8);

        grid.setCellValue(7, 0, 7);
        grid.setCellValue(7, 4, 1);
        grid.setCellValue(7, 6, 2);

        grid.setCellValue(8, 2, 8);
        grid.setCellValue(8, 7, 5);

        grid.stopInitialisation();
    }

    private void initFirstGrid(Grid grid) {
        grid.setCellValue(0, 0, 8);
        grid.setCellValue(0, 2, 9);
        grid.setCellValue(0, 6, 7);
        grid.setCellValue(0, 8, 6);

        grid.setCellValue(1, 2, 3);
        grid.setCellValue(1, 3, 9);
        grid.setCellValue(1, 4, 7);
        grid.setCellValue(1, 5, 2);
        grid.setCellValue(1, 6, 1);

        grid.setCellValue(2, 0, 2);
        grid.setCellValue(2, 3, 8);
        grid.setCellValue(2, 5, 5);
        grid.setCellValue(2, 8, 4);

        grid.setCellValue(3, 3, 7);
        grid.setCellValue(3, 4, 1);
        grid.setCellValue(3, 5, 4);

        grid.setCellValue(4, 0, 1);
        grid.setCellValue(4, 1, 4);
        grid.setCellValue(4, 7, 9);
        grid.setCellValue(4, 8, 7);

        grid.setCellValue(5, 3, 5);
        grid.setCellValue(5, 4, 9);
        grid.setCellValue(5, 5, 8);

        grid.setCellValue(6, 2, 2);
        grid.setCellValue(6, 3, 3);
        grid.setCellValue(6, 5, 7);
        grid.setCellValue(6, 6, 6);

        grid.setCellValue(7, 2, 7);
        grid.setCellValue(7, 4, 4);
        grid.setCellValue(7, 6, 3);

        grid.setCellValue(8, 0, 3);
        grid.setCellValue(8, 2, 4);
        grid.setCellValue(8, 6, 8);
        grid.setCellValue(8, 8, 2);

        grid.stopInitialisation();
    }
}
