/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * @author Vinit Kothari
 * Copyright ©2020 Gary F. Pollice
 *******************************************************************************/

package escape.observer;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.*;
import org.junit.jupiter.api.*;
import escape.*;
import escape.required.*;

public class MasterTestObserver implements GameObserver
{
    public Deque<Notification> messages;
    
    public MasterTestObserver()
    {
        messages = new LinkedList<Notification>();
    }
    
    /*
     * @see escape.GameObserver#notify(java.lang.String)
     */
    @Override
    public void notify(String message)
    {
        messages.addLast(new Notification(message, null));
//        System.err.println(message);
    }

    /*
     * @see escape.GameObserver#notify(java.lang.String, java.lang.Throwable)
     */
    @Override
    public void notify(String message, Throwable cause)
    {
        messages.addLast(new Notification(message, cause));
//        System.err.println(message);
    }    
    
    public int messageCount()
    {
        return messages.size();
    }
   /**
    * Function to look at next messages
    * @return Notification containing message and a cause
    */
    public Notification nextMessage()
    {
        if (messages.isEmpty()) {
            return null;
        }
        return messages.removeFirst();
    }
    
    /**
     * Function to clear messages
     * 
     */
    public void clearMessages()
    {
        messages.clear();
    }
    /**
     * Function to get the message since Notification was not directly accessible
     * @return String of the message
     */
    public String getMessage()
    {
        return messages.getFirst().getMessage();
    }
    
    /**
     * Function to get the cause of the notification since notification was not directly accesible when testing
     * 
     * @return Throwable cause of the error if there exists
     */
    public Throwable getCause() {
    	return messages.getFirst().getCause();
    }
}

/**
 * Notification Class
 * @author vinit
 *
 */
/**
 * @param String is the message of the Notification
 * @param cause the cause of the error that results in the message
 * @author Professor Gary Pollice
 *
 */
 class Notification
{
    final String message;
    final Throwable cause;
    
    public Notification(String message, Throwable cause)
    {
        this.message = message;
        this.cause = cause;
    }

    /**
     * Function to return the message
     * @return the message
     */
    public String getMessage()
    
    
    {
    	
    System.out.println("got here");	
        return message;
    }

    /**Function to return the cause
     * @return the cause
     */
    public Throwable getCause()
    {
        return cause;
    }

}