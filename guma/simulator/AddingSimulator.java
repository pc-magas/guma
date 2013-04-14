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
		super(telestis1,telestis2);
		type=Praxis.ADDING;
	}

	/**
	*@override
	*/	
	public boolean next()
	{
			byte tempTelestis1=(telestis1Index<0)?0:telestis1[telestis1Index];
			byte tempTelestis2=(telestis2Index<0)?0:telestis2[telestis2Index];
			message="";
			
			if(telestis1Index<0 && telestis2Index<0)//run out of digits
			{
				if(kratoumeno!=0)//if carry remained
				{
					message+="Το κρατούμενο που περίσεψε το βάζω στο αποτέλεσμα.\n";
					result[resultIndex]=kratoumeno;
					return true;
				}
				message+="Τέλος προσομοίωσης";
				return false;
			}
			else
			{
				if(kratoumeno==0)//If carry
				{
					System.out.println("ADDING");
					message+="Προσθέτουμε τα ψηφία. Άν το άθροισμα των ψηφίων είναι μεγαλύτερο του 10 κρατάμε κρατούμενο το πρώτο ψηφίο του αθροίσματος.";
					temp=tempTelestis1+tempTelestis2;
				}
				else
				{
					System.out.println("Adding with carry");
					message+="Προσθέτουμε τα ψηφία μαζί με το κρατούμενο.\n Άν το άθροισμα των ψηφίων είναι διψήφιος κρατάμε κρατούμενο το πρώτο ψηφίο του αθροίσματος.";
					temp=kratoumeno+tempTelestis1+tempTelestis2;
					kratoumeno=0;
				}
				
				if(temp>=10)//if need to take a carry
				{
					message+="Το άθροισμα των ψηφίων είναι μεγαλύτερο του 10 κρατάμε κρατούμενο";
					kratoumeno=1;
					result[resultIndex]=(byte)(temp-10);
				}
				else
				{
					message+="Βάζουμε το άθροισμα των ψηφίων στο αποτέλεσμα";
					result[resultIndex]=(byte)temp;
				}
				resultIndex--;
				telestis1Index--;
				telestis2Index--;
				temp=-1;
				return true;
			}
			
	}
		
}
