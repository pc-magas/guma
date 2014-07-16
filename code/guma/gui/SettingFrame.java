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

package guma.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import guma.core.*;
import guma.arithmetic.Praxis;
import guma.ui.general.UTFResourceBundle;

/**
*This class allows me to set the settings of a new Game
*/
public class SettingFrame extends JPanel implements ItemListener
{

	/**
	*Checkbox for selecting the arithmetic praxis of adding
	*/
	private JCheckBox pros8esiCheck =null;
	
	/**
	*Checkbox for selecting the arithmetic praxis of substraction
	*/
	private JCheckBox afairesiCheck = null;
	
	/**
	*Checkbox for selecting the arithmetic praxis of multiplication
	*/
	private JCheckBox pollaplasiasmosCheck = null;

	/**
	*
	*/
	private JCheckBox divisionCheck=null;

	/**
	*Label that describes what we choose with the JCheckloxes
	*/
	private JLabel praxisTypeLabel=null;

	
	/**
	*JSpinner for selecting the number of arithmetic praxis
	*/
	private JSpinner pli8osPraxewn=new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));
	
	/**
	*JLabel for describing what we set with JSpinner pli8osPraxewn
	*/
	private JLabel pli8osLabel=null;


	/**
	*JSpinner for selecting the maximum number that can participate in a praxis (except from result)
	*/
	private JSpinner maxNum=new JSpinner(new SpinnerNumberModel(2,2,99,1));		
	
	/**
	*JLabel that describes what we set with JSPinner maxNum
	*/
	private JLabel maxNumLabel=null;
	
	
	/**
	*The game we want to get
	*/
	private Game gameToCreate=null;
	
	/**
	*With this I store the characters that represents the praxis type
	*eg '+' for adding
	*/
	private ArrayList<Character> praxeis=new ArrayList<Character>(3);
	
	/**
	 * Interlocalization method
	 */
	private UTFResourceBundle u=new UTFResourceBundle("messages.gui.gameSettings");
	
	/**
	*Returns the game that the frame created
	*/	
	public Game getGame(Component parent)
	{
		String[] options={"OK",u.getString("cancel")};

		int returnVal=JOptionPane.showOptionDialog((Component)parent,this,u.getString("title"),
							JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,(Object[])options,
							(Object)options[1]);//Shows the Dialog

		if(returnVal==JOptionPane.OK_OPTION)
		{
			SpinnerNumberModel pli8osModel = (SpinnerNumberModel)pli8osPraxewn.getModel();
			SpinnerNumberModel maxNumModel =(SpinnerNumberModel) maxNum.getModel();
			int size=praxeis.size();
			char [] p2= new char[size];//make a char table
			
			for(int i=0;i<size;i++)
			{
				p2[i]=praxeis.get(i).charValue();
			}
			if(praxeis.isEmpty())
			{
				JOptionPane.showMessageDialog((Component)praxisTypeLabel,u.getString("errormsg"),
									u.getString("error"),
									JOptionPane.ERROR_MESSAGE);

				gameToCreate=getGame(parent);

			}
			else
			{
				gameToCreate=new Game(pli8osModel.getNumber().intValue(),maxNumModel.getNumber().intValue(),p2);
			}
		}
		else
		{
			 gameToCreate=null;
		}

		return gameToCreate;
	}
	
	/**
	*Constructor Method that creates the gui
	*/
	public SettingFrame()
	{
		/*Setting the Frame*/
		super();
		
		/*Setting the label messagess*/
		pros8esiCheck = new JCheckBox(u.getString("adding"));
		afairesiCheck = new JCheckBox(u.getString("substraction"));
		pollaplasiasmosCheck = new JCheckBox(u.getString("multiplication"));
		divisionCheck=new JCheckBox(u.getString("division"));
		praxisTypeLabel=new JLabel(u.getString("type"));
		pli8osLabel=new JLabel(u.getString("pli8os"));
		maxNumLabel=new JLabel(u.getString("maxval"));

		
		setSize(500,345);
		GridBagLayout gridbag= new GridBagLayout();
			GridBagConstraints c=new GridBagConstraints();//This  variable helps us out in making the gui 
			c.fill=GridBagConstraints.HORIZONTAL;
			c.weightx=1.0;
			c.weighty=1.0;
			setLayout(gridbag);

		 	   /*Desighning the frame*/

			/*Adding setting for number of arithmetic praxeis*/	
			c.gridwidth = GridBagConstraints.RELATIVE;
			addComponent(pli8osLabel,gridbag,c);//Adding Label		
			c.gridwidth = GridBagConstraints.REMAINDER;
			addComponent(pli8osPraxewn,gridbag,c);//Adding JSpinner
		
			/*Adding settings of the maximum number*/
			c.gridwidth = GridBagConstraints.RELATIVE;
			addComponent(maxNumLabel,gridbag,c);//Adding JSpinner
			c.gridwidth = GridBagConstraints.REMAINDER;
			addComponent(maxNum,gridbag,c);//Adding Label
	
			/*Adding Components for praxis settings*/
			c.gridwidth = GridBagConstraints.REMAINDER;
			addComponent(praxisTypeLabel,gridbag,c);
			addComponent(pros8esiCheck,gridbag,c);
			addComponent(afairesiCheck,gridbag,c);
			addComponent(pollaplasiasmosCheck,gridbag,c);
			addComponent(divisionCheck,gridbag,c);

			/*Adding Listenners to the checkboxes*/
			pros8esiCheck.addItemListener(this);
			afairesiCheck.addItemListener(this);
			pollaplasiasmosCheck.addItemListener(this);
			divisionCheck.addItemListener(this);
	}
	
	/**
	*Method that hepls us to add components with GribBagLayout 
	*/

	public void addComponent(JComponent component,GridBagLayout gridbag,GridBagConstraints c)
	{
		gridbag.setConstraints(component,c);
		add(component);
	}
	
	public void itemStateChanged(ItemEvent e) 
	{
		Object source=e.getItemSelectable();
		char praxis;

		/*We find out what type is selected*/
		if(source==pros8esiCheck)
		{
			praxis=Praxis.ADDING;
		}
		else if(source==afairesiCheck)
		{
			praxis=Praxis.SUBSTRACTION;
		}
		else if(source==divisionCheck)
		{
			praxis=Praxis.DIVISION;
		}
		else
		{
			praxis=Praxis.MULTIPLICATION;
		}

		/*If Selected or Deselected item what to do*/
		if(e.getStateChange() == ItemEvent.DESELECTED)
		{
			praxeis.remove(new Character(praxis));
		}
		else
		{
			praxeis.add(new Character(praxis));
		}
	}
}
