package gui;
import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;

import classes.Member;
import classes.Human;
import classes.Registration;
public class MemberRegForm extends JFrame implements ActionListener   
{  
	private static final long serialVersionUID = 1L;
	JLabel head_Label, id_lbl, frst_name_lbl, lst_name_lbl;  
    JTextField id_tf, frst_nm_tf, lst_nm_tf;  
    JButton sbmt_btn, clr_btn, back_btn;  
    String new_mem_Fname, new_mem_Lname;
    String new_mem_id;
    ArrayList<Member> member_list;
    
 //===================constructor======================    
    public MemberRegForm(ArrayList<Member> lib_mem_list)  
    {	
    	setResizable(false);
    	setSize(550, 350);
    	setLocationRelativeTo(null);
    	member_list = lib_mem_list;
	   	setLayout(null);  
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
	    setTitle("Member Registration Form");  
	         
	    // make new instances
	    head_Label = new JLabel("Member Registration Form:");         
        
	    sbmt_btn = new JButton("Submit");  
        clr_btn = new JButton("Clear");
        back_btn = new JButton("Back");
        
        id_lbl = new JLabel("ID:");  
        frst_name_lbl = new JLabel("First name:");  
        lst_name_lbl = new JLabel("last name:");  
        
        id_tf = new JTextField();  
        frst_nm_tf = new JTextField();  
        lst_nm_tf = new JTextField();      
	       
        head_Label.setFont(new Font("Serif", Font.BOLD, 20));  
        head_Label.setForeground(Color.blue);  
        head_Label.setBounds(80, 1, 400, 30);  
       
	    sbmt_btn.addActionListener(this);  
        clr_btn.addActionListener(this);
        back_btn.addActionListener(this);
        
        //setBounds(int x-coordinate, int y-coordinate, int width, int height)
        id_lbl.setBounds(80, 70, 200, 30);  
        frst_name_lbl.setBounds(80, 110, 200, 30);  
        lst_name_lbl.setBounds(80, 150, 200, 30);  
	       
        id_tf.setBounds(300, 70, 200, 30);  
        frst_nm_tf.setBounds(300, 110, 200, 30);  
        lst_nm_tf.setBounds(300, 150, 200, 30);  
    
        sbmt_btn.setBounds(50, 210, 100, 30);  
        clr_btn.setBounds(170, 210, 100, 30); 
        back_btn.setBounds(50, 250, 100, 30);
        
        add(head_Label);  
        add(id_lbl);  
        add(id_tf);  
        add(frst_name_lbl);  
        add(frst_nm_tf);  
        add(lst_name_lbl);  
        add(lst_nm_tf);  
        add(sbmt_btn);  
        add(clr_btn); 
	    add(back_btn);
        
        setVisible(true);
          
    } 
    
//========================functions===========================    
	public void actionPerformed(ActionEvent e)   
	{
		if (e.getSource() == sbmt_btn)  //submit button as clicked
	    {     	
	       String tmp =  id_tf.getText();
	       if (tmp.matches("^[0-9]+$")) //check if id is only numbers 
	       {
	    	   new_mem_id = tmp;
	    	   // need to check these too??
	    	   new_mem_Fname = frst_nm_tf.getText();  
		       new_mem_Lname = lst_nm_tf.getText(); 
		       
		       //create new human before adding him as member
		       Human tmp_mem = new Human(new_mem_id, new_mem_Fname, new_mem_Lname);
		       //create register process
		       Registration register_process = new Registration(tmp_mem, member_list);
		       
		       //check through register process if member exists using his id
		       if(!register_process.isMemExist()) // if no member has same id
		       {
		    	   //adding member to member list

					Member newMember = register_process.register_member();

				JOptionPane.showMessageDialog(null,"thank you for joining our library\n"
						+ " your password is "+newMember.getMember_ID(), "success", JOptionPane.INFORMATION_MESSAGE);
			       System.out.println("Member added!");	        		
			       setVisible(false);
		       }
		       else
		       {   
				   JOptionPane.showMessageDialog
				(null,"Please enter the correct ID or check if you are already a member", "faild to join", JOptionPane.ERROR_MESSAGE);
		    	   System.out.println("ID is already in use");
		    	   id_tf.setText("ID is already in use");  
		       }
	        }
	        	
	        else	// id is not only numbers
	        {
				JOptionPane.showMessageDialog
				(null,"ID is not only numbers!", "Error!", JOptionPane.ERROR_MESSAGE);
	        	System.out.println("id is not only numbers!");	        		
	           	id_tf.setText("");	
	        		
	        }
	     }
		
		if (e.getSource() == clr_btn)  // if clear button was clicked
		{
			id_tf.setText("");  
	        frst_nm_tf.setText("");  
	        lst_nm_tf.setText("");  
	    }
		if (e.getSource() == back_btn) {
			dispose();
		}
	}   
    
} 