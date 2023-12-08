package IO;

import Algorithms.Searches;
import PuzzleClasses.BOARD_SIZES;
import PuzzleClasses.Board;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    final int NUMBER_OF_MOVES = 25;
    BOARD_SIZES boardSize;
    List<Board> boards;

    List<DataPerRun> BFSDataPerRuns = new ArrayList<>();
    List<DataPerRun> DijkstraDataPerRuns = new ArrayList<>();
    List<DataPerRun> AStarManhattanDataPerRuns = new ArrayList<>();
    List<DataPerRun> AStarBadDataPerRuns = new ArrayList<>();

    public TestResult(int numberOfBoards, BOARD_SIZES boardSize) {
        this.boardSize = boardSize;
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

            System.out.println("\tStarting BFS... " + this.getCurrentTimeFrom(start));

            DataPerRun BFSdataPerRun = Searches.BFS(board, idealBoard);
            this.addBFSDataPerRun(BFSdataPerRun);

            System.out.println("\tBFS done " + this.getCurrentTimeFrom(start));

            System.out.println("\tStarting Dijkstra... " + this.getCurrentTimeFrom(start));

            DataPerRun DijkistraDataPerRun = Searches.AStar(board, idealBoard, Searches.dijkstra);
            this.addDijkstraDataPerRun(DijkistraDataPerRun);

            System.out.println("\tDijkstra done " + this.getCurrentTimeFrom(start));

            System.out.println("\tStarting AStarManhattan... " + this.getCurrentTimeFrom(start));

            DataPerRun AStarManhattenDataPerRun = Searches.AStar(board, idealBoard, Searches.manhattan);
            this.addAStarManhattanDataPerRun(AStarManhattenDataPerRun);

            System.out.println("\tAStarManhattan done " + this.getCurrentTimeFrom(start));

            System.out.println("\tStarting AStarBad... " + this.getCurrentTimeFrom(start));

            DataPerRun AStarBadDataPerRun = Searches.AStar(board, idealBoard, Searches.bad);
            this.addAStarBadDataPerRun(AStarBadDataPerRun);

            System.out.println("\tAStarBad done " + this.getCurrentTimeFrom(start));
        }
    }

    public List<Board> generateBoardsFromIdeal(int numberOfBoards, BOARD_SIZES boardSize) {
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < numberOfBoards; i++) {
            Board board = Board.getIdealBoard(boardSize).moveRandomly(this.NUMBER_OF_MOVES);
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

    public void addAStarBadDataPerRun(DataPerRun dataPerRun) {
        this.AStarBadDataPerRuns.add(dataPerRun);
    }

    public DataPerRun getBFSAverageDataPerRun() {
        long averageDuration = 0;
        int averageNumberOfVerticesDiscovered = 0;
        int averageRouteLength = 0;

        for (DataPerRun dataPerRun : this.BFSDataPerRuns) {
            averageDuration += dataPerRun.durationInMilliseconds;
            averageNumberOfVerticesDiscovered += dataPerRun.numberOfVerticesDiscovered;
            averageRouteLength += dataPerRun.routeLength;
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
            averageDuration += dataPerRun.durationInMilliseconds;
            averageNumberOfVerticesDiscovered += dataPerRun.numberOfVerticesDiscovered;
            averageRouteLength += dataPerRun.routeLength;
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
            averageDuration += dataPerRun.durationInMilliseconds;
            averageNumberOfVerticesDiscovered += dataPerRun.numberOfVerticesDiscovered;
            averageRouteLength += dataPerRun.routeLength;
        }

        averageDuration /= this.AStarManhattanDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.AStarManhattanDataPerRuns.size();
        averageRouteLength /= this.AStarManhattanDataPerRuns.size();

        return new DataPerRun(averageDuration, averageNumberOfVerticesDiscovered, averageRouteLength);
    }

    public DataPerRun getAStarBadAverageDataPerRun() {
        long averageDuration = 0;
        int averageNumberOfVerticesDiscovered = 0;
        int averageRouteLength = 0;

        for (DataPerRun dataPerRun : this.AStarBadDataPerRuns) {
            averageDuration += dataPerRun.durationInMilliseconds;
            averageNumberOfVerticesDiscovered += dataPerRun.numberOfVerticesDiscovered;
            averageRouteLength += dataPerRun.routeLength;
        }

        averageDuration /= this.AStarBadDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.AStarBadDataPerRuns.size();
        averageRouteLength /= this.AStarBadDataPerRuns.size();

        return new DataPerRun(averageDuration, averageNumberOfVerticesDiscovered, averageRouteLength);
    }

    @Override
    public String toString() {
        return "TestResult{" +
                "\n\tboardSize= " + this.boardSize + ", " + this.NUMBER_OF_MOVES + " moves away from ideal Board" +
                "\n\t\tBFS Average DataPerRuns=" + this.getBFSAverageDataPerRun().toString() +
                "\n\t\tDijkstra Average DataPerRuns=" + this.getDijkistraAverageDataPerRun().toString() +
                "\n\t\tAStarManhattan Average DataPerRuns=" + this.getAStarManhattenAverageDataPerRun().toString() +
                "\n\t\tAStarBad Average DataPerRuns=" + this.getAStarBadAverageDataPerRun().toString() +
                "\n}";
    }
}
