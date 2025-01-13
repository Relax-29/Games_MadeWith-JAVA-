public class FractalTree {
    private static final int TREE_HEIGHT = 8; // Depth of recursion

    // Generate the fractal tree
    public void generateTree(int depth, int x, int y, int direction, char[][] canvas) {
        if (depth == 0) return;

        // Calculate branch length based on depth
        int branchLength = depth + 2;

        // Calculate the new end of the branch
        int dx = (int) (Math.cos(Math.toRadians(direction)) * branchLength);
        int dy = (int) (Math.sin(Math.toRadians(direction)) * branchLength);

        // Draw the branch
        drawBranch(x, y, x + dx, y + dy, canvas);

        // Recursively generate left and right branches
        generateTree(depth - 1, x + dx, y + dy, direction - 30, canvas); // Left branch
        generateTree(depth - 1, x + dx, y + dy, direction + 30, canvas); // Right branch
    }

    // Draw a line (branch) on the canvas
    private void drawBranch(int x1, int y1, int x2, int y2, char[][] canvas) {
        int dx = x2 - x1, dy = y2 - y1;
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        double xInc = (double) dx / steps, yInc = (double) dy / steps;

        double x = x1, y = y1;
        for (int i = 0; i <= steps; i++) {
            int xi = (int) Math.round(x);
            int yi = (int) Math.round(y);
            if (xi >= 0 && xi < canvas[0].length && yi >= 0 && yi < canvas.length) {
                canvas[yi][xi] = '*'; // Draw the branch point
            }
            x += xInc;
            y += yInc;
        }
    }

    // Display the fractal tree canvas
    public void displayCanvas(char[][] canvas) {
        for (char[] row : canvas) {
            for (char cell : row) {
                System.out.print(cell == '\0' ? ' ' : cell);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        final int canvasWidth = 80;
        final int canvasHeight = 40;

        FractalTree tree = new FractalTree();
        char[][] canvas = new char[canvasHeight][canvasWidth];

        // Generate the tree
        tree.generateTree(TREE_HEIGHT, canvasWidth / 2, canvasHeight - 1, -90, canvas);

        // Display the result
        tree.displayCanvas(canvas);
    }
}
