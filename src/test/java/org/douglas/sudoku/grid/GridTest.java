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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Douglas SIX
 */
public class GridTest {

    private Grid sut;

    @Before
    public void setup(){
        sut = new Grid();
    }

    @Test
    public void setCellValue_should_do_nothing_if_value_is_null() {
        CellStatus[][][] copy = Arrays.copyOf(sut.getCells(), 9);

        sut.setCellValue(1, 1, null);

        assertThat(sut.getCells()).containsExactly(copy);
    }

    @Test
    public void setCellValue_should_set_value_on_1th_layer_when_value_is_1() {
        sut.setCellValue(1, 1, 1);

        assertThat(sut.getCells()[0][1][1]).isEqualTo(CellStatus.ONE);
    }

    @Test
    public void setCellValue_should_set_value_on_4th_layer_when_value_is_4() {
        sut.setCellValue(1, 1, 4);

        assertThat(sut.getCells()[3][1][1]).isEqualTo(CellStatus.FOUR);
    }

    @Test
    public void setCellValue_should_set_value_on_9th_layer_when_value_is_9() {
        sut.setCellValue(1, 1, 9);

        assertThat(sut.getCells()[8][1][1]).isEqualTo(CellStatus.NINE);
    }

    @Test
    public void setCellValue_should_set_value_on_5th_layer_when_value_is_5() {
        sut.setCellValue(4, 4, 5);

        assertThat(sut.getCells()[4][4][4]).isEqualTo(CellStatus.FIVE);
    }

    @Test
    public void setCellValue_should_set_occupied_on_other_layers_when_value_is_4() {
        sut.setCellValue(1, 1, 4);

        assertThat(sut.getCells()[0][1][1]).isEqualTo(CellStatus.OCCUPIED);
        assertThat(sut.getCells()[1][1][1]).isEqualTo(CellStatus.OCCUPIED);
        assertThat(sut.getCells()[2][1][1]).isEqualTo(CellStatus.OCCUPIED);
        assertThat(sut.getCells()[4][1][1]).isEqualTo(CellStatus.OCCUPIED);
        assertThat(sut.getCells()[5][1][1]).isEqualTo(CellStatus.OCCUPIED);
        assertThat(sut.getCells()[6][1][1]).isEqualTo(CellStatus.OCCUPIED);
        assertThat(sut.getCells()[7][1][1]).isEqualTo(CellStatus.OCCUPIED);
        assertThat(sut.getCells()[8][1][1]).isEqualTo(CellStatus.OCCUPIED);
    }

    @Test
    public void setCellValue_should_set_forbidden_on_other_cells_of_the_second_line_when_value_is_4() {
        sut.setCellValue(1, 1, 4);

        assertThat(sut.getCells()[3][0][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][2][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][3][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][4][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][5][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][6][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][7][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][8][1]).isEqualTo(CellStatus.FORBIDDEN);
    }

    @Test
    public void setCellValue_should_set_forbidden_on_other_cells_of_the_second_column_when_value_is_4() {
        sut.setCellValue(1, 1, 4);

        assertThat(sut.getCells()[3][1][0]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][2]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][3]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][4]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][5]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][6]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][7]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][8]).isEqualTo(CellStatus.FORBIDDEN);
    }

    @Test
    public void setCellValue_should_set_forbidden_on_other_cells_of_the_same_quadrant_when_value_is_4() {
        sut.setCellValue(1, 1, 4);

        assertThat(sut.getCells()[3][0][0]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][0][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][0][2]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][0]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][1][2]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][2][0]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][2][1]).isEqualTo(CellStatus.FORBIDDEN);
        assertThat(sut.getCells()[3][2][2]).isEqualTo(CellStatus.FORBIDDEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_cell_value_is_forbidden() {
        // Given
        sut.getCells()[3][1][1] = CellStatus.FORBIDDEN;

        // When
        sut.setCellValue(1, 1, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_cell_value_is_occupied() {
        // Given
        sut.getCells()[3][1][1] = CellStatus.OCCUPIED;

        // When
        sut.setCellValue(1, 1, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_cell_is_occupied() {
        sut.setCellValue(1, 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_value_is_below_1() {
        sut.setCellValue(1, 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_value_is_over_9() {
        sut.setCellValue(1, 1, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_x_is_below_0() {
        sut.setCellValue(-1, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_x_is_over_8() {
        sut.setCellValue(9, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_y_is_below_0() {
        sut.setCellValue(1, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void setCellValue_should_throw_if_y_is_over_8() {
        sut.setCellValue(1, 9, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findQuadrant_should_throw_if_x_is_below_0() {
        sut.findQuadrant(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findQuadrant_should_throw_if_x_is_over_8() {
        sut.findQuadrant(9, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findQuadrant_should_throw_if_y_is_below_0() {
        sut.findQuadrant(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findQuadrant_should_throw_if_y_is_over_8() {
        sut.findQuadrant(1, 9);
    }

    @Test
    public void findQuadrant_top_left() {
        Pair<Integer, Integer> goodQuadrant = Pair.of(0, 0);
        Pair<Integer, Integer> quadrant = sut.findQuadrant(0, 0);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(0, 1);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(0, 2);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(1, 0);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(1, 1);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(1, 2);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(2, 0);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(2, 1);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(2, 2);
        assertThat(quadrant).isEqualTo(goodQuadrant);
    }

    @Test
    public void findQuadrant_bottom_right() {
        Pair<Integer, Integer> goodQuadrant = Pair.of(2, 2);
        Pair<Integer, Integer> quadrant = sut.findQuadrant(6, 6);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(6, 7);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(6, 8);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(7, 6);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(7, 7);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(7, 8);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(8, 6);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(8, 7);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(8, 8);
        assertThat(quadrant).isEqualTo(goodQuadrant);
    }

    @Test
    public void findQuadrant_center() {
        Pair<Integer, Integer> goodQuadrant = Pair.of(1, 1);
        Pair<Integer, Integer> quadrant = sut.findQuadrant(3, 3);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(3, 4);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(3, 5);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(4, 3);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(4, 4);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(4, 5);
        assertThat(quadrant).isEqualTo(goodQuadrant);

        quadrant = sut.findQuadrant(5, 3);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(5, 4);
        assertThat(quadrant).isEqualTo(goodQuadrant);
        quadrant = sut.findQuadrant(5, 5);
        assertThat(quadrant).isEqualTo(goodQuadrant);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCellValue_should_throw_if_x_is_below_0() {
        sut.getCellValue(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCellValue_should_throw_if_x_is_over_8() {
        sut.getCellValue(9, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCellValue_should_throw_if_y_is_below_0() {
        sut.getCellValue(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCellValue_should_throw_if_y_is_over_8() {
        sut.getCellValue(1, 9);
    }

    @Test
    public void getCellValue_should_return_blank_when_all_layers_are_occupied() {
        // Given
        givenAStatusForCells(CellStatus.OCCUPIED, -1, 1, 1);

        // When
        String cellValue = sut.getCellValue(1, 1);

        // Then
        assertThat(cellValue).isEqualToIgnoringCase(StringUtils.SPACE);
    }

    @Test
    public void getCellValue_should_return_blank_when_all_layers_are_empty() {
        // Given
        givenAStatusForCells(CellStatus.EMPTY, -1, 1, 1);

        // When
        String cellValue = sut.getCellValue(1, 1);

        // Then
        assertThat(cellValue).isEqualToIgnoringCase(StringUtils.SPACE);
    }

    @Test
    public void getCellValue_should_return_blank_when_all_layers_are_forbidden() {
        // Given
        givenAStatusForCells(CellStatus.FORBIDDEN, -1, 1, 1);

        // When
        String cellValue = sut.getCellValue(1, 1);

        // Then
        assertThat(cellValue).isEqualToIgnoringCase(StringUtils.SPACE);
    }

    @Test
    public void getCellValue_should_return_four_when_value_is_four() {
        // Given
        givenAStatusForCells(CellStatus.FORBIDDEN, -1, 1, 1);

        sut.getCells()[4][1][1] = CellStatus.FOUR;

        // When
        String cellValue = sut.getCellValue(1, 1);

        // Then
        assertThat(cellValue).isEqualToIgnoringCase(CellStatus.FOUR.getValue().toString());
    }

    private void givenAStatusForCells(CellStatus status, int value, int x, int y) {
        for (int i = 0; i < 9; i++){
            int layer = value < 0 ? i : value;
            int row = x < 0 ? i : x;
            int column = y < 0 ? i : y;

            sut.getCells()[layer][row][column] = status;
        }
    }
}