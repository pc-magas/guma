/**
*	GUMA a simple math game for elementary school students
*	Copyright (C) 2012-1013  Dimitrios Desyllas (pc_magas)
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
*Contact with me by main at this address: pc_magas@yahoo.gr
*/
package guma.simulator.abs;

import java.nio.charset.Charset;

/**
*An arithmetic Operation Simulator. This class is an abstract arithmetic operation simulator that will do step by step 
*an arithmetic operation.
*@author pc_magas
*/
public abstract class AbstractSimulator
{

	/**
	*Stores the first number that will participate into emulation with seperated digits 
	*/
	protected byte[] telestis1;
	
	/**
	*Stores the second number that will participate into emulation with seperated digits
	*/
	protected byte[] telestis2;

	/**
	*Stores the final result that will come during the emulation.
	*/	
	protected byte[] finalResult;

	/**
	*Returns a number with seperated digits
	*@param number 
	*/
	public static byte[] seperateDigits(int number)
	{
		byte[] n=String.valueof(number).getBytes(Charset.US_ASCII);
	
		//Making the 'Number value' as number value
		for(int i; i<n.length;i++)
		{
			byte[i]-=48;
		}

		return byte;
	}
	
	/**
	*Constructor of AbstractSimulator
	*@param telestis1: The first number that will participate into an arithmetic operation simulation
	*@param telestis2: The second number that will participate into an arithmetic operation simulation
	*/
	public AbstractSimulator(int telestis1, int telestis2)
	{
		telestis1=seperateDigits(telestis1);
		telestis2=seperateDigits(telestis2);
	}

	/**
	*Get a string as Instance
	*/
	public abstract toString();
	
	/**
	*Move as next step
	*/
	public abstract next();
}
