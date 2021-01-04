package escape.board;

import escape.coordinate.MyCoordinate;
import escape.piece.EscapePiece;
import escape.piece.PieceTypeDescriptor;
import escape.util.LocationInitializer;
import escape.util.LocationType;

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
public interface Board  <C extends MyCoordinate>{

	
	
	    /**
	     * Get the piece at the specified coordinate
	     * @param coord the coordinate at which a piece should be got from
	     * @return the piece that is there, or null if nothing is found
	     */
	    EscapePiece getPieceAt(C coord);
	    
	    /*
	     * Return the XMax of the board
	     */
	    int getXMax();
	    
	    /*
	     * Return y max of the board. 
	     */
	    int getYMax();
	   

	    /**
	     * Get the piece at the specified coordinate
	     * @param ep an Escape piece that is to be put in a certain position
	     * @param coord the coordinate at which the escape piece is to be put to
	     * 
	     */
		void PlacePiece(EscapePiece ep, C coord);
		 /**
	     * Get the piece at the specified coordinate
	     * @param ep an Escape piece that is to be put in a certain position
	     * @param coord the coordinate at which the escape piece is to be put to
	     * 
	     */
		
		LocationType LocationTypeRet(C coord);


	    /**
	     * Get the piece at the specified coordinate
	     * @param coord the coordinate at which a piece should be got from
	     * @return the piece that is there, or null if nothing is found
	     */
		void remove(C from);

		LocationInitializer[] returnlc();

		PieceTypeDescriptor[] getDescriptors();

		boolean move(C from, C to);


	
		
		
	    
	    
}
	    

