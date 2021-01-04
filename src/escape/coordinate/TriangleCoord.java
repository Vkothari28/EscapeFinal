package escape.coordinate;
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
 * @author vinit
 * 
 * This file is the TriangleCoordinate implemenation of my Coordinate.
 * For Jdoc comments on all @override functions excepts equals and hashcodde check out the MyCoordinate Interface
 *******************************************************************************/
import java.util.LinkedList;
import java.util.Objects;

import escape.exception.EscapeException;
import escape.piece.EscapePiece.MovementPattern;

public class TriangleCoord implements MyCoordinate {

	private int x;
	private int y;
	
	/**
	 * Constrcutor for the triangle Coordinate makesa  trinagle coordnate
	 * @param x
	 * @param y
	 */
	public TriangleCoord(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	
	
	
	 
	@Override 
	public int DistanceTo(Coordinate c) {
		int Rx=0;
		int Ry=0;
		int result=0;
		if(c instanceof TriangleCoord) {
			TriangleCoord triang= (TriangleCoord)c;
			int result_func= Math.abs(this.getX() -triang.getX())+Math.abs(this.getY()-triang.getY());  //result function without R
			//function to use: Change in x + change in y +((source x + source y + r ) -(target x + target y + r ))
			/*int x_dis= Math.abs(triang.getX()-this.getX()); // Triangle formula to be added next iteration
			int y_dis=Math.abs(triang.getY()-this.getY());
			return x_dis+y_dis;*/
		
			//change in x + change in y 
			if( this.getY()==triang.getY()) {// check to see if this y  and to y are the same
				if(up_check(this)==true) { // upward coordinate check for this coord
					
					if((triang.getX() +triang.getY())% 2!=0) { //upward  coordinate check for target
					
						return Math.abs( Math.abs(this.getX()+this.getY()) - Math.abs(triang.getX()+triang.getY())) +result_func; // r value is 0 because it is an upward coordinate
						
						
					}
					else { // downward c coord
						
						return 1+ result_func + Math.abs(((this.x + this.y + 0)-(triang.getX() + triang.getY()))); 
						//result= result_func+1;// r value is 1 because to is a downward coordinate. 
						
						 
					} 
					
				}
				
				
				
				else if((up_check(this)==false)) {  //downward coordinate check
					
					if (up_check(triang)==true) {
						result= result_func + Math.abs(((this.x + this.y + 1)-(triang.getX() + triang.getY() + 0))); 
						//result= result_func+1; // only this coordinate has r value 1;
					}
					
					else {
						result= result_func + Math.abs((this.x + this.y + 1)- (triang.getX() + triang.getY() + 1)); 
						//result =result_func + 2; // both have R values 1 ;
					}
				}
					
					
				}
				
				
			 
				else if (Math.abs(this.getY()-triang.getY())<=Math.abs(this.getX()-triang.getX())) 
				{
					
					if( this.getX()<triang.getX()) { // check which half of the board you are looking at 
						// check  here if source aka this coordinate is facing up or down
						if (up_check(this)){ // facing up
							//check to see where the target is facing - up or down
							if(up_check(triang))
							{
							result=2* Math.abs( this.getX()- triang.getX());	
								//inside of here, check the change in x and how it compares to the actual distance
							}
							else
								
							{
								result= 2* Math.abs(this.getX()-triang.getX())+1;
								
							}
							//result = 2*Math.abs(this.getX()- triang.getX());// result is change in x
						}
						else { //this facing down
							
							if(up_check(triang))
							{
								return 2* Math.abs(this.getX()-triang.getX()) -1;
								
							
								
								
							}
							else {
								
							}
							return 2*(Math.abs(this.getX()-triang.getX()));
						}

					}
					else { // when youre looking at the lower half od the board 
						if(up_check(this)) {
							
							if(up_check(triang)) {
								return 2*(Math.abs(this.getX()-triang.getX()));
							}
							
							else {
								
								return 2*(Math.abs(this.getX()-triang.getX())) -1;
								
							}
						}
							
							else {
								if(up_check(triang)) {
									return 2*(Math.abs(this.getX()-triang.getX())) +1;
							}
								
								else {
									  return 2*(Math.abs(this.getX()-triang.getX()));
								}
						}
						
							
								//downward facing target
								
							}
							//result =2*(Math.abs(this.getY())-triang.getY());
						}
					
				
					
				
			
					else if(Math.abs(this.getY()-triang.getY()) >Math.abs(this.getX()-triang.getX()))  { // check 
						result=result_func;
						
					}
					
		}
		else {
			throw new EscapeException("Not the right coordinate");
		}
			
			return result;
		}
		
		//if() {    //second check
			
		//}
		
		//else if () { //third check
//	}
	
	//public DirectionTo(Coordinate c) {
		
	


	@Override 
	/*
	 * Implemented using Geeksforgeeks
	 */
	public int hashCode() {
		return Objects.hash(x,y);
	}
	/**
	 * Return the x value for this coordinate
	 * 
	 * @return x
	 */
	
	public int getX() {
		return x;
	}
	
	/**
	 * Return the x value for this coordinate
	 * 
	 * @return y
	 */
	
	public int getY() {
		return y;
	}
	@Override
	/*
	 * Override equals based on geeksforgeeks override
	 * @return boolean, returns a parameter depending on whether the object equals the current
	 */
	 public boolean equals(Object o) { 
		 if (o == this) { 
	            return true; 
	        } 
	  
	        /* Check if o is an instance of Complex or not 
	          "null instanceof [type]" also returns false */
	        if (!(o instanceof TriangleCoord)) { 
	            return false; 
	        } 
	          
	        // typecast o to Complex so that we can compare data members  
	        TriangleCoord c = (TriangleCoord) o; 
	          
	        // Compare the data members and return accordingly  
	        return Double.compare(x, c.x) == 0
	                && Double.compare(y, c.y) == 0; 
	    } 
	
	@Override
	public DIRECTIONS DirectionTo(Coordinate c) {
		
		// TODO Auto-generated method stub
		if(c instanceof TriangleCoord) {
		TriangleCoord tg= (TriangleCoord) c;
		/*if(this.getX()==tg.getX()) {
			if(this.getY()<tg.getY()) {
				return MyCoordinate.DIRECTIONS.EAST;
				
			}
			else {
				return MyCoordinate.DIRECTIONS.WEST;
			}
			
		}*/
		
		if(this.getX()==tg.getX()) {
			if(this.getY()<tg.getY()) {
				return DIRECTIONS.EAST;
			}
			else {
				return DIRECTIONS.WEST;
			}
		}
		
		if(up_check(this)==true && this.getY()==tg.getY()&&this.getX()>tg.getX()) {
			return DIRECTIONS.SOUTH;
		}
		
		if(up_check(this)==false && this.getY()==tg.getY()&&this.getX()<tg.getX()) {
			return DIRECTIONS.NORTH;
		}
		
		if(up_check(this)==false && up_check(tg)==false) {
			//(x1-y1)==(x2-y2) && x1<x2
			if(Math.abs(this.getX()-this.getY()) == Math.abs(tg.getX()-tg.getY())) {
				
				if(this.x>tg.getX()) {
					return MyCoordinate.DIRECTIONS.SOUTHWEST;
				}
				else if(this.x<tg.getX()) {
					return MyCoordinate.DIRECTIONS.SOUTHEAST;
				}
			}
		}
		
		else if(up_check(this)==true && up_check(tg)==true){
			if(Math.abs(this.getX()-tg.getX())==Math.abs(this.getY()-tg.getY())) {
				 if(this.getX()<tg.getX()) {//(x1-y1)==(x2-y2) && x1<x2	
					 
					 if(this.getY()>tg.getY()) {
						 return MyCoordinate.DIRECTIONS.NORTHWEST;
					 }
				 else {
					 return MyCoordinate.DIRECTIONS.NORTHEAST;
					 
				 }
				 }
				 if(this.getY()<tg.getY()) {
					 return MyCoordinate.DIRECTIONS.NORTHWEST;
				 }
				 
				 else if (this.getX()>tg.getX()) {
					 return MyCoordinate.DIRECTIONS.NONE;
				 }
			}
		}
			
			else if( up_check(this)==false && up_check(tg)==true) {
				if(Math.abs(this.getX()-tg.getX()) ==Math.abs(this.getY()-tg.getY()+1) &&this.getY()!=tg.getY() &&this.getX()>tg.getX()) {
					if (this.getX()>tg.getX() &&this.getY() >tg.getY()) {
					
					return MyCoordinate.DIRECTIONS.SOUTHWEST;
					}
					
					else  {
						
						return MyCoordinate.DIRECTIONS.SOUTHEAST;
					}
					
				}
				
				
				
				
				
			
				//delta x == delta y, src x less than target x, src y is greater than target y; 
				
				//southeast down to up delta x + 1 
			}
				
		
		
			else if(up_check(this)==true && up_check(tg)==false) {
				if(1+Math.abs(this.getX()-tg.getX())==Math.abs(this.getY()-tg.getY())) {
					System.out.println((tg.getX()-this.getX()));
					if(this.getY()>tg.getY()) {
						return MyCoordinate.DIRECTIONS.NORTHWEST;	
					}
						else if(this.getY()<tg.getY()) {
							return MyCoordinate.DIRECTIONS.NORTHEAST;
						}
					}
					
				}
			}
			
		
		
		
			return MyCoordinate.DIRECTIONS.NONE;
		
	
	
	}
	
	/**
	 * A function that checks if a coordinate is up facing or down facing
	 * @param c A triangleCoordinate
	 * @return
	 */
public boolean up_check( TriangleCoord c) {
	if(((c.getX()+c.getY()) %2) !=0) { // up check
		return true;//
	}
	
	return false;
}
	
	@Override
	public LinkedList NeighboringCoords() {
		LinkedList<TriangleCoord> neighbors_list= new LinkedList<>();
		/*
		int x_add=-1;// start with -1 and go to +1
		int y_add=-1;
		while(neighbors_list.size()<=8 ) {
			TriangleCoord sqc= new TriangleCoord((this.getX()+x_add),(this.getY())+y_add);
			neighbors_list.add(sqc);
			if(y_add==-1) {
				y_add=1;
			}
			if(y_add==1) {
				x_add++;
				y_add=-1;
			}*/ 
			
		
			
			
			
		
		neighbors_list.add(new TriangleCoord(this.getX(), this.getY()+1));// add the north neighbor
		neighbors_list.add(new TriangleCoord(this.getX(), this.getY()-1));//adding south neigbor
		neighbors_list.add(new TriangleCoord(this.getX()+1, this.getY()));//adding east neigbor
		neighbors_list.add(new TriangleCoord(this.getX()-1, this.getY()));//adding west neigbor	
		neighbors_list.add(new TriangleCoord(this.getX()+1, this.getY()+1)); // adding northeast neighbor
		neighbors_list.add(new TriangleCoord(this.getX()+1, this.getY()-1)); // adding northwest neighbor
		neighbors_list.add(new TriangleCoord(this.getX()-1, this.getY()-1));//adding southwest neighbor
		neighbors_list.add(new TriangleCoord(this.getX()-1, this.getY()+1));//adding southeast neighbor
		
		
		
		
		
		
		
		return neighbors_list;
		
	}

	
	@Override
	public MyCoordinate neighborAt(DIRECTIONS d) {
		LinkedList Neighbors=this.NeighboringCoords();	
		MyCoordinate ret=null;
		if(d!=null) {
			if(this.up_check(this)==true) {
			switch (d) {
			// conditions for other types to be added later
			
			case SOUTH:
				ret = (MyCoordinate) new TriangleCoord(this.getX()-1, this.getY());
			case EAST:
				ret = (MyCoordinate) new TriangleCoord(this.getX(), (this.getY()+1));
			case WEST:
				ret = (MyCoordinate) new TriangleCoord(this.getX(), (this.getY()-1));
			case NORTHEAST:
				ret = (MyCoordinate) new TriangleCoord(this.getX()+1, this.getY()+1);
		
			case NORTHWEST:
				ret = (MyCoordinate) new TriangleCoord(this.getX()+1, this.getY()-1);
			
				
			

		}
			System.out.println("neighbor:"+ret);
	
	 }
			
			else {
				switch (d) {
				// conditions for other types to be added later
				
				case NORTH:
					ret = (MyCoordinate) new TriangleCoord(this.getX()+1, this.getY());
				case EAST:
					ret = (MyCoordinate) new TriangleCoord(this.getX(), (this.getY()+1));
				case WEST:
					ret = (MyCoordinate) new TriangleCoord(this.getX(), (this.getY()-1));
				
				case SOUTHEAST:
					ret = (MyCoordinate)  new TriangleCoord(this.getX()-1, this.getY()+1);
				
				case SOUTHWEST:
					ret = (MyCoordinate) new TriangleCoord(this.getX()-1, this.getY()-1);
					
				
				
			}
				
			}
		
	}
	
	
	
	
	
return ret;
	}





	@Override
	public boolean isLinearTo(Coordinate c) {
		if(this.DirectionTo(c)!=DIRECTIONS.NONE) {
			return true;
		}
		return false;
	}
	
}


