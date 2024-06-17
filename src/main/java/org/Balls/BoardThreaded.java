//package org.Balls;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.util.ArrayList;
//import java.util.Random;
//
//public class Board extends JPanel {
//    Random rand = new Random();
//    long simTick = 0;
//    double g = 0.1;
//    int boardWidth, boardHeight;
//    int ballSize;
//    ArrayList<Ball> balls = new ArrayList<>();
//    ArrayList<BallThread> ballThreads = new ArrayList<>();
//    int i = 0;
//    Timer gameLoop;
//    Color[] colors = new Color[9];
//    double totalVelocity = 0;
//
//    public Board(int boardWidth, int boardHeight, int ballSize) {
//        this.boardWidth = boardWidth;
//        this.boardHeight = boardHeight;
//        this.ballSize = ballSize;
//        colors[0] = Color.MAGENTA;
//        colors[1] = Color.GREEN;
//        colors[2] = Color.BLUE;
//        colors[3] = Color.RED;
//        colors[4] = Color.YELLOW;
//        colors[5] = Color.CYAN;
//        colors[6] = Color.PINK;
//        colors[7] = Color.GRAY;
//        colors[8] = Color.BLACK;
//
//        this.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                if (e.getButton() == MouseEvent.BUTTON1) {
//                    int x = e.getX();
//                    int y = e.getY();
//                    Ball newBall = new Ball(10, x, y, colors[i++], rand.nextInt(-5, 5), rand.nextInt(-5, 5));
//                    balls.add(newBall);
//                    BallThread ballThread = new BallThread(newBall);
//                    ballThreads.add(ballThread);
//                    ballThread.start();
//                    i = i % 9;
//                }
//            }
//        });
//
//        setPreferredSize(new Dimension(boardWidth, boardHeight));
//        setBackground(Color.LIGHT_GRAY);
//
//        gameLoop = new Timer(1, e -> {
//            Ball.collisions(boardWidth, boardHeight, balls);
//            repaint();
//        });
//        gameLoop.start();
//    }
//
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        draw(g);
//    }
//
//    public void draw(Graphics g) {
//        for (Ball ball : balls) {
//            g.setColor(ball.getColor());
//            g.fillOval(ball.getBallX() - ball.getBallRadius(), ball.getBallY() - ball.getBallRadius(), ball.getBallRadius() * 2, ball.getBallRadius() * 2);
//        }
//        g.setColor(Color.BLACK);
//        g.drawString("n = " + (long) balls.size() + " velocity: " + totalVelocity, 20, 20);
//    }
//
//    class BallThread extends Thread {
//        private Ball ball;
//
//        public BallThread(Ball ball) {
//            this.ball = ball;
//        }
//
//        @Override
//        public void run() {
//            while (true) {
//                ball.moveBall(boardWidth, boardHeight, 0.1);
//                ball.ballSpeedY += g;
//
//                synchronized (Board.this) {
//                    totalVelocity = 0;
//                    for (Ball b : balls) {
//                        totalVelocity += Math.sqrt(Math.pow(b.ballSpeedY, 2) + Math.pow(b.ballSpeedX, 2));
//                    }
//                }
//
//                try {
//                    Thread.sleep(2); // Adjust the sleep time to control the speed of the ball updates
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
