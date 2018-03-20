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

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridCheckerTest {

    private Grid grid;
    private GridChecker sut;

    @Before
    public void setUp() throws Exception {
        grid = new Grid();
        sut = new GridChecker(grid);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInLine_should_throw_if_value_is_below_1() {
        sut.isValuePresentInLine(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInLine_should_throw_if_value_is_over_9() {
        sut.isValuePresentInLine(10, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInLine_should_throw_if_x_is_below_0() {
        sut.isValuePresentInLine(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInLine_should_throw_if_x_is_over_8() {
        sut.isValuePresentInLine(1, 9);
    }

    @Test
    public void isValuePresentInLine_should_return_false_when_value_is_null() {
        // Given

        // When
        boolean present = sut.isValuePresentInLine(null, 1);

        // Then
        assertThat(present).isFalse();
    }

    @Test
    public void isValuePresentInLine_should_return_true_when_present() {
        // Given
        givenAStatusForCells(CellStatus.FORBIDDEN, 1, 1, -1);

        grid.getCells()[1][1][4] = CellStatus.ONE;

        // When
        boolean present = sut.isValuePresentInLine(1, 1);

        // Then
        assertThat(present).isTrue();
    }

    @Test
    public void isValuePresentInLine_should_return_false_when_not_present() {
        // Given
        givenAStatusForCells(CellStatus.OCCUPIED, 1, 1, -1);

        // When
        boolean present = sut.isValuePresentInLine(4, 1);

        // Then
        assertThat(present).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInColumn_should_throw_if_value_is_below_1() {
        sut.isValuePresentInColumn(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInColumn_should_throw_if_value_is_over_9() {
        sut.isValuePresentInColumn(10, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInColumn_should_throw_if_y_is_below_0() {
        sut.isValuePresentInColumn(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInColumn_should_throw_if_y_is_over_8() {
        sut.isValuePresentInColumn(1, 9);
    }

    @Test
    public void isValuePresentInColumn_should_return_false_when_value_is_null() {
        // Given

        // When
        boolean present = sut.isValuePresentInColumn(null, 1);

        // Then
        assertThat(present).isFalse();
    }

    @Test
    public void isValuePresentInColumn_should_return_true_when_present() {
        // Given
        givenAStatusForCells(CellStatus.FORBIDDEN, 1, -1, 1);

        grid.getCells()[1][4][1] = CellStatus.ONE;

        // When
        boolean present = sut.isValuePresentInColumn(1, 1);

        // Then
        assertThat(present).isTrue();
    }

    @Test
    public void isValuePresentInColumn_should_return_false_when_not_present() {
        // Given
        givenAStatusForCells(CellStatus.OCCUPIED, 1, -1, 1);

        // When
        boolean present = sut.isValuePresentInColumn(4, 2);

        // Then
        assertThat(present).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInQuadrant_should_throw_if_value_is_below_0() {
        sut.isValuePresentInQuadrant(0, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInQuadrant_should_throw_if_value_is_over_9() {
        sut.isValuePresentInQuadrant(10, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInQuadrant_should_throw_if_x_is_below_0() {
        sut.isValuePresentInQuadrant(1, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInQuadrant_should_throw_if_x_is_over_8() {
        sut.isValuePresentInQuadrant(1, 1, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInQuadrant_should_throw_if_y_is_below_0() {
        sut.isValuePresentInQuadrant(1, 1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isValuePresentInQuadrant_should_throw_if_y_is_over_8() {
        sut.isValuePresentInQuadrant(1, 1, 9);
    }

    @Test
    public void isValuePresentInQuadrant_should_return_false_when_value_is_null() {
        // Given

        // When
        boolean present = sut.isValuePresentInQuadrant(null, 1, 1);

        // Then
        assertThat(present).isFalse();
    }

    @Test
    public void isValuePresentInQuadrant() {
    }

    @Test
    public void isValuePresentInQuadrant_should_return_true_when_present() {
        // Given
        grid.getCells()[1][0][0] = CellStatus.FORBIDDEN;
        grid.getCells()[1][0][1] = CellStatus.FORBIDDEN;
        grid.getCells()[1][0][2] = CellStatus.FORBIDDEN;
        grid.getCells()[1][1][0] = CellStatus.ONE;
        grid.getCells()[1][1][1] = CellStatus.FORBIDDEN;
        grid.getCells()[1][1][2] = CellStatus.FORBIDDEN;
        grid.getCells()[1][2][0] = CellStatus.FORBIDDEN;
        grid.getCells()[1][2][1] = CellStatus.FORBIDDEN;
        grid.getCells()[1][2][2] = CellStatus.FORBIDDEN;

        // When
        boolean present = sut.isValuePresentInQuadrant(1, 0, 0);

        // Then
        assertThat(present).isTrue();
    }

    @Test
    public void isValuePresentInQuadrant_should_return_false_when_not_present() {
        // Given
        grid.getCells()[1][0][0] = CellStatus.OCCUPIED;
        grid.getCells()[1][0][1] = CellStatus.OCCUPIED;
        grid.getCells()[1][0][2] = CellStatus.OCCUPIED;
        grid.getCells()[1][1][0] = CellStatus.OCCUPIED;
        grid.getCells()[1][1][1] = CellStatus.OCCUPIED;
        grid.getCells()[1][1][2] = CellStatus.OCCUPIED;
        grid.getCells()[1][2][0] = CellStatus.OCCUPIED;
        grid.getCells()[1][2][1] = CellStatus.OCCUPIED;
        grid.getCells()[1][2][2] = CellStatus.OCCUPIED;

        // When
        boolean present = sut.isValuePresentInQuadrant(1, 0, 0);

        // Then
        assertThat(present).isFalse();
    }

    private void givenAStatusForCells(CellStatus status, int value, int x, int y) {
        for (int i = 0; i < 9; i++){
            int layer = value < 0 ? i : value;
            int row = x < 0 ? i : x;
            int column = y < 0 ? i : y;

            grid.getCells()[layer][row][column] = status;
        }
    }
}