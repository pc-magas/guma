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

package guma.ui;

import guma.ui.UIStatus;
import guma.core.Game;

/**
*A class that allows you to interact with the game
*/
public abstract class GameController
{

	/**
	*The game that the Controller will control
	*/
	private Game paixnidi=null;

	/**
	*User interface status
	*/
	UIStatus current=new UIStatus();
	
	/**
	*Method that creates a new game
	*@override
	*/
	public abstract void newGame();

	/**
	*Constructor method that allows you to make the communicatio between UI and core (Game)
	*/
	public GameController()
	{
		newGame();
	}
	
	/**
	*Method that allows the UI to cimmunicate with the core (Game class)
	*@param: apotelesma the result given from UI (User Interface)
	*/
	public UIStatus takeResult(int apotelesma)
	{
		int results[]=new int[paixnidi.getResultsNum()];
		boolean ok=false;

		if(paixnidi!=null)
		{
			try
			{
				results[0]=apotelesma;
				if(results.length>1)
				{
					results[1]=getExtraResult();
				}
				while(!ok)
				{
				
					if(paixnidi.checkApotelesma(results))
					{
						current.praxisValue=paixnidi.toString();
						ok=true;
						current.praxisRemainingDisplay="Πράξεις:"+paixnidi.getRemainingPraxis()+'/'+paixnidi.getPraxisNum();
					}
					else
					{
						
							int pos=paixnidi.getWrongResultPos();
							if(pos==Praxis.MODULO_POS)
							{
								displayError("Προσπάθησε ξανα","Λάθος τιμή υπολοίπου\nEναπομένουσες προσπάθειες: "
												+paixnidi.getTries());
								results[1]=getExtraResult();
							
							}
							else
							{
									displayError("Προσπάθησε ξανα","Λάθος αποτέλεσμα!\nEναπομένουσες προσπάθειες: "
														+paixnidi.getTries());
								ok=true;
							}
					}
				}
			}
			catch(TriesEndException tend)//Tries Ended
			{
				displayError("Προσπάθησε ξανα",tend.getMessage());
				current.praxisValue=paixnidi.toString());
			}
			catch(GameOverException gend)//Game Ended
			{
				displayMessage(gend.getMessage(),"Τέλος Παιχνιδιού");
				current.nextOperationActivated=false;
				paixnidi=null;
				current.praxisValue="x*y=";
				current.save=false;
				current.saveAs=false;
			}
		}
		
		return current;
	}

	/**
	*In some arithmetic operations especially into Division
	*So it provides you a way to get the extra result. 
	*/
	protected abstract int getExtraResult();

	/**
	*Method that shows to the user an error message
	*/
	protected abstract void displayError(String title, String message);

	/**
	*Displays a Message
	*/
	protected abstract void displayMessage(String title, String message);

}
