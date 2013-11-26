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
*Contact with me by main at this address: pc_magas@yahoo.gr
*/

package guma.ui.general;

import java.util.Hashtable;
/**
 * This class replaces %number% values with specific String where number is a number
 * @author pcmagas
 *
 */
public class LabelString 
{
	
	private Hashtable<Integer,String> labelvalues=null;

	/**
	 * Making a LabelString Object without labels
	 */
	public LabelString()
	{
		labelvalues=new Hashtable<Integer,String>();
	}
	
	/**
	 * Makin a LabelString Object With labels
	 * @param labels
	 */
	public LabelString(String[] labels)
	{
		this();
		if(labels!=null && labels.length>0)
		{
			for(int i=0;i<labels.length;i++)
			{
				setLabel(i+1,labels[i]);
			}
		}
	}
	
	/**
	 * Adding a label
	 * @param label The label
	 * @param value The value to be set
	 */
	public void setLabel(int label, String value)
	{
		labelvalues.put(label,value);
	}
	
	/**
	 * Setting a label
	 * @param label The label
	 * @param value The value
	 */
	public void setLabel(int label, int value)
	{
		setLabel(label,String.valueOf(value));
	}
	
	/**
	 * 
	 * @param labeled the string we want to replace the labels
	 * @return
	 */
	public String toString(String labeled)
	{
		String returnVal=labeled;
		for(int i=1;i<=labelvalues.size();i++)
		{
			System.out.println("HashTable Value "+i+": "+labelvalues.get(i));
			returnVal=returnVal.replaceAll("%"+i+"%",labelvalues.get(i));
		}
		return returnVal;
	}
}
