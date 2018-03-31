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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.douglas.sudoku.tools.Colors;
import org.douglas.sudoku.tools.Console;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {

    private static final String LINE_SEPARATOR = "\n+---+---+---+---+---+---+---+---+---+\n";
    static final int LOWER_BOUND = 0;
    static final int HIGHER_BOUND = 9;
    static final int QUADRANT_HIGH_BOUND = 2;
    /**
     * This cell array represent the grid in three dimension.
     * First dimension is the value;
     * Second dimension is the X;
     * Third dimension is the Y;
     */
    private CellStatus[][][] cells;
    private Integer[][] initialValuesCells;
    private boolean initialisation = true;

    public Grid(){
        cells = new CellStatus[HIGHER_BOUND][HIGHER_BOUND][HIGHER_BOUND];
        initialValuesCells = new Integer[HIGHER_BOUND][HIGHER_BOUND];

        initGrid();
    }

    static void checkX(int x) {
        if (x < LOWER_BOUND || x >= HIGHER_BOUND){
            throw new IllegalArgumentException("'x' must be between 0 and 8 included, but was: " + x);
        }
    }

    static void checkY(int y) {
        if (y < LOWER_BOUND || y >= HIGHER_BOUND){
            throw new IllegalArgumentException("'y' must be between 0 and 8 included, but was: " + y);
        }
    }

    static void checkValue(Integer value) {
        if (value < LOWER_BOUND + 1 || value >= HIGHER_BOUND + 1){
            throw new IllegalArgumentException("'value' must be between 1 and 9 included or NULL, but was: " + value);
        }
    }

    private void initGrid() {
        IntStream.range(LOWER_BOUND, HIGHER_BOUND).forEach(layer ->
                IntStream.range(LOWER_BOUND, HIGHER_BOUND).forEach(x ->
                        IntStream.range(LOWER_BOUND, HIGHER_BOUND).forEach(y -> {
                                    cells[layer][x][y] = CellStatus.EMPTY;
                                    initialValuesCells[x][y] = 0;
                                }
                        )
                )
        );

        initialisation = true;
    }

    public void setCellValue(int x, int y, Integer value){
        if (value == null){
            return;
        }

        checkValue(value);

        checkX(x);

        checkY(y);

        if (cells[value - 1][x][y] == CellStatus.FORBIDDEN){
            throw new IllegalArgumentException("Cell value ["+value+"] impossible here ["+x+","+y+"]");
        }

        if (cells[value - 1][x][y] == CellStatus.OCCUPIED){
            throw new IllegalArgumentException("Another value has been set here here ["+x+","+y+"]");
        }

        // Going through all values layers to set the Cell value when
        // on the right value layer and set it as Occupied on the other layers
        for (int i = LOWER_BOUND; i < HIGHER_BOUND; i++) {
            if (value - 1 == i) {
                setCellStatus(i, x, y, CellStatus.getCellStatus(value));
                // Set the full line, row and quadrant as Forbidden for this layer value.
                fillLineAsForbidden(i, x, y);
                fillRowAsForbidden(i, x, y);
                fillQuadrantAsForbidden(i, x, y);
            } else {
                setCellStatus(i, x, y, CellStatus.OCCUPIED);
            }
        }
    }

    public void stopInitialisation(){
        initialisation = false;
    }

    public String getCellValue(int x, int y) {

        checkX(x);

        checkY(y);

        String value = StringUtils.SPACE;
        for (int i = 0; i < HIGHER_BOUND; i++){
            if (cells[i][x][y] != CellStatus.OCCUPIED && cells[i][x][y] != CellStatus.EMPTY  && cells[i][x][y] != CellStatus.FORBIDDEN){
                value = cells[i][x][y].getValue().toString();
            }
        }

        return value;
    }

    public boolean isGridFinished() {
        boolean gridFinished = true;

        for (int x = LOWER_BOUND; x < HIGHER_BOUND; x++) {
            for (int y = LOWER_BOUND; y < HIGHER_BOUND; y++) {
                if (StringUtils.SPACE.equals(getCellValue(x, y))) {
                    gridFinished = false;
                    break;
                }
            }
            if (!gridFinished) {
                break;
            }
        }

        return gridFinished;
    }

    public Collection<String> getCellPossibleValues(int x, int y) {

        checkX(x);

        checkY(y);

        List<String> possibleValues = new ArrayList<>();
        for (int i = 0; i < HIGHER_BOUND; i++){
            if (cells[i][x][y] == CellStatus.EMPTY){
                possibleValues.add(String.valueOf(i + 1));
            }
        }

        return possibleValues;
    }

    public String gridToString() {
        StringBuilder sb = new StringBuilder(LINE_SEPARATOR);

        for (int x = LOWER_BOUND; x < HIGHER_BOUND; x++){
            for (int y = LOWER_BOUND; y < HIGHER_BOUND; y++) {
                Colors currentColor = Colors.DEFAULT;
                if (isInitialValue(x, y)) {
                    currentColor = Colors.RED;
                }
                sb.append("+ ").append(Console.getColoredString(getCellValue(x, y), currentColor)).append(StringUtils.SPACE);
            }
            sb.append("+").append(LINE_SEPARATOR);
        }

        return sb.toString();
    }

    CellStatus[][][] getCells() {
        return cells;
    }

    Pair<Integer, Integer> findQuadrant(int x, int y) {

        checkX(x);

        checkY(y);

        int quadrantX;
        int quadrantY;

        switch (x) {
            case 0:
            case 1:
            case QUADRANT_HIGH_BOUND:
                quadrantX = 0;
                break;
            case 3:
            case 4:
            case 5:
                quadrantX = 1;
                break;
            default:
                quadrantX = QUADRANT_HIGH_BOUND;
                break;
        }

        switch (y) {
            case 0:
            case 1:
            case QUADRANT_HIGH_BOUND:
                quadrantY = 0;
                break;
            case 3:
            case 4:
            case 5:
                quadrantY = 1;
                break;
            default:
                quadrantY = QUADRANT_HIGH_BOUND;
                break;
        }

        return Pair.of(quadrantX, quadrantY);
    }

    private void fillLineAsForbidden(int layer, int x, int y) {
        for (int cpt = LOWER_BOUND; cpt < HIGHER_BOUND; cpt++){
            if (cpt != y){
                setCellStatus(layer, x, cpt, CellStatus.FORBIDDEN);
            }
        }
    }

    private void fillRowAsForbidden(int layer, int x, int y) {
        for (int cpt = LOWER_BOUND; cpt < HIGHER_BOUND; cpt++){
            if (cpt != x){
                setCellStatus(layer, cpt, y, CellStatus.FORBIDDEN);
            }
        }
    }

    private void fillQuadrantAsForbidden(int i, int x, int y) {
        Pair<Integer, Integer> quadrant = findQuadrant(x, y);

        int quadrantX = quadrant.getLeft();
        int quadrantY = quadrant.getRight();

        for (int cptX = LOWER_BOUND; cptX < 3; cptX++){
            for (int cptY = LOWER_BOUND; cptY < 3; cptY++){
                if ((cptX + (3 * quadrantX)) != x && (cptY + (3 * quadrantY)) != y) {
                    setCellStatus(i, cptX + (3 * quadrantX), cptY + (3 * quadrantY), CellStatus.FORBIDDEN);
                }
            }
        }
    }

    private void setCellStatus(int layer, int x, int y, CellStatus cellStatus) {
        if (cells[layer][x][y] != cellStatus && cells[layer][x][y] != CellStatus.OCCUPIED) {
            cells[layer][x][y] = cellStatus;
            if (initialisation && cellStatus != CellStatus.FORBIDDEN && cellStatus != CellStatus.OCCUPIED && cellStatus != CellStatus.EMPTY) {
                initialValuesCells[x][y] = cellStatus.getValue();
            }
        }
    }

    public boolean isInitialValue(int x, int y) {
        return (initialValuesCells[x][y] != 0);
    }
}
