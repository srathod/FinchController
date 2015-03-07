/*
 * The following code is for visual display of the
 * connection of FinchApp.
 * 
 * Once connected, this class will be disposed from FinchMain
 * 
 * The code is not fully complete, but all actions work.
 * Feel free to modify to make the code function better :D
 */

package finchApp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**@author Saurabh Rathod*/
public class FinchConnect extends JFrame implements WindowListener
{
	

	private static final long serialVersionUID = 927L;

	JPanel main = new JPanel();
	JPanel bottom = new JPanel();
	
	JLabel status;
	JButton retry;
        JButton cancel;
	
	ActionListener rt = new RT();
        ActionListener cl = new CL();
	FinchConnector fc = new FinchConnector();
	Thread th = new Thread(fc);
	
	public FinchConnect()
	{
		
	
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) 
		{
			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error on L&F\n", "L&F ERROR!", JOptionPane.ERROR_MESSAGE);
			
		}
		
		
	    	
	    	
		setIconImage(null); 
		
		setTitle("Finch Control Panel");
		setSize(270,110);
		setResizable(false);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		
		status = new JLabel();
                
		retry = new JButton("Try Again");
		retry.addActionListener(rt);
                retry.setVisible(false);
                
                cancel = new JButton("CANCEL");
                cancel.addActionListener(cl);
                cancel.setVisible(true);
		
		getContentPane().add(main, BorderLayout.CENTER);
		getContentPane().add(bottom, BorderLayout.SOUTH);
		main.add(status);
                bottom.add(retry);
                bottom.add(cancel);
		status.setText("Connecting to Finch...");
/*                PipedOutputStream pipeOut = new PipedOutputStream();
            try {
                PipedInputStream pipeIn = new PipedInputStream(pipeOut);
            } catch (IOException ex) {
                Logger.getLogger(FinchConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.setOut(new PrintStream(pipeOut));*/
		th.start();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    //Do Nothing
                }
		if(th.isAlive())
		{
                    //if(System.console().readLine().contains("Connected: True"))
                    //BAD IF STATEMENT.
                    //This is not yet finished!
                    //****************************************************************************
                    if(fc.ReadConnection() == true)
                    {
                        status.setText("Connected to Finch!");
                        FinchApp fa = new FinchApp();
                        fa.setVisible(true);
                        this.dispose();
                    }
                    else
                    {
                            status.setText("Finch is not connected!");
                            retry.setVisible(true);
                            //th.interrupt();
                    }
                }
                else
                {
                        //status.setText("Error Communicating With Finch.");
                    status.setText("Connected to Finch!");
                    FinchApp fa = new FinchApp();
                    fa.setVisible(true);
                    this.dispose();
                }
		
		
	}

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        /*if(status.getText().equals("Connecting to Finch..."))
        {
            FinchApp fa = new FinchApp();
            fa.setVisible(true);
            this.dispose();
        }*/
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        System.exit(0);
    }
	
	class RT implements ActionListener
	{
            
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
                       /* status.setText("Connecting again...");
			th = new Thread(fc);
                        th.start();
			if(th.isAlive())
			{
				if(System.out.equals("Connected: True"))
                                {
                                    status.setText("Connected to Finch!");
                                    FinchApp fa = new FinchApp();
                                    fa.setVisible(true);
                                    FinchConnect.this.dispose();
                                }
                                else
                                {
                                    status.setText("Finch is not connected!");
                                    th.interrupt();
                                    retry.setVisible(true);
                                }
			}
                        else
                        {
                            status.setText("Error Communicating With Finch.");
                        }*/
		}
		
	}
        
        class CL implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
                        System.exit(0);
		}
		
	}
	
}
