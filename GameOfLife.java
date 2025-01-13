import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameOfLife {
    private static final int GRID_SIZE = 20;
    private static final int THREAD_COUNT = 4;
    private static final int GENERATIONS = 10;

    private int[][] grid = new int[GRID_SIZE][GRID_SIZE];


    public void initializeGrid() {
        Random random = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = random.nextInt(2);
            }
        }
    }


    public void printGrid() {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "■ " : "□ ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void updateGrid(int startRow, int endRow, int[][] nextGrid) {
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                int aliveNeighbors = countAliveNeighbors(i, j);
                if (grid[i][j] == 1) {
                    nextGrid[i][j] = (aliveNeighbors == 2 || aliveNeighbors == 3) ? 1 : 0;
                } else {
                    nextGrid[i][j] = (aliveNeighbors == 3) ? 1 : 0;
                }
            }
        }
    }


    private int countAliveNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nx = x + i, ny = y + j;
                if (nx >= 0 && nx < GRID_SIZE && ny >= 0 && ny < GRID_SIZE) {
                    count += grid[nx][ny];
                }
            }
        }
        return count;
    }


    public void simulate() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int generation = 1; generation <= GENERATIONS; generation++) {
            System.out.println("Generation " + generation + ":");
            printGrid();

            int[][] nextGrid = new int[GRID_SIZE][GRID_SIZE];
            int rowsPerThread = GRID_SIZE / THREAD_COUNT;

            for (int t = 0; t < THREAD_COUNT; t++) {
                int startRow = t * rowsPerThread;
                int endRow = (t == THREAD_COUNT - 1) ? GRID_SIZE : startRow + rowsPerThread;

                int finalStartRow = startRow;
                int finalEndRow = endRow;

                executor.submit(() -> updateGrid(finalStartRow, finalEndRow, nextGrid));
            }

            executor.shutdown();
            while (!executor.isTerminated()) {
                Thread.yield();
            }

            grid = nextGrid; // Update the grid for the next generation
        }
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife();
        game.initializeGrid();
        game.simulate();
    }
}
