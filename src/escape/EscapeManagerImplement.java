package escape;
/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 *
 *This class is the implementation of escapeGameManager
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * @author vinit
 * @version December 2020
 * Copyright ©2016-2020 Gary F. Pollice
 *******************************************************************************/



import escape.board.Board;
import escape.board.pathFinder.PathFinderImpl;
import escape.coordinate.Coordinate;
import escape.coordinate.MyCoordinate;
import escape.coordinate.SquareCoord;
import escape.coordinate.TriangleCoord;
import escape.coordinate.Coordinate.CoordinateType;
import escape.exception.EscapeException;
import escape.observer.GameObserver;
import escape.piece.EscapePiece;
import escape.piece.EscapePieceNeeded;
import escape.piece.PieceTypeDescriptor;
import escape.piece.EscapePiece.PieceAttributeID;
import escape.piece.EscapePiece.PieceName;
import escape.util.LocationInitializer;
import escape.util.LocationType;
import escape.util.Player;
import escape.util.RuleDescriptor;
import escape.util.Rule.RuleID;

public class EscapeManagerImplement <C extends MyCoordinate> extends ObserverController {
private CoordinateType coordinateType;
	
	// Board items

	private int maxturns;
	private int player_2_score;
	private int maxscore;
	private int xMax, yMax;
	private int player_1_piece_count=0;
	private int player_2_piece_count=0;
	private Player winner = null;
	private boolean isTie = false;
	private PathFinderImpl path;
	private int  playercount= 2; // 2 by default but changeable;
	private int[] player_scores= new int[playercount];
	private int player_index=0;
	private int turncount=0;

	private Board<MyCoordinate> gb;
	
	 private Player crr_player=Player.PLAYER1;
	private boolean player1= true;
	
	private RuleDescriptor[] rd;
	
	/*
	 * Constructor for EscapeManager
	 * @param coordinatetype: A certain coordinatetype
	 * @param xMax
	 */
	
	public EscapeManagerImplement(CoordinateType coordinatetype, Board gb, RuleDescriptor[] rd) {
		this.coordinateType=coordinatetype;
		this.gb=gb;
		this.path= new PathFinderImpl(gb);
		this.rd=rd;
		PieceCounter();
		this.max_turns_and_score();

	}
	
	
	
	@Override
	public boolean move(Coordinate from, Coordinate to) {
		boolean ret = false;
		EscapePieceNeeded PieceMove = null;
		try {
			this.turn_check();
		}
			catch(EscapeException e) {
				
				sendExceptionMessage("Game Over", e);
				return false;
			}
		
	if(this.isGameOver()) {
		sendMessage("Game is already Over, Can't Move");
		return false;
	}
	
	
		
		if(from==null ||to==null) {
			sendMessage("Coordinates cannot be null, please try again");
			return false;
		}
		
		if(this.getPieceAt(to)!=null) { // updated for final iteration placing a piece on another player's piece is not allowed
			return false;
		}
		
		
	if(((MyCoordinate) to).getX()>gb.getXMax()||((MyCoordinate) to).getY()>gb.getYMax()) {
		return false;
	} 
		
		System.out.println(this.crr_player);
		
		System.out.println(this.crr_player);
		
	

	
	
			
	if(this.getPieceAt(from)!=null) {
		PieceMove=(EscapePieceNeeded) this.getPieceAt(from);
	}
	ret=gb.move((MyCoordinate) from ,(MyCoordinate)to);
	
	if( ret ==true) {
		
		if(PieceMove.getPlayer()==Player.PLAYER2) {
			turncount++;
			System.out.println("Turns:"+ turncount);
			player_index=0;
		}
		if(this.getPieceAt(to)==null && this.getPieceAt(from)==null) {  // if the piece is removed from the board to will have no piece, aka it reaches exit
			
			if(PieceMove.getPlayer()==Player.PLAYER1) {
				this.player_1_piece_count--;
				player_scores[0]+=1;
			}
			
			if(PieceMove.getPlayer()==Player.PLAYER2) {
				this.player_2_piece_count--;
				player_scores[1]+=1;
				player_2_score++;
			}
			
			
			
			
		 
			
			
			System.out.println("Score:"+player_scores[player_index]);
			
			
		}
			
		} 
		
		
		
		else {
			player_index++;
		}
	
	if(this.isGameOver()) {
		sendMessage("Game is already Over, Can't Move"); // sending message right after win
	}
	
	
	return ret;
		
	}

	
	

	@Override
	public EscapePiece getPieceAt(Coordinate coord) {

				
		 if ((MyCoordinate)coord==null) {
		
		return null;
	}
		return  gb.getPieceAt((MyCoordinate) coord);
}
	
	public Board getBoard() {
		return this.gb;
	}

	@Override
	public MyCoordinate makeCoordinate(int x, int y) {
		
	MyCoordinate ret=null;
					
	
	try {
		checkLimits(x, y);
	} catch (EscapeException e) {
		sendExceptionMessage("Failed to make coordinate, returning null", e);
	return null;
	}
		
		if(coordinateType==Coordinate.CoordinateType.SQUARE) {
			ret=(MyCoordinate) new SquareCoord(x,y);
		}
		
		
		
		
		else if(coordinateType==Coordinate.CoordinateType.TRIANGLE) {
			ret= (MyCoordinate) new TriangleCoord(x,y) ;
		}
			
			
		
		
	
return ret;
}
	
	
	
	
	
	/**
	 * Function to check if  coordinate maker has  x and y that are within the max limit
	 * @param x the x value that is to be checked
	 * @param y the y value that is to be checked
	 * @return boolean true if it is in limits and an exception thrown if not.
	 */
private boolean checkLimits(int x,int y) {
	
	if(x <= gb.getXMax() &&( y <= gb.getYMax())) {
		return true;
	}
	
 throw new EscapeException("x and y are outside coordinate limits");
}
	

	
/**
 * Checks if the game is over and sends an appropriate message
 * 
 * @return boolean value of true or false
 */
private boolean isGameOver() {
	update_winner();
	if (winner != null) {
		sendMessage("Game is over and " + winner + " has won");
		return true;
	}
	if (isTie != false) {
		sendMessage("Game is over and PLAYER1 and PLAYER2 have tied");
		return true;
	}
	
		return false;
}

/**
 * Helper function to check if game is won by either player
 * 
 */
private void update_winner() {
	int wincount=0;
	int lastscore=0;
	System.out.println("MaxScoreIs"+this.maxscore);
	
	for(int i=0; i<player_scores.length;i++) {
		if(lastscore==player_scores[i] && lastscore!=0 &&player_scores[i]!=0) {
			isTie=true;
		}
		
		lastscore=player_scores[i];
		if( player_scores[i]>=this.maxscore ) {
			wincount++;
			if(i==0) {
				winner=Player.PLAYER1;
			}
			else if (i==1){
				winner= Player.PLAYER2;
			}
		}
		
	}
		if(wincount>1 && turncount>=maxturns && player_1_piece_count!=0 && player_2_piece_count!=0 ) { // if both have same score and game is over given that piece counts are not 0
			
			isTie=true;
		
			
		}
		if(player_2_piece_count==0 && player_1_piece_count ==0) {
			isTie=true;
		}
		else if(player_1_piece_count==0 ) {
			winner=Player.PLAYER2;
		}
		else if(player_2_piece_count==0 ){
			winner=Player.PLAYER1;
		}
		 		
			
	}

/**
 * Function to initialize max turns and maxscore variables 
 */

private void max_turns_and_score() {
	for(int i=0;i<rd.length;i++) {
		if (rd[i].id==RuleID.TURN_LIMIT) {
			this.maxturns=rd[i].value;
		}
		if(rd[i].id==RuleID.SCORE) {
			this.maxscore=rd[i].value;
		}
		
	}
}

/**
 * 
 * @return boolean value depending if turn_limit has been met
 */


private boolean turn_check(){
	
	
	if((turncount < this.maxturns )) {
		System.out.println("Turn Limit:"+maxturns);
		System.out.println(turncount);
		
		return true;
	}
	
	throw new EscapeException("Game is over as turn limit is met");
	
}




/**
 * Counts pieces on the board
 */
private void PieceCounter() {
	for(int i=0;i<gb.returnlc().length;i++) {
		if(gb.returnlc()[i].player==Player.PLAYER1) {
			this.player_1_piece_count++;
		}
		
		else if(gb.returnlc()[i].player==Player.PLAYER2) {
			this.player_2_piece_count++;
		}
	}
}








	
	
	}

	


	
