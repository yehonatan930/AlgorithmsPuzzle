package PuzzleClasses;

public enum BOARD_SIZES {
    FIFTEEN(4), TWENTY_FOUR(5);

    private final int numVal;

    BOARD_SIZES(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
