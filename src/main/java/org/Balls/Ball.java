package org.Balls;

import java.awt.*;
import java.util.ArrayList;
import java.lang.Math;


public class Ball {
    private final Color color;
    private int ballRadius;
    private double ballX;
    private double ballY;
    public double ballSpeedX;
    public double ballSpeedY;
    public Ball(int ballRadius, int ballX, int ballY, Color color, int ballSpeedX, int ballSpeedY) {
        this.ballRadius = ballRadius;
        this.ballX = ballX;
        this.ballY = ballY;
        this.color = color;
        this.ballSpeedX = ballSpeedX;
        this.ballSpeedY = ballSpeedY;
    }
    public int getBallRadius() {
        return ballRadius;
    }
    public int getBallX() {
        return (int)ballX;
    }
    public int getBallY() {
        return (int)ballY;
    }
    public Color getColor() {
        return color;
    }
    public void moveBall(int boardWidth, int boardHeight, double multiplier){
        ballX += ballSpeedX*multiplier;
        ballY += ballSpeedY*multiplier;
    }
    public static void collisions(int boardWidth, int boardHeight, ArrayList<Ball> balls){

        for (Ball ball : balls) {
            if(ball.ballX - ball.ballRadius < 0) {
                ball.ballX = -ball.ballX + ball.ballRadius + ball.ballRadius;
                ball.ballSpeedX = -ball.ballSpeedX;
            }
            else if (ball.ballX + ball.ballRadius > boardWidth) {
                ball.ballX = boardWidth - (boardWidth + ball.ballRadius - boardWidth);
                ball.ballSpeedX = -ball.ballSpeedX;
            }
            if(ball.ballY - ball.ballRadius < 0) {
                ball.ballY = - ball.ballY + ball.ballRadius + ball.ballRadius;
                ball.ballSpeedY = -ball.ballSpeedY;
            }
            else if(ball.ballY + ball.ballRadius > boardHeight) {
                ball.ballY =  boardHeight - (ball.ballY + ball.ballRadius - boardHeight);
                ball.ballSpeedY = -ball.ballSpeedY;
            }

            for (Ball ball1 : balls) {
                Vector v1 = new Vector(ball.ballSpeedX, ball.ballSpeedY)
                        ,v2 = new Vector(ball1.ballSpeedX, ball1.ballSpeedY);
                Vector x1 = new Vector(ball.ballX, ball.ballY);
                Vector x2 = new Vector(ball1.ballX, ball1.ballY);
                int phi;
                if(ball == ball1) continue;
//                if(ball.ballX + ball.ballRadius < ball1.ballX - ball1.ballRadius)
//                    continue;
//                if(ball.ballX - ball.ballRadius > ball1.ballX + ball1.ballRadius)
//                    continue;
//                if(ball.ballY + ball.ballRadius < ball1.ballY - ball1.ballRadius)
//                    continue;
//                if(ball.ballY - ball.ballRadius > ball1.ballY + ball1.ballRadius)
//                    continue;
                if(Math.sqrt(x1.subtract(x2).normSquare()) > 2*ball.ballRadius)
                    continue;
                System.console().printf("KOLIZJA \n");
                //https://en.wikipedia.org/wiki/Elastic_collision
                Vector v1p = v1.subtract(x1.subtract(x2).multiply(v1.subtract(v2).scalarMultiplication(x1.subtract(x2))).divide(x1.subtract(x2).normSquare()));
                Vector v2p = v2.subtract(x2.subtract(x1).multiply(v2.subtract(v1).scalarMultiplication(x2.subtract(x1))).divide(x2.subtract(x1).normSquare()));
                ball.ballSpeedX = v1p.x;
                ball.ballSpeedY = v1p.y;
                ball1.ballSpeedX = v2p.x;
                ball1.ballSpeedY = v2p.y;
                ball.ballX += (int) v1p.x;
                ball.ballY += (int) v1p.y;
                ball1.ballX += (int) v2p.x;
                ball1.ballY += (int) v2p.y;

            }

        }
    }
    public static void collision(int boardWidth, int boardHeight, ArrayList<Ball> balls, Ball ball){


        if(ball.ballX - ball.ballRadius < 0) {
            ball.ballX = -ball.ballX + ball.ballRadius + ball.ballRadius;
            ball.ballSpeedX = -ball.ballSpeedX;
        }
        else if (ball.ballX + ball.ballRadius > boardWidth) {
            ball.ballX = boardWidth - (boardWidth + ball.ballRadius - boardWidth);
            ball.ballSpeedX = -ball.ballSpeedX;
        }
        if(ball.ballY - ball.ballRadius < 0) {
            ball.ballY = - ball.ballY + ball.ballRadius + ball.ballRadius;
            ball.ballSpeedY = -ball.ballSpeedY;
        }
        else if(ball.ballY + ball.ballRadius > boardHeight) {
            ball.ballY =  boardHeight - (ball.ballY + ball.ballRadius - boardHeight);
            ball.ballSpeedY = -ball.ballSpeedY;
        }

        for (Ball ball1 : balls) {
            Vector v1 = new Vector(ball.ballSpeedX, ball.ballSpeedY)
                    ,v2 = new Vector(ball1.ballSpeedX, ball1.ballSpeedY);
            Vector x1 = new Vector(ball.ballX, ball.ballY);
            Vector x2 = new Vector(ball1.ballX, ball1.ballY);
            int phi;
            if(ball == ball1) continue;
//                if(ball.ballX + ball.ballRadius < ball1.ballX - ball1.ballRadius)
//                    continue;
//                if(ball.ballX - ball.ballRadius > ball1.ballX + ball1.ballRadius)
//                    continue;
//                if(ball.ballY + ball.ballRadius < ball1.ballY - ball1.ballRadius)
//                    continue;
//                if(ball.ballY - ball.ballRadius > ball1.ballY + ball1.ballRadius)
//                    continue;
            if(Math.sqrt(x1.subtract(x2).normSquare()) > 2*ball.ballRadius)
                continue;
            System.console().printf("KOLIZJA \n");
            Vector v1p = v1.subtract(x1.subtract(x2).multiply(v1.subtract(v2).scalarMultiplication(x1.subtract(x2))).divide(x1.subtract(x2).normSquare()));
            Vector v2p = v2.subtract(x2.subtract(x1).multiply(v2.subtract(v1).scalarMultiplication(x2.subtract(x1))).divide(x2.subtract(x1).normSquare()));
            ball.ballSpeedX = v1p.x;
            ball.ballSpeedY = v1p.y;
            ball1.ballSpeedX = v2p.x;
            ball1.ballSpeedY = v2p.y;
            ball.ballX += (int) v1p.x;
            ball.ballY += (int) v1p.y;
            ball1.ballX += (int) v2p.x;
            ball1.ballY += (int) v2p.y;



        }
    }

}
