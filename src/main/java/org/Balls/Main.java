package org.Balls;
import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;


public class Main {
    public static void main(String[] args) {
        int boardWidth = 500, boardHeight = 500;
        int divider = 100;
        int ballSize = 30;
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board(boardWidth, boardHeight, ballSize, divider);
        frame.add(board);
        frame.pack();

    }
}