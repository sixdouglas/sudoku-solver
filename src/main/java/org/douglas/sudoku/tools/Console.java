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

package org.douglas.sudoku.tools;

/**
 * @author Douglas SIX
 */
public class Console {

    private static final String PRE_FORMATED = (char) 27 + "[%dm%s" + (char) 27 + "[%dm";

    /*
    *
        +~~~~~~+~~~~~~+~~~~~~~~~~~+
        |  fg  |  bg  |  color    |
        +~~~~~~+~~~~~~+~~~~~~~~~~~+
        |  30  |  40  |  black    |
        |  31  |  41  |  red      |
        |  32  |  42  |  green    |
        |  33  |  43  |  yellow   |
        |  34  |  44  |  blue     |
        |  35  |  45  |  magenta  |
        |  36  |  46  |  cyan     |
        |  37  |  47  |  white    |
        |  39  |  49  |  default  |
        +~~~~~~+~~~~~~+~~~~~~~~~~~+
    *
    * */

    public static String getColoredString(String value, Colors color) {
        return String.format(PRE_FORMATED, color.getForeground(), value, Colors.DEFAULT.getBackground());
    }

}
