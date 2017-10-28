/**
*	GUMA a simple math game for elementary school students
*	Copyright (C) 2011-1012  Dimitrios Desyllas (pc_magas)
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
package guma.arithmetic;
import java.util.Random;

import guma.enums.PraxisType;
public class Diairesis extends Praxis
{

	/**
	*Helpful variable in order to generate a random diairesis
	*/
	private Random r=new Random();
	
	/**
	*Constructor methos that Creates a random Division arithmetic praxis
	*@param maxNum: The maximum number that can have an arithmetic praxis
	*/
	public Diairesis(int maxNum)
	{
		super(maxNum,1,2);

		if(!hasModulo())
		{
			results=1;
		}
		else
		{
			results=2;
		}
		praxistype=PraxisType.DIVISION;
	}

	/**
	*Constructor method that creates the arithmetis praxis telestis1/telestis2 or telestis2/telestis1
	*@param telestis1: The first operator of an arithmetic praxis
	*@param telestis2: The seciond operator on an arithmetic praxis
	*/
	public Diairesis(int telestis1,int telestis2)
	{
		super(telestis1,telestis2);

		if(!hasModulo())
		{
			results=1;
		}
		else
		{
			results=2;
		}
		praxistype=PraxisType.DIVISION;
	}

	/**
	*Returns a string form of the division
	*/
	public String toString()
	{
		int telestis1=getTelestis1();
		int telestis2=getTelestis2();
		
		/*Example:
		*If telestis1=3 and telestis2=5 then the following code shows "5/3="
		*If telestis1=7 and telestis2=3 then the following code shows "7/3="
		*(The greatest number goes to the front)
		*/
		return (telestis1>telestis2)?(String.valueOf(telestis1)+" / "+String.valueOf(telestis2)+" ="):
			(String.valueOf(telestis2)+" / "+String.valueOf(telestis1)+" =");
	}

	/**
	*Returns a sting form of the division including the result
	*/
	public String toFullString()
	{
		return (toString()+' '+apotelesma[0]+' '+"Υπόλοιπο Διαίρεσης:"+' '+apotelesma[1]);
	}

	
	/**
	*@override
	*This method executes the arithmetic praxis 
	*/
	protected void doPraxis()
	{
		int telestis1=getTelestis1();
		int telestis2=getTelestis2();

		if(telestis1<telestis2)
		{
			int temp=telestis1;
			telestis1=telestis2;
			telestis2=temp;	
		}

		this.apotelesma[0]= telestis1/telestis2;

		this.apotelesma[1]= telestis1%telestis2;
		
	}

	/**
	*Returns true if the modulo of this Division is 0 else it returns true
	*/
	public boolean hasModulo()
	{
		return (apotelesma[1]==0)?false:true;
	}
}
