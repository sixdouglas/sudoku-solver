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

package org.douglas.sudoku.solver;

import org.douglas.sudoku.grid.Grid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Douglas SIX
 */
public final class GridSolverEngine implements Runnable {

    private Grid gridToSolve;

    private List<GridSolver> gridSolvers;

    public GridSolverEngine(Grid gridToSolve){
        this.gridToSolve = gridToSolve;
        this.gridSolvers = new ArrayList<>();
        this.gridSolvers.add(new OnlyPossibleValueGridSolver());
        this.gridSolvers.add(new OnlyPossibleValuePlaceGridSolver());
    }

    @Override
    public void run() {
        gridSolvers.forEach(gridSolver -> gridSolver.solve(gridToSolve));
    }
}
