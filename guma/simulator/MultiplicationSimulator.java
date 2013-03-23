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
	*Stores for each intermediate result where is the last digit that will participate into filan adding
	*/
	private int[] endiamesaLastDigit=null;
	/**
	*Shows what intermediate result will be chosen 
	*/
	private int endiamesoApotelesmaIndex=0;

	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the second operation
	*/
	public MultiplicationSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2);
		endiamesa=new byte[this.telestis2.length][];
		endiamesaLastDigit=new int[this.telestis2.length];

		for(int i=0;i<endiamesa.length;i++)
		{
			//From 
			endiamesa[i]=new byte[this.telestis1.length+1+i];
			endiamesaLastDigit[i]=endiamesa[i].length-1;
			Arrays.fill(endiamesa[i],(byte)0);
		}
		result=new byte[this.telestis1.length+this.telestis2.length];
		resultIndex=result.length-1;
		endiamesaIndex=endiamesa[1].length-1;
		
	}

	/**
	*This Method does the next step of an arithmetic praxis Simulation
	*Returns true if it has next step to do
	*/	
	public boolean next()
	{
		message="";
		if(telestis2Index>=0)
		{
			if(telestis1Index>=0)
			{
				message="Πολλαπλασιάζουμε τα ψηφία "+telestis1[telestis1Index]+"*"+telestis2[telestis2Index];
				temp=telestis1[telestis1Index]*telestis2[telestis2Index];
				message+=" και ο πολαπλασιαμός έχει αποτέλεσμα: "+endiamesa[endiamesoApotelesmaIndex][endiamesaIndex];
				if(kratoumeno!=0)
				{
					message+="\n Επειδή από την προηγούμενη πράξη έχουμε κρατούμενο το προσθέτουμε στο αποτέλεσμα";					
					temp+=kratoumeno;
				}

				byte tempM[]=AbstractSimulator.seperateDigits((int)endiamesa[endiamesoApotelesmaIndex][endiamesaIndex]);
				
				if(tempM.length>1)
				{
					message="\n Η πράξη μας είχε διψήφιο αποτέλεσμα άρα το πρώτο ψηφίο το κρατάμε κρατούμενο";
					kratoumeno=tempM[0];
					endiamesa[endiamesoApotelesmaIndex][endiamesaIndex]=tempM[1];
				}
				else
				{
					kratoumeno=0;
					endiamesa[endiamesoApotelesmaIndex][endiamesaIndex]=(byte)temp;
				}
				return true;
			}
			else
			{
				telestis2Index--;
				telestis1Index=telestis1.length-1;
				
				if(telestis2Index>=0 && resultIndex<endiamesaLastDigit.length)
				{
					message="Βάζουμε ένα 0 κάτω από το "+endiamesa[resultIndex][endiamesa[resultIndex].length-1];
					resultIndex++;
					endiamesaLastDigit[resultIndex]=endiamesaLastDigit[resultIndex-1]-1;
				}
				return true;
			}
		}
		else
		{
			temp=0;
			message="Προσθαίτουμε τα ενδιάμεσα αποτελέσματα.\nΤέλος προσομοίωσης";
			for(int i=0;i<endiamesa.length;i++)
			{
				temp+=mergeDigits(endiamesa[i]);
			}
			
			result=seperateDigits(temp);

			return false;
		}
	}
		
}
