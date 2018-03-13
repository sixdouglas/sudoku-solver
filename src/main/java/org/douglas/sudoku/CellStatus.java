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

public enum CellStatus {
    EMPTY(null),
    OCCUPIED(Integer.MAX_VALUE),
    FORBIDDEN(Integer.MIN_VALUE),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    HEIGHT(8),
    NINE(9);

    private Integer value;

    private CellStatus(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }

    public static CellStatus getCellStatus(Integer value){
        switch(value){
            case 1:
                return ONE;
            case 2:
                return TWO;
            case 3:
                return THREE;
            case 4:
                return FOUR;
            case 5:
                return FIVE;
            case 6:
                return SIX;
            case 7:
                return SEVEN;
            case 8:
                return HEIGHT;
            case 9:
                return NINE;
            default:
                return EMPTY;
        }
    }
}
