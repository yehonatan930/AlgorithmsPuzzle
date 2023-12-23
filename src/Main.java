import IO.TestResult;
import PuzzleClasses.BOARD_SIZES;

public class Main {
    public static void main(String[] args) {
        TestResult testResult = new TestResult(50, BOARD_SIZES.FIFTEEN, 10);
        testResult.runTests();
        System.out.println();
        System.out.println(testResult);
    }
}