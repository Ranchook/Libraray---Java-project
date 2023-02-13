package gui;

import classes.Book;
import classes.add_book_to_library;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;  


public class NewBookGui extends JFrame implements ActionListener   
{  
	private JLabel new_bk_frm_lbl, bk_name_lbl, atr_name_lbl,qty_lbl,genre_lbl;  
	private JTextField bk_name_tf, atr_name_tf,qty_tf;  
	private JButton submt_btn, clr_btn, exit_btn;   
	private JComboBox<String> gen_list;  
	String bookName,authorName,Gen;  
	int quan;
	ArrayList<Book> bk_list;
	public  NewBookGui(ArrayList<Book> bk_list1)  
    {  
		bk_list = bk_list1;
		//generate the  genre list to choose from; we need to add a few more
		String[] genre= {"Fantasy",
						 "Sci-Fi",
						 "Mystery",
						 "Thriller",
						 "Romance",
						 "Westerns",
						 "Dystopian",
						 "Contemporary",
						 "Comics books",
						 "Nature",
						 "Photo"};
        setSize(600, 330);
		setResizable(false);
		  
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setTitle("Add New Book");  
        new_bk_frm_lbl = new JLabel("New Book Form:");  
        new_bk_frm_lbl.setForeground(Color.blue);  
        new_bk_frm_lbl.setFont(new Font("Serif", Font.BOLD, 20));  
        bk_name_lbl = new JLabel("book name:");  
        atr_name_lbl = new JLabel("author  name:");  
        qty_lbl = new JLabel("quantity:");  
        genre_lbl = new JLabel("Genre");
      
        bk_name_tf = new JTextField();  
        atr_name_tf = new JTextField();  
        qty_tf = new JTextField("1");
    
        gen_list=new JComboBox<String>(genre);
        submt_btn = new JButton("Submit");  
        clr_btn = new JButton("Clear");  
        exit_btn = new JButton("Exit");
        
        submt_btn.addActionListener(this);  
        clr_btn.addActionListener(this);  
        exit_btn.addActionListener(this);
        
        //set size for the component
        new_bk_frm_lbl.setBounds(80, 30, 400, 30);  
        bk_name_lbl.setBounds(80, 70, 200, 30);  
        atr_name_lbl.setBounds(80, 110, 200, 30);  
        genre_lbl.setBounds(80, 150, 200, 30);  
        qty_lbl.setBounds(80, 190, 200, 30);  

        bk_name_tf.setBounds(300, 70, 200, 30);  
        atr_name_tf.setBounds(300, 110, 200, 30);  
        qty_tf.setBounds(300, 190, 200, 30);  

        gen_list.setBounds(300, 150, 200, 30);  
    
        submt_btn.setBounds(50, 240, 100, 30);  
        clr_btn.setBounds(170, 240, 100, 30);  
        exit_btn.setBounds(290, 240, 100, 30);
        
        // add the components to the JFrame
        add(new_bk_frm_lbl);  
        add(bk_name_lbl);  
        add(bk_name_tf);  
        add(atr_name_lbl);  
        add(atr_name_tf);  

        add(genre_lbl);
        add(gen_list);
        add(qty_lbl);
        add(qty_tf);
        add(submt_btn);  
        add(clr_btn);  
        add(exit_btn);
        setVisible(true);
    }  
	synchronized public void actionPerformed(ActionEvent e)   
	{
		if (e.getSource() == submt_btn)//if the Submit button was clicked  
			{  
			this.bookName = bk_name_tf.getText();  
			this.authorName = atr_name_tf.getText();  
			this.Gen = gen_list.getActionCommand();
			String temp = qty_tf.getText();

			//check if the quantity contains only numbers
			if (temp.matches("[0-9]+"))
			{
				if((!this.bookName.isEmpty()) && (!this.authorName.isEmpty()))
				{
					this.quan=Integer.valueOf(qty_tf.getText());				
					Book bk_tmp = new Book(bookName, authorName, Gen, quan);
					add_book_to_library add_bk = new add_book_to_library(bk_list,bk_tmp);
					try {
						add_bk.add_a_book(quan);
						JOptionPane.showMessageDialog
						(null,"book added successfuly", "succses", JOptionPane.PLAIN_MESSAGE);
						dispose();
					} 
					catch (IOException e1) {
						JOptionPane.showMessageDialog
						(null,"failed to add the book", "Failed", JOptionPane.PLAIN_MESSAGE);
						System.out.println("couldnt add a book ");
					}
				}
			}
     //if the quantity contains letter put error message
		else
			qty_tf.setText("please input only numbers");	 
		 
		}  
		if(e.getSource() == clr_btn)  	// clear button was clicked
		{
			bk_name_tf.setText("");  
			atr_name_tf.setText("");  
			qty_tf.setText("");  
		}
		if(e.getSource() == exit_btn)
			dispose();
	} 
	
    
    //-------------------------getter/setter--------------------------------
    
    public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getGen() {
		return Gen;
	}
	public void setGen(String gen) {
		Gen = gen;
	}
}  