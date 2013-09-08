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
import guma.arithmetic.Praxis;

public class AddingSimulator extends SimpleSimulator
{
	
	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the first operation
	*/
	public AddingSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2,Praxis.ADDING);
	}

	@Override	
	public boolean doPraxis()
	{
			byte tempTelestis1;
			byte tempTelestis2;
			message="";
				
			try
			{
				tempTelestis1=(byte)telestis1.getDigit();
			}
			catch(IndexOutOfBoundsException out1)
			{
				tempTelestis1=0;
			}
			
			try
			{
				tempTelestis2=(byte)telestis2.getDigit();
			}
			catch(IndexOutOfBoundsException out2)
			{
				tempTelestis2=0;
			}
			
			if(telestis1.getDigitPos()<0 && telestis2.getDigitPos()<0)//run out of digits
			{
				if(kratoumeno!=0)//if carry remained
				{
					message="Το κρατούμενο που περίσεψε το βάζω στο αποτέλεσμα.\n";
					result.setDigit(kratoumeno);
					kratoumeno=0;
					addStatus();
					return true;
				}
				message="Τέλος προσομοίωσης";
				addStatus(true);
				return false;
			}
			else
			{
				if(kratoumeno==0)//If carry
				{
					message="Προσθέτουμε τα ψηφία. Άν το άθροισμα των ψηφίων είναι μεγαλύτερο του 10 κρατάμε κρατούμενο το πρώτο ψηφίο του αθροίσματος.";
					temp=tempTelestis1+tempTelestis2;
					addStatus();
				}
				else
				{
					System.out.println("Adding with carry");
					message="Προσθέτουμε τα ψηφία μαζί με το κρατούμενο.\n Άν το άθροισμα των ψηφίων είναι διψήφιος κρατάμε κρατούμενο το πρώτο ψηφίο του αθροίσματος.";
					temp=kratoumeno+tempTelestis1+tempTelestis2;
					kratoumeno=0;
					addStatus();
				}
				
				if(temp>=10)//if need to take a carry
				{
					message="Το άθροισμα των ψηφίων είναι μεγαλύτερο του 10 κρατάμε κρατούμενο";
					kratoumeno=1;
					result.setDigit((byte)(temp-10));
					addStatus();
				}
				else
				{
					message="Βάζουμε το άθροισμα των ψηφίων στο αποτέλεσμα";
					result.setDigit((byte)temp);
					addStatus();
				}
				result.previousDigit();
				telestis1.previousDigit();
				telestis2.previousDigit();
				temp=-1;
				return true;
			}	
	}
		
}
