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

import java.util.Collection;

/**
 * @author Douglas SIX
 */
@Log4j2
final class OnlyPossibleValueGridSolver extends GridSolver {

    @Override
    protected boolean findSolution(Grid gridToSolve, int x, int y) {
        Collection<String> cellPossibleValues = gridToSolve.getCellPossibleValues(x, y);

        if (cellPossibleValues.size() == 1) {
            Integer value = Integer.valueOf(cellPossibleValues.iterator().next());
            log.trace(String.format("unique value found:   X: %s, Y: %s, Value: %d", x, y, value));
            gridToSolve.setCellValue(x, y, value);
            return true;
        }

        return false;
    }
}
