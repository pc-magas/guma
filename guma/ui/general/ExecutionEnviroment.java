/**
*	GUMA a simple math game for elementary school students
*	Copyright (C) 2013-1014  Dimitrios Desyllas (pc_magas)
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
package guma.ui.general;

public abstract class ExecutionEnviroment 
{

	/**
	*Method for nitiliizing the User Interface
	**/ 
	protected abstract void initGui();

	/**
	*Method for initlizing the Localization Options
	**/
	protected abstract void initLoco();
	
	/**
	*Method used for initializing the  Environment
	**/
	public void init()
	{
		initLoco();
		initGui();
	}


	/**
	*Method used on execution
	**/
	public abstract void excecute();

	/**
	*Initilization and excecution method
	*@param args parameters ma be reguested for running
	**/
	public void run(String args[])
	{
		init();
		excecute();
	}
}
