/*
 * Copyright 2012-2017 the original author or authors.
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

import java.util.Collection;

import static org.douglas.sudoku.grid.Grid.HIGHER_BOUND;
import static org.douglas.sudoku.grid.Grid.LOWER_BOUND;

@Log4j2
public class GridSolver implements Runnable {
    private final Grid gridToSolve;

    public GridSolver(Grid grid) {
        this.gridToSolve = grid;
    }

    @Override
    public void run() {
        int count = 0;
        boolean solutionFound = true;

        while (!gridToSolve.isGridFinished() && solutionFound && count < 20) {
            solutionFound = false;

            for (int x = LOWER_BOUND; x < HIGHER_BOUND; x++) {
                for (int y = LOWER_BOUND; y < HIGHER_BOUND; y++) {
                    if (!gridToSolve.isInitialValue(x, y) && findSolution(x, y)) {
                        solutionFound = true;
                    }
                }
            }

            count++;

            log.debug("___ " + count);
        }
    }

    private boolean findSolution(int x, int y) {
        Collection<String> cellPossibleValues = gridToSolve.getCellPossibleValues(x, y);

        if (cellPossibleValues.size() == 1) {
            Integer value = Integer.valueOf(cellPossibleValues.iterator().next());
            log.trace(String.format("value found: X: %s, Y: %s, Value: %d", x, y, value));
            gridToSolve.setCellValue(x, y, value);
            return true;
        }

        return false;
    }
}
