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

public class InternalStatus
{

	/**
	*Tells if this is the final status
	*/
	private boolean finalStatus=false;
	
	/**
	*Stores the string value of the simulator
	*/
	private String status="";
	
	/**
	*Stores the message of the curremt status
	*/
	private String message="";
	
	/**
	*Creates a Internal Status
	*@param: status: the status value
	*@param: message: the message of the current status
	*@param: finalStatus: tells if the status if final or not
	*/
	public InternalStatus(String status,String message,boolean finalStatus)
	{
		this.status=status;
		this.finalStatus=finalStatus;
	}
	
	/**
	*Creates a non final Status
	*@param: status: the status value
	*@param: message: the message of the current status
	*/
	public InternalStatus(String status,String message)
	{
		this(status,message,false);
	}
	
	/**
	*Returns the status value
	*/
	public String getStatusValue()
	{
		return status;
	}
	
	/**
	*We get if this status if final or not
	*@return returns true if this status is final
	*/
	public boolean isFinal()
	{
		return finalStatus;
	}
	
	/**
	*Returns the message of this status
	*/
	public String getMessage()
	{
		return message;
	}

}
