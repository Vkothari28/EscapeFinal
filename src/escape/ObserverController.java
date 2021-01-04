package escape;

import java.util.ArrayList;

import escape.board.Board;
import escape.coordinate.Coordinate;
import escape.coordinate.MyCoordinate;
import escape.coordinate.Coordinate.CoordinateType;
import escape.observer.GameObserver;
import escape.piece.EscapePiece;


public  abstract class ObserverController implements EscapeGameManager{
	


protected static ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
	
	/*
	 * @see escape.EscapeGameManager#addObserver(escape.GameObserver)
	 */
	@Override
	public GameObserver addObserver(GameObserver observer) {
		observers.add(observer);
		return observer;
	}
	
	/*
	 * @see escape.EscapeGameManager#removeObserver(escape.GameObserver)
	 */
	@Override
	public GameObserver removeObserver(GameObserver observer) {
		if (observers.contains(observer) != false) {
			observers.remove(observer);
			return observer;
		}
		return null;
	}
	
	/**
     * Sends a message to the observer
     * 
     * @param message the message to be sent
     */
	public static void sendMessage(String message) {
		observers.forEach(observer -> observer.notify(message));
	}
	
	/**
     * Sends a message to the observer with an exception
     * 
     * @param message the message to be sent
     * @param cause the exception thrown
     */
	public static void sendExceptionMessage(String message, Throwable cause) {
		observers.forEach(observer -> observer.notify(message, cause));
	}
	
	
}
	




