package com.mars.Explorer.domain;

public class Position {
	
	int x;
    int y;
    
    public Position(Position position) {
    	this.x = position.getX();
        this.y = position.getY();
    }
    
    public Position(int x, int y) {
    	this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
