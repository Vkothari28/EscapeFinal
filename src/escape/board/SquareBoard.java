package escape.board;
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
 *This is the class for the SquareBoard type for this project
 *** For JDoc comments on @Override functions check the board interface
 *******************************************************************************/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

import escape.board.pathFinder.PathFinderImpl;
import escape.board.pathFinder.PathFinderStrategy;
import escape.coordinate.MyCoordinate;
import escape.coordinate.SquareCoord;
import escape.exception.EscapeException;
import escape.piece.EscapePiece;
import escape.piece.EscapePieceNeeded;
import escape.piece.PieceAttribute;
import escape.piece.PieceTypeDescriptor;
import escape.piece.EscapePiece.MovementPattern;
import escape.piece.EscapePiece.PieceAttributeID;
import escape.util.LocationInitializer;
import escape.util.LocationType;
import escape.util.PieceTypeInitializer;
import escape.util.Player;

public class SquareBoard<C extends MyCoordinate> implements Board<C> {

	private Map<MyCoordinate, LocationType> locmap;
	private Map<MyCoordinate, EscapePiece> pieces;
	
	LocationInitializer[] locationInitializer;
	private boolean player1= true;
	PieceTypeDescriptor[] pd;
	private PathFinderStrategy path;
	 private Player crr_player=Player.PLAYER1;

	// private MyCoordinate.CoordinateType coord_type =
	// MyCoordinate.CoordinateType.SQUARE;

	private final int xMax, yMax;

	/**
	 * Constructor for a squareboard
	 * 
	 * @param xMax: The xMax of the board
	 * 
	 * @param yMax: The yMax of the board
	 * 
	 * @param locatationInitalizer: A locationInitializer Array
	 */

	public SquareBoard(PieceTypeDescriptor[] pd2, int xMax, int yMax, LocationInitializer[] locationInitializer)

	{
		this.pd = pd2;
		this.xMax = xMax;
		this.yMax = yMax;
		this.locationInitializer = locationInitializer;
		locmap = InitializeLocMap();
		pieces = InitializeMap();
		path= new PathFinderImpl(this);

		// pieces=this.InitializeMap();
	}

	/**
	 * Constructor for the alpha iteration of this project
	 * 
	 * @param xMax the max x value
	 * @param yMax the max y value
	 * @param locationInitializer an array that contains locationinitializers which contain information on player, piecetype at certain coordinates
	 */
	public SquareBoard(int xMax, int yMax, LocationInitializer[] locationInitializer)

	{
		this.xMax = xMax;
		this.yMax = yMax;
		this.locationInitializer = locationInitializer;
		// pieces = InitializeMap();
		// locmap = InitializeLocMap();

		// pieces=this.InitializeMap();
	}

	@Override
	public int getXMax() {
		return xMax;

	}

	@Override
	public int getYMax() {
		return yMax;
	}

	@Override
	public LocationInitializer[] returnlc() {
		return this.locationInitializer;
	}

	@Override
	public PieceTypeDescriptor[] getDescriptors() {
		return this.pd;
	}

	@Override
	public EscapePiece getPieceAt(C coord) {

		if (coord == null || coord.getX() > xMax || coord.getY() > yMax || coord.getX() < 1 || coord.getY() < 1) {
			// throw new EscapeException("Coordinate not on Board");
			return null;
		}

		System.out.println(pieces.get(coord));

		return pieces.get(coord);
	}

	@Override
	public LocationType LocationTypeRet(C coord) {

		return locmap.get(coord);

	}
/**
 * A function to place an escapepiece at a certain coordinate
 *  @param ep An EscapePiece that is to be placed
 * @param C a coordinate at which the piece is to be placed
 */
	@Override
	public void PlacePiece(EscapePiece ep, C coord) {
		if (LocationTypeRet(coord) != LocationType.BLOCK) {
			pieces.put(coord, ep);

		}

	}
/**
 * This is the function to initalize the map of all the pieces that are on the board
 * @return a Hashmap containing a coordinate and an escape piece that it contains
 */
	public Map<MyCoordinate, EscapePiece> InitializeMap() {
		Map<MyCoordinate, EscapePiece> piece = new HashMap<MyCoordinate, EscapePiece>();

		LocationInitializer[] loc = this.returnlc();
		PieceTypeDescriptor[] poc = this.getDescriptors();

		for (int i = 0; i < loc.length; i++) {

			if (loc[i].player != null) {
				System.out.println("pd" + pd);
				for (int j = 0; j < poc.length; j++) {
					System.out.println("pd.length");
					if (poc[j].getPieceName() == loc[i].pieceName) {

						MovementPattern mp = poc[j].getMovementPattern();
						PieceAttribute[] PA = poc[j].getAttributes();

						MyCoordinate sq = new SquareCoord(loc[i].x, loc[i].y);
						EscapePiece epn = new EscapePieceNeeded(loc[i].player, loc[i].pieceName, PA, mp);
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
		Map<MyCoordinate, LocationType> piece_loc = new HashMap<MyCoordinate, LocationType>();

		LocationInitializer[] loc = this.returnlc();
		for (int i = 0; i < loc.length; i++) {
			MyCoordinate sq_loc = new SquareCoord(loc[i].x, loc[i].y);
			LocationType lc = loc[i].locationType;
			piece_loc.put(sq_loc, lc);
		}
		return piece_loc;
	}

	/*
	 * Helper fuction to change ,locationType to be used in the next implementation
	 */
	/*
	 * public void setLocationType(C c, LocationType lt) throws EscapeException {
	 * 
	 * locmap.put(c, lt); }
	 */
	/**
	 * Function to remove Piece from board
	 * 
	 * @param C the coordinate from which a piece has to be removed
	 * @param return from the coordinate that the should be removed from the board
	 * 
	 */
	@Override
	public void remove(C from) {

		// TODO Auto-generated method stub
		if (this.getPieceAt(from) != null) {
			System.out.println(pieces.get(from));
			// this.updatelc(from);
			pieces.remove(from);
			System.out.println(pieces.remove(from));

		}
	}
	
	@Override
	public boolean move(C from, C to) {
		
	
		
		
		
		
	if(to.getX()>this.getXMax()||to.getY()>this.getYMax()) {
		return false;
	}
		
		System.out.println(this.crr_player);
		
		System.out.println(this.crr_player);
		
	

	
	EscapePieceNeeded movingPiece = (EscapePieceNeeded)getPieceAt(from);
	
		if(movingPiece!=null &&crr_player==movingPiece.getPlayer()) {
			if( ( path.findPath(movingPiece, from, to)!=null)) {
			
			/*if(from==to) {
				ChangePlayer();
				return true;
				}*/
	
		if(crr_player!= movingPiece.getPlayer()){ return false; }
		
if((this.LocationTypeRet(to)==LocationType.BLOCK))  {
			
			//System.out.println(this.LocationTypeRet(to));
			return false;
			
			
		}
	
	EscapePiece endPiece = getPieceAt(to);
	
	
	 if((this.LocationTypeRet(to)==LocationType.EXIT))  {
		
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
	 
	else if(movingPiece.hasAttribute(PieceAttributeID.UNBLOCK) && this.LocationTypeRet(to)==LocationType.EXIT) {
		this.PlacePiece(movingPiece,to);
		this.remove(from);
		ChangePlayer();
		
		return true;
	}
	
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

	/*
	 * Function that finds the path Returns the value of the path found
	 * Not used but ideas from this a Professor pollice combined in PathFinder IMpl but kept just in case it might be needed in gamma
	 */

	/*
	 * public boolean findpath(C from, C to,EscapePiece epn) {
	 * 
	 * EscapePieceNeeded epp= (EscapePieceNeeded) epn; // need some way before all
	 * this to set certain restrictions such as jump, fly etc //HashSet<SquareCoord>
	 * visited= new HashSet<SquareCoord>(); // a hash that contains all visited
	 * nodes if(from.isLinearTo(to)) { path.addLast(from);// path is the linkedlist
	 * that mimicks a stack where the nodes are put in.
	 * 
	 * while (path.size()!=0) {
	 * 
	 * C coord1 = (C) path.removeFirst();
	 * 
	 * if(coord1.equals(to)) { // we have reached the end coordinate this is to be
	 * checked during the second iteration of pathfind return true;
	 * 
	 * }
	 * 
	 * // we need to use the list of adjacent neighbor coordinates to check if from
	 * might be one of them, if it is return true
	 * 
	 * 
	 * HashMap<C, ArrayList<C>> map=new HashMap<C, ArrayList<C>>();// map containing
	 * all the coords and their neighbors HashMap<C, Integer> distance = new
	 * HashMap<C, Integer>(); // distance from a coord to a certain point HashMap<C,
	 * Boolean> visits = new HashMap<C, Boolean>(); // see if a coord is visited or
	 * not in the algo
	 * 
	 * LinkedList<C> queue = new LinkedList<C>(); // the queue which keeps track of
	 * elements
	 * 
	 * for(C key : map.keySet()) { distance.put(key, Integer.MAX_VALUE);
	 * visits.put(key, false); }
	 * 
	 * visits.put(from, true); distance.put(from, 0); queue.add(from);
	 * 
	 * //BFS algorithm while (queue.size() != 0) { from = queue.poll(); ArrayList<C>
	 * adj = map.get(from); if(adj != null) { for (int i = 0; i < adj.size(); i++) {
	 * if (visits.get(adj.get(i)) == false) { visits.put(adj.get(i), true);
	 * distance.put(adj.get(i), distance.get(from) + 1); queue.add(adj.get(i));
	 * 
	 * if (adj.get(i).equals(to)) return true;//distance.get(to); } } } } } } return
	 * false; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
}

//}
