/**
*GUMA a simple math game for elementary school students
*	Copyright (C) 2014-2015  Dimitrios Desyllas (pc_magas)
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
package guma.ui.elements

import guma.ui.elements
public class UIMessage extends UIDescription
{
	/**
	*content of message
	*/
	private	String message="";

	/**
	*@param message: the message we want to display
	*@param use: what is use for this message
	*@param error: we show if it is a error message or not
	*/
	public UIMessage(String message,String use,boolean error)
	{
		super((error)?UIDescription.UI_ERROR:UIDescpription.UI_MESSAGE,use,"");
		setMessage(message);
	}
	
	public setMessage(String message)
	{
		this.message=message;
	}
	
	public String getMessage()
	{
		return message;
	}
}
