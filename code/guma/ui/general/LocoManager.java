/**
*	GUMA a simple math game for elementary school students
*	Copyright (C) 2011-1012  Dimitrios Desyllas (pc_magas)
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

import java.util.Locale;

public class LocoManager 
{
	/**
	*Stores Ui LanguageS
	*/

	private static Locale loco=null;
	
	/**
	 * Creates a Local Localization Option
	 * @param language The language you want to load
	 * @param country The country of the language
	 */
	public static void setLoco(String language, String country)
	{
		loco=new Locale(language,country);
	}
	
	/**
	 * Setting the default's system Locale
	 */
	public static void setLoco()
	{
		loco=Locale.getDefault();
	}
	
	/**
	 * Setting a p-remade locale as systems default locale
	 * @param l The locale we want to set
	 */
	public static void setLoco(Locale l)
	{
		loco=(Locale) l.clone();
	}
	
	/**
	 * Checks is a Localse is set if not then sets the system's default and returns it.
	 * @return The locale of the Program
	 */
	public static Locale getLoco()
	{		
		if(loco==null)
		{
			setLoco();
		}
		return loco;
	}
	
}
