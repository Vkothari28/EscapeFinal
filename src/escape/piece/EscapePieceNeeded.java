package escape.piece;

import escape.piece.EscapePiece.PieceAttributeID;
import escape.util.Player;
import javafx.print.PageOrientation;
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
 *This is the Abstract class for the escape Piece
 * @param <C>
 */


public class EscapePieceNeeded implements EscapePiece{
	
	 private final PieceName name;
	    private final Player player;
	    private PieceAttribute[] pa;
	    private MovementPattern mp;
	 
	    /*
	     * This is the constructor for the escapePieceNeeded for alpha 
	     * Creates an escapePieceneeded with a player and a name
	     */
	    
	    public  EscapePieceNeeded(Player player, PieceName name) 
	    {
	    	this.player = player;
	    	this.name = name;
	    		    }
	/**
	 * Constructor for escapePiece
	 * @param player the Player the piece belongs to 
	 * @param name: The name of the player
	 * @param pa: The attributes a piece has
	 * 
	 */
	 public  EscapePieceNeeded(Player player, PieceName name,PieceAttribute[] pa, MovementPattern mp) 
    {
    	this.player = player;
    	this.name = name;
    	this.pa=pa;
    	this.mp=mp;
    }
		@Override
	/**
	 * Function the returns a piece name 
	 * @return pieceName
	 */
	public PieceName getName(){
		return this.name;
		
		
	}
		/**
		 * Function to return the player of the piece
		 * @return player. 
		 */
		@Override
	 public Player getPlayer() {
		 return this.player;
		 
	 }
		
	/**
	 * 	
	 * @return the pieceAttributes of an EscapePiece
	 */
	public PieceAttribute[] getAttributes() {
		return this.pa;
	}
		
	
	
		
	
		
	
	
	
	/**
	 * A function that checks if a piece has a certain attribute
	 * @param pid the PieceAttributeId a piece has such as FLY, JUMP etc
	 * @return a true or false based on whether the piece has that attribute or not
	 */
	public boolean hasAttribute(PieceAttributeID pid) {
		for (int i=0;i<this.getAttributes().length;i++ ) {
		
			if(pid==this.getAttributes()[i].getId()) {
				return true;
				}
			
			
					
		}
		
		return false;
	}
		
	/**
	 * A function the returns the maximum distance is capable of moving
	 * @return the max distance a piece can move
	 */
	public int getMaxPathLength() {
		
	for(int i=0;i<this.getAttributes().length;i++) {
		
		if(this.getAttributes()[i].getId() == PieceAttributeID.DISTANCE ||this.getAttributes()[i].getId()==PieceAttributeID.FLY) {
			
			return this.getAttributes()[i].getValue();
		}
			
				}
	
		return 0;
	}
	/**
	 * 
	 * @return the movementpattern ID of  apiece
	 */
	public MovementPattern getMovePattern() {
		return this.mp;
	}
		
	}
	


