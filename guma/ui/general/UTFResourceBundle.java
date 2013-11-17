/**
*GUMA a simple math game for elementary school students
*	Copyright (C) 2013-2014  Dimitrios Desyllas (pc_magas)
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
*Contact with me by main at this address: pc_magas@yahoo.gr
*/

package guma.ui.general;

import java.util.ResourceBundle;

public class UTFResourceBundle 
{

	private ResourceBundle resource=null;
	private guma.ui.general.LabelString l=null;
	/**
	 * Makes a Resource Bundle
	 * @param basename the name of file we get the 
	 */
	
	public UTFResourceBundle(String basename)
	{
		resource=ResourceBundle.getBundle(basename);
	}
	
	/**
	 * Returns a string from resource bundle with given encoding
	 * @param iteml The basename of the resource bundle
	 * @param encoding The encoding to be changed
	 * @return A string from resource bundle with given encoding
	 */
	public String getString(String item, String encoding)
	{
		String s=resource.getString(item);
		try
		{
				s=new String(s.getBytes("ISO-8859-1"),encoding);
				
				if(l!=null)
				{
					s=l.toString(s);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return s;
	}

	/**
	 * Returns a string from resource bundle as UTF-8
	 * @param label The basename of the resource bundle
	 * @return A string from resource bundle with as UTF-8
	 */
	public String getString(String item)
	{
		return getString(item,"UTF-8");
	}
	
	/**
	 * Get a resource Bundle and replaces all the labels we set. 
	 * @param item the item we want to get from ResourceBundle
	 * @param encoding the encoding of the string we want to get
	 * @param labels The labels to be replaced. if not null it makes a new object or labels
	 * @return A string from resource bundle with as UTF-8 with labels replaced
	 */
	public String getString(String item, String encoding ,String[] labels)
	{		
		l=new guma.ui.general.LabelString(labels);
		return getString(item,encoding);
	}

	/**
	 * Get a bundle as UTF 8 with labels replaced
	 * @param item the item we want to get from ResourceBundle
	 * @param labels The labels to be replaced. if not null it makes a new object or labels
	 * @return A string from resource bundle with as UTF-8 with labels replaced
	 */
	public String getString(String item, String[] labels)
	{
		return getString(item,"UTF-8",labels);
	}

	/**
	 * Add a label to be replaced with a Value
	 * @param label The label
	 * @param value The value
	 */
	public void addLabel(int label, String value)
	{
		if(l==null)
		{
			l=new guma.ui.general.LabelString();
		}
		l.setLabel(label,value);
	}
	
	/**
	 * Add a label to be replaced with a value
	 * @param label The label
	 * @param value The Value
	 */
	public void addLabel(int label, int value)
	{
		addlabel(label,String.valueOf(value));
	}

}
