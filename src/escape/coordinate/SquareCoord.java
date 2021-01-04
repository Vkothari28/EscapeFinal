package escape.coordinate;

/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2020 Gary F. Pollice
 * @author vinit
 * 
 * This file is the squareCoordinate implemenation of my Coordinate.
 * For Jdoc comments on all @override functions excepts equals and hashcodde check out the MyCoordinate Interface
 *******************************************************************************/

import java.util.LinkedList;
import java.util.Objects;

import escape.exception.EscapeException;

public class SquareCoord implements MyCoordinate{

	
	private int x;
	private int y;
	/**
	 * The Constructor for a squareCoordinate
	 * @param x the x value 
	 * @param y the y value for a coordinate
	 */
	
	public SquareCoord(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	@Override
	/**
	 * Return the distance from this coordinate to another based solely upon
	 * the coordinate type as described in the Escape Developer's Guide. This
	 * method is normally not used by a client, but is defined her in order for
	 * some tests for the alpha release.
	 * @param c The coordinate that is to be chcecked for distance
	 * @return the distance to the other coordinate
	 */
	public int DistanceTo(Coordinate c) {
		if (c instanceof SquareCoord) {
			SquareCoord f = (SquareCoord) c;
			int xx = Math.abs(this.getX() - f.getX());
			int yy = Math.abs(this.getY() - f.getY());
			if (xx > yy) {
				return xx;
			} else
				return yy;
		} else {
			throw new EscapeException("comparing different types of coordinate");
		}
	} 
	
	/**
	 * Checking the direction from one coordinate to the other
	 * @param c A  coordinate that a direction has to checked to
	 */
	public MyCoordinate.DIRECTIONS DirectionTo(Coordinate c) {
		if(c instanceof SquareCoord) {
			SquareCoord sc= (SquareCoord) c;
		
		if(this.getX()==sc.getX() ) {
			
			if( this.getY()<sc.getY()) {
				return MyCoordinate.DIRECTIONS.EAST;
			
				
			
		}
			
			else {
				return MyCoordinate.DIRECTIONS.WEST;
			}
		}
		
		
		else if(this.getY()==sc.getY() ) {
			
			if(this.getX()< sc.getX()) {
				return MyCoordinate.DIRECTIONS.NORTH;
				
			}
			else {
				return MyCoordinate.DIRECTIONS.SOUTH;
			}
						
		}
		
		// southwest from x and y is greater
			else if(this.getX()>sc.getX() && this.getY()>sc.getY() && Math.abs(this.getX()-sc.getX())==Math.abs(this.getY()-sc.getY())) {
				return MyCoordinate.DIRECTIONS.SOUTHWEST;
				
			}
			
			else if(this.getX()>sc.getX()&&this.getY()<sc.getY()  && Math.abs(this.getX()-sc.getX())==Math.abs(this.getY()-sc.getY())) {
				return MyCoordinate.DIRECTIONS.SOUTHEAST;
			// southeast x in the from is greater and  y in to is greater. 
		}
		
			else if(this.getX()<sc.getX() && this.getY()<sc.getY() && Math.abs(this.getX()-sc.getX())==Math.abs(this.getY()-sc.getY())) {
				return MyCoordinate.DIRECTIONS.NORTHEAST;
				
			}
			
			else if(this.getX()<sc.getX()&&this.getY()>sc.getY() && Math.abs(this.getX()-sc.getX())==Math.abs(this.getY()-sc.getY())) {
				return MyCoordinate.DIRECTIONS.NORTHWEST;
			// southeast x in the from is greater and  y in to is greater. 
		}
		
		
		
	}
		
		return MyCoordinate.DIRECTIONS.NONE;	
		
	}
	/**
	 * The function that checks if a certain coordinate has a direction from another square coordinate and is hence linear to it
	 * @param c the coordinate that is to be chcked for linearity
	 * @return a true or  false dependingm on whether a coordinate is linear to the other one
	 */
	@Override
	public boolean isLinearTo(Coordinate c) {
		
		if(DirectionTo(c)!=MyCoordinate.DIRECTIONS.NONE) {
			return true;
		}
		
		return false;
	}
		
		
		
		
		
	
	
	

	
	
	@Override 
	/**
	 * A function to see the value associate with each squarecoord Implemented using Geeksforgeeks
	 * returns a different object based on the hash
	 */
	public int hashCode() {
		return Objects.hash(x,y);
	}
	/**
	 * Return the x value for this coordinate
	 * 
	 * @return x
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * Return the y value for this coordinate
	 * 
	 * @return y
	 */
	
	public int getY() {
		return y;
	}
	@Override
	/*
	 * Override equals based on geeksforgeeks override
	 * @return boolean, returns a parameter depending on whether the object equals the current
	 */
	 public boolean equals(Object o) { 
		 if (o == this) { 
	            return true; 
	        } 
	  
	        /* Check if o is an instance of Complex or not 
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof SquareCoord)) { 
	            return false; 
	        } 
	          
	        // typecast o to Complex so that we can compare data members  
	        SquareCoord c = (SquareCoord) o; 
	          
	        // Compare the data members and return accordingly  
	        return Double.compare(x, c.x) == 0
	                && Double.compare(y, c.y) == 0; 
	    } 
	

	/*
	 * returns a linked list of all neighboring coordinates 
	 */
	@Override
	public LinkedList NeighboringCoords() {
		// automation to be added later
		LinkedList<SquareCoord> neighbors_list= new LinkedList<>();
		/*
		int x_add=-1;// start with -1 and go to +1
		int y_add=-1;
		while(neighbors_list.size()<=8 ) {
			SquareCoord sqc= new SquareCoord((this.getX()+x_add),(this.getY())+y_add);
			neighbors_list.add(sqc);
			if(y_add==-1) {
				y_add=1;
			}
			if(y_add==1) {
				x_add++;
				y_add=-1;
			}*/ 
			
		
			
			
			
		
		neighbors_list.add(new SquareCoord(this.getX(), this.getY()+1));// add the north neighbor
		neighbors_list.add(new SquareCoord(this.getX(), this.getY()-1));//adding south neigbor
		neighbors_list.add(new SquareCoord(this.getX()+1, this.getY()));//adding east neigbor
		neighbors_list.add(new SquareCoord(this.getX()-1, this.getY()));//adding west neigbor	
		neighbors_list.add(new SquareCoord(this.getX()+1, this.getY()+1)); // adding northeast neighbor
		neighbors_list.add(new SquareCoord(this.getX()+1, this.getY()-1)); // adding northwest neighbor
		neighbors_list.add(new SquareCoord(this.getX()-1, this.getY()-1));//adding southwest neighbor
		neighbors_list.add(new SquareCoord(this.getX()-1, this.getY()+1));//adding southeast neighbor
		
		
		
		
		
		
		
		return neighbors_list;
		
	}

	@Override
	public MyCoordinate neighborAt(DIRECTIONS d) {
		LinkedList Neighbors=this.NeighboringCoords();	
		MyCoordinate ret=null;
		if(d!=null) {
			
			switch (d) {
			// conditions for other types to be added later
			case NORTH:
				ret = (MyCoordinate) Neighbors.get(0);
			case SOUTH:
				ret = (MyCoordinate) Neighbors.get(0);
			case EAST:
				ret = (MyCoordinate) Neighbors.get(0);
			case WEST:
				ret = (MyCoordinate) Neighbors.get(0);
			case NORTHEAST:
				ret = (MyCoordinate) new SquareCoord(this.getX()+1, this.getY()+1);
			case SOUTHEAST:
				ret = (MyCoordinate)  new SquareCoord(this.getX()-1, this.getY()+1);
			case NORTHWEST:
				ret = (MyCoordinate) new SquareCoord(this.getX()+1, this.getY()-1);
			case SOUTHWEST:
				ret = (MyCoordinate) new SquareCoord(this.getX()-1, this.getY()-1);
				
			

		}
			System.out.println("neighbor:"+ret);
	
	 }
		return ret;
	}
}

