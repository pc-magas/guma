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
import java.util.Arrays;
import guma.arithmetic.Praxis;

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
	*Stores temporaly the current number that will be divided with telestis2 
	*/
	private byte[] tempSeperated=null;
	
	/**
	*Stores miscelanous info (such as intermediate values from the division)
	*/
	private ArrayList<byte[]> miscelanous=new ArrayList<byte[]>();
	
	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the second operation
	*/
	public DivisionSimulator(int telestis1,int telestis2)
	{
		super(telestis1,telestis2,Praxis.DIVISION);
		
		//Counting how many zeros have at the end each number
		int telestis1Zeros=this.telestis1.getendZeroCount();
		int telestis2Zeros=this.telestis2.getendZeroCount();
		
		//Removing the zeros from the end
		if(telestis1Zeros>0 && telestis2Zeros>0)
		{
			int min=Math.min(telestis1Zeros,telestis2Zeros);
			message="Αρχικά ο Διαρέτης ήταν "+telestis1+" και ο διεραιτέος ήταν."+telestis2+", γι αυτό αφαιρούμε το κοινό αριθμό μηδενικών από το τέλος. ";
			//We divide with the correct power of 10
			this.telestis1=this.telestis1.removeEndZero(min);
			this.telestis2=this.telestis2.removeEndZero(min);
			
			telestis1Zeros=0;
			telestis2Zeros=0;
			addStatus();
			
			
		}
		
		this.telestis1.setDigitPos((int)(this.telestis2.length()-1));
		
		System.out.println("DIVISION  Telestis 1 Selected Digit= "+this.telestis1.getDigitPos());
		System.out.println("DIVISION  Telestis 2 Length= "+this.telestis2.length());
			
		tempSeperated=new byte[this.telestis2.length()];
		
		//Initilizing the number that will participate into division
		for(int i=0;i<tempSeperated.length;i++)
		{
			//System.out.println("i val:"+i+" length:"+tempSeperated.length);
			tempSeperated[i]=this.telestis1.getDigit(i);
		}
		
		/*System.out.println("++++++++++++++++++++++++PRINTING TEMP SEPERATED+++++++++++++++++++++++");
		for(int i=0;i<tempSeperated.length;i++)
		{
			System.out.print(tempSeperated[i]);
		}
		
		System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");*/
		
		message="Κατεβάζουμε από τον διεραιτέο τόσα ψηφία όσα είναι και ο διεραιτης";
		temp=-1;
		addStatus();
	}

	@Override
	public boolean doPraxis()
	{
		message="";
		int temp2=Number.mergeDigits(tempSeperated);
		
		System.out.println("Selected Digit= "+this.telestis1.getDigitPos()+"\n Temp2="+temp2);
		try
		{	
			
			//If the selected digits not enough	
			if(temp2<telestis2.getValue())
			{	
				System.out.println("ΜΠΗΚΑ===");
				if(temp2==0)
				{
					System.out.println("=======Κάτι=======");
					message="Βάζουμε 0 εις το πιλίκο.";
					piliko.add(new Byte((byte)0));
					addStatus();
					katevazwPsifio();
					return true;
				}
				
				message="To "+temp2+" δεν χωράει στο "+telestis2.getValue()+". ";
				addStatus();
				try
				{
					katevazwPsifio();
					message="Γι αυτό κατεβάζουμε και το "+telestis1.getDigit();
					addStatus();
					return true;
				}
				catch(IndexOutOfBoundsException out)
				{
					message+="Tέλος πράξης το "+temp2+" μένει  σαν υπόλοιπο. Και βάζουμε 0 εις το πιλίκο";
					piliko.add(new Byte((byte)0));
					addStatus(true);				
					return false;
				}
				
			}
			
			//Finding the correct digit of the result
			temp=temp2/telestis2.getValue();
			message="Το "+temp2+" χωράει "+ temp +" φορές στο "+telestis2.getValue()+". ";	
			
			piliko.add(new Byte((byte)temp));
			
			int numr=telestis2.getValue()*temp;
			addStatus();

			message="Αφαιρούμε το "+temp2+" με το "+numr+" (Προέκυψε από τον πολλαπλασιασμό του "+temp + " με τον διεραίτη τον "
					+telestis2.getValue()+ " )." ;
				
			temp2-=numr;
			tempSeperated=Number.seperateDigits(temp2);
			addStatus();
			
			katevazwPsifio();

		}
		catch(IndexOutOfBoundsException o)
		{	
			System.out.println("END");

			/*if(temp2==0 && telestis1.getLastDigit()==0)
			{
				message="Βάζουμε 0 εις το πιλίκο.";
				piliko.add(new Byte((byte)0));
				addStatus();
			}*/
			
			modulo=temp2;
			message="Η πράξη τελείωσε";
			addStatus(true);
			return false;
		}
		
		return true;
	}
	
	/**
	*Adds an another digit to temp seperated 
	*/
	private void katevazwPsifio() throws IndexOutOfBoundsException
	{
		String s="";

		for(int i=0;i<tempSeperated.length;i++)
		{
			s+=tempSeperated[i];		
		}

		
		telestis1.nextDigit();
		
		try
		{	
			s+=telestis1.getDigit();
			tempSeperated=Number.seperateDigits(Integer.valueOf(s));
			miscelanous.add(tempSeperated);

		}
		catch(IndexOutOfBoundsException e)
		{
			miscelanous.add(tempSeperated);
			throw e;
		}

	}
	
	
	@Override
	public String getResult(String front, String back,String posFront, String posBack)
	{
		String s="";
		front=(front!=null)?front:"";
		back=(back!=null)?back:"";
		posFront=(posFront!=null)?posFront:"";
		posBack=(posBack!=null)?posBack:"";
		int i=0;
		if(piliko!=null)
		{
			for(Byte b:piliko)
			{
				if(i==(piliko.size()-1))
				{
					s+=posFront+b.toString()+posBack;
				}
				else
				{
					s+=front+b.toString()+back;
				}
			}
		}
		else
		{
			s="";
		}
		return s;
	}
	
	
	@Override
	public String getResult(String front, String back)
	{
		return getResult(front,back,front,back);
	}
	
	@Override
	public String getResult()
	{
		return getResult("","");
	}
	
	
	@Override
	public String toString()
	{
		String s="";
		
		//designinig a table of tables so I can have the vertical line fos shpwing the division
		s+="<table rules=\"cols\"> <tr><td style=\"padding:0.25em; border-right:1px solid black;\"><table>"
			+"<tr>"+getTelestis1("<td>","</td>","<td><font color=\"blue\">","</font></td>")+"</tr></td>";
		
		
		for(int i=0;miscelanous!=null && i<miscelanous.size()  ;i++)
		{
			byte[] temp=miscelanous.get(i);
			
			if(!Arrays.equals(telestis1.getSeperatedDigits(),temp))
			{
				s+="<tr>";
				for(int j=0; j<i;j++)
				{
					s+="<td>\t</td>";
				}
				Number tamp=new Number(Number.mergeDigits(temp));
				s+=tamp.toString("<td>","</td>","<td>","</td>")+"</tr>";
			}
		}
		
		s+="</table></td><td><table rules=\"rows\">"+
			"<tr style=\"padding:0.25em; border-bottom:1px solid black;\">"+getTelestis2("<td>","</td>")
			+"</tr><tr>"+getResult("<td>","</td>","<td><font color=\"blue\">","</font></td>")
			+"</tr></table></td></tr></table>";
			
		return s;
	}
	
}
