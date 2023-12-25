package IO;

import Algorithms.Searches;
import PuzzleClasses.BOARD_SIZES;
import PuzzleClasses.Board;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    BOARD_SIZES boardSize;
    int numberOfMoves;
    List<Board> boards;

    List<DataPerRun> BFSDataPerRuns = new ArrayList<>();
    List<DataPerRun> DijkstraDataPerRuns = new ArrayList<>();
    List<DataPerRun> AStarManhattanDataPerRuns = new ArrayList<>();
    List<DataPerRun> AStarUnAdmissibleDataPerRuns = new ArrayList<>();

    public TestResult(int numberOfBoards, BOARD_SIZES boardSize, int numberOfMoves) {
        this.boardSize = boardSize;
        this.numberOfMoves = numberOfMoves;
        this.boards = this.generateBoardsFromIdeal(numberOfBoards, boardSize);
        System.out.println("Generated " + numberOfBoards + " boards of size " + boardSize);
    }

    private double getCurrentTimeFrom(long start) {
        return (double) (System.currentTimeMillis() - start) / 1000;
    }

    public void runTests() {
        Board idealBoard = Board.getIdealBoard(this.boardSize);
        long start = System.currentTimeMillis();

        List<Board> boardList = this.boards;
        for (int i = 0; i < boardList.size(); i++) {
            Board board = boardList.get(i);
            System.out.println("Starting board " + i);

            System.out.println("\tStarting BFS... ");

            DataPerRun BFSdataPerRun = Searches.BFS(board, idealBoard);
            this.addBFSDataPerRun(BFSdataPerRun);

            System.out.println("\tBFS done " + this.getCurrentTimeFrom(start));

            System.out.println("\tStarting Dijkstra... ");

            DataPerRun DijkistraDataPerRun = Searches.AStar(board, idealBoard, Searches.dijkstra);
            this.addDijkstraDataPerRun(DijkistraDataPerRun);

            System.out.println("\tDijkstra done " + this.getCurrentTimeFrom(start));

            System.out.println("\tStarting AStarManhattan... ");

            DataPerRun AStarManhattenDataPerRun = Searches.AStar(board, idealBoard, Searches.manhattan);
            this.addAStarManhattanDataPerRun(AStarManhattenDataPerRun);

            System.out.println("\tAStarManhattan done " + this.getCurrentTimeFrom(start));

            System.out.println("\tStarting AStarUnAdmissible... ");

            DataPerRun AStarUnAdmissibleDataPerRun = Searches.AStar(board, idealBoard, Searches.unAdmissible);
            this.addAStarUnAdmissibleDataPerRun(AStarUnAdmissibleDataPerRun);

            System.out.println("\tAStarUnAdmissible done " + this.getCurrentTimeFrom(start));
        }
    }

    public List<Board> generateBoardsFromIdeal(int numberOfBoards, BOARD_SIZES boardSize) {
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < numberOfBoards; i++) {
            Board board = Board.getIdealBoard(boardSize).moveRandomly(this.numberOfMoves);
            boards.add(board);
        }
        return boards;
    }

    public void addBFSDataPerRun(DataPerRun dataPerRun) {
        this.BFSDataPerRuns.add(dataPerRun);
    }

    public void addDijkstraDataPerRun(DataPerRun dataPerRun) {
        this.DijkstraDataPerRuns.add(dataPerRun);
    }

    public void addAStarManhattanDataPerRun(DataPerRun dataPerRun) {
        this.AStarManhattanDataPerRuns.add(dataPerRun);
    }

    public void addAStarUnAdmissibleDataPerRun(DataPerRun dataPerRun) {
        this.AStarUnAdmissibleDataPerRuns.add(dataPerRun);
    }

    public DataPerRun getBFSAverageDataPerRun() {
        long averageDuration = 0;
        int averageNumberOfVerticesDiscovered = 0;
        int averageRouteLength = 0;

        for (DataPerRun dataPerRun : this.BFSDataPerRuns) {
            averageDuration += dataPerRun.getDurationInMilliseconds();
            averageNumberOfVerticesDiscovered += dataPerRun.getNumberOfVerticesDiscovered();
            averageRouteLength += dataPerRun.getRouteLength();
        }

        averageDuration /= this.BFSDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.BFSDataPerRuns.size();
        averageRouteLength /= this.BFSDataPerRuns.size();

        return new DataPerRun(averageDuration, averageNumberOfVerticesDiscovered, averageRouteLength);
    }

    public DataPerRun getDijkistraAverageDataPerRun() {
        long averageDuration = 0;
        int averageNumberOfVerticesDiscovered = 0;
        int averageRouteLength = 0;

        for (DataPerRun dataPerRun : this.DijkstraDataPerRuns) {
            averageDuration += dataPerRun.getDurationInMilliseconds();
            averageNumberOfVerticesDiscovered += dataPerRun.getNumberOfVerticesDiscovered();
            averageRouteLength += dataPerRun.getRouteLength();
        }

        averageDuration /= this.DijkstraDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.DijkstraDataPerRuns.size();
        averageRouteLength /= this.DijkstraDataPerRuns.size();

        return new DataPerRun(averageDuration, averageNumberOfVerticesDiscovered, averageRouteLength);
    }

    public DataPerRun getAStarManhattenAverageDataPerRun() {
        long averageDuration = 0;
        int averageNumberOfVerticesDiscovered = 0;
        int averageRouteLength = 0;

        for (DataPerRun dataPerRun : this.AStarManhattanDataPerRuns) {
            averageDuration += dataPerRun.getDurationInMilliseconds();
            averageNumberOfVerticesDiscovered += dataPerRun.getNumberOfVerticesDiscovered();
            averageRouteLength += dataPerRun.getRouteLength();
        }

        averageDuration /= this.AStarManhattanDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.AStarManhattanDataPerRuns.size();
        averageRouteLength /= this.AStarManhattanDataPerRuns.size();

        return new DataPerRun(averageDuration, averageNumberOfVerticesDiscovered, averageRouteLength);
    }

    public DataPerRun getAStarUnAdmissibleAverageDataPerRun() {
        long averageDuration = 0;
        int averageNumberOfVerticesDiscovered = 0;
        int averageRouteLength = 0;

        for (DataPerRun dataPerRun : this.AStarUnAdmissibleDataPerRuns) {
            averageDuration += dataPerRun.getDurationInMilliseconds();
            averageNumberOfVerticesDiscovered += dataPerRun.getNumberOfVerticesDiscovered();
            averageRouteLength += dataPerRun.getRouteLength();
        }

        averageDuration /= this.AStarUnAdmissibleDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.AStarUnAdmissibleDataPerRuns.size();
        averageRouteLength /= this.AStarUnAdmissibleDataPerRuns.size();

        return new DataPerRun(averageDuration, averageNumberOfVerticesDiscovered, averageRouteLength);
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "\n\tboardSize= " + this.boardSize + ", " + this.numberOfMoves + " moves away from ideal Board" +
                "\n\t\tBFS Average DataPerRuns = " + this.getBFSAverageDataPerRun().toString() +
                "\n\t\tDijkstra Average DataPerRuns = " + this.getDijkistraAverageDataPerRun().toString() +
                "\n\t\tAStarManhattan Average DataPerRuns = " + this.getAStarManhattenAverageDataPerRun().toString() +
                "\n\t\tAStarUnAdmissible Average DataPerRuns = " + this.getAStarUnAdmissibleAverageDataPerRun().toString() +
                "\n}";
    }
}
