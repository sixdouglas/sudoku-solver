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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Grid {

    /**
     * This cell array represent the grid in three dimension.
     * First dimension is the value;
     * Second dimension is the X;
     * Third dimension is the Y;
     */
    private CellStatus[][][] cells;

    public Grid(){
        cells = new CellStatus[10][9][9];

        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++) {
                for (int k = 0; k < cells[0][0].length; k++) {
                    cells[i][j][k] = CellStatus.EMPTY;
                }
            }
        }
    }

    public CellStatus[][][] getCells() {
        return cells;
    }

    public void setCellValue(int x, int y, Integer value){
        if (value == null){
            return;
        }

        if (value < 1 || value > 9){
            throw new IllegalArgumentException("'value' must be between 1 and 9 included or NULL");
        }

        if (x < 0 || x > 8){
            throw new IllegalArgumentException("'x' must be between 0 and 8 included");
        }

        if (y < 0 || y > 8){
            throw new IllegalArgumentException("'y' must be between 0 and 8 included");
        }

        if (cells[value][x][y] == CellStatus.FORBIDDEN){
            throw new IllegalArgumentException("Cell value impossible");
        }

        if (cells[value][x][y] == CellStatus.OCCUPIED){
            throw new IllegalArgumentException("Another value has been set here");
        }

        // Going through all values layers to set the Cell value when
        // on the right value layer and set it as Occupied on the other layers
        for (int i = 1; i < 10; i++) {
            if (value == i) {
                setCellStatus(i, x, y, CellStatus.getCellStatus(i));
                // Set the full line, row and quadrant as Forbidden for this layer value.
                fillLineAsForbidden(i, x, y);
                fillRowAsForbidden(i, x, y);
                fillQuadrantAsForbidden(i, x, y);
            } else {
                setCellStatus(i, x, y, CellStatus.OCCUPIED);
            }
        }
    }

    private void setCellStatus(int i, int x, int y, CellStatus cellStatus) {
        cells[i][x][y] = cellStatus;
    }

    private void fillLineAsForbidden(int i, int x, int y) {
        for (int cpt = 0; cpt < 9; cpt++){
            if (cpt != y){
                setCellStatus(i, x, cpt, CellStatus.FORBIDDEN);
            }
        }
    }

    private void fillRowAsForbidden(int i, int x, int y) {
        for (int cpt = 0; cpt < 9; cpt++){
            if (cpt != x){
                setCellStatus(i, cpt, y, CellStatus.FORBIDDEN);
            }
        }
    }

    private void fillQuadrantAsForbidden(int i, int x, int y) {
        Pair<Integer, Integer> quadrant = findQuadrant(x, y);

        int quadrantX = quadrant.getLeft();
        int quadrantY = quadrant.getRight();

        for (int cptX = 0; cptX < 3; cptX++){
            for (int cptY = 0; cptY < 3; cptY++){
                if (cptX != x && cptY != y) {
                    setCellStatus(i, cptX + (3 * quadrantX), cptY + (3 * quadrantY), CellStatus.FORBIDDEN);
                }
            }
        }
    }

    public Pair<Integer, Integer> findQuadrant(int x, int y) {

        if (x < 0 || x > 8){
            throw new IllegalArgumentException("'x' must be between 0 and 8 included");
        }

        if (y < 0 || y > 8){
            throw new IllegalArgumentException("'y' must be between 0 and 8 included");
        }

        int quadrantX;
        int quadrantY;

        switch (x) {
            case 0:
            case 1:
            case 2:
                quadrantX = 0;
                break;
            case 3:
            case 4:
            case 5:
                quadrantX = 1;
                break;
            default:
                quadrantX = 2;
                break;
        }

        switch (y) {
            case 0:
            case 1:
            case 2:
                quadrantY = 0;
                break;
            case 3:
            case 4:
            case 5:
                quadrantY = 1;
                break;
            default:
                quadrantY = 2;
                break;
        }

        return Pair.of(quadrantX, quadrantY);
    }

    public String getCellValue(int x, int y) {

        if (x < 0 || x > 8){
            throw new IllegalArgumentException("'x' must be between 0 and 8 included");
        }

        if (y < 0 || y > 8){
            throw new IllegalArgumentException("'y' must be between 0 and 8 included");
        }

        String value = StringUtils.SPACE;
        for (int i = 1; i < 10; i++){
            if (cells[i][x][y] != CellStatus.OCCUPIED && cells[i][x][y] != CellStatus.EMPTY  && cells[i][x][y] != CellStatus.FORBIDDEN){
                value = cells[i][x][y].name();
            }
        }

        return value;
    }

    public boolean isValuePresentInLine(Integer value, int x){

        if (value == null){
            return false;
        }

        if (value < 1 || value > 9){
            throw new IllegalArgumentException("'value' must be between 1 and 9 included or NULL");
        }

        if (x < 0 || x > 8){
            throw new IllegalArgumentException("'x' must be between 0 and 8 included");
        }

        for (int y = 0; y < 9; y++){
            if (cells[value][x][y] == CellStatus.getCellStatus(value)){
                return true;
            }
        }

        return false;
    }

    public boolean isValuePresentInColumn(Integer value, int y){

        if (value == null){
            return false;
        }

        if (value < 1 || value > 9){
            throw new IllegalArgumentException("'value' must be between 1 and 9 included or NULL");
        }

        if (y < 0 || y > 8){
            throw new IllegalArgumentException("'y' must be between 0 and 8 included");
        }

        for (int x = 0; x < 9; x++){
            if (cells[value][x][y] == CellStatus.getCellStatus(value)){
                return true;
            }
        }

        return false;
    }

    public boolean isValuePresentInQuadrant(Integer value, int quadrantX, int quadrantY){

        if (value == null){
            return false;
        }

        if (value < 1 || value > 9){
            throw new IllegalArgumentException("'value' must be between 1 and 9 included or NULL");
        }

        if (quadrantX < 0 || quadrantX > 2){
            throw new IllegalArgumentException("'quadrantX' must be between 0 and 2 included");
        }

        if (quadrantY < 0 || quadrantY > 2){
            throw new IllegalArgumentException("'quadrantY' must be between 0 and 2 included");
        }

        for (int cptX = 0; cptX < 2; cptX++){
            for (int cptY = 0; cptY < 2; cptY++){
                if (cells[value][cptX + (3 * quadrantX)][cptY + (3 * quadrantY)] == CellStatus.getCellStatus(value)){
                    return true;
                }
            }
        }

        return false;
    }
}
