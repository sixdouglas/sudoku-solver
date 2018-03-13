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

package org.douglas.sudoku;

public final class GridInitializer {

    private GridInitializer() {}

    public Grid initGrid(){
        Grid grid = new Grid();

        grid.getCells()[1][0][7] = CellStatus.ONE;
        grid.getCells()[1][1][3] = CellStatus.ONE;
        grid.getCells()[1][2][0] = CellStatus.ONE;
        grid.getCells()[1][3][5] = CellStatus.ONE;
        grid.getCells()[1][4][8] = CellStatus.ONE;
        grid.getCells()[1][5][1] = CellStatus.ONE;
        grid.getCells()[1][6][2] = CellStatus.ONE;
        grid.getCells()[1][7][4] = CellStatus.ONE;
        grid.getCells()[1][8][6] = CellStatus.ONE;

        grid.getCells()[2][0][8] = CellStatus.TWO;
        grid.getCells()[2][1][2] = CellStatus.TWO;
        grid.getCells()[2][2][5] = CellStatus.TWO;
        grid.getCells()[2][3][7] = CellStatus.TWO;
        grid.getCells()[2][4][1] = CellStatus.TWO;
        grid.getCells()[2][5][4] = CellStatus.TWO;
        grid.getCells()[2][6][6] = CellStatus.TWO;
        grid.getCells()[2][7][0] = CellStatus.TWO;
        grid.getCells()[2][8][3] = CellStatus.TWO;

        grid.getCells()[3][0][1] = CellStatus.THREE;
        grid.getCells()[3][1][6] = CellStatus.THREE;
        grid.getCells()[3][2][3] = CellStatus.THREE;
        grid.getCells()[3][3][8] = CellStatus.THREE;
        grid.getCells()[3][4][5] = CellStatus.THREE;
        grid.getCells()[3][5][2] = CellStatus.THREE;
        grid.getCells()[3][6][4] = CellStatus.THREE;
        grid.getCells()[3][7][7] = CellStatus.THREE;
        grid.getCells()[3][8][0] = CellStatus.THREE;

        grid.getCells()[4][0][2] = CellStatus.FOUR;
        grid.getCells()[4][1][7] = CellStatus.FOUR;
        grid.getCells()[4][2][4] = CellStatus.FOUR;
        grid.getCells()[4][3][6] = CellStatus.FOUR;
        grid.getCells()[4][4][0] = CellStatus.FOUR;
        grid.getCells()[4][5][5] = CellStatus.FOUR;
        grid.getCells()[4][6][8] = CellStatus.FOUR;
        grid.getCells()[4][7][3] = CellStatus.FOUR;
        grid.getCells()[4][8][1] = CellStatus.FOUR;

        grid.getCells()[5][0][0] = CellStatus.FIVE;
        grid.getCells()[5][1][5] = CellStatus.FIVE;
        grid.getCells()[5][2][6] = CellStatus.FIVE;
        grid.getCells()[5][3][1] = CellStatus.FIVE;
        grid.getCells()[5][4][4] = CellStatus.FIVE;
        grid.getCells()[5][5][7] = CellStatus.FIVE;
        grid.getCells()[5][6][3] = CellStatus.FIVE;
        grid.getCells()[5][7][8] = CellStatus.FIVE;
        grid.getCells()[5][8][2] = CellStatus.FIVE;

        grid.getCells()[6][0][3] = CellStatus.SIX;
        grid.getCells()[6][1][0] = CellStatus.SIX;
        grid.getCells()[6][2][7] = CellStatus.SIX;
        grid.getCells()[6][3][4] = CellStatus.SIX;
        grid.getCells()[6][4][2] = CellStatus.SIX;
        grid.getCells()[6][5][8] = CellStatus.SIX;
        grid.getCells()[6][6][1] = CellStatus.SIX;
        grid.getCells()[6][7][6] = CellStatus.SIX;
        grid.getCells()[6][8][5] = CellStatus.SIX;

        grid.getCells()[7][0][4] = CellStatus.SEVEN;
        grid.getCells()[7][1][1] = CellStatus.SEVEN;
        grid.getCells()[7][2][8] = CellStatus.SEVEN;
        grid.getCells()[7][3][3] = CellStatus.SEVEN;
        grid.getCells()[7][4][6] = CellStatus.SEVEN;
        grid.getCells()[7][5][0] = CellStatus.SEVEN;
        grid.getCells()[7][6][5] = CellStatus.SEVEN;
        grid.getCells()[7][7][2] = CellStatus.SEVEN;
        grid.getCells()[7][8][7] = CellStatus.SEVEN;

        grid.getCells()[8][0][5] = CellStatus.HEIGHT;
        grid.getCells()[8][1][8] = CellStatus.HEIGHT;
        grid.getCells()[8][2][2] = CellStatus.HEIGHT;
        grid.getCells()[8][3][0] = CellStatus.HEIGHT;
        grid.getCells()[8][4][3] = CellStatus.HEIGHT;
        grid.getCells()[8][5][6] = CellStatus.HEIGHT;
        grid.getCells()[8][6][7] = CellStatus.HEIGHT;
        grid.getCells()[8][7][1] = CellStatus.HEIGHT;
        grid.getCells()[8][8][4] = CellStatus.HEIGHT;

        grid.getCells()[9][0][6] = CellStatus.NINE;
        grid.getCells()[9][1][4] = CellStatus.NINE;
        grid.getCells()[9][2][1] = CellStatus.NINE;
        grid.getCells()[9][3][2] = CellStatus.NINE;
        grid.getCells()[9][4][7] = CellStatus.NINE;
        grid.getCells()[9][5][3] = CellStatus.NINE;
        grid.getCells()[9][6][0] = CellStatus.NINE;
        grid.getCells()[9][7][5] = CellStatus.NINE;
        grid.getCells()[9][8][8] = CellStatus.NINE;

        return grid;
    }
}
