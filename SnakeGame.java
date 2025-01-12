import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new GamePanel());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

    class GamePanel extends JPanel implements ActionListener {

        private final int TILE_SIZE = 30;
        private final int WIDTH = 20;
        private final int HEIGHT = 20;
        private final int GAME_UNITS = WIDTH * HEIGHT;
        private final int DELAY = 150;

        private int[] x = new int[GAME_UNITS];
        private int[] y = new int[GAME_UNITS];
        private int bodyParts = 3;
        private int applesEaten;
        private int appleX;
        private int appleY;
        private char direction = 'R';
        private boolean running = false;
        private Timer timer;
        private Random random;

        public GamePanel() {
            setPreferredSize(new Dimension(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE));
            setBackground(Color.BLACK);
            setFocusable(true);
            addKeyListener(new MyKeyAdapter());
            random = new Random();
            startGame();
        }

        private void startGame() {
            spawnApple();
            running = true;
            timer = new Timer(DELAY, this);
            timer.start();
        }

        private void spawnApple() {
            appleX = random.nextInt(WIDTH) * TILE_SIZE;
            appleY = random.nextInt(HEIGHT) * TILE_SIZE;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            draw(g);
        }

        private void draw(Graphics g) {
            if (running) {
                g.setColor(Color.RED);
                g.fillOval(appleX, appleY, TILE_SIZE, TILE_SIZE);

                for (int i = 0; i < bodyParts; i++) {
                    if (i == 0) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(Color.YELLOW);
                    }
                    g.fillRect(x[i], y[i], TILE_SIZE, TILE_SIZE);
                }

                g.setColor(Color.WHITE);
                g.setFont(new Font("Ink Free", Font.BOLD, 20));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: " + applesEaten, (WIDTH * TILE_SIZE - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
            } else {
                gameOver(g);
            }
        }

        private void move() {
            for (int i = bodyParts; i > 0; i--) {
                x[i] = x[i - 1];
                y[i] = y[i - 1];
            }

            switch (direction) {
                case 'U':
                    y[0] -= TILE_SIZE;
                    break;
                case 'D':
                    y[0] += TILE_SIZE;
                    break;
                case 'L':
                    x[0] -= TILE_SIZE;
                    break;
                case 'R':
                    x[0] += TILE_SIZE;
                    break;
            }
        }

        private void checkApple() {
            if (x[0] == appleX && y[0] == appleY) {
                bodyParts++;
                applesEaten++;
                spawnApple();
            }
        }

        private void checkCollisions() {
            for (int i = bodyParts; i > 0; i--) {
                if (x[0] == x[i] && y[0] == y[i]) {
                    running = false;
                }
            }

            if (x[0] < 0 || x[0] >= WIDTH * TILE_SIZE || y[0] < 0 || y[0] >= HEIGHT * TILE_SIZE) {
                running = false;
            }

            if (!running) {
                timer.stop();
            }
        }

        private void gameOver(Graphics g) {
            g.setColor(Color.RED);
            g.setFont(new Font("Ink Free", Font.BOLD, 50));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Game Over", (WIDTH * TILE_SIZE - metrics.stringWidth("Game Over")) / 2, HEIGHT * TILE_SIZE / 2);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Ink Free", Font.BOLD, 20));
            metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (WIDTH * TILE_SIZE - metrics.stringWidth("Score: " + applesEaten)) / 2, HEIGHT * TILE_SIZE / 2 + 30);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (running) {
                move();
                checkApple();
                checkCollisions();
            }
            repaint();
        }

        private class MyKeyAdapter extends KeyAdapter {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') {
                            direction = 'L';
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') {
                            direction = 'R';
                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') {
                            direction = 'U';
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') {
                            direction = 'D';
                        }
                        break;
                }
            }
        }
    }
}
