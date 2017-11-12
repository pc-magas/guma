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
import guma.arithmetic.Praxis;
import guma.enums.PraxisType;

public class SubstractionSimulator extends SimpleSimulator
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
		super(telestis1,telestis2,PraxisType.SUBSTRACTION);
		
		if(telestis1>telestis2)
		{
			canOperate=true;
		}
		else
		{
			canOperate=false;
		}
	}

	@Override
	public boolean doPraxis()
	{
		boolean returnVal;
		message="";
		
		if(!canOperate)
		{
			message="H πράξη αυτή δεν μπορεί να γίνει.";
			returnVal=false;
			addStatus();
		}
		else
		{		
			try
			{
				int tempTelestis1=telestis1.getDigit();
				int tempTelestis2=0;
				try
				{
					tempTelestis2=telestis2.getDigit();
				}
				catch(IndexOutOfBoundsException o)
				{
					tempTelestis2=0;
				}
				
				if(kratoumeno>0)
				{
					message="Προσθέτουμε το δανεικό στο "+tempTelestis2+" και έτσι θα γίνει ";
					tempTelestis2+=kratoumeno;
					message+=tempTelestis2+"\n";
					kratoumeno=0;
					addStatus();
				}
				
				if(tempTelestis1<tempTelestis2)
				{

					message="To "+tempTelestis1+" είναι μικρότερο από το "+tempTelestis2+". Θα χρειαστούμε ένα δανεικό που θα μπεί μπορστά από το "+tempTelestis1+" έτσι το "+tempTelestis1+"θα γίνει 1"+tempTelestis1+". Μετά εκτελούμε την πράξη μεταξύ των ψηφίων 1"+tempTelestis1+"-"+tempTelestis2;
					
					result.setDigit((byte)((10+tempTelestis1)-tempTelestis2));
					
					kratoumeno=1;
					reduceIndex();
					
					returnVal=true;
					addStatus();
				}
				else
				{
					message="Κάνουμε την αφαίρεση των ψηφίων ";
					result.setDigit((byte)(tempTelestis1-tempTelestis2));
					reduceIndex();
					addStatus();
				}
				returnVal=true;
			}
			catch(IndexOutOfBoundsException o)
			{
				message="H πράξη τελείωσε";
				returnVal=false;
				addStatus(true);
			}
		}
		return returnVal;
	}
	
	/**
	*Method that reduces the indexes of telestis variables and result Index
	*/
	private void reduceIndex()
	{
		result.previousDigit();
		telestis1.previousDigit();
		telestis2.previousDigit();
	}
}
