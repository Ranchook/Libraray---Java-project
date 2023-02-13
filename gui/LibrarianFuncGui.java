package gui;

import javax.swing.*;
import classes.Book;
import classes.Librarian;
import java.awt.*;  
import java.awt.event.*;
import java.util.ArrayList;

public class LibrarianFuncGui extends JFrame implements ActionListener   
{  
  private JLabel title_label, lib_bks_labl;
  private JButton addBook_btn1, goBack_btn2, show_bk_info_btn;
  private ArrayList<Book> booksInArrayListLib;
  private DefaultListModel<String> l1;
  private JList<String> bookShowCaseBox;
//--------------------------constructor-----------------------------
  
  public LibrarianFuncGui(ArrayList<Book> books, Librarian liby)  {  
	  	setResizable(false);  
	  	setLayout(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        setTitle("Dear librarian what shall we do?");  
        title_label = new JLabel("Please choose the action you want to perform:");  
        title_label.setForeground(Color.blue);  
        title_label.setFont(new Font("Serif", Font.BOLD, 20));  
        
        lib_bks_labl = new JLabel("Library Books:");  
        lib_bks_labl.setForeground(Color.blue);  
        lib_bks_labl.setFont(new Font("Serif", Font.BOLD, 20));
        
        addBook_btn1 = new JButton("Add new book ");  
        goBack_btn2 = new JButton("Exit"); 
        show_bk_info_btn = new JButton("Show book info");
    
        this.booksInArrayListLib = books;
        
// add ActionListener to the buttons
        
        addBook_btn1.addActionListener(this);  
        goBack_btn2.addActionListener(this);
        show_bk_info_btn.addActionListener(this);
        
        
//      set the location on the jFrame
        title_label.setBounds(10, 20, 400, 30);
        lib_bks_labl.setBounds(400, 45, 400, 30);
        addBook_btn1.setBounds(30, 75, 150, 30);  
        goBack_btn2.setBounds(210, 75, 150, 30);
        show_bk_info_btn.setBounds(30,120,150,30);
     
        
//    add the components to the JFrame
        add(title_label);
        add(lib_bks_labl);  
        add(addBook_btn1);  
        add(goBack_btn2);
        add(show_bk_info_btn);
        makeBookList();
    }  
  //--------------------------------------function---------------------------------------------------
  
  public void makeBookList() {
	  //read the book list and add the books names + author names and add it to String[].
	  //the function will work by reading the book names and author names and add "by" between them	  
	  //afterward it will go and put it in JList.
	 
	  int book_amount=booksInArrayListLib.size();
	  l1 = new DefaultListModel<>();  
	  
	  for(int i=0;i<book_amount;i++) 
		  l1.addElement(booksInArrayListLib.get(i).getBook_name()+	
                         " by " +booksInArrayListLib.get(i).getAuthor_name());
	  
	  bookShowCaseBox = new JList<>(l1);
	  
	  JScrollPane library_books_scrollPane = new JScrollPane(bookShowCaseBox);
	  library_books_scrollPane.setBounds(400, 75, 150, 300);
	  library_books_scrollPane.setViewportView(bookShowCaseBox);
		  
	  setSize(600, 500);
	  setLocationRelativeTo(null);
	  add(library_books_scrollPane);
	  this.setVisible(true);
  }
  
  public void updateBookList() {
	  Book tmpBook = booksInArrayListLib.get(booksInArrayListLib.size()-1);
	  l1.addElement(tmpBook.getBook_name() + " by " + tmpBook.getAuthor_name());
  }
  
  
  private Book findBook(ArrayList<Book> bk_list, JList<String> list) {
		int list_size = bk_list.size();
		String s = list.getSelectedValue();
		if (s == null)
			JOptionPane.showMessageDialog
			(null,"Please Select a Book", "Error!", JOptionPane.ERROR_MESSAGE);
		
		else{
			s = extractBookName(s);
			for(int i=0; i<list_size; i++) {			
				Book tmpBook = bk_list.get(i);
				if(s.equals(tmpBook.getBook_name()))
					return tmpBook;
			}
		}
		return null;
	}
  
  private String extractBookName(String s) {
		
		String[] tmp = s.split(" by");
		return tmp[0];
	}
  
  
  public void actionPerformed(ActionEvent e)   { 
	  if (e.getSource() == addBook_btn1)
	  {
		  //create a new book and add it to the list
		   NewBookGui newBook = new NewBookGui(booksInArrayListLib);
			 
		   newBook.addWindowListener(new WindowAdapter() {     
			   @Override
		       public void windowClosed(WindowEvent e) {
		           	updateBookList();
		       }
		   });
	  }
		
	  if(e.getSource() == show_bk_info_btn) {
		  Book tmp = findBook(booksInArrayListLib, bookShowCaseBox);
		  JOptionPane.showMessageDialog
			(null,tmp, "Book info", JOptionPane.INFORMATION_MESSAGE);
		
	  }
	  
	  if (e.getSource() == goBack_btn2)
	  {
		  dispose();
	  }
  }
}
