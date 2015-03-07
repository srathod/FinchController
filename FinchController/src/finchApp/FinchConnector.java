package finchApp;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;


public class FinchConnector implements Runnable{

    boolean connection;
    
    File cfg;

	private Scanner cfgScan;
	static FileWriter cfgWrite;
	public static FileReader cfgRead;
    
    @Override
    public void run() {
    	Write("false", "cn.vpr");
	Finch f = new Finch();
	System.out.println("Connected: True");
	Write("true", "cn.vpr");
        connection = true;
    }
    
    public boolean isConnected()
    {
        return connection;
    }
    
    public static void Write(String s, String file)
	{
		try 
		{
			cfgWrite = new FileWriter(file);
			cfgWrite.write(s);
			cfgWrite.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();			
			JOptionPane.showMessageDialog(null, "Unable to fetch Finch's connection information.\n" +
					"Error Code: PSVW(Ss, Sf)", "Unexpected Error", JOptionPane.INFORMATION_MESSAGE);
			
		}
	
	}
    
	public boolean ReadConnection()
	{
		try 
		{
			cfgRead = new FileReader("cn.vpr");
			cfgScan = new Scanner(cfgRead);
			String pref = cfgScan.next();			
			
			if(pref.contains("true"))
			{
				return true;
			}
			else
			{
				return false;
			}
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return false;
		
		
	}


}
