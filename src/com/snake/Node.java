package com.snake;

import java.util.Random;

public class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Node() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void random(){
        Random random = new Random();

        this.x=random.nextInt(40);
        this.y=random.nextInt(40);
    }
}
