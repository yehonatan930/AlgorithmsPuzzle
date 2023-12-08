import IO.TestResult;
import PuzzleClasses.BOARD_SIZES;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        TestResult testResult = new TestResult(1, BOARD_SIZES.FIFTEEN, 30);
        testResult.runTests();
        System.out.println();
        System.out.println(testResult);
    }
}