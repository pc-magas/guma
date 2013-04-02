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
	private int modulo=0;
	
	/**
	*Stores temporary the number the second operator  WITHOUT seperated digits
	*/
	private int telestis2Full=0;
	
	/**
	*Stores temporaly the current number that will be divided with telestis2 
	*/
	private byte[] tempSeperated=null;
	
	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the second operation
	*/
	public DivisionSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2);
		
		//Counting how many zeros have at the end each number
		int telestis1Zeros=zeroEndCount(this.telestis1);
		int telestis2Zeros=zeroEndCount(this.telestis2);
		
		//Removing the zeros from the end
		if(telestis1Zeros>0 && telestis2Zeros>0)
		{
			int min=Math.min(telestis1Zeros,telestis2Zeros);
			
			//We divide with the correct power of 10
			telestis1/=(int)Math.pow(10,min);
			telestis2/=(int)Math.pow(10,min);
			
			//Reseting the environment with removed the common number of zeros
			this.telestis1= AbstractSimulator.seperateDigits(telestis1);
			this.telestis2= AbstractSimulator.seperateDigits(telestis2);

		}
		
		telestis2Full=telestis2;
		
		telestis1Index=this.telestis2.length-1;
		telestis2Index=0;
		
		tempSeperated=new byte[this.telestis2.length];
		
		
		//Initilizing the number that will participate into division
		for(int i=0;i<tempSeperated.length;i++)
		{
			tempSeperated[i]=this.telestis1[i];
		}
		
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
			//If the selected digits not enough	
			if(temp2<telestis2Full)
			{
				if(temp2==0)
				{
					message+="Βάζουμε 0 εις το πιλίκο.";
					piliko.add(new Byte((byte)0));
					if(!katevazwPsifio())
					{
						message+="Η πράξη τελείωσε";
						modulo=temp2;
						return false;
					}
					return true;
				}
				
				message="To "+temp2+" δεν χωράει στο "+telestis2Full+". ";
				if(katevazwPsifio())
				{
					message+="Γι αυτό κατεβάζουμε και το "+telestis1[telestis1Index];
					return true;
				}
				else
				{
					message+="Tέλος πράξης το "+temp2+" μένει  σαν υπόλοιπο. Και βάζουμε 0 εις το πιλίκο";
					piliko.add(new Byte((byte)0));					
					return false;
				}
				
			}
			
			//Finding the correct digit of the result
			temp=temp2/telestis2Full;
			message="Το "+temp2+" χωράει "+ temp +" φορές στο "+telestis2Full+". ";	
			
			piliko.add(new Byte((byte)temp));
			
			int numr=telestis2Full*temp;
				
			message+="Αφαιρούμε το "+temp2+" με το "+numr+" (Προέκυψε από τον πολλαπλασιασμό του "+temp + " με τον διεραίτη τον "
					+telestis2Full+ " )." ;
				
			temp2-=numr;
			
			tempSeperated=seperateDigits(temp2);
			
			if(!katevazwPsifio())
			{
				message="Η πράξη τελείωσε";
				modulo=temp2;
				return false;
			}
			
		}
		
		return true;
	}
	
	/**
	*Adds an another digit to temp seperated 
	*/
	private boolean katevazwPsifio()
	{
		String s="";

		for(int i=0;i<tempSeperated.length;i++)
		{
			s+=tempSeperated[i];		
		}
		
		System.out.println("Before adding another digit: "+s);
		telestis1Index++;
		System.out.println("Telestis1Index: "+telestis1Index);
		
		if(telestis1Index<telestis1.length)
		{
			s+=telestis1[telestis1Index];
			tempSeperated=seperateDigits(Integer.valueOf(s));
			return true;
		}
		else
		{
			return false;
		}

	}
	
	/**
	*@overide
	*/
	public String getResult()
	{
		String s="";
		for(Byte b:piliko)
		{
			s+=b.toString();
		}
		return s;
	}
	
}
