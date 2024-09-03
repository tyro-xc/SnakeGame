package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame {
    private Snake snake;
    private JPanel jPanel;
    private Node food;

    public MainFrame() {
        initFrame();
        initGamePanel();
        initSnake();
        initTimer();
        setKeyListener();
        initFood();

    }

    private void initFood() {
        food = new Node();
        food.random();
    }

    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                        }
                        break;
                }
            }
        });
    }

    private void initTimer() {
        Timer timer = new Timer();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()== KeyEvent.VK_SPACE) {
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            snake.move();
                            Node head = snake.getBody().getFirst();
                            if (head.getX()==food.getX()&& head.getY()==food.getY()) {
                                snake.eat(food);
                                food.random();
                            }

                            jPanel.repaint();
                        }
                    };
                    timer.scheduleAtFixedRate(timerTask, 50, 80);
                }
                super.keyReleased(e);
            }
        });


    }

    private void initSnake() throws HeadlessException {
        snake = new Snake();
    }

    private void initGamePanel() {
        jPanel = new JPanel() {

            @Override
            public void paint(Graphics g) {
                super.paint(g);
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(0, i * 15, 600, i * 15);
                }
                for (int i = 0; i <= 40; i++) {
                    g.drawLine(i * 15, 0, i * 15, 600);
                }
                LinkedList<Node> body = snake.getBody();
                for (Node node : body) {
                    g.fillRect(node.getX() * 15, node.getY() * 15, 15, 15);
                }
                //g.drawString("Game Start", 11, 20);
                g.fillRect(food.getX() * 15, food.getY() * 15, 15, 15);
                if (!snake.isLiving()) {
                    g.drawString("Snake Died",250,250);
                }
            }
        };
        add(jPanel);
    }

    private void initFrame() {
        setSize(610, 640);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - 610) / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height - 640) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void StartGame(){

    }
    public static void main(String[] args) {


        new MainFrame().setVisible(true);
    }
}
