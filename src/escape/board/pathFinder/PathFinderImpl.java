/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * @author Vinit Kothari
 * Copyright ©2020 Gary F. Pollice
 *******************************************************************************/

package escape.board.pathFinder;

import java.util.*;

import escape.board.Board;
import escape.coordinate.MyCoordinate;
import escape.coordinate.Coordinate.CoordinateType;
import escape.coordinate.MyCoordinate.DIRECTIONS;
import escape.piece.EscapePiece;
import escape.piece.EscapePieceNeeded;
import escape.piece.EscapePiece.MovementPattern;
import escape.piece.EscapePiece.PieceAttributeID;
import escape.util.LocationType;


/**
 * BASED ON THE IMPLEMENTATION from Professor Gary Pollice
 * Pathfinder strategy
 * @version December 1
 */
public class PathFinderImpl<C extends MyCoordinate> implements PathFinderStrategy<C>
{
    private final Board<C> board;
    private Set<C> visited;
    private Deque<Path<C>> toVisit;
    private Map<C, Integer> reached;
    private boolean jumped;
    private C jumpedCoordinate;
    private CoordinateType ct;
    
    private int maxLength = 0;	// # of locations in a path
    
    public PathFinderImpl(Board<C> board)
    {
    	
    	this.ct= ct;
        this.board = board;
    }
    
    /**
     * Preconditions already checked:
     * <ul>
     * 	<li>Coordinates are valid for the board</li>
     * 	<li><tt>to</tt> is not a BLOCK</li>
     *  <li><tt>from</tt> contains a piece</li>
     *  <li>if <tt>from</tt> and <tt>to</tt> contain pieces, they are for different players</li>
     *  <li>if the movement pattern is DIAGONAL, the two ends are diagonal to each other</li>
     *  <li>if the movement pattern is LINEAR, the two ends are in a straight line</li>
     * </ul>
     * @see escape.board.path.PathFinderStrategy#findPath(escape.board.BoardImpl, escape.piece.EscapePiece, escape.board.coordinate.EscapeCoordinate, escape.board.coordinate.EscapeCoordinate)
     */
    @Override
    public Path<C> findPath(EscapePiece p, C from, C to)
    {
        Path<C> path = searchForPath(p, from, to);
        return path;
    }
    
  /*  private Path<C> searchforTrianglePath(EscapePiece p, C from, C to){
    	
    	 visited = new HashSet<C>();
         toVisit = new LinkedList<Path<C>>();
         toVisit.add(new Path<C>(from));
         reached = new HashMap<C, Integer>();
         EscapePieceNeeded epn=(EscapePieceNeeded) p;
         //C LastCoord=null;
         
         maxLength = epn.getMaxPathLength() + 1;
    }*/
    /**
     * 
     * @param p the escapePiece 
     * @param from the from coordinate from which a path is to be search
     * @param to the to coordinate to which a path has to be searched
     * @return Path a
     */
    
    private Path<C> searchForPath(EscapePiece p, C from, C to)
    {
        visited = new HashSet<C>();
        toVisit = new LinkedList<Path<C>>();
        toVisit.add(new Path<C>(from));
        reached = new HashMap<C, Integer>();
        EscapePieceNeeded epn=(EscapePieceNeeded) p;
        //C LastCoord=null;
        
        maxLength = epn.getMaxPathLength() + 1;
        /*
         * At this point we have the starting path {{from}} and the nodes visited {from}
         */
        while (!toVisit.isEmpty())
        {
           Path<C> path = toVisit.removeFirst();							// potential path
            if (path.length() >= maxLength) continue;  							// we can't add any more to this
            C cEnd = path.getLast();   
            List<C> neighbors = (List<C>) cEnd.NeighboringCoords();
            for (C c : neighbors) {
            	if (!isBetterPath(c, path.length())) continue;					// new path would be no better
            	if (!pieceCanMove(epn, path, c, to)) continue;						// piece see if piece can move to c 
            	// We know the piece can move on the path + c
            	 
                Path<C> newPath = path.extend(c);
                /*if(ct==CoordinateType.TRIANGLE) {
                 	if (from.DirectionTo((newPath.getLast()))==DIRECTIONS.NONE) {
                 		return null;
                 	}
            	 }*/
               
                
                C destination = c;
                if (jumped) {
                	newPath = newPath.extend(jumpedCoordinate);
                	destination = jumpedCoordinate;    
                System.out.println("destination:"+destination);	
                }
                if(destination!=null || jumped==false) { // ensures valid destination otherwise results in null pointer exception
                if (  destination.equals(to) ) {
            		if (epn.getMovePattern() != MovementPattern.LINEAR || newPath.isLinear(epn))
            			return newPath;
            	}
                
                
                // This is a possible path
            	toVisit.addLast(newPath);
            	reached.put(c, newPath.length() - 1);
            }
            }
       }
        
        return null;
    }
    /**
     * A function to check a better path aka the best path
     * @param c a coordinate 
     * @param length the length of the path 
     * @return true if  abtter payh is available
     */
    private boolean isBetterPath(C c, int length)
    {
    	if (!visited.contains(c)) {
    		visited.add(c);
    		return true;
    	}
    	return reached.get(c) == null || length < reached.get(c);
    }
    
    /**
     * The two coordinates are neighbors. Check to see if, given
     * other characteristics, such as the movement pattern, this would
     * be a valid move.
     * @param piece
     * @param path the current path
     * @param target
     * @return true if the piece can move the end of the path to the target
     */
    private boolean pieceCanMove(EscapePiece piece1, Path<C> path, C target, C moveEnd)
    {
EscapePieceNeeded piece= (EscapePieceNeeded) piece1;
    	C from = path.getLast(); 
    	boolean result = movementPatternCheck(piece.getMovePattern(), target, from);
    	result = result && blockCheck(piece, target);
    	result = result && jumpCheck(piece, from, target, moveEnd);
    	result = result && exitCheck(piece, target, moveEnd);
    	return result;
    }

	/**
	 * Determine if the move can be made based upon the movement pattern.
	 * Precondition: If the pattern is DIAGONAL, then we know the board is SQUARE
	 * @param mpid
	 * @param target
	 * @param from
	 * @return
	 */
	private boolean movementPatternCheck(MovementPattern mpid, C target, C from)
	{
		return (mpid != MovementPattern.DIAGONAL && mpid != MovementPattern.ORTHOGONAL)
			|| (mpid == MovementPattern.DIAGONAL && (target.DirectionTo(from)==DIRECTIONS.NORTHEAST || target.DirectionTo(from)==DIRECTIONS.SOUTHWEST
			||target.DirectionTo(from)==DIRECTIONS.SOUTHEAST ||target.DirectionTo(from)==DIRECTIONS.NORTHWEST))	
			|| (mpid == MovementPattern.ORTHOGONAL && (target.DirectionTo(from)==DIRECTIONS.NORTH || target.DirectionTo(from)==DIRECTIONS.SOUTH
			||target.DirectionTo(from)==DIRECTIONS.EAST ||target.DirectionTo(from)==DIRECTIONS.WEST));
	}
	
	/**
	 * Determine if the target is a BLOCK whether the move can be made based upon
	 * the piece's attributes (FLY, UNBLOCK).
	 * @param piece
	 * @param target
	 * @return a true or false if piece has attributes fly or unblock 
	 */
	private boolean blockCheck(EscapePiece piece, C target)
	{
		EscapePieceNeeded epp= (EscapePieceNeeded) piece;
		return (board.LocationTypeRet(target) != LocationType.BLOCK)
			|| (epp.hasAttribute(PieceAttributeID.FLY) || epp.hasAttribute(PieceAttributeID.UNBLOCK));
	}
	
	private boolean jumpCheck(EscapePiece piece1, C from, C target, C moveEnd)
	{
		EscapePieceNeeded piece= (EscapePieceNeeded) piece1;
		jumped = false;
		jumpedCoordinate = null;
		if (board.getPieceAt(target) == null) return true;	// no piece there
		// There is a piece in the way
		// From the preconditions, we know that it's different from the moving piece
		if (target.equals(moveEnd)) return true;
		boolean result = piece.hasAttribute(PieceAttributeID.FLY) || piece.hasAttribute(PieceAttributeID.JUMP);
		if (piece.hasAttribute(PieceAttributeID.JUMP)) {
			MyCoordinate.DIRECTIONS d = from.DirectionTo(target);
			C next = (C) target.neighborAt(d);	// Jump has to be in a straight line
			if (board.getPieceAt(next) != null) {
				if (board.getPieceAt(next).getPlayer() == piece.getPlayer()) {
					result = false;
				}
			}
			if (board.LocationTypeRet(next) == LocationType.BLOCK && !piece.hasAttribute(PieceAttributeID.UNBLOCK)) {
				result = false;
			}
			if (result) {
				jumped = true;
				jumpedCoordinate = next;	// need to extend the path
			}
		}
		return result;
	}
	
	/**
	 * @param piece1 an EscapePiece that is to be moved
	 * @param target the target of movement 
	 * @the end point of a move
	 * @return a boolean based on if a piece runs into an exit midway
	 */
	private boolean exitCheck(EscapePiece piece1, C target, C moveEnd)
	{
		EscapePieceNeeded piece= (EscapePieceNeeded) piece1;
	    return board.LocationTypeRet(target) != LocationType.EXIT
	        || target.equals(moveEnd)
	        || piece.hasAttribute(PieceAttributeID.FLY);
	}
}
