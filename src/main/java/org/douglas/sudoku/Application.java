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

    public static final String LINE_SEPARATOR = "+-+-+-+-+-+-+-+-+-+\n";
    private static Application app;

    private Grid grid;

    private Application(){
        this.grid = new Grid();
    }

    public static void main(String[] args) {
        app = new Application();
        app.displayGrid();
        app.initGrid();
        System.out.println(app.gridToString());
    }

    private void displayGrid() {
        System.out.println("grids: " + grid.getCells().length);
        System.out.println("grids[0]: " + grid.getCells()[0].length);
        System.out.println("grids[0][0]: " + grid.getCells()[0][0].length);
        System.out.println("grids[0][0][0]: " + grid.getCells()[0][0][0]);
    }

    private void initGrid() {
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

    private String gridToString() {
        StringBuilder sb = new StringBuilder(LINE_SEPARATOR);

        for (int x = 0; x < 9; x++){
            for (int y = 0; y < 9; y++) {
                sb.append("+").append(grid.getCellValue(x, y));
            }
            sb.append("+\n").append(LINE_SEPARATOR);
        }

        return sb.toString();
    }
}
