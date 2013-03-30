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
import java.util.ArrayList;


public class DivisionSimulator extends AbstractSimulator
{

	
	/**
	*Stores the Final Result
	*/
	private ArrayList<Byte> piliko=new ArrayList<Byte>();
	
	/**
	*Stores Modulo
	*/
	private byte modulo=0;
	
	/**
	*Stores temporary the number the second operator  WITHOUT seperated digits
	*/
	private int telestis2Full=0;
	
	/**
	*Stores temporaly the current number that will be divided with telestis2 
	*/
	private byte[] tempSeperated=null;
	
	/**
	*Tells if found a number to divide
	*/
	private boolean found=false;
	
	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the second operation
	*/
	public DivisionSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2);
		
		telestis2Full=telestis2;
		
		telestis1Index=this.telestis2.length-1;
		telestis2Index=0;
		
		tempSeperated=new byte[this.telestis2.length];
		
		System.arraycopy(this.telestis1,0,tempSeperated,0,tempSeperated.length);
		
		temp=-1;
	}

	/**
	*This Method does the next step of an arithmetic praxis Simulation
	*Returns true if it has next step to do
	*/	
	public boolean next()
	{
		message="";
		int temp2=mergeDigits(tempSeperated);
		
		if(telestis1Index<telestis1.length)
		{
			if(!found)
			{
				if(temp<0)
				{
					temp=tempSeperated[0]/telestis2[0];
					message="Το "+tempSeperated[0]+" χωράει "+ temp +" φορές στο "+telestis2[0];
				}
				else
				{
					message="Το "+temp+" δεν μας κάνει γιατί "+temp+"X"+telestis2Full+"= "+temp*telestis2Full+" που είναι μεγαλύτερο από το "+temp2+". Γι αυτό δοκιμάζουμε με το αμέσως προηγούμενο";
					temp--;
				}
			}	
			else
			{
				piliko.add(new Byte((byte)temp));
				temp=-1;
			}
				
			return true;
		}
		else
		{
			message="Η πράξη τελείωσε";
			return false;
		}
		

	}		
}
