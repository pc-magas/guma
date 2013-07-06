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
*Contact with me by main at thes address: pc_magas@yahoo.gr
*/

package guma.net;

import net.Downloader;
import java.util.Date;
import java.io.File;
import java.io.IOException;

public class WebGameLoader
{
	
	/**
	*Stores the Status
	*/
	private String status="Neutral";
	
	/**
	*Class that we use to download files
	*/
	private Downloader d=null;
	
	/**
	*Percent of complete download 
	*/
	private  float percent=0.0f;
	
	/**
	*Where the file downloads
	*/
	private String path=null;
	
	/**
	*Tells if we can load the game or not
	*/
	private boolean load=false;
	
	/**
	*Contructor Method
	*@param url: the url of file
	*@param size: The maximum size in Bytes
	*/
	public WebGameLoader(String url,long size)
	{
		path=System.getProperty("java.io.tmpdir")+File.separator+new Date().getTime()+".guma";
		
		String[] allowed={"text/plain","text/xml","application/xml","application/octet-stream"};
		
		d= new Downloader(url,path,allowed,size,false);
	}
	
	
	/**
	*Constructor method
	*@param url: the url of file on the web
	*/
	public WebGameLoader(String url)
	{
		this(url,0l);
	}
	
	/**
	*Returns the game
	*/
	public guma.core.Game getGame() throws IOException,ClassNotFoundException
	{
		download();
		if(load)
		{	
			System.out.println("Can load the Game");
			guma.core.Game g=guma.core.Game.load(path);
			if(g!=null)
			{
				System.out.println("GAme Loaded");
			}
			else
			{
				System.err.println("Game Not Loaded");
			}
			return guma.core.Game.load(path);
		}
		else
		{
			System.out.println("Cannot load the Game status: "+status);
			return null;
		}
	}
	
	/**
	*Returns the temprary file that WebGameLoader creates to load the Game  
	*/
	public File getTempFile()
	{
		return new File(path);
	}
	
	/**
	*Returns the full path of temporary file that WebGameLoader creates
	*/
	public String getTempPath()
	{
		return path;
	}
	
	/**
	*Returns the status of downloader
	*/
	public String getStatus()
	{
		return status;
	}
	
	/**
	*Get the download progress as %
	*/
	public float getPercent()
	{
		return percent;
	}

	/**
	*Returns The Downloader
	*/
	public Downloader getDownloader()
	{
		return d;
	}
	
	/**
	*Downloads the file
	*/
	public void download()
	{
		d.download();
		do
		{
			status=d.getStatus();
			if(!status.equalsIgnoreCase("Error"))
			{
				//System.out.println("OK");
				percent = d.getPercent();
			}
			else
			{
				load=false;
				//System.err.println("Error");
				break;

			}
		}while(!status.equalsIgnoreCase(Downloader.ERROR) && !status.equalsIgnoreCase(Downloader.FINISHED) );
		load=true;
	}
}
