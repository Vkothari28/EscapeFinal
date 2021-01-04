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
 * This file is the implementation of Coordinate interface.
 *******************************************************************************/

package escape.coordinate;

import java.util.LinkedList;

/*
 * My Coordinate interface that implements Coordinate
 */

public interface MyCoordinate extends Coordinate {
	
	int getX();
	int getY();
	
	/*The enum for the direction values 
	 * contains all the directions that are possible from one coord to another
	 * 
	 */
	public static enum DIRECTIONS{
		EAST, WEST, NORTH, SOUTH,NORTHWEST, NORTHEAST, SOUTHEAST, SOUTHWEST , NONE
		
		
	}
	/**
	 * A function 
	 * @param c the direction to which a certain piece is 
	 * 
	 * @return one of the enum Direction values 
	 */
	 DIRECTIONS DirectionTo( Coordinate c) ;
	 
	 /**
	  * A function to check the linearity to anogther coordinate
	  * @param c the param to which linear is to be checked to 
	  * @return a true or false depending on whether a piece has linearity to a certain other piece
	  */
	 boolean isLinearTo(Coordinate c);
	 /**
	  * 
	  * @return all the neighbors of a certain coordinate
	  */
	 LinkedList NeighboringCoords();
	 /**
	  * FUnction to return the neighboring coordinate at a certain direction
	  * @param d the direction a certain neighbor  has to be got
	  * @return
	  */
	MyCoordinate neighborAt(DIRECTIONS d);
}
	

