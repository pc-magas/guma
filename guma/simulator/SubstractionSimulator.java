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
import java.lang.Math;

public abstract class SubstractionSimulator extends AbstractSimulator
{

	/**
	*Shows if you can do the arithmetic operation
	*/
	private boolean canOperate=true;

	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the first operation
	*/
	public SubstractionSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2);
		
		if(telestis1>telestis2)
		{
			canOperate=true;
		}
		else
		{
			canOperate=false;
		}
	}

	/**
	*This Method does the next step of an arithmetic praxis Simulation
	*Returns true if it has next step to do
	*/
	public boolean next()
	{
		boolean returnVal;
		message="";
		if(!canOperate)
		{
			message="H πράξη αυτή δεν μπορεί να γίνει.";
			returnVal=false;
		}
		else
		{
			if(telestis1Index<0 && telestis2Index<0)
			{
				if(telestis1[telestis1Index]<telestis2[telestis2Index])
				{
					if(kratoumeno==0)
					{
						message="To "+telestis1[telestis1Index]+" είναι μικρότερο από το "+telestis2[telestis2Index]+"Θα χρειαστούμε ένα δανεικό που θα μπεί μπορστά από το "+telestis1[telestis1Index]+"\n έτσι το "+telestis1[telestis1Index]+"θα γίνει 1"+telestis1[telestis1Index];
						kratoumeno=1;
					}
					else
					{
						message="Κάνουμε την αφαίρεση των ψηφίων με την χρήση του δανεικού.";
						result[resultIndex]=(byte)((10+telestis1[telestis1Index])-telestis2[telestis2Index]);
						kratoumeno=0;						
						reduceIndex();
					}
				}
				else
				{
					message="Κάνουμε την αφαίρεση των ψηφίων με την χρήση του δανεικού.";
					result[resultIndex]=(byte)(telestis1[telestis1Index]-telestis2[telestis2Index]);
					reduceIndex();
				}
				returnVal=true;
			}
			else
			{
				message="H πράξη τελείωσε";
				returnVal=false;
			}
		}
		return returnVal;
	}
	
	/**
	*Method that reduces the indexes of telestis variables and result Index
	*/
	private void reduceIndex()
	{
		resultIndex--;
		telestis1Index--;
		telestis2Index--;
	}
}
