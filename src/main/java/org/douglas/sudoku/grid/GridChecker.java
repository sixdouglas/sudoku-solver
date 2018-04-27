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

import static org.douglas.sudoku.grid.Grid.*;

/**
 * @author Douglas SIX
 */
public class GridChecker {
    private final Grid grid;

    GridChecker(Grid grid) {
        this.grid = grid;
    }

    boolean isValuePresentInLine(Integer value, int x){

        if (value == null){
            return false;
        }

        checkValue(value);

        checkX(x);

        for (int y = LOWER_BOUND; y < HIGHER_BOUND; y++){
            if (grid.getCells()[value][x][y] == CellStatus.getCellStatus(value)){
                return true;
            }
        }

        return false;
    }

    boolean isValuePresentInColumn(Integer value, int y){

        if (value == null){
            return false;
        }

        checkValue(value);

        checkY(y);

        for (int x = LOWER_BOUND; x < HIGHER_BOUND; x++){
            if (grid.getCells()[value][x][y] == CellStatus.getCellStatus(value)){
                return true;
            }
        }

        return false;
    }

    boolean isValuePresentInQuadrant(Integer value, int quadrantX, int quadrantY){

        if (value == null){
            return false;
        }

        checkValue(value);

        if (quadrantX < QUADRANT_LOW_BOUND || quadrantX > QUADRANT_HIGH_BOUND) {
            throw new IllegalArgumentException("'quadrantX' must be between 0 and 2 included");
        }

        if (quadrantY < QUADRANT_LOW_BOUND || quadrantY > QUADRANT_HIGH_BOUND) {
            throw new IllegalArgumentException("'quadrantY' must be between 0 and 2 included");
        }

        for (int cptX = QUADRANT_LOW_BOUND; cptX < QUADRANT_HIGH_BOUND; cptX++) {
            for (int cptY = QUADRANT_LOW_BOUND; cptY < QUADRANT_HIGH_BOUND; cptY++) {
                if (grid.getCells()[value][cptX + (3 * quadrantX)][cptY + (3 * quadrantY)] == CellStatus.getCellStatus(value)){
                    return true;
                }
            }
        }

        return false;
    }
}
