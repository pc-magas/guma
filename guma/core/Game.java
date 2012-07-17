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

package guma.core;

import guma.arithmetic.*;
import java.util.Random;
import java.io.*;
import java.lang.*;
import guma.core.GameOverException;
import guma.core.TriesEndException;
/**
*Basic Game Class
*@author Dimitrios Desyllas
*/

public class Game implements Serializable 
{

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
	*This Matrix Stores all tha arithmetic operations that the user has to answer
	*/
	private Praxis[] p=null;

	/**
	*Gets the wrong result posiiton 
	*/
	private int wrongResultPos;	

	/**
	*Creator method
	*@param pli8os The number of arithmetic praxis that a game should have.
	*@param maxNum The maximum value that can have a number as Operator.
	*@param typosPraksewn Matrix that contains the correct characters for the praxis we wat to play.
	*/

	public Game(int pli8os,int maxNum,char [] praxisType)
	{
		p=new Praxis[pli8os];
		Random r=new Random();
		for(int i=0;i<p.length;i++)
		{
			do{
				switch(praxisType[r.nextInt(praxisType.length)])
				{
					case Praxis.ADDING: 
					p[i]=new Prosthesis(maxNum+1);	
					break;
					case Praxis.SUBSTRACTION:
					p[i]=new Afairesis(maxNum+1);
					break;

					case Praxis.DIVISION:
					p[i]=new Diairesis(maxNum+1);
					break;

					case Praxis.MULTIPLICATION:
					p[i]=new Multiplication(maxNum+1);
				}
			}
			while(p[i]==null);

			/*Stirring the praxis matrix*/
			int times=r.nextInt(100);
	
			for(int j=0;j<times;j++)
			{
				int pos1=r.nextInt(praxisType.length);
				int pos2=r.nextInt(praxisType.length);
				char temp=praxisType[pos1];
				praxisType[pos1]=praxisType[pos2];
				praxisType[pos2]=temp;
				
			}
		}
		praksisCounter=p.length-1;
	}
	
	/**
	*Checks if an arithmetis result is correct
	*@param result The result that user gives to check if it is corrent
	*/
	public boolean checkApotelesma(int result[]) throws TriesEndException,GameOverException
	{
		wrongResultPos=p[praksisCounter].getWrongResult(result);
		if(wrongResultPos<0)/*If the gived result is correct*/
		{
			score+=tries;/*Add tries to the score*/
			tries=3;
			praksisCounter--;
		}
		else
		{
			tries--;/*reduce tries*/
			if(tries==0)/*No more tries*/
			{
				tries=3;
				if((praksisCounter-1)>0)
				{
					tries=3;

					/*Throw the correct error*/
					throw new TriesEndException("Δώσατε Λάθος αποτέλεσμα 3 φορές.\n"+p[praksisCounter--].toFullString()+"\nΤο πρόγραμμα μεταβαίνει στην επόμενη πράξη.");
				}
				else
				{
					throw new GameOverException("Τέλος Παιχνιδιού \n Σκόρ: "+score+'/'+p.length);
				}
				
			}
		}
		return wrongResultPos<0?true:false;
	}


	/**
	*returns where the result is wrong
	*/
	public int getWrongResultPos()
	{
		return wrongResultPos;
	}	

	/**
	*Shows current praxis as a string
	*/
	
	public String toString() throws GameOverException
	{
		String s="";
		System.out.println(praksisCounter);
		if(!(praksisCounter<0))
		{
			s=p[praksisCounter].toString();
		}
		else
		{
			throw new GameOverException("Τέλος Παιχνιδιού \n Σκόρ: "+(float)((float)score/3)+'/'+p.length);

		}
		return s;
	}
	
	/**
	*Returns the current praxis that the user is requ3ested to answer
	*/

	public Praxis getCurrentPraxis() throws GameOverException
	{

		if(!(praksisCounter<0))
		{
			return p[praksisCounter];
		}
		else
		{
			throw new GameOverException("Τέλος Παιχνιδιού \n Σκόρ: "+(float)((float)score/3)+'/'+p.length);

		}
		
	}

	/**
	*Stores the current progress
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
	public byte getTries()
	{
		return tries;
	}

	public int getPraxisNum()
	{
		return p.length;
	}

	public int getRemainingPraxis()
	{
		return p.length-praksisCounter;
	}

}
