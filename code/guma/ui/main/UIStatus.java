/**
*GUMA a simple math game for elementary school students
*	Copyright (C) 2012-1013  Dimitrios Desyllas (pc_magas)
*
*	This program is free software: you can redistribute it and/or modify
*	it under the terms of the GNU General Public License as published by
*	the Free Software Foundation, either version 3 of the License, or
*	(at your option) any later version.
*
*	This program is distributed in the hope that it will be useful,
*	but WITHOUT ANY WARRANTY; without even the implied warranty of
*	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*	GNU General Public License for more details.
*
*	You should have received a copy of the GNU General Public License
*	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*
*	Contact with me by at this address: pc_magas@yahoo.gr
*/


package guma.ui.main;

import guma.ui.elements;
/**
*A class that describes the currest status of basic elements of the user interface.
*/
public class UIStatus extends UIDescription
{
	/**
	*Show what value will the UI display for the remaining operations.
	*In other words stores how many operations left and how it will shown 
	*/
	public String praxisRemainingDisplay="Πράξεις 0/0";

	/**
	*What operation Label/Field (depenging on the UI) will show
	*The value has this form: eg 5+3, 6*7 etc etc
	*/
	public String praxisValue="x+y=";

	/**
	*Tells if the button for next arithmetic operation can be activated or not
	*/
	public boolean next=false;


	/**
	*Tells if save as option (if needed) will be activated.
	*/
	public boolean saveAs=false;

	/**
	*Tells if save option (if needed) will be activated.
	*/
	public boolean save=false;

	
	/**
	*Constructor method that creates a UI status with specific values
	*@param  praxisRemaningDisplay:  Set valut to display remaining operations
	*@param praxisValue: What operation Label/Field (depenging on the UI) will show
	*@param nextOperationActivated: Tells if the button for next arithmetic operation can be activated or not
	*@param error: Shows if an error occured
	*@param errorMessage: Shows the error
	*/
	
	public UIStatus(String praxisRemaningDisplay,
							String praxisValue,
							boolean next,
							boolean saveAs,
							boolean save)
	{
		super(UIDescription.)
		this.praxisRemainingDisplay=praxisRemainingDisplay;
		this.praxisValue=praxisValue;
		this.next=next;
		this.saveAs=saveAs;
		this.save=save;
	}

	/**
	*Creates a blank UIStatus
	*/
	public UIStatus()
	{
			praxisRemainingDisplay="Πράξεις 0/0";
			praxisValue="x+y=";
			next=false;
			save=false;
			saveAs=false;
	}
	
	/**
	*Create a Clone of UIStatus
	*@param other: An instance of UIStatus
	*/
	public UIStatus(UIStatus other)
	{
		this.praxisRemainingDisplay=other.praxisRemainingDisplay;
		this.praxisValue=other.praxisValue;
		this.next=other.next;
		this.saveAs=other.saveAs;
		this.save=other.save;
	}

	@Override
	public UIDescription clone()
	{
		return new UIStatus(this);
	}
}
