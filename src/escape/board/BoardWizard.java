package escape.board;

import escape.coordinate.Coordinate;
import escape.coordinate.Coordinate.CoordinateType;
import escape.piece.PieceTypeDescriptor;
import escape.util.LocationInitializer;


/**
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
 *This is the interface for the Board that will used to fill different types of boards
 * @param <C>
 */

public interface BoardWizard {
	/*
	 * Function to create Board
	 * @returns a type of board based on the coordinate given 
	 */
	
	public static  Board createB(Coordinate.CoordinateType co, int x, int y, LocationInitializer[]lc,PieceTypeDescriptor[] pd )
	{
		

		Board ret = null;
		if(co== CoordinateType.TRIANGLE) {
			ret = new TriangleBoard(pd,x,y,lc);
		}
		
		else if(co==CoordinateType.SQUARE) {
			// conditions for other types to be added later
			
				ret = new SquareBoard(pd,x, y,lc);
				
				}
		return ret;
	}

}
	


