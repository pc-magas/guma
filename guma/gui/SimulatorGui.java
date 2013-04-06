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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import guma.core.*;
import guma.arithmetic.Praxis;
import guma.simulator.*;

public class SimulatorGui extends JPanel implements ActionListener
{
	/**
	*Button that allows you to move on the next step of Operation Simulation
	*/
	private JButton next= new JButton("Επόμενο Βήμα");
	
	/**
	*Button that closes the window
	*/
	private JButton close=new JButton("Κλείσιμο προσομοιώσης");
	
	/**
	*Panel that stores the Buttons
	*/
	private JPanel buttonPanel=new JPanel();
	
	/**
	*Label that dislpay the message of the cuttenr step
	*/
	private JLabel info=new JLabel();
	
	/**
	*Showing the Carry
	*/
	private JLabel carry=new JLabel("Κρατούμενο<br>");
	
	/**
	*Showing the operation
	*/
	private JLabel praxis=new JLabel();
	
	/**
	*Showing the message
	*/
	private JLabel message=new JLabel();
	
	/**
	*Simulator of an operation
	*/
	private AbstractSimulator s=null;
	
	public SimulatorGui()
	{
		super();
		setLayout(new BorderLayout());
		buttonPanel.setLayout(new FlowLayout());
		add( BorderLayout.EAST,buttonPanel);
		add(BorderLayout.SOUTH,carry);
		add(BorderLayout.NORTH,message);
		add(BorderLayout.SOUTH,praxis);
		buttonPanel.add(close);
		buttonPanel.add(next);
	}
	
		
	public void actionPerformed(ActionEvent e)
	{
    
	}
}
