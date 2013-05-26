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
*Contact with me by main at this address: pc_magas@yahoo.gr
*/

package guma.gui;

import guma.gui.*;

import guma.core.*;
import guma.ui.main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.*;

/**
*The Basic Frame of the Game
*@author pc_magas
*/
public class MainFrame extends JFrame implements ActionListener,UIUpdater
{
	/**
	*The Basic menu Bar that it is on the top of the game window
	*/
	private JMenuBar panwBar=new JMenuBar();
	
	/**
	*The Menu "File"
	*/
	private JMenu fileMenu= new JMenu("Αρχείο");
	
	/**
	*The menu Help
	*/
	private JMenu help=new JMenu("Βοήθεια");	
	
	/**
	*The MenuItem that the user clicks for a new game 
	*/
	private JMenuItem newGameOption = new JMenuItem("Νέο Παιχνίδι");

	/**
	*The MenuItem in which the user select file to load progress
	*/
	private JMenuItem loadGameOption= new JMenuItem("Άνοιγμα");
	
	/**
	*Option that allows you to open from the web a Game
	*/
	private JMenuItem loadWebOption= new JMenuItem("Άνοιγμα από το διαδικτυο");
	
	/**
	*The MenuItem in which the user select file to load progress
	*/
	private JMenuItem saveGameOption= new JMenuItem("Αποθήκευση");

	/**
	*The menu Iten that creates a new save game
	*/
	private JMenuItem saveAs = new JMenuItem("Αποθήκευση Ως");

	/**
	*The menu Iten that shows the credits
	*/
	private JMenuItem about= new JMenuItem("Σχετικά με το GUMA");
	/**
	*Panel for placing the Buttons Here
	*/
	private JPanel buttonsPanel=new JPanel();

	/**
	*Button for checking result and going to next arithmetic praxis
	*/
	private JButton nextPraxisButton=new JButton("Επόμενη Πράξη>>");
	
	/**
	*Button for closing the window
	*/
	private JButton close=new JButton("Κλείσιμο");

	/**
	*Layout that use the Jpanels
	*/
	private FlowLayout panelLayout=new FlowLayout();

	/**
	*The panel that we use from giving the result and taking the praxis from the game
	*/
	private JPanel praxisPanel=new JPanel();

	/**
	*Label that displays into gui the praxis
	*/
	private JLabel praxisLabel=new JLabel("x+y=");
	
	/**
	*TextField that we put into the result of the displayed praxis
	*/
	private JTextField resultField=new JTextField(4);
	
	/**
	*Controller that interacts between the Game and the Frame
	*/
	private SwingGameController controller=new SwingGameController();

	/**
	*File that game is saved
	*/
	private File f=null;

	/**
	*That shows the remaining arithmetic Operations
	*/
	private JLabel remaining=new JLabel("Πράξεις:");
	
	/**
	*Inner class that handles what to do when the window closes
	*/

	private class WindowCloser extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			closeFrame();
		}
	}

	/**
	*Class that activates and deactivates nextPraxis Button
	*/
	
	private class TextListener implements DocumentListener
	{
		public void insertUpdate(DocumentEvent e)
		{
			enableButton();
		}
		public void removeUpdate(DocumentEvent e)
		{
			enableButton();
		} 
		public void changedUpdate(DocumentEvent e)
		{
			enableButton();
		}

		private void enableButton()
		{
			if(controller.gameStarted())
			{
				nextPraxisButton.setEnabled(true);
			}
		} 
	}

	/**
	*Method that allows user to select  a file and saves or load it depending on the value of "sav" parameter
	*@param save If it is true saves a file else allows loads a file. After the uses has chosen it with JFileChooser
	*/ 

	private void fileLoadAndSave(boolean save)
	{
		int returnVal;
		JFileChooser file=new JFileChooser();
		FileNameExtensionFilter filter1=new FileNameExtensionFilter("Guma game saves","guma");
		file.setFileFilter(filter1);//Seting file filters


		if(save==false)//Select to save
		{
			file.setFileSelectionMode(JFileChooser.FILES_ONLY);
			returnVal = file.showOpenDialog(this);
		}
		else
		{
			file.setFileSelectionMode(JFileChooser.FILES_ONLY);
			returnVal = file.showSaveDialog(this);
		}
		
		if(returnVal == JFileChooser.APPROVE_OPTION) 
		{
			f=file.getSelectedFile();
			String filename=f.getAbsolutePath();

			System.out.println(filename);			
			/*Do save or load */
			try
			{
				/*Select to save*/
				if(save==true)
				{
					//Apend .guma at the end of file
					if(!filename.endsWith(".guma"))
					{
						System.out.println("Renaming File");
						f=new File(filename+".guma");
						System.out.println("New Filename: "+f.getName());
					} 
					
					if(f.exists())
					{
						int option=JOptionPane.showConfirmDialog((Component)file,"To αρχείο "+f.getName() +" όπου επιλέξατε"+ 										"υπάρχει AΝΤΙΚΑΤΑΣΤΑΣΗ?",
											"Αντικατάσταση αρχείου",
											JOptionPane.YES_NO_OPTION,
										JOptionPane.WARNING_MESSAGE );

						if(option==JOptionPane.NO_OPTION)
						{
							JOptionPane.showConfirmDialog((Component)file,
								"Το αρχείο όπου επιλέξατε ΔΕΝ θα αντικατασταθεί",
								"Περι αντικατάστασης αρχείου",
								JOptionPane.ERROR_MESSAGE);
							return;
						}
						else
						{
							JOptionPane.showMessageDialog((Component)file
									,"Το αρχείο όπου επιλέξατε ΘΑ αντικατασταθεί",
										"Περι αντικατάστασης αρχείου",
										JOptionPane.ERROR_MESSAGE);
						}
					
					}
					controller.save(f);
				}
				else
				{
					UIStatus s=controller.load(f);
					if(s!=null)
					{	
						updateUI(s);
					}
					else
					{
							JOptionPane.showMessageDialog((Component)file,"Το αρχείο που επιλέξατε δεν μπορεί να φορτωθεί",
																	"Πρόβλημα με το αρχείο",JOptionPane.ERROR_MESSAGE);
					}
				}

			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog((Component)file,e.getMessage(),"Πρόβλημα με το αρχείο",JOptionPane.ERROR_MESSAGE);
			}
			catch(ClassNotFoundException c)
			{
				JOptionPane.showMessageDialog((Component)file,c.getMessage(),"Πρόβλημα με το αρχείο",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		return;
	}
	
	/**
	*Asks a question if we need to save the file or not
	*/
	public boolean askSaveQuestion()
	{
		boolean doAction=true;
		if(controller.gameStarted() && !controller.gameSaved())/*If an unsaved game has created*/
		{
		
			/*The String of each option we have*/
			String [] options={"Aποθήκευση","Χωρίς αποθήκευση","Ακύρωση"};
				
			/*Creating the Dialog window*/
			 int option=JOptionPane.showOptionDialog(MainFrame.this,
								"H πρόοδος δεν έχει αποθηκευτεί. \n"
								+"Τι να κάνω;",
								"Κλείσιμο",
								JOptionPane.YES_NO_CANCEL_OPTION,
								    JOptionPane.QUESTION_MESSAGE,
								null,
								options,options[2]);
			if(option==JOptionPane.YES_OPTION)
			{
				fileLoadAndSave(true);/*Open Filechooser frame for Save*/
			}
			else if(option==JOptionPane.NO_OPTION)
			{
			}
			else if(option==JOptionPane.CANCEL_OPTION)/*If we select not to save*/
			{
				/*Or else close the dialog that have showed to you*/
				JOptionPane.getRootFrame().dispose();
				doAction=false;
			}
										
		}
		return doAction;
	}
	
	/**
	*Method that closes the frame 
	*/
	public void closeFrame()
	{
			if(askSaveQuestion())
			{	
				System.exit(0);
			}
	}

	/**
	*The Constructor of the Frame
	*/
	public MainFrame()
	{
		/*Basic settings of the frame*/
		super("GUMA: Μια  εκπαιδευτική εφαρμογή  αριθμιτικής ελευθέρου λογισμικού");
		setSize(330,200);
		setResizable(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowCloser());
		setLayout(new BorderLayout());

		/*Adding the bar*/
		setJMenuBar(panwBar);
		panwBar.add(fileMenu);
		panwBar.add(help);
		/*Adding MenuOptions*/
		fileMenu.add(newGameOption);
		fileMenu.add(loadGameOption);
		fileMenu.add(loadWebOption);
		fileMenu.add(saveGameOption);
		fileMenu.add(saveAs);
	
		add(remaining,BorderLayout.NORTH);
		/*Adding "Help" menu Option*/
		help.add(about);		
	
		/*Adding Main game Panel*/
		add(praxisPanel,BorderLayout.CENTER);
		praxisPanel.setLayout(panelLayout);
		praxisPanel.add(praxisLabel);
		praxisPanel.add(resultField);
		resultField.getDocument().addDocumentListener(new TextListener());//Adding the lissener for the Button NextPraxis

		/*Adding Buttons*/
		buttonsPanel.setLayout(panelLayout);
		buttonsPanel.add(close);
		close.setToolTipText("Κάνε κλικ για να κλείσεις το πρόγραμμα.");
		buttonsPanel.add(nextPraxisButton);
		nextPraxisButton.setToolTipText("Κάνε κλικ για να πας στην επόμενη πράξη.");
		add(buttonsPanel,BorderLayout.SOUTH);
		
		/*Adding Action Listeners*/
		newGameOption.addActionListener(this);
		loadGameOption.addActionListener(this);
		loadWebOption.addActionListener(this);
		saveGameOption.addActionListener(this);
		nextPraxisButton.addActionListener(this);
		close.addActionListener(this);
		saveAs.addActionListener(this);
		about.addActionListener(this);

		/*Disabling Buttons and options*/
		nextPraxisButton.setEnabled(false);
		saveAs.setEnabled(false);
		saveGameOption.setEnabled(false);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object option=e.getSource();//get who caused the Action

		
		if(saveGameOption==option)//If we selected to save game
		{
			if(f!=null)
			{
				try
				{
					if(!controller.gameSaved())
					{
						controller.save(f);
					}
				}
				catch(IOException x)
				{
					JOptionPane.showMessageDialog((Component)option,x.getMessage(),"Πρόβλημα με το αρχείο",JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				fileLoadAndSave(true);
			}
		}
		else if(option==loadGameOption)//if we selected to load game
		{
			askSaveQuestion();
			fileLoadAndSave(false);
			
			
		}
		else if(option==saveAs)//If we selected to change file
		{
			fileLoadAndSave(true);
		}
		else if(option==newGameOption)//if we select to create a game
		{
			askSaveQuestion();
			controller.newGame();
			updateUI(controller.getUIStatus());
			
		}
		else if(option==loadWebOption)
		{
			if(askSaveQuestion())
			{
				controller.loadFromWeb();
				updateUI(controller.getUIStatus());
			}
		}
		else if(option==nextPraxisButton)//If we selected to move onj the next arithmetic praxis
		{

			try
			{
				UIStatus current=controller.takeResult(Integer.parseInt(resultField.getText()));
				updateUI(controller.getUIStatus());
			}	
			catch(NumberFormatException nfe)//Not a number
			{
				JOptionPane.showMessageDialog((Component)resultField,
								"Δεν δώσατε αριθμιτική τιμή στο αποτέλεσμα.\n"+
								"Πρέπει να δώσετε αριθμιτική τιμή στο αποτέλεσμα",
								"Προσπάθησε ξανα",
								JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(option==about)
		{
			JOptionPane.showMessageDialog((Component)about,
					"GUMA: Εφαρμογή εκμάνθησης αριθμιτικών πράξεων για μαθητές δημοτικού\n\n"+
									"To GUMA είναι project ελευθέρου λογισμικού ύπο"+
									" την άδεια GNU GPLv3\n\n"+
									"Δημιουργός: Δεσύλλας Δημήτριος (pc_magas)\n"+
									"email: pc_magas@yahoo.gr",
									"Σχετικά:",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(option==close)//Select to close
		{
			closeFrame();
		}
		
	}

	public void updateUI(UIStatus status)
	{		
		System.out.println("Updating status:\nPraxisvalue: "+status.praxisValue+"\nRemaining:"+status.praxisRemainingDisplay);
		praxisLabel.setText(status.praxisValue);
		remaining.setText(status.praxisRemainingDisplay);
		nextPraxisButton.setEnabled(status.next);
		saveAs.setEnabled(status.saveAs);
		saveGameOption.setEnabled(status.save);
	}
}
