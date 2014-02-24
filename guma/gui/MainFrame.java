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
import guma.ui.general.UTFResourceBundle;

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
public class MainFrame extends JFrame implements ActionListener,UIUpdater,KeyListener
{
	/**
	*The Basic menu Bar that it is on the top of the game window
	*/
	private JMenuBar panwBar=new JMenuBar();
	
	/**
	*The Menu "File"
	*/
	private JMenu fileMenu= null;// new JMenu("Αρχείο");
	
	/**
	*The menu Help
	*/
	private JMenu help=null;//new JMenu("Βοήθεια");	
	
	/**
	*The MenuItem that the user clicks for a new game 
	*/
	private JMenuItem newGameOption =null;// new JMenuItem("Νέο Παιχνίδι");

	/**
	*The MenuItem in which the user select file to load progress
	*/
	private JMenuItem loadGameOption=null;// new JMenuItem("Άνοιγμα");
	
	/**
	*Option that allows you to open from the web a Game
	*/
	private JMenuItem loadWebOption=null;// new JMenuItem("Άνοιγμα από το διαδικτυο");
	
	/**
	*The MenuItem in which the user select file to load progress
	*/
	private JMenuItem saveGameOption=null; //new JMenuItem("Αποθήκευση");

	/**
	*The menu Iten that creates a new save game
	*/
	private JMenuItem saveAs =null;// new JMenuItem("Αποθήκευση Ως");

	/**
	*The menu Iten that shows the credits
	*/
	private JMenuItem about=null;
	
	/**
	*Panel for placing the Buttons Here
	*/
	private JPanel buttonsPanel=new JPanel();

	/**
	*Button for checking result and going to next arithmetic praxis
	*/
	private JButton nextPraxisButton=null;
	
	/**
	*Button for closing the window
	*/
	private JButton close=null;

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
	private JLabel remaining=null;
	
	/**
	 * 
	 */
	private UTFResourceBundle u=new UTFResourceBundle("messages.gui.mainview"); 
	
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
	 * Initilizes the Environment
	 */
	private void init()
	{
		fileMenu= new JMenu(u.getString("file"));
		help=new JMenu(u.getString("help"));	
		newGameOption =new JMenuItem(u.getString("newgame"));
		loadGameOption=new JMenuItem(u.getString("open"));
		loadWebOption=new JMenuItem(u.getString("openweb"));
		saveGameOption=new JMenuItem(u.getString("save"));
		saveAs =new JMenuItem(u.getString("saveAs"));
		about=new JMenuItem(u.getString("about"));
		nextPraxisButton=new JButton(u.getString("next"));
		close=new JButton(u.getString("close"));
		remaining=new JLabel(u.getString("remaining"));
		this.setTitle(u.getString("title"));
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
						int option=JOptionPane.showConfirmDialog((Component)file,u.getString("replacefileMessage",new String[]{f.getName()}),
											u.getString("replaceFileTitle"),
											JOptionPane.YES_NO_OPTION,
										JOptionPane.WARNING_MESSAGE );

						if(option==JOptionPane.NO_OPTION)
						{
							fileLoadAndSave(save);
							return;
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
							JOptionPane.showMessageDialog((Component)file,u.getString("fileErrorMessage"),
																	u.getString("fileErrorTitle"),JOptionPane.ERROR_MESSAGE);
					}
				}

			}
			catch(IOException e)
			{
				JOptionPane.showMessageDialog((Component)file,e.getMessage(),u.getString("fileErrorTitle"),JOptionPane.ERROR_MESSAGE);
			}
			catch(ClassNotFoundException c)
			{
				JOptionPane.showMessageDialog((Component)file,c.getMessage(),u.getString("fileErrorTitle"),JOptionPane.ERROR_MESSAGE);
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
			String [] options={u.getString("save"),u.getString("noSave"),u.getString("cancel")};
				
			/*Creating the Dialog window*/
			 int option=JOptionPane.showOptionDialog(MainFrame.this,
					 			u.getString("closeMessage"),
								u.getString("close"),
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
		super();
		setSize(330,200);
		try
		{
			setIconImage(Toolkit.getDefaultToolkit().getImage("."+File.separator+"pics"+File.separator+"icons"+File.separator+"icon_small.png"));
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowCloser());
		setLayout(new BorderLayout());
		init();
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
		close.setToolTipText(u.getString("closeToolTip"));
		buttonsPanel.add(nextPraxisButton);
		nextPraxisButton.setToolTipText(u.getString("nextOperationTooltip"));
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
		
		/*Ading Key LIsteners*/
		resultField.addKeyListener(this);

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
					JOptionPane.showMessageDialog((Component)option,x.getMessage(),u.getString("fileErrorTitle"),JOptionPane.ERROR_MESSAGE);
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
				resetUI();
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
				JOptionPane.showMessageDialog((Component)resultField,u.getString("notNumericMessage"),u.getString("notNumericTitle"),
								JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(option==about)
		{
			JOptionPane.showMessageDialog((Component)about,u.getString("aboutMessage"),u.getString("about"),JOptionPane.INFORMATION_MESSAGE);
		}
		else if(option==close)//Select to close
		{
			closeFrame();
		}
		
	}

	/**
	*Updates the Gui from a given UIStatus
	*/
	@Override
	public void updateUI(UIStatus status)
	{	
		resultField.setText("");	
		System.out.println("Updating status:\nPraxisvalue: "+status.praxisValue+"\nRemaining:"+status.praxisRemainingDisplay);
		praxisLabel.setText(status.praxisValue);
		remaining.setText(status.praxisRemainingDisplay);
		nextPraxisButton.setEnabled(status.next);
		saveAs.setEnabled(status.saveAs);
		saveGameOption.setEnabled(status.save);
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
	}
	
	@Override
	public void keyReleased(KeyEvent e) 
	{
		if ((int)e.getKeyChar()==10)
		{
			String s=resultField.getText();
			if(controller.gameStarted() && !s.isEmpty())
			{
				try
				{
					UIStatus current=controller.takeResult(Integer.parseInt(s));
					updateUI(controller.getUIStatus());
				}	
				catch(NumberFormatException nfe)//Not a number
				{
					JOptionPane.showMessageDialog((Component)resultField,u.getString("notNumericMessage"),u.getString("notNumericTitle"),
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private void resetUI()
	{
		updateUI(new UIStatus());
	}
}
