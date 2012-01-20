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
*Contact with me by main at thes address: pc_magas@yahoo.gr
*/

package guma;

import java.util.Random;
import java.io.*;
import java.lang.*;
import guma.GameOverException;
/**
*Basic Game Class
*/

public class Game implements Serializable 
{
	
	/**
	*The number of arithmetic praxis that game has 
	*/

	private int pli8os;

	/**
	*The maximum value that can have a number as Operator
	*/

	private int maxNum;

	/**
	*Craracter table that contains these characters:
	*For adding contains character '+'
	*For multiplication contains the number 'X'
	*For subtraction contains the character '-'
	*eg. If the user wants to have adding and multiplication the table contains {'+','X'}
	*/

	private char[] praxisType;

	/**
	*The fiors operatior of a arithmatic praxis. In case of substraction can be the second operator.
	*/
	private int telestis1;

	/**
	*The second Opewrator of a arithmetic praxis.  In case of substraction can be the first operator.
	*/

	private int telestis2;

	/**
	*The result of an arithmetic praxis
	*/

	private int apotelesma;

	/**
	*This variable counts the praxis the user has done. 
	*/

	private int praksisCounter=0;
	
	/**
	*Saves the current score
	*/
	private long score=0;
	
	/**
	*Stores the remaining tries
	*/
	private byte tries=3;
		
	
	/**
	*Stores the corrent character of current arithmetic praxis eg for adding the variable has the value '+'
	*/
	private char praxis=' ';

	/**
	*Creator method
	*@param pli8os The number of arithmetic praxis that a game should have.
	*@param maxNum The maximum value that can have a number as Operator.
	*@param typosPraksewn Matrix that contains the correct characters for the praxis we wat to play.
	*/

	public Game(int pli8os,int maxNum,char [] typosPraksewn)
	{
		this.pli8os=pli8os;
		this.maxNum=maxNum+1;
		praxisType=new char[typosPraksewn.length];
		for(int i=0;i<typosPraksewn.length;i++)
		{
			praxisType[i]=typosPraksewn[i];
		}
		nextPraxis();
	}

	/**
	*Creates a random arithmetic praxis
	*/
	private void nextPraxis() throws GameOverException
	{
		if(praksisCounter==pli8os)
		{
			throw new GameOverException("Τέλος Παιχνιδιού \n Σκόρ: "+score+'/'+pli8os);
		}
		Random r=new Random();
		telestis1=r.nextInt(maxNum);
		telestis2=r.nextInt(maxNum);
		praxis=praxisType[r.nextInt(praxisType.length)];
		switch(praxis)
		{
			case '+': 
				apotelesma=telestis1+telestis2;
			break;

			case '-':
				if(telestis1>telestis2)
				{
					apotelesma=telestis1-telestis2;
				}
				else
				{
					apotelesma=telestis2-telestis1;
				}
			break;
			default:

			apotelesma=telestis1*telestis2;
				
		}
		praksisCounter++;
		
	}
	
	/**
	*Checks if an arithmetis result is correct
	*@param apotelesma The result that user gives to check if it is corrent
	*/

	public boolean checkApotelesma(int apotelesma) throws TriesEndException,GameOverException
	{
		if(this.apotelesma==apotelesma)/*If the giver result is correct*/
		{
			score+=tries;/*Add tries to the score*/
			tries=3;/*Resfresh the tries into 3 3*/
			nextPraxis();/*Create a next praxis*/
			return true;
		}
		else
		{
			tries--;/*reduce tries*/
			if(tries==0)/*No more tries*/
			{
				nextPraxis();/*Go to a next random arithmetic praxis*/
				tries=3;
				throw new TriesEndException("Δώσατε Λάθος αποτέλεσμα 3 φορές. \n Το πρόγραμμα μεταβαίνει στην επόμενη πράξη.");/*Throw the correct error*/
			}

			return false;
		}
	}
	
	/**
	*Shows current praxis as a string
	*/
	
	public String toString()
	{
		if((praxis=='-')&&(telestis1<telestis2))
		{
			return String.valueOf(telestis2)+' '+praxis+' '+String.valueOf(telestis1)+'=';
		}
		else
		{
			return String.valueOf(telestis1)+' '+praxis+' '+String.valueOf(telestis2)+'=';
		}
	}
	
	/**
	*Απόθηκεύει την τρέχουσα πρόοδο
	*@param f The file we want to store the current progress
	*/
	
	public void save(File f) throws IOException
	{
		
		if(f.exists())/* If file exists delete is in order to replace it*/
		{
			f.delete();
		}		
		try
		{
			ObjectOutputStream file=new ObjectOutputStream(new BufferedOutputStream((new FileOutputStream(f))));
			file.writeObject(this);/*Save file*/
			file.close();
		}
		catch(IOException e)/*Error Ocurred*/
		{
			throw new 
IOException("Το αρχείο δεν είναι προσβάσιμο ή δεν είναι δυνατόν να αποθηκεύσεται στον κατάλογο:\n"+f.getAbsolutePath()+"\n Μπορείτε να δοκιμάσετε με ένα άλλο αρχείο η να ρυθμίσεται τα δικαιόματα στον κατάλογο αυτό");/*Shows the Correct message as an exception*/

		}
	}

	/**
	*Loads a saved progress
	*@param f The file we want to load the current progress
	*/

	public static Game load(File f) throws IOException,ClassNotFoundException
	{
		Game g=null;/*Class to return*/
		try
		{
			ObjectInputStream file=new ObjectInputStream(new BufferedInputStream((new FileInputStream(f))));
			g=(Game)file.readObject();/*Read file*/
			file.close();
			return g;
		}
		catch(ClassNotFoundException c)/*If file does not contain correct data*/
		{
			throw new ClassNotFoundException("Το αρχειο δεν περιέχει δεδομένα του σωστού τύπου. \n Μπορείτε να δοκιμάσετε να φορτώσετε ένα άλλο αρχείο");
		}
		catch(IOException e)/*If a file IO error occured*/
		{
			throw new 
IOException("Το αρχείο δεν είναι προσβάσιμο ή δεν είναι δυνατόν να αναγνωστεί. \n Μπορείτε να δοκιμάσετε με ένα άλλο αρχείο");
		}

	}
	
	/**
	*Return the Current Score
	*/

	public long getScore()
	{
		return score;
	}
	
	/**
	*Returns the current tries
	*/
	public long getTries()
	{
		return tries;
	}
}
