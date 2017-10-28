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

import java.util.*;

import guma.enums.PraxisType;

/**
*A Simple simulator that works as base for Adding and Substraction
*/
public abstract class SimpleSimulator extends guma.simulator.AbstractSimulator
{

	/**
	*Constructor Method
	*@param telestis1: the first operator of the number that we will simulate the first operation
	*@param telestis2: the second operator of the number that we will simulate the first operation
	*@param type: the type or praxis we simulate
	*/
	public SimpleSimulator(int telestis1, int telestis2,PraxisType type)
	{
		super(telestis1,telestis2,type);
		this.result=new guma.simulator.Number((Math.max(this.telestis1.length(),this.telestis2.length())+1),true);
		result.setSelectedDigitToEnd();
	}
	
	
	@Override
	public String toString()
	{
		String s="";
		
		s="<table><tr><td> </td>";
		
		if(telestis2.length()>telestis1.length())
		{
				
			for(int i=(telestis2.length()-telestis1.length());i>0;i--)
			{
				s+="<td> </td>";
			}
			s+=getTelestis1("<td>","</td>","<td><font color=\"#006400\">","</font></td>")
						+"<tr><td>"+ type+"</td>";
			s+=getTelestis2("<td>","</td>","<td><font color=\"#006400\">","</font></td>");
							
		}
		else
		{
			s+=getTelestis1("<td>","</td>","<td><font color=\"#006400\">","</font></td>")
						+"<tr><td>"+ type+"</td>";	
			for(int i=(telestis1.length()-telestis2.length());i>0;i--)
			{
				s+="<td> </td>";
			}
			s+=getTelestis2("<td>","</td>","<td><font color=\"#006400\">","</font></td>");
		}
		s+="</tr></table><hr><table><tr>"+getResult("<td>","</td>","<td><font color=\"#006400\">","</font></td>")+
				"</tr></table>";								
			
		return s;
	}
	
}
