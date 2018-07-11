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
import org.douglas.sudoku.solver.GridSolverEngine;

import javax.swing.*;

/**
 * @author Douglas SIX
 */
@Log4j2
public final class MainGui extends JFrame implements InitializationListener, ResetListener {
    private Grid grid;

    public MainGui() {
        super("Sudoku Solver");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        doInit();
    }

    private void doInit() {
        //Create and set up the content pane.
        this.grid = new Grid();
        EmptyGui newContentPane = new EmptyGui(grid);
        newContentPane.addListener(this);
        newContentPane.setOpaque(true); //content panes must be opaque
        setContentPane(newContentPane);

        //Display the window.
        pack();
        setSize(500, 500);
    }


    @Override
    public void initializationDone() {
        GridSolverEngine solver = new GridSolverEngine(grid);
        solver.run();
        SolvedGui solvedGui = new SolvedGui(grid);
        solvedGui.addListener(this);
        setContentPane(solvedGui);

        //Display the window.
        pack();
        setSize(500, 500);
    }

    @Override
    public void resetDone() {
        doInit();
    }
}
