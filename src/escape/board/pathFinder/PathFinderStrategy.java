/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Copyright ©2016-2020 Gary F. Pollice
 *******************************************************************************/
package escape.board.pathFinder;

import java.util.List;
import escape.*;
import escape.coordinate.MyCoordinate;
import escape.piece.EscapePiece;


/**
 * Interface for the path finding algorithms that are encapsulated as a strategy.
 * @version May 3, 2020
 */
public interface PathFinderStrategy<C extends MyCoordinate>
{
	/**
	 * Find a valid path between two locations. If there is none, return null.
	 * There may be more than one path. Just one is returned that is valid.
	 * @param p the piece trying to move
	 * @param from the starting location
	 * @param to the final location
	 * @return a list containing the path between the two coordinates that is valid
	 * 	or null
	 */
	Path<C> findPath(EscapePiece p, C from, C to);
}
