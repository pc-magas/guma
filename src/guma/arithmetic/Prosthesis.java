/**
*GUMA a simple math game for elementary school students
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

import guma.enums.PraxisType;

/**
*Class that simulates adding between prosthesis
*/
public class Prosthesis extends Praxis
{
	
	/**
	*Constructor methos that Creates a random Adding arithmetic praxis
	*@param maxNum: The maximum number that can have an arithmetic praxis
	*/
	public Prosthesis(int maxNum)
	{
		super(maxNum);
		praxistype=PraxisType.ADDING;
	}
	
	/**
	*Constructor method that creates the arithmetis praxis telestis1+telestis2
	*@param telestis1: The first operator of an arithmetic praxis
	*@param telestis2: The seciond operator on an arithmetic praxis
	*/
	public Prosthesis(int telestis1, int telestis2)
	{
		super(telestis1,telestis2);
		praxistype=PraxisType.ADDING;
	}

	/**
	*Returns a string form of the prosthesis
	*/
	public String toString()
	{
		return String.valueOf(getTelestis1())+" + "+String.valueOf(getTelestis2())+" =";
	}

	/**
	*Returns a sting form of the prosthaisis including the result
	*/
	public String toFullString()
	{
		return toString()+' '+String.valueOf(apotelesma[0]);
	}
	/**
	*@override
	*This method executes the arithmetic praxis 
	*/
	protected void doPraxis()
	{
		super.apotelesma[0]=super.getTelestis1()+super.getTelestis2();
	}	
}
