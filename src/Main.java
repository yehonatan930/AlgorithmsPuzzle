import IO.TestResult;
import PuzzleClasses.BOARD_SIZES;

public class Main {
    public static void main(String[] args) {
        TestResult testResult = new TestResult(1, BOARD_SIZES.FIFTEEN, 30);
        testResult.runTests();
        System.out.println();
        System.out.println(testResult);
    }
}