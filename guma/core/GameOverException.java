/**
*GUMA a simple math game for elementary school students
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
*Contact with me by main at these addresses: pc_magas@yahoo.gr
*/
package guma.core;

import java.lang.Exception;
/**
*This class it thrown when the game ends 
*/
public class GameOverException extends RuntimeException
{
	
	/**
	*Constructor method
	*@param message The messege we get from getMessage()
	*/
	public GameOverException(String message)
	{
		super(message);
	}
}
