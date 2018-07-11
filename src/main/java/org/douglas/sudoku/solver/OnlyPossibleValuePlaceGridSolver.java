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
import org.apache.commons.lang3.tuple.Pair;
import org.douglas.sudoku.grid.Grid;

import java.util.Collection;

import static org.douglas.sudoku.grid.Grid.HIGHER_BOUND;
import static org.douglas.sudoku.grid.Grid.LOWER_BOUND;

/**
 * @author Douglas SIX
 */
@Log4j2
final class OnlyPossibleValuePlaceGridSolver extends GridSolver {

    @Override
    protected boolean findSolution(Grid gridToSolve, int x, int y) {
        Collection<String> cellPossibleValues = gridToSolve.getCellPossibleValues(x, y);

        for (String possibleValue : cellPossibleValues) {
            Integer value = Integer.valueOf(possibleValue);
            if (isOnlyPossibleValueInLine(gridToSolve, value, x, y)
                    || isOnlyPossibleValueInColumn(gridToSolve, value, x, y)
                    || isOnlyPossiblePlaceInQuadrant(gridToSolve, value, x, y)) {
                log.debug(String.format("possible value found: X: %s, Y: %s, Value: %d", x, y, value));
                gridToSolve.setCellValue(x, y, value);
                return true;
            }
        }
        return false;
    }

    private boolean isOnlyPossibleValueInLine(Grid gridToSolve, Integer value, int x, int y) {
        // Check on the same line
        for (int lineIndex = LOWER_BOUND; lineIndex < HIGHER_BOUND; lineIndex++) {
            if (lineIndex == x) {
                continue;
            }
            Collection<String> cellPossibleValues = gridToSolve.getCellPossibleValues(lineIndex, y);
            for (String possibleValue : cellPossibleValues) {
                if (Integer.valueOf(possibleValue).equals(value)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isOnlyPossibleValueInColumn(Grid gridToSolve, Integer value, int x, int y) {
        // Check on the same line
        for (int columnIndex = LOWER_BOUND; columnIndex < HIGHER_BOUND; columnIndex++) {
            if (columnIndex == y) {
                continue;
            }
            Collection<String> cellPossibleValues = gridToSolve.getCellPossibleValues(x, columnIndex);
            for (String possibleValue : cellPossibleValues) {
                if (Integer.valueOf(possibleValue).equals(value)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isOnlyPossiblePlaceInQuadrant(Grid gridToSolve, Integer value, int x, int y) {
        // Check on the same quadrant
        Pair<Integer, Integer> quadrant = gridToSolve.findQuadrant(x, y);

        int quadrantX = quadrant.getLeft();
        int quadrantY = quadrant.getRight();

        for (int cptX = LOWER_BOUND; cptX < 3; cptX++) {
            for (int cptY = LOWER_BOUND; cptY < 3; cptY++) {
                int currentX = cptX + (3 * quadrantX);
                int currentY = cptY + (3 * quadrantY);
                if (currentX != x || currentY != y) {
                    Collection<String> cellPossibleValues = gridToSolve.getCellPossibleValues(currentX, currentY);
                    for (String possibleValue : cellPossibleValues) {
                        if (Integer.valueOf(possibleValue).equals(value)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}
