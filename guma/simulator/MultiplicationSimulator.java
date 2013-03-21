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

import guma.simulator.AbstractSimulator;
import java.util.Arrays;

public class MultiplicationSimulator extends AbstractSimulator
{

	/**
	*Array List that stores intermediate results
	*/
	private byte[][] endiamesa=null;

	/**
	*Shows index of intermediate results
	*/
	private int endiamesaIndex=0;

	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the second operation
	*/
	public MultiplicationSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2);
		endiamesa=new byte[telestis2.length][];
		
		for(int i=0;i<endiamesa.length;i++)
		{
			endiamesa[i]=new byte[telestis1.length+1];
			Arrays.fill(endiamesa[i],(byte)0);
		}
		result=new byte[telestis1.length+telestis2.length];
		resultIndex=result.length-1;
		endiamesaIndex=endiamesa[1].length-1;
	}

	/**
	*This Method does the next step of an arithmetic praxis Simulation
	*Returns true if it has next step to do
	*/	
	public boolean next()
	{
		if(telestis2Index>=0)
		{
			if(telestis1Index>=0)
			{
				if(kratoumeno!=0)
				{
					message="Πολλαπλασιάζουμε τα ψηφία "+telestis1[telestis1Index]+"*"+telestis2[telestis2Index];
					
				}
			}
		}
		else
		{
			//do adding between results
		}
	}
		
}
