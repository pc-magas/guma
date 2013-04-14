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
import guna.arithmetic.Praxis;

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
		super(telestis1,telestis2);
		type=Praxis.SUBSTRACTION;
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
			if(telestis1Index>=0)
			{
				int tempTelestis1=telestis1[telestis1Index];
				int tempTelestis2=(telestis2Index>=0)?telestis2[telestis2Index]:0;
				
				if(kratoumeno>0)
				{
					message="Προσθέτουμε το δανεικό στο "+tempTelestis2+" και θα γίνει ";
					tempTelestis2+=kratoumeno;
					message+=tempTelestis2+"\n";
					kratoumeno=0;
				}
				
				if(tempTelestis1<tempTelestis2)
				{

					message+="To "+tempTelestis1+" είναι μικρότερο από το "+tempTelestis2+". Θα χρειαστούμε ένα δανεικό που θα μπεί μπορστά από το "+tempTelestis1+" έτσι το "+tempTelestis1+"θα γίνει 1"+tempTelestis1+". Μετά εκτελούμε την πράξη μεταξύ των ψηφίων 1"+tempTelestis1+"-"+tempTelestis2;
					
					result[resultIndex]=(byte)((10+tempTelestis1)-tempTelestis2);
					
					kratoumeno=1;
					reduceIndex();
					
					returnVal=true;
					
				}
				else
				{
					message="Κάνουμε την αφαίρεση των ψηφίων ";
					result[resultIndex]=(byte)(tempTelestis1-tempTelestis2);
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
