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

import lombok.extern.log4j.Log4j2;
import org.douglas.sudoku.grid.Grid;

import static org.douglas.sudoku.grid.Grid.HIGHER_BOUND;
import static org.douglas.sudoku.grid.Grid.LOWER_BOUND;

/**
 * @author Douglas SIX
 */
@Log4j2
abstract class GridSolver {

    boolean solve(Grid gridToSolve) {
        int count = 0;
        boolean solutionFound = true;

        while (gridToSolve.isGridNotFinished() && solutionFound) {
            solutionFound = false;

            for (int x = LOWER_BOUND; x < HIGHER_BOUND; x++) {
                for (int y = LOWER_BOUND; y < HIGHER_BOUND; y++) {
                    if (!gridToSolve.isInitialValue(x, y)
                            && gridToSolve.isValueNotSet(x, y)
                            && findSolution(gridToSolve, x, y)) {
                        solutionFound = true;
                    }
                }
            }

            log.debug("count: " + count);
            count++;
        }

        return solutionFound;
    }

    abstract protected boolean findSolution(Grid grid, int x, int y);
}
