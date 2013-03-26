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

/**
*A Simple simulator that works as basis for Adding and Substraction
*/
public abstract class SimpleSimulator extends guma.simulator.AbstractSimulator
{

	
	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the first operation
	*/
	public SimpleSimulator(int telestis1, int telestis2)
	{
		super(telestis1,telestis2);
		
		int maxLength=	(int)((this.telestis1.length>this.telestis2.length)? this.telestis1.length : this.telestis2.length);
		
		resultIndex=maxLength;
		result=new byte[resultIndex+1];
		Arrays.fill(result,(byte)0);
		temp=0;	
	}

	
}
