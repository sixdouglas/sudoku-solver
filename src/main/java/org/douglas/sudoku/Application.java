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

package org.douglas.sudoku;

import lombok.extern.log4j.Log4j2;
import org.douglas.sudoku.gui.MainGui;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

/**
 * @author Douglas SIX
 */
@Log4j2
public final class Application implements Runnable {

    private Application(){
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    @Override
    public void run() {
        invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new MainGui();
        frame.setVisible(true);
    }
}
