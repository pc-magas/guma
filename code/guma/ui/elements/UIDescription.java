/**
*	GUMA a simple math game for elementary school students
*	Copyright (C) 2014-1015  Dimitrios Desyllas (pc_magas)
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
*    
*	Contact with me by main at this address: pc_magas@yahoo.gr
*/


package guma.ui.elements;

public abstract class UIDescription
{
	
	/**
	*For frame use
	*/
	public static final byte UI_FRAME=1;

	/**
	*For Message use
	*/
	public static final byte UI_MESSAGE=2;
	
	/**
	*For Error use
	*/
	public static final byte UI_ERROR=3;

	/**
	*For another popup frame use
	*/
	public static final byte UI_POPUP=4;

	/**
	*Stores the use of this UIDescription
	*/
	protected String use="";
	
	/**
	*Storing the type of UIElement that updates this UIDescription
	*/
	protected byte type=0;
	
	/**
	*Creates a plain UIDescription Object
	*/
	public UIDescription()
	{
		use="";
		type=0;
	}

	/**
	*
	*/
	public UIDescription(byte type,String use)
	{
		setType(type);
		setUse(use);
	}
	
	/**
	*Clones the instance of UIDescription 
	*/
	public abstract class UIdescription clone();
	
	/**
	*Method that sets a Type to the UIElement
	*@param type: the type of UIElement we want to.
	*/
	public void setType(boolean type)
	{
		if(type==UI_FRAME || type==UI_MESSAGE || type==UI_ERROR || type==UI_POPUP) 
		{
			this.type=type;
		}
		else
		{
			this.type=0;
		}
	}

	/**
	*Method that sets the use of the Frame
	*@param use: the use of the UIElement	
	*/
	public void setUse(String use)
	{
		this.use=use;
	}
	
	/**
	*Getting the type of UIDescription
	*/
	public byte getType()
	{
		return type;
	}

	/**
	*Getting the use of UI description
	*/
	public String getUse()
	{
		return use;
	}
}
