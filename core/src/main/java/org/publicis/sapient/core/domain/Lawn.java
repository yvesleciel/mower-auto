package org.publicis.sapient.core.domain;

public class Lawn {

    private final int width;
    private final int height;

    public Lawn(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }
}
