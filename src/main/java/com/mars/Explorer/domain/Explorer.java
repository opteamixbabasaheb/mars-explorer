package com.mars.Explorer.domain;

public class Explorer {
	private Position position;
	
	public Explorer() {}
	
	public Explorer(Position position) {
		this.position = position;
	}
	
	public boolean setPosition(Position position) {
        if (position == null)
            return false;

        this.position = position;
        return true;
    }
	
	/**
     * Moves the robot one unit forward in the direction it is currently facing
     *
     * @return true if moved successfully
     */
    public boolean move(Position newPosition) {
        if (newPosition == null)
            return false;
        
        
        // change position
        this.position = newPosition;
        return true;
    }
    
    public Position getPosition() {
        return this.position;
    }
}
