package com.snake;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Node> body;
    private Direction direction = Direction.LEFT;
    private boolean isLiving = true;

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Snake() {
        initSnake();
    }

    public Direction getDirection() {
        return direction;
    }

    public void move() {

        if (isLiving) {
            Node head = body.getFirst();
            switch (direction) {
                case UP:
                    body.addFirst(new Node(head.getX(), head.getY() - 1));
                    break;
                case DOWN:
                    body.addFirst(new Node(head.getX(), head.getY() + 1));
                    break;
                case LEFT:
                    body.addFirst(new Node(head.getX() - 1, head.getY()));
                    break;
                case RIGHT:
                    body.addFirst(new Node(head.getX() + 1, head.getY()));
                    break;
            }
            body.removeLast();
            head=body.getFirst();
            if (head.getX() < 0 || head.getY() < 0 || head.getX() >= 40 || head.getY() >= 40) {
                isLiving = false;
            }
            for (int i = 1; i < body.size(); i++) {
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()) {
                    isLiving = false;
                    break;
                }
            }
        }
    }

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    private void initSnake() {
        body = new LinkedList<Node>();
        body.add(new Node(16, 20));
        body.add(new Node(17, 20));
        body.add(new Node(18, 20));
        body.add(new Node(19, 20));
        body.add(new Node(20, 20));
        body.add(new Node(21, 20));
    }

    public boolean isLiving() {
        return isLiving;
    }

    public void setLiving(boolean living) {
        isLiving = living;
    }

    public void eat(Node food) {
        Node head = body.getFirst();
        switch (direction) {
            case UP:
                body.addFirst(new Node(head.getX(), head.getY() - 1));
                break;
            case DOWN:
                body.addFirst(new Node(head.getX(), head.getY() + 1));
                break;
            case LEFT:
                body.addFirst(new Node(head.getX() - 1, head.getY()));
                break;
            case RIGHT:
                body.addFirst(new Node(head.getX() + 1, head.getY()));
                break;
        }
    }
}
