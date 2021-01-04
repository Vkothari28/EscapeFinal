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
 *******************************************************************************/

package escape.board.pathFinder;

import java.util.*;
import escape.*;
import escape.board.Board;
import escape.coordinate.MyCoordinate;
import escape.piece.EscapePiece;
import escape.piece.EscapePieceNeeded;
import escape.piece.EscapePiece.PieceAttributeID;


/**
 * 
 * USED FROM THE IMPLEMENTATION PROVIDED BY PROFESSOR GARY POLLICE
 * This class implements a path of coordinates. It is used by
 * the pathfinder classes.
 * @version May 3, 2020
 */
public class Path<C extends MyCoordinate>
{
    private final LinkedList<C> path;
    private Board gb;
    public Path(C first)
    {
        path = new LinkedList<C>();
        path.add(first);
            }
    
    private Path(LinkedList<C> path)
    {
        this.path = path;
    }
     
    public Path<C> extend(C next)
    {
        LinkedList<C> p = (LinkedList<C>) path.clone();
        p.add(next);
        return new Path<C>(p);
    }
    
    public C getLast()
    {
    	return path.getLast();
    }
    
    public List<C> getPath()
    {
        return path;
    }
    
    public int length()
    {
        return path.size();
    }
    
    public boolean isLinear(EscapePiece p)
    {
        boolean result = true;
        EscapePieceNeeded ep=(EscapePieceNeeded) p;
        if (ep.hasAttribute(PieceAttributeID.FLY)) {
        	result = path.getFirst().DirectionTo(path.getLast()) != MyCoordinate.DIRECTIONS.NONE;
        } else if (path.size() > 2) {
            MyCoordinate.DIRECTIONS d = path.get(0).DirectionTo(path.get(1));
            for (int i = 1; i < path.size() - 1; i++) {
                if (path.get(i).DirectionTo(path.get(i+1)) != d) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

	/*
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(path);
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Path)) {
			return false;
		}
		Path other = (Path) obj;
		return Objects.equals(path, other.path);
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	/*@Override
	public String toString()
	{
		return "Path [path=" + path + "]";
	}*/
}
