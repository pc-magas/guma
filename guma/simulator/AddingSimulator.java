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

public class AddingSimulator extends AbstractSimulator
{

	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the first operation
	*/
	public AddingSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2);
	}

	/**
	*This Method does the next step of an arithmetic praxis Simulation
	*Returns true if it has next step to do
	*/	
	public boolean next()
	{
			byte tempTelestis1=(telestis1Index<0)?0:telestis1[telestis1Index];
			byte tempTelestis2=(telestis2Index<0)?0:telestis2[telestis2Index];

			if(telestis1Index<=0 && telestis2Index<=0)
			{
				if(kratoumeno!=0)
				{
					message="Το κρατούμενο που περίσεψε το βάζω στο αποτέλεσμα";
					result[resultIndex]=kratoumeno;
					return true;
				}
				message="Τέλος προσομοίωσης";
				return false;
			}
			else
			{
				if(temp==0)
				{
					if(kratoumeno==0)
					{
						if(telestis1Index<=0 || telestis2Index<=0)
						{
							message="Προσθέτουμε τα ψηφία";
						}
						else
						{
							message="Προσθέτουμε τα ψηφία. Άν το άθροισμα των ψηφίων είναι μεγαλύτερο του 10 κρατάμε κρατούμενο το πρώτο ψηφίο του αθροίσματος.";
						}
						temp=tempTelestis1+tempTelestis2;
					}
					else
					{
						if(telestis1Index<=0 || telestis2Index<=0)
						{
							message="Προσθέτουμε το κρατούμενο με το ψηφίο που μας περίσεψε";
						}
						else
						{
							message="Προσθέτουμε τα ψηφία μαζί με το κρατούμενο. Άν το άθροισμα των ψηφίων είναι μεγαλύτερο του 10 κρατάμε κρατούμενο το πρώτο ψηφίο του αθροίσματος.";
						}
						temp=kratoumeno+tempTelestis1+tempTelestis2;
					}
				}
				else
				{
					if(temp>=10)
					{
						message="Το άθροισμα των ψηφίων είναι μεγαλύτερο του 10 κρατάμε κρατούμενο";
						kratoumeno=1;
						result[resultIndex]=(byte)(temp-10);
					}
					else
					{
						message="Βάζουμε το άθροισμα των ψηφίων στο αποτέλεσμα";
						result[resultIndex]=(byte)temp;
					}
					resultIndex--;
					telestis1Index--;
					telestis2Index--;
				}
				return true;
			}
	}
		
}
