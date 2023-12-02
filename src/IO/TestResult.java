package IO;

import Algorithms.Searches;
import PuzzleClasses.BOARD_SIZES;
import PuzzleClasses.Board;

import java.util.ArrayList;
import java.util.List;

public class TestResult {
    final static int NUMBER_OF_MOVES = 5000;
    BOARD_SIZES boardSize;
    List<Board> boards;

    List<DataPerRun> BFSDataPerRuns = new ArrayList<DataPerRun>();
    List<DataPerRun> DijkistraDataPerRuns = new ArrayList<DataPerRun>();
    List<DataPerRun> AStarManhattenDataPerRuns = new ArrayList<DataPerRun>();
    List<DataPerRun> AStarBadDataPerRuns = new ArrayList<DataPerRun>();

    public TestResult(int numberOfBoards, BOARD_SIZES boardSize) {
        this.boardSize = boardSize;
        this.boards = generateBoards(numberOfBoards, boardSize);
    }

    public void runTests() {
        for (Board board : this.boards) {
            DataPerRun BFSdataPerRun = Searches.BFS(board, Board.getIdealBoard(this.boardSize));
            this.addBFSDataPerRun(BFSdataPerRun);

            DataPerRun DijkistraDataPerRun = Searches.AStar(board, Board.getIdealBoard(this.boardSize), Searches.dijkstra);
            this.addDijkistraDataPerRun(DijkistraDataPerRun);

            DataPerRun AStarManhattenDataPerRun = Searches.AStar(board, Board.getIdealBoard(this.boardSize), Searches.manhattan);
            this.addAStarManhattenDataPerRun(AStarManhattenDataPerRun);

            DataPerRun AStarBadDataPerRun = Searches.AStar(board, Board.getIdealBoard(this.boardSize), Searches.bad);
            this.addAStarBadDataPerRun(AStarBadDataPerRun);
        }
    }

    public List<Board> generateBoards(int numberOfBoards, BOARD_SIZES boardSize) {
        List<Board> boards = new ArrayList<Board>();
        for (int i = 0; i < numberOfBoards; i++) {
            Board board = Board.getIdealBoard(boardSize).moveRandomly(NUMBER_OF_MOVES);
            boards.add(board);
        }
        return boards;
    }

    public void addBFSDataPerRun(DataPerRun dataPerRun) {
        this.BFSDataPerRuns.add(dataPerRun);
    }

    public void addDijkistraDataPerRun(DataPerRun dataPerRun) {
        this.DijkistraDataPerRuns.add(dataPerRun);
    }

    public void addAStarManhattenDataPerRun(DataPerRun dataPerRun) {
        this.AStarManhattenDataPerRuns.add(dataPerRun);
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

        for (DataPerRun dataPerRun : this.DijkistraDataPerRuns) {
            averageDuration += dataPerRun.durationInMilliseconds;
            averageNumberOfVerticesDiscovered += dataPerRun.numberOfVerticesDiscovered;
            averageRouteLength += dataPerRun.routeLength;
        }

        averageDuration /= this.DijkistraDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.DijkistraDataPerRuns.size();
        averageRouteLength /= this.DijkistraDataPerRuns.size();

        return new DataPerRun(averageDuration, averageNumberOfVerticesDiscovered, averageRouteLength);
    }

    public DataPerRun getAStarManhattenAverageDataPerRun() {
        long averageDuration = 0;
        int averageNumberOfVerticesDiscovered = 0;
        int averageRouteLength = 0;

        for (DataPerRun dataPerRun : this.AStarManhattenDataPerRuns) {
            averageDuration += dataPerRun.durationInMilliseconds;
            averageNumberOfVerticesDiscovered += dataPerRun.numberOfVerticesDiscovered;
            averageRouteLength += dataPerRun.routeLength;
        }

        averageDuration /= this.AStarManhattenDataPerRuns.size();
        averageNumberOfVerticesDiscovered /= this.AStarManhattenDataPerRuns.size();
        averageRouteLength /= this.AStarManhattenDataPerRuns.size();

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
}
