package gui;

import classes.Library;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;
import java.io.IOException;

public class WelcomeGui extends JFrame implements ActionListener   
{  
	private JLabel title_label;
	private JButton librarian_btn, mem_btn, mem_Register_btn, mem_LogIn_btn, mem_back_btn,librarian_log_btn;  
	private Library lib;
	//============constructor======================
	public WelcomeGui(Library lib1)  
	{
		this.lib = lib1;
		setSize(500, 150);  
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(null);  
		setVisible(true);  
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setTitle("Welcome");  
        
		// init and style the header for this frame
		title_label = new JLabel("Are you a member or an employee?");  
		title_label.setForeground(Color.blue);  
      	title_label.setFont(new Font("Serif", Font.BOLD, 20));  
    
        // init the buttons 
      	mem_Register_btn = new JButton("Sign up");  
      	mem_LogIn_btn = new JButton("Log in"); 
      	mem_back_btn = new JButton("Back"); 
      	librarian_log_btn = new JButton("employee Sign up"); 
      	librarian_btn = new JButton("Librarian");  
      	mem_btn = new JButton("Member");  
 
      	// add action listener to the first buttons
      	librarian_btn.addActionListener(this);  
      	mem_btn.addActionListener(this);       
      	// set the location for the new buttons
      	title_label.setBounds(100, 30, 400, 30);  
      	librarian_btn.setBounds(80, 70, 100, 30);  
      	mem_btn.setBounds(255, 70, 100, 30);  
       
      	// add the relevant buttons to the frame    
      	add(title_label);  
      	add(librarian_btn);  
      	add(mem_btn);
      	
      	this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	try {
					lib.writeLibraryFile(lib.get_lib_member_list(),
							 			 lib.get_lib_book_list(),
							 			 lib.get_loan_book_list(),
										 lib.get_pending_book_list());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });
      	}  
  
//==========================Action listiner=============================  
		
	public void actionPerformed(ActionEvent e)   
	{  
		if (e.getSource() == librarian_btn)  
		{  
			//open the option for a librarian to log in  
			// create the new buttons for the librarian to  log in, disable
			// the existing ones and add a go back button
			librarian_btn.setEnabled(false);
			mem_btn.setEnabled(false);
			librarian_log_btn = new JButton("Log in"); 
			mem_back_btn = new JButton("Back");
			librarian_log_btn.addActionListener(this); 
			mem_back_btn.addActionListener(this);
			librarian_log_btn.setBounds(50, 130, 100, 30);
			mem_back_btn.setBounds(170, 130, 100, 30);
			add(librarian_log_btn);
			add(mem_back_btn);
			setSize(500, 250);
			setLocationRelativeTo(null);
			}   
		
		if (e.getSource() == mem_btn)  
		{  
			//open the option for a member to  choose sign in or sign up
			// create the new buttons for the Member to  log in or sign up, disable
			// the existing ones and add a go back button
			librarian_btn.setEnabled(false);
			mem_btn.setEnabled(false);
			mem_Register_btn = new JButton("Register");  
			mem_LogIn_btn = new JButton("Log in"); 
			mem_back_btn = new JButton("Back"); 
			mem_Register_btn.addActionListener(this);  
			mem_LogIn_btn.addActionListener(this);
			mem_back_btn.addActionListener(this);
			mem_Register_btn.setBounds(50, 130, 100, 30);  
			mem_LogIn_btn.setBounds(170, 130, 100, 30);  
			mem_back_btn.setBounds(50, 170, 100, 30);  
			add(mem_Register_btn);
			add(mem_LogIn_btn);
			add(mem_back_btn);
			setSize(500, 250); 
			setLocationRelativeTo(null);	
		}
		if (e.getSource() == mem_back_btn) 
		{
			//return to choose if u want to log in as a librarian or a member.
			// disable and delete the added buttons aside from the 
			// choosing if  librarian or member
			mem_Register_btn.setEnabled(false);
			mem_LogIn_btn.setEnabled(false);
			mem_back_btn.setEnabled(false);
			librarian_btn.setEnabled(true);
			librarian_log_btn.setEnabled(false);
			mem_btn.setEnabled(true);
			mem_Register_btn.setVisible(false);
			mem_LogIn_btn.setVisible(false);
			mem_back_btn.setVisible(false);
			librarian_log_btn.setVisible(false);
			setSize(500, 150);
			setLocationRelativeTo(null);
		}
		if (e.getSource() == mem_Register_btn)  
		{  	  
			new MemberRegForm(lib.get_lib_member_list());   
		}   
		
		if (e.getSource() == mem_LogIn_btn)  
		{  
			//create a new login frame
			new LoginFrame(lib.get_lib_member_list(), lib.get_lib_book_list(),
					lib.get_loan_book_list(), lib.get_pending_book_list());
		}   
	 
		if (e.getSource() == librarian_log_btn )
		{
		  	new LoginFrame(lib.get_lib_book_list(),lib.safranit);
		}
	}   
}   