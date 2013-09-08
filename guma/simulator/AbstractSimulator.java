/**
*GUMA a simple math game for elementary school students
*	Copyright (C) 2012-2013  Dimitrios Desyllas (pc_magas)
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*Contact with me by main at thes address: pc_magas@yahoo.gr
*/

package guma.simulator;

import java.util.*;
import guma.arithmetic.Praxis;
import guma.simulator.Number;
import java.util.ArrayList;
import guma.simulator.InternalStatus;

public abstract class AbstractSimulator 
{
	/**
	*The first number with seperated Digits that we will simulate the arithmetic operation
	*/
	protected Number telestis1=null;

	/**
	*The second number with seperated Digits that we will simulate the arithmetic operation
	*/
	protected Number telestis2=null;

	/**
	*Arraylist that we will keep the carry
	*/
	protected byte kratoumeno=0;

	/**
	*Array that will keep the final result
	*/
	protected Number result=null;


	/**
	*Stores temporaly the result
	*/
	protected int temp=0;

	/**
	*Show a messages to be displayed during the simulation
	*/
	protected String message="";

	/**
	*Variable that tells what type of operation simulates
	*/
	protected char type;
	
	/**
	*Linked list of Internal Status
	*/
	protected ArrayList<InternalStatus> status= new ArrayList<InternalStatus>();

	/**
	*Tells us if the simulation ended
	*/
	private boolean ended=false;
	
	/**
	*Gives us the selected item of the list
	*/
	protected int item=0;
	
	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the first operation
	*/
	public AbstractSimulator(int telestis1, int telestis2)
	{
		this.telestis1=new Number(telestis1);
		this.telestis2=new Number(telestis2);
		this.telestis1.setSelectedDigitToEnd();
		this.telestis2.setSelectedDigitToEnd();
		temp=0;
		addStatus();
	}
	
	/**
	*Returns the carry
	*/
	public int getCarry()
	{
		return kratoumeno;
	}

	/**
	*Returns the message
	*/
	public String getMessage()
	{
		return message;
	}

	/**
	*Returns the position of the result digit 
	*/
	public int getResDigit() throws IndexOutOfBoundsException
	{	
			return result.getDigit();
	}

	/**
	*Returns the position of the  digit of first Operator 
	*/
	public int getTelests1Digit() throws IndexOutOfBoundsException
	{
		return telestis1.getDigit();
	}

	/**
	*Returns the position of the  digit of first Operator 
	*/
	public int getTelests2Digit() throws IndexOutOfBoundsException
	{
		return telestis2.getDigit();
	}
	
	/**
	*Reprsents the telestis1 as String Form
	*@param front: The string that wioll be pun ton the front on non selected digit
	*@param back: The string that wioll be pun ton the back on non selected digit
	*@param frontSelected: The string that will be pun on the front selected digit
	*@param backSelected: The string that will be pun on the back selected digit
	*/
	public String getTelestis1(String front, String back, String frontSelected, String backSelected)
	{
		return telestis1.toString(front,back,frontSelected,backSelected);
	}
	
	/**
	*Reprsents the telestis1 as String Form
	*@param front: The string that wioll be pun ton the front on non selected digit
	*@param back: The string that wioll be pun ton the back on non selected digit
	*/
	public String getTelestis1(String front, String back)
	{
		return telestis1.toString(front,back,front,back);
	}
	
	/**
	*Reprsents the telestis2 as String Form
	*@param front: The string that wioll be pun ton the front on non selected digit
	*@param back: The string that wioll be pun ton the back on non selected digit
	*@param frontSelected: The string that will be pun on the front selected digit
	*@param backSelected: The string that will be pun on the back selected digit
	*/
	public String getTelestis2(String front, String back, String frontSelected, String backSelected)
	{
		return telestis2.toString(front,back,frontSelected, backSelected);
	}
	
	/**
	*Reprsents the telestis2 as String Form
	*@param front: The string that wioll be pun ton the front on non selected digit
	*@param back: The string that wioll be pun ton the back on non selected digit
	*/
	public String getTelestis2(String front, String back)
	{
		return getTelestis2(front,back,front,back);
	}
	
	/**
	*Reprsents the telestis2 as String Form
	*/
	public String getTelestis2()
	{
		return getTelestis2("","");
	}
	
	/**
	*Returns the first operator
	*@param front:  The sting you want to be be th the front of a digit
	*@param back: The string you want to be at the back of a digit
	*@param pos: select a specified position that will have seperate texnt on the front and back
	*@param posFront: The sting you want to be on the front of a digit at specified positions, given by pos paramenter
	*@param posBack: The sting you want to be at the back of a digit at specified positions, given by pos parameter
	*/
	public String getResult(String front, String back, String posFront, String posBack)
	{
		if(result!=null)
		{
			System.out.println("Getting the result String");
			return result.toString(front,back,posFront,posBack);
		}
		else
		{
			return front+"0"+back;
		}
	}
	
	/**
	*Returns the first operator
	*@param front:  The sting you want to be be th the front of a digit
	*@param back: The string you want to be at the back of a digit
	*@param pos: select a specified position that will have seperate texnt on the front and back
	*/
	public String getResult(String front, String back)
	{
		return getResult(front,back,front,back);
	}
	
	/**
	*gets the result as string without anything special on the front or the back
	*/
	public String getResult()
	{
		return getResult("","");
	}
	
	/**
	*Creates a Simulator Bazed on the praxisType is given on Praxis Type
	*@param telestis1: the first operator (depending in the operation) of the operation we want to simulate
	*@param telesits2: the second operator (depending in the operation) of the operation we want to simulate
	*@param praxisType: The type of Operation that tells what kind of simulator we want
	*/
	public static AbstractSimulator makeSimulator(int telestis1, int telestis2,char praxisType)
	{
		AbstractSimulator a=null;
		switch(praxisType)
		{
			case Praxis.ADDING: a= new AddingSimulator(telestis1,telestis2);
			break;
			
			case Praxis.SUBSTRACTION:
				if(telestis1>telestis2)
				{
					a= new SubstractionSimulator(telestis1,telestis2);
				}
				else
				{
					a= new SubstractionSimulator(telestis2,telestis1);
				}
			break;
			
			case Praxis.DIVISION:
				if(telestis1>telestis2)
				{
					a= new DivisionSimulator(telestis1,telestis2);
				}
				else
				{
					a= new DivisionSimulator(telestis2,telestis1);
				}
				break;
			
			case Praxis.MULTIPLICATION: a= new MultiplicationSimulator(telestis1,telestis2,false);
			break;
			
			default: a=null;		
		}
		
		return a;
	}
	
	

	/**
	*Gets the next step of the operation
	*/
	public InternalStatus next()
	{
		try
		{
			//If simulation not ended and
			if(!ended)
			{
				while(doPraxis()){}
				ended=true;
			}
		
			InternalStatus status_=status.get(item);
			item++;
		
			return status_;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	*Adds a status to the simulator
	*/
	protected void addStatus(boolean finalStatus)
	{
		System.out.println("Adding Status");
		status.add(new InternalStatus(this.toString(),this.getMessage(),this.getCarry(),finalStatus));
	}
	
	/**
	*Adds a  non final status to the simulator
	*/
	protected void addStatus()
	{
		addStatus(false);
	}
	
	/**
	*This Method does the next step of an arithmetic praxis Simulation
	*Returns true if it has next step to do
	*/
	public abstract boolean doPraxis();

	/**
	*This method shows as String the operation of simulator by using html
	*/
	public abstract String toString();
	
	
}
