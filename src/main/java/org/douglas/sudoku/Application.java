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

public class Application {

    private Grid grid;

    private Application(){
        this.grid = new Grid();
    }

    public static void main(String[] args) {
        System.out.println((char)27 + "[31mRED");
        System.out.println((char)27 + "[32mGREEN");
        System.out.println((char)27 + "[33mYELLOW");
        System.out.println((char)27 + "[34mBLUE");
        System.out.println((char)27 + "[35mMAGENTA");
        System.out.println((char)27 + "[36mCYAN");
        Application app = new Application();
//        app.displayGrid();
        app.initFirstGrid();
        System.out.println(app.grid.gridToString());
        app.grid.initGrid();
        app.initEasyGrid();
        System.out.println(app.grid.gridToString());
        System.out.println("Possible values for [1, 1]: " + app.grid.getCellPossibleValues(1, 1));
    }

    private void displayGrid() {
        System.out.println("grids: " + grid.getCells().length);
        System.out.println("grids[0]: " + grid.getCells()[0].length);
        System.out.println("grids[0][0]: " + grid.getCells()[0][0].length);
        System.out.println("grids[0][0][0]: " + grid.getCells()[0][0][0]);
    }

    private void initEasyGrid() {
        grid.setCellValue(0, 0, 5);
        grid.setCellValue(0, 1, 3);
        grid.setCellValue(0, 2, 4);
        grid.setCellValue(0, 3, 6);
        grid.setCellValue(0, 4, 7);
        grid.setCellValue(0, 5, 8);
        grid.setCellValue(0, 6, 9);
        grid.setCellValue(0, 7, 1);
        grid.setCellValue(0, 8, 2);

        grid.setCellValue(1, 0, 6);
//        grid.setCellValue(1, 1, 7);
        grid.setCellValue(1, 2, 2);
        grid.setCellValue(1, 3, 1);
        grid.setCellValue(1, 4, 9);
        grid.setCellValue(1, 5, 5);
        grid.setCellValue(1, 6, 3);
        grid.setCellValue(1, 7, 4);
        grid.setCellValue(1, 8, 8);

        grid.setCellValue(2, 0, 1);
        grid.setCellValue(2, 1, 9);
        grid.setCellValue(2, 2, 8);
        grid.setCellValue(2, 3, 3);
        grid.setCellValue(2, 4, 4);
        grid.setCellValue(2, 5, 2);
        grid.setCellValue(2, 6, 5);
        grid.setCellValue(2, 7, 6);
        grid.setCellValue(2, 8, 7);

        grid.setCellValue(3, 0, 8);
        grid.setCellValue(3, 1, 5);
        grid.setCellValue(3, 2, 9);
        grid.setCellValue(3, 3, 7);
        grid.setCellValue(3, 4, 6);
        grid.setCellValue(3, 5, 1);
        grid.setCellValue(3, 6, 4);
        grid.setCellValue(3, 7, 2);
        grid.setCellValue(3, 8, 3);

        grid.setCellValue(4, 0, 4);
        grid.setCellValue(4, 1, 2);
        grid.setCellValue(4, 2, 6);
        grid.setCellValue(4, 3, 8);
        grid.setCellValue(4, 4, 5);
        grid.setCellValue(4, 5, 3);
        grid.setCellValue(4, 6, 7);
        grid.setCellValue(4, 7, 9);
        grid.setCellValue(4, 8, 1);

        grid.setCellValue(5, 0, 7);
        grid.setCellValue(5, 1, 1);
        grid.setCellValue(5, 2, 3);
        grid.setCellValue(5, 3, 9);
        grid.setCellValue(5, 4, 2);
        grid.setCellValue(5, 5, 4);
        grid.setCellValue(5, 6, 8);
        grid.setCellValue(5, 7, 5);
        grid.setCellValue(5, 8, 6);

        grid.setCellValue(6, 0, 9);
        grid.setCellValue(6, 1, 6);
        grid.setCellValue(6, 2, 1);
        grid.setCellValue(6, 3, 5);
        grid.setCellValue(6, 4, 3);
        grid.setCellValue(6, 5, 7);
        grid.setCellValue(6, 6, 2);
        grid.setCellValue(6, 7, 8);
        grid.setCellValue(6, 8, 4);

        grid.setCellValue(7, 0, 2);
        grid.setCellValue(7, 1, 8);
        grid.setCellValue(7, 2, 7);
        grid.setCellValue(7, 3, 4);
        grid.setCellValue(7, 4, 1);
        grid.setCellValue(7, 5, 9);
        grid.setCellValue(7, 6, 6);
        grid.setCellValue(7, 7, 3);
        grid.setCellValue(7, 8, 5);

        grid.setCellValue(8, 0, 3);
        grid.setCellValue(8, 1, 4);
        grid.setCellValue(8, 2, 5);
        grid.setCellValue(8, 3, 2);
        grid.setCellValue(8, 4, 8);
        grid.setCellValue(8, 5, 6);
        grid.setCellValue(8, 6, 1);
        grid.setCellValue(8, 7, 7);
        grid.setCellValue(8, 8, 9);
    }

    private void initFirstGrid() {
        grid.setCellValue(0, 0, 8);
        grid.setCellValue(0, 2, 9);
        grid.setCellValue(0, 6, 7);
        grid.setCellValue(0, 8, 6);

        grid.setCellValue(1, 2, 3);
        grid.setCellValue(1, 3, 9);
        grid.setCellValue(1, 4, 7);
        grid.setCellValue(1, 5, 2);
        grid.setCellValue(1, 6, 1);

        grid.setCellValue(2, 0, 2);
        grid.setCellValue(2, 3, 8);
        grid.setCellValue(2, 5, 5);
        grid.setCellValue(2, 8, 4);

        grid.setCellValue(3, 3, 7);
        grid.setCellValue(3, 4, 1);
        grid.setCellValue(3, 5, 4);

        grid.setCellValue(4, 0, 1);
        grid.setCellValue(4, 1, 4);
        grid.setCellValue(4, 7, 9);
        grid.setCellValue(4, 8, 7);

        grid.setCellValue(5, 3, 5);
        grid.setCellValue(5, 4, 9);
        grid.setCellValue(5, 5, 8);

        grid.setCellValue(6, 2, 2);
        grid.setCellValue(6, 3, 3);
        grid.setCellValue(6, 5, 7);
        grid.setCellValue(6, 6, 6);

        grid.setCellValue(7, 2, 7);
        grid.setCellValue(7, 4, 4);
        grid.setCellValue(7, 6, 3);

        grid.setCellValue(8, 0, 3);
        grid.setCellValue(8, 2, 4);
        grid.setCellValue(8, 6, 8);
        grid.setCellValue(8, 8, 2);
    }
}
