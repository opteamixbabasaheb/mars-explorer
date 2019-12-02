package com.mars.Explorer.dal;

import com.mars.Explorer.domain.Command;
import com.mars.Explorer.domain.Explorer;
import com.mars.Explorer.domain.Position;
import com.mars.Explorer.domain.Surface;

public class Game {
	
	Surface tableSurface;
	Explorer mes;
	
	public Game(Surface tableSurface,Explorer mes) {
		this.tableSurface = tableSurface;
		this.mes = mes;
	}
	
	/**
     * Places the explorer on the table surface in position X,Y and direction is not considered.
     *
     * @param position explorer position
     * @return true if placed successfully
     * @throws ExplorerException
     */
	public boolean placeExplorer(Position position) throws ExplorerException {
		
		if (tableSurface == null)
            throw new ExplorerException("Surface object outbound!");
		if (position == null)
            throw new ExplorerException("object position outbound!");
		
		// validate the position
        if (!tableSurface.isValidPosition(position))
            return false;
		
        // if position is valid then assign values to fields
        mes.setPosition(position);
        return true;
	}
	
	public String eval(String inputString) throws ExplorerException {
		
		String[] args = inputString.split(" ");
		// validate command
        Command command;
        try {
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new ExplorerException("Invalid command");
        }
        if (command == Command.PLACE && args.length < 2) {
            throw new ExplorerException("Invalid command");
        }
		// validate PLACE params
        String[] params;
        int x = 0;
        int y = 0;
        if (command == Command.PLACE) {
            params = args[1].split(",");
            try {
                x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
            } catch (Exception e) {
                throw new ExplorerException("Invalid command");
            }
        }
        if (command == Command.EXPLORE) {
        	params = args[1].split(",");
        	try {
        		x = Integer.parseInt(params[0]);
                y = Integer.parseInt(params[1]);
            } catch (Exception e) {
                throw new ExplorerException("Invalid command");
            }
        }
        String output;
        
        switch (command) {
        case PLACE:
            output = String.valueOf(placeExplorer(new Position(x, y)));
            break;
        case EXPLORE:
            Position newPosition = new Position(x, y);
            Position oldPosition = mes.getPosition();
            int oldx = oldPosition.getX();
            int oldy = oldPosition.getY();
            int newx = newPosition.getX();
            int newy = newPosition.getY();
            if (!tableSurface.isValidPosition(newPosition))
                output = String.valueOf(false);
            else {
            	
            	if((oldy-newy)<0) {
    	        	do {
    	        		oldy++;
    	        		System.out.println(oldx+","+oldy);
    	        	}while(oldy<newy)	;
    	        }
    	      if((oldy-newy)>0) {
    	    	  do {
    	        		oldy--;
    	        		System.out.println(oldx+","+oldy);
    	        	}while(oldy>newy);
    	      }
    	      
    	      if((oldx-newx)<0) {
    	        	 do{
    	        		oldx++;
    	        		System.out.println(oldx+","+oldy);
    	        	}	while(oldx<newx);
    	        }
    	      if((oldx-newx)>0) {
    	    	 do {
    	        		oldx--;
    	        		System.out.println(oldx+","+oldy);
    	        	} while(oldx>newx);
    	      }
    	      
    	      System.out.println("newposition("+oldx+","+oldy+")" );
    	
            }
                output = String.valueOf(mes.move(newPosition));
            break;
        case BLOCK:
        	Position newPosition1 = mes.getPosition();
            if (!tableSurface.isValidPosition(newPosition1))
                output = String.valueOf(false);
            else
                output = String.valueOf(mes.move(newPosition1));
            break;
        case REPORT:
            output = report();
            break;
        default:
            throw new ExplorerException("Invalid command");
    }

    return output;
	}
	
	/**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (mes.getPosition() == null)
            return null;

        return mes.getPosition().getX() + "," + mes.getPosition().getY();
    }
}
