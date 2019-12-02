package com.mars.Explorer.domain;

public class TableSurface implements Surface {
	
	int rows;
    int columns;
    
    public TableSurface(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

	@Override
	public boolean isValidPosition(Position position) {
		// TODO Auto-generated method stub
		return !(
                position.getX() > this.columns || position.getX() < 0 ||
                        position.getY() > this.rows || position.getY() < 0
        );
	}

}
