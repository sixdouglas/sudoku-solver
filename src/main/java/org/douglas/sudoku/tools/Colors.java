package org.douglas.sudoku.tools;

public enum Colors {
    BLACK(30, 40),
    RED(31, 41),
    GREEN(32, 42),
    YELLOW(33, 43),
    BLUE(34, 44),
    MAGENTA(35, 45),
    CYAN(36, 46),
    WHITE(37, 47),
    DEFAULT(39, 49);

    private int foreground;
    private int background;

    Colors(int foreground, int background) {
        this.foreground = foreground;
        this.background = background;
    }

    public int getForeground() {
        return foreground;
    }

    public int getBackground() {
        return background;
    }
}
