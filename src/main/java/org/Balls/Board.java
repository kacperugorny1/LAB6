package org.Balls;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.List; // Import java.util.List for type parameters
import java.util.concurrent.atomic.AtomicInteger;

public class Board extends JPanel implements ActionListener{
    Random rand = new Random();
    long simTick = 0;
    double g = 0.1;
    int boardWidth, boardHeight;
    int ballSize;
    ArrayList<Ball> balls = new ArrayList<>();
    int i = 0;
    Timer gameLoop;
    Color[] colors = new Color[9];
    double totalVelocity = 0;
    ArrayList<Runnable> tasks = new ArrayList<>();
    double divider = 0;

    List<Ball> sublist1 = new ArrayList<>();
    List<Ball> sublist2 = new ArrayList<>();
    List<Ball> sublist3 = new ArrayList<>();
    List<Ball> sublist4 = new ArrayList<>();


    public Board(int boardWidth, int boardHeight, int ballSize, double divider) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.ballSize = ballSize;
        this.divider = divider;
        colors[0] = Color.MAGENTA;
        colors[1] = Color.GREEN;
        colors[2] = Color.BLUE;
        colors[3] = Color.RED;
        colors[4] = Color.YELLOW;
        colors[5] = Color.CYAN;
        colors[6] = Color.PINK;
        colors[7] = Color.GRAY;
        colors[8] = Color.BLACK;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX();
                    int y = e.getY();
                    Ball ball = new Ball(ballSize/2,x,y, colors[i++],rand.nextInt(-5,5),rand.nextInt(-5,5));
                    balls.add(ball);
                    if(balls.size()%4 == 0) sublist1.add(ball);
                    else if(balls.size()%4 == 1) sublist2.add(ball);
                    else if(balls.size()%4 == 2) sublist3.add(ball);
                    else sublist4.add(ball);
                    i = i % 9;
                }
            }
        });
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.LIGHT_GRAY);

        tasks.add(() -> {
            sublist1.forEach(ball -> ball.moveBall(boardWidth,boardHeight,1/divider));
        });
        tasks.add(() -> {
            sublist2.forEach(ball -> ball.moveBall(boardWidth,boardHeight,1/divider));
        });
        tasks.add(() -> {
            sublist3.forEach(ball -> ball.moveBall(boardWidth,boardHeight,1/divider));
        });
        tasks.add(() -> {
            sublist4.forEach(ball -> ball.moveBall(boardWidth,boardHeight,1/divider));
        });


        gameLoop = new Timer(0, this);
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        for (Ball ball : balls) {
            g.setColor(ball.getColor());
            g.fillOval(ball.getBallX() - ball.getBallRadius(),ball.getBallY() - ball.getBallRadius(),ball.getBallRadius()*2,ball.getBallRadius()*2);
        }
        g.setColor(Color.BLACK);
        g.drawString("n = " + (long) balls.size() + " velocity: " + totalVelocity, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        simTick++;
        totalVelocity = 0;
//        for(int i = 0; i < divider; ++i){
//            balls.forEach(u ->{
//                u.moveBall(boardWidth,boardHeight,1/divider);
//            });
//            Ball.collisions(boardWidth,boardHeight, balls);
//        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        AtomicInteger completedTasks = new AtomicInteger(0);
        for(int i = 0; i < divider; ++i){
            completedTasks.set(0);
            for (Runnable task : tasks) {
                executorService.execute(()->{
                    try{
                        task.run();
                    }
                    finally{
                        completedTasks.incrementAndGet();
                    }
                });
            }

            while (completedTasks.get() < tasks.size());
            Ball.collisions(boardWidth,boardHeight, balls);
        }


        balls.forEach(u ->{
            u.ballSpeedY += g;
            totalVelocity += Math.sqrt(Math.pow(u.ballSpeedY,2) + Math.pow(u.ballSpeedX,2));
        });
        repaint();
    }

}

