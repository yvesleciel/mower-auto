package org.publicis.sapient.core.domain;

import lombok.Getter;

@Getter
public class Mower {
    private int x;
    private int y;
    private Orientation orientation;

    public Mower(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public void turnLeft() {
        orientation = orientation.left();
    }

    public void turnRight() {
        orientation = orientation.right();
    }

    public void moveForward(Lawn lawn) {
        int newX = x;
        int newY = y;

        switch (orientation) {
            case N -> newY++;
            case E -> newX++;
            case S -> newY--;
            case W -> newX--;
        }

        if (lawn.isWithinBounds(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }
}
