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

import java.util.Arrays;

/**
*Method that represend a positive interger number
*/
public class Number
{

	/**
	*Variable thaty stores the integer value of the number
	*/
	private int value;
	
	/**
	*Stores the value od the number with seperated digits
	*/
	private byte[] digits=null;
	
	/**
	*Charater that will put befor the zeros that ands the number if it is into a string form
	*/
	private String zeroSepetator="";
	
	/**
	*Tells where is the first zero that is on the end of number.<br> Eg: For the number 100 the value will be 1 
	*<br>and for 11000 the value will be 2 etc etc  
	*/
	private int firstEndzero=0;
	
	/**
	*Interger that tells us a digit that is marked
	*/
	private int selectedDigit=-1;
	
	/**
	*Keeps how many zeros has on the end the number
	*/
	private int endZeroCount=0;
	
	
	/**
	*Constructor Method: Initializes a Number with zeros or ones 
	*/
	public Number(int digitsNo, boolean zeros)
	{
		digits=new byte[digitsNo];
		if(zeros)
		{
			Arrays.fill(digits,(byte)0);
			value=0;
		}
		else
		{
			Arrays.fill(digits,(byte)1);
			value=mergeDigits(digits);
		}
	}
	
	/**
	*Constructor method Creates a number of specified number
	*@param value: the intherger value of the number
	*/
	public Number(int value)
	{
		this.value=value;
		digits=Number.seperateDigits(value);
		zeroEndCount();
	}
	
	/**
	*Method that seperates the digits from this number
	*@param: number: the number we want to seperate the digits
	*/
	public static byte[] seperateDigits(int number)
	{
		byte[] intermediate=new byte[String.valueOf(number).length()];
		
		for(int i=0;i<intermediate.length;i++)
		{
			intermediate[intermediate.length-1-i]=(byte)(number%10);
			number/=10;
		}		
		return intermediate;
	}

	/**
	*Method that merges a number with seperated digits
	*@param digits: Number with seperated ditits	
	*/
	public static int mergeDigits(byte[] digit)
	{
		int value=0;
		for(int i=0;i<digit.length;i++)
		{
			int pow=digit.length-1-i;
			value+=digit[i]*(long)Math.pow(10,pow);
		}
		return value;
	}
	
	/**
	*A way to return the operatos as String with distinct space between tht digits
	*@param num: the number with seperated Digits
	*@param front:  The sting you want to be on the front of a digit
	*@param back: The string you want to be at the back of a digit
	*@param posFront: The sting you want to be on the front of a digit at specified positions, given by pos paramenter
	*@param posBack: The sting you want to be at the back of a digit at specified positions, given by pos parameter
	*/
	public String toString(String front, String back, String posFront, String posBack)
	{
			String s="";
			String n="";
			for(int i=0;i<digits.length;i++)
			{
				if(i==firstEndzero)
				{
					n=""+zeroSepetator+digits[i];
				}
				else
				{
					n=""+digits[i];
				}
				
				if(i==selectedDigit)
				{
					s+=posFront+n+posBack;
				}
				else
				{
					s+=front+n+back;
				}
			}
			return s;
	}
	
	/**
	*Counts how many zeros has on the end a number with seperated digits.
	*/
	private void zeroEndCount()
	{
		endZeroCount=0;
		for(int i=digits.length-1;i>=0;i--)
		{
			if(digits[i]==0)
			{
				endZeroCount++;
			}
			else
			{
				break;
			}
		} 
		firstEndzero=digits.length-endZeroCount;
	}
	
	/**
	*Removes Zeros at the end of number
	*/
	public Number removeEndZero(int zeros)
	{
		return new Number((int)value/(int)Math.pow(10,zeros));
	}
	
	/**
	*Returns How many zeros has a Number
	*/
	public int getendZeroCount()
	{
		return endZeroCount;
	}
	
	/**
	*Returns the current selected Digit
	*/
	public int getDigitPos()
	{
		return selectedDigit;
	}
	
	/**
	*Select a digit
	*@param: pos the position of digit we want to set as selected
	*/
	public void setDigitPos(int pos)
	{
		setDigitPos(pos,false);
	}
	
	/**
	*Select a digit relativlely from he end of from the begining
	*@param: pos: How many digits we want to select
	*@param fromTheEnd: if we want to select from the begining or from the end
	*/
	public void setDigitPos(int pos,boolean fromTheEnd)
	{
		if(pos>0)
		{
			if(fromTheEnd)
			{
				selectedDigit=digits.length-1-pos;
			}
			else
			{
				selectedDigit=pos;
			}
		}
		System.out.println("Number length: "+this.length()+" Digit position"+selectedDigit);
	}
	
	/**
	*Gets a digit from a given parameter
	*/
	public byte getDigit(int digit) throws IndexOutOfBoundsException
	{
		return digits[digit];
	}
	
	/**
	*Resets a the digit count
	*/
	public void setSelectedDigitToEnd()
	{
		setSelectedDigitToEnd(false);
	}
	
	/**
	*Resets a the digit count
	*@param before end zeros: if the number ends with zero digit thet it sets to the last digit that is not non zero 
	*/
	public void setSelectedDigitToEnd(boolean beforeEndZeros)
	{
		if(digits!=null)
		{
			if(beforeEndZeros && endZeroCount!=0)
			{
				selectedDigit=firstEndzero;
			}
			else
			{				
				selectedDigit=digits.length-1;
			}
		}
	}
	
	
	
	/**
	*Resets a the digit count
	*/
	public void setSelectedDigitToBegin()
	{
		if(digits!=null)
		{
			selectedDigit=0;
		}
	}
	
	/**
	*Returns The selected digit
	*/
	public int getDigit() throws IndexOutOfBoundsException
	{
		return digits[selectedDigit];
	}
	
	/**
	*Select next digit
	*/
	public void nextDigit()
	{
		selectedDigit++;
	}
	
	/**
	*Select next  Previous digit
	*/
	public void previousDigit()
	{
		selectedDigit--;
	}
	
	/**
	*Returns the value of the number
	*/
	public int getValue()
	{
		return value;
	}
	
	/**
	*Set value to the Selected Digit
	*@param digitValue: the value of the selected digit
	*/
	public boolean setDigit(byte digitValue)
	{
		try
		{
			if(digitValue>=0)
			{
				digits[selectedDigit]=digitValue;
				value=mergeDigits(digits);
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(IndexOutOfBoundsException o)
		{
			return false;
		}
	}
	
	/**
	*Returns the number of digits
	*/
	public int length()
	{
		return digits.length;
	}
	
	/**
	*Sets the sepetator String that seperates the  ending zeros from the rest of the number
	*@param: seperator: the string that seperate the number from the ending zeros
	*/
	public void setSeperator(String seperator)
	{
		zeroSepetator=seperator;
	}
	
	/**
	*Returns the seperated digits
	*/
	public byte[] getSeperatedDigits()
	{
		return digits;
	}
}
