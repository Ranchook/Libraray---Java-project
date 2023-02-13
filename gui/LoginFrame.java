package gui;
import classes.Book;
import classes.Librarian;
import classes.Member;
import classes.loan_book;
import classes.pending_books;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class LoginFrame extends JFrame implements ActionListener {
	
	//declaration of the variables  and components and create a new instance of them 
	
	private Container container = getContentPane();
	private JLabel userLabel = new JLabel("Human ID");
	private JLabel passwordLabel = new JLabel("Member ID");
    private JLabel logFailed= new JLabel("");
	private JTextField userTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("LOGIN");
    private JButton resetButton = new JButton("RESET");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    private String userText,pwdText;
    private Librarian hedva;
    private ArrayList<Book> bookList;
	private ArrayList<Member> member_list;
	private Member logged_in_Member;
	private ArrayList<loan_book> loan_books;
	private ArrayList<pending_books> pend_return;	

	//================constructor=====================
    
	//there are 2 constructor  1 for member and 1 for librarian
	
    public LoginFrame(ArrayList<Member> mem, ArrayList<Book> books, ArrayList<loan_book> lb_list, ArrayList<pending_books> pr) {
    	setLayoutManager();//set layout 
    	setLocationAndSize(); //location for the components 
        addComponentsToContainer();// add components to the frame
        addActionEvent(); //add the action listener to the buttons 
        manageFrame(); // set the size of the frame and make it 
        pend_return = pr;
        loan_books = lb_list;
        member_list = mem;
        this.hedva=null;
        bookList = books;
    }
    
    public LoginFrame(ArrayList<Book> books,Librarian hedva1) {
    	setLayoutManager();//set layout 
    	setLocationAndSize(); //location for the components 
        addComponentsToContainer();// add components to the frame
        addActionEvent(); //add the action listener to the buttons 
        manageFrame(); // set the size of the frame and make it 
        member_list = null;
        bookList = books;
        this.hedva=hedva1;
        passwordLabel.setText("Employee ID"); 
    }


	//================functions=====================

    public void setLayoutManager() {
        container.setLayout(null);
    }
    
 //set the bound  of the components
    public void setLocationAndSize() {
    	userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        logFailed.setBounds(50, 350, 150, 30);
    }
 //add the components
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(logFailed);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
    
    public void manageFrame() {
    	 this.setTitle("Login Form");
    	 this.setBounds(10, 10, 370, 600);
 		 this.setLocationRelativeTo(null);
    	 this.setVisible(true);
    	 this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	 this.setResizable(false);
    }
    
 //----------------------------------------action listener----------------------------------------------------------
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
    	//check if the password member_id and the id human id are contained by one of the members.
        if (e.getSource() == loginButton)
         {
             pwdText = new String(passwordField.getPassword());
            userText= this.userTextField.getText();
            if(this.member_list!=null){
            
                logged_in_Member = mem_find(userText, pwdText);
                if(logged_in_Member==null){
                    this.clearFields();
                    JOptionPane.showMessageDialog(null,"Incorrect pasword or ID", "failed", JOptionPane.PLAIN_MESSAGE);
			       System.out.println("Member added!");	
                }
                else{
                    this.setVisible(false);
                    new MemberGui(logged_in_Member, bookList, loan_books, pend_return);

                }
            }
            else if(this.hedva!=null)
            {
                if(hedva.getEmployee_id().equals(pwdText)&&hedva.getId().equals(userText))
                {
                    new LibrarianFuncGui(bookList,hedva);
                }
                else
                {
                    this.clearFields();
                    this.logFailed.setText("Incorrect pasword or ID");
                }

            }
        }
        
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
           this.clearFields();
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
 
 
        }
    }

//===========================================Functions===============================================
    /**
     * @param hId used as the id
     * @param mId used as the password
     * @return the member that contain the same Hid(human_id)and the mId(member_id) and return him
     * if he/she doesn't exist the return null
     */
    
    private Member mem_find(String hId,String mId)
    {//mId is password 
    	Member tempMem;
    	String tempMid,tempHid;
    	for (int i = 0; i < member_list.size(); i++) 
    	{
    		// Printing and display the elements in ArrayList
    		tempMem= member_list.get(i);
    		tempMid=tempMem.getMember_ID();
    		tempHid=tempMem.getId();
            if(tempMid.equals(mId) &&tempHid.equals(hId))
        	{
            	System.out.println("success! :)");
            	return tempMem;
        	}
         }
    	System.out.println("not success! :(");
    	return null;
    }
    
    public Member getMember() {
    	return logged_in_Member;
    }    

    public void clearFields(){
        this.passwordField.setText("");
        this.userTextField.setText("");
    }
   


}