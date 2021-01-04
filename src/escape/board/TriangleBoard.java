package escape.board;

import java.util.HashMap;


import java.util.LinkedList;
import java.util.Map;

import escape.board.pathFinder.PathFinderImpl;
import escape.board.pathFinder.PathFinderStrategy;
import escape.coordinate.MyCoordinate;
import escape.coordinate.TriangleCoord;
import escape.coordinate.Coordinate.CoordinateType;
import escape.piece.EscapePiece;
import escape.piece.EscapePieceNeeded;
import escape.piece.PieceAttribute;
import escape.piece.PieceTypeDescriptor;
import escape.piece.EscapePiece.MovementPattern;
import escape.piece.EscapePiece.PieceAttributeID;
import escape.util.LocationInitializer;
import escape.util.LocationType;
import escape.util.Player;

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
 * @author Vinit Kothari
 *This is the class for the TriangleBoard type for this project
 *** For JDoc comments on @Override functions check the board interface
 *******************************************************************************/

public class TriangleBoard<C extends MyCoordinate> implements Board<C>{

	private Map<MyCoordinate, LocationType> locmap;
	private Map<MyCoordinate, EscapePiece> pieces;
	
	LocationInitializer[]locationInitializer;
	 PieceTypeDescriptor[]  pd;
	 private Player crr_player=Player.PLAYER1;
	 private PathFinderStrategy path;
	 private boolean player1= true;
	
	
	//private MyCoordinate.CoordinateType coord_type = MyCoordinate.CoordinateType.Triangle; 

	private final int xMax, yMax;

	/**
	 * Constructor for a Triangleboard 
	 * @param xMax: The xMax of the board
	 * @param yMax: The yMax of the board
	 * @param locatationInitalizer: A locationInitializer Array
	 */

	public TriangleBoard(PieceTypeDescriptor[] pd2,int xMax, int yMax,LocationInitializer[] locationInitializer)
	 
	{ 
		this.pd=pd2;
		this.xMax = xMax;
		this.yMax = yMax;
		this.locationInitializer=locationInitializer;			
		locmap = InitializeLocMap();
		pieces = InitializeMap();
		path= new PathFinderImpl(this);
	
		//pieces=this.InitializeMap();
	}
	
	@Override
	public int getXMax() {
		return xMax;
		
		
	}
	@Override
	public int getYMax() {
		return yMax;
	}

	public LocationInitializer[] returnlc(){
		return this.locationInitializer;
	}
	
	public PieceTypeDescriptor[] getDescriptors() {
		return this.pd;
	}
	
	
	
	@Override
	public EscapePiece getPieceAt(C coord) {
		 
		 if (coord==null||coord.getX() > xMax || coord.getY() > yMax || coord.getX() < 1
					|| coord.getY() < 1 ) {
				//throw new EscapeException("Coordinate not on Board");
				return null;
		 }
			
	
		System.out.println(pieces.get(coord));
	
		return  pieces.get(coord);
	}

	@Override
	public LocationType LocationTypeRet(C coord)
	{
		
		
		return locmap.get(coord);
		
		
	

}
	@Override
	public void PlacePiece(EscapePiece ep, C coord) {
		if(LocationTypeRet(coord)!=LocationType.BLOCK) {
			pieces.put(coord, ep);
		
	}
	
}
	
	
	
	

	/**
	 * This is the function to initalize the map of all the pieces that are on the board
	 * @return a Hashmap containing a coordinate and an escape piece that it contains
	 */
		public Map<MyCoordinate, EscapePiece> InitializeMap() {
			Map<MyCoordinate,EscapePiece> piece = new HashMap<MyCoordinate, EscapePiece>();
			
			LocationInitializer[] loc =this.returnlc();
			PieceTypeDescriptor[] poc= this.getDescriptors();
			
			for(int i=0;i<loc.length;i++) {
				
				if(loc[i].player!=null) {
				System.out.println("pd" +pd);
				for(int j=0;j<poc.length;j++) { 
					//System.out.println("pd.length");
					if(poc[j].getPieceName()==loc[i].pieceName) {
					
					
						MovementPattern mp= poc[j].getMovementPattern();
						PieceAttribute[] PA = poc[j].getAttributes();
					    
					MyCoordinate sq= new TriangleCoord(loc[i].x,loc[i].y);
				EscapePiece epn= new EscapePieceNeeded(loc[i].player,loc[i].pieceName,PA,mp);
				piece.put(sq, epn);	
			
			
				
					}
				}
		}
		
			}
				
			
			
			
			
			
			
			return piece;
		}
		
		
		/**
		 * The function to initialize the location has map
		 * @return a hasmap conatining a coordinate a locationtype
		 */
		public Map<MyCoordinate, LocationType> InitializeLocMap() {
			Map<MyCoordinate,LocationType> piece_loc = new HashMap<MyCoordinate, LocationType>();
			
			LocationInitializer[] loc =this.returnlc();
			for(int i=0; i< loc.length;i++) {
				MyCoordinate sq_loc= new TriangleCoord(loc[i].x,loc[i].y);
				LocationType lc =loc[i].locationType;
				piece_loc.put(sq_loc, lc );
			}
			return piece_loc;
		}
	
	
	/*
	 * Helper fuction to change ,locationType to be used in the next implementation
	 */
	/*public void setLocationType(C c, LocationType lt) throws EscapeException
	{
		
		locmap.put(c, lt);
	}*/
		/*
		 * Function to remove coordinate from board
		 * @param from the coordinate that the should be removed from the board
		 */
	@Override
	public void remove(C from) {
		
		// TODO Auto-generated method stub
		if(this.getPieceAt(from)!=null) {
			 System.out.println(pieces.get(from.hashCode()));
			// this.updatelc(from);
			 pieces.remove(from);
			 System.out.println(pieces.remove(from.hashCode()));
			 
	}
	}

	@Override
	public boolean move(C from, C to) {
		EscapePiece movingPiece = getPieceAt(from);
		EscapePiece endPiece = getPieceAt(to);
		
		if(movingPiece!=null &&crr_player==movingPiece.getPlayer()) {
			if( ( path.findPath(movingPiece, from, to)!=null) || this.Triang_Jumper_Check(from, to, movingPiece)) {
			
			/*if(from==to) {
				ChangePlayer();
				return true;
				}*/
	
		if(crr_player!= movingPiece.getPlayer()){ return false; }
		
if((this.LocationTypeRet(to)==LocationType.BLOCK))  {
			
			//System.out.println(this.LocationTypeRet(to));
			return false;
			
			
		}

else if((this.LocationTypeRet(to)==LocationType.BLOCK) && ((EscapePieceNeeded)movingPiece).hasAttribute(PieceAttributeID.UNBLOCK))  {
	this.PlacePiece(movingPiece, to);
	this.remove(from);
	ChangePlayer();
	return true;
}
	
	
	
	
	else if((this.LocationTypeRet(to)==LocationType.EXIT))  {
		
		System.out.println(this.LocationTypeRet(to));
		this.remove(from);
		ChangePlayer();
		player1=!player1;
		return true; 
		
		
	}
	

	
	
	
	else if (endPiece==null ) {
		ChangePlayer();
		this.remove(from);
		EscapePiece MovedPiece=movingPiece;
		this.PlacePiece(MovedPiece, to);
		player1=!player1;
		
		return true;
	}
	
	/*else if(movingPiece.getPlayer()==crr_player&& endPiece.getPlayer()==crr_player) {
		return false;
	}*/
	
	
	
	/*else if(this.LocationTypeRet(to)!=LocationType.BLOCK && this.LocationTypeRet(to)!=LocationType.EXIT) {
		this.PlacePiece(movingPiece,to);
		this.remove(from);
		ChangePlayer();
		player1=!player1;
		return true;
	}*/
	
		}
	
		}
	return false;	
		
	}
	
	public void ChangePlayer() {
		if(crr_player==Player.PLAYER1) {
			crr_player=Player.PLAYER2;
		}
		else if(crr_player==Player.PLAYER2) {
			crr_player=Player.PLAYER1;
		}
	}
	
	
	/**
	 * A function that checks jump apart from pathfinderimpl for triangles
	 * @param from the from coordinate
	 * @param to the to coordinate
	 * @param p an escapePiece
	 * @return a true or a false depending on whether the coordinate type is triangle and 
	 */
	private boolean Triang_Jumper_Check(MyCoordinate from,MyCoordinate to,EscapePiece p) {
		
		EscapePieceNeeded epn= (EscapePieceNeeded)p;
		
		if(epn.hasAttribute(PieceAttributeID.JUMP)) {
			if(from.DistanceTo(to)<=epn.getMaxPathLength()) {
				return true;
			}
		}
		
		return false;
	}

		
	}

	
	
