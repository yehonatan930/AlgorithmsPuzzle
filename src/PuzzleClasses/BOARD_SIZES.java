package PuzzleClasses;

public enum BOARD_SIZES {
    A(4), B(5);

    private final int numVal;

    BOARD_SIZES(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
