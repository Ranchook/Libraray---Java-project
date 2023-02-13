package gui;
import classes.Book;
import classes.Member;
import classes.UtilitysClass;
import classes.loan_book;
import classes.pending_books;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class MemberGui extends JFrame implements ActionListener {

	private Member mem;
	private ArrayList<Book> library_bk_ArrayList, searchedBooks;
	private ArrayList<loan_book> loan_books_List; 
	private ArrayList<pending_books> pend_return;	
	
	private DefaultListModel<String> member_list_l1, library_list_l2, searched_list_l3;
	private JList<String> mem_bk_list, lib_bk_list, searched_bk_list;
	private JScrollPane library_books_scrollPane, loaned_books_scrollPane, searched_Book_scrollPane;
	
	private JButton loan_bk_btn, retrn_bk_btn, exit_btn, search_btn, clearAll_btn;
	
	private JTextField bk_name_tf, auth_name_tf;
	private String bookName, authorName;
	
	private JLabel title_label, lib_label, loan_label,
					search_bkName_label, search_authName_label;	
	
	//===================constructor=======================
	public MemberGui(Member mem1, ArrayList<Book> bk_list, ArrayList<loan_book> lb_list, ArrayList<pending_books> pr) {
		this.mem = mem1;
		this.library_bk_ArrayList = bk_list;
		this.loan_books_List = lb_list;
		this.pend_return = pr;
		setSize(850, 450);  
		setLocationRelativeTo(null);
		setVisible(true);  
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
		setTitle("Member");		
		
		// init and style the header for this frame
		title_label = new JLabel("Welcome " + mem.getFullName() + "!");  
		lib_label = new JLabel("Library Books:");  
		loan_label = new JLabel("Loaned Books:");  
		search_bkName_label = new JLabel("Book name: ");
		search_authName_label = new JLabel("Author name: ");
		
		manageLabels(title_label, true);
		manageLabels(lib_label, true);	
		manageLabels(loan_label, true);
		manageLabels(search_authName_label, false);
		manageLabels(search_bkName_label, false);
		
		title_label.setBounds(80, 10, 400, 30);
		loan_label.setBounds(400, 40, 150, 30);
		lib_label.setBounds(600, 40, 150, 30);
		
		search_bkName_label.setBounds(80,200,150,30);
		search_authName_label.setBounds(80,260,150,30);
		
		// init the buttons 
		loan_bk_btn = new JButton("Loan Book");  
		retrn_bk_btn = new JButton("Return Book"); 
		exit_btn = new JButton("Exit"); 
		search_btn = new JButton("Search Book"); 
		clearAll_btn = new JButton ("Clear all");
		
		manageButtons(exit_btn);
		manageButtons(loan_bk_btn);
		manageButtons(retrn_bk_btn);
		manageButtons(search_btn);
		manageButtons(clearAll_btn);
		
		loan_bk_btn.setBounds(80, 70, 110, 30);
		retrn_bk_btn.setBounds(200, 70, 110, 30);  
		exit_btn.setBounds(80, 130, 110, 30);  
		search_btn.setBounds(250, 300, 110, 30);
		clearAll_btn.setBounds(80,300, 110, 30);
		
		bk_name_tf = new JTextField();
		auth_name_tf = new JTextField();
		bk_name_tf.setBounds(160, 200, 200, 30);
		auth_name_tf.setBounds(160, 260, 200, 30);
        add(bk_name_tf);  
        add(auth_name_tf);  

		//create JLists and scroll panes
		member_list_l1 = new DefaultListModel<>();
		library_list_l2 = new DefaultListModel<>();
		searched_list_l3 = new DefaultListModel<>();
		
		loaned_books_scrollPane = new JScrollPane(mem_bk_list);
		library_books_scrollPane = new JScrollPane(lib_bk_list);
		searched_Book_scrollPane = new JScrollPane(searched_bk_list);
		
		mem_bk_list = new JList<String>(member_list_l1);
		lib_bk_list = new JList<String>(library_list_l2);
		
		
		manageJList(member_list_l1, mem1.getLoaned_books(), mem_bk_list, loaned_books_scrollPane);
		manageJList(library_list_l2, library_bk_ArrayList, lib_bk_list, library_books_scrollPane);
		
		library_books_scrollPane.setBounds(600, 70, 150, 300);
		loaned_books_scrollPane.setBounds(400, 70, 150, 300);
		searched_Book_scrollPane.setBounds(600, 70, 150, 300);
	}
	//===================functions=======================
		
	private void manageButtons(JButton button) {
		button.addActionListener(this);
		add(button);
	}
	
	private void manageLabels(JLabel label, Boolean flag) {
		if(flag)
		{
			label.setForeground(Color.blue);  
			label.setFont(new Font("Serif", Font.BOLD, 20));
		}
		else {
			label.setFont(new Font("Serif", Font.BOLD, 12));
		}		
		add(label);
	}
	
	  public void updateBookList(Book bk, boolean bool) {
		  if(bool)
			  member_list_l1.addElement(bk.getBook_name() + " by " + bk.getAuthor_name());
		  else 
			  member_list_l1.removeElement(bk);	
		
	  }
	  
	private void fillJList(ArrayList<Book> bks, DefaultListModel<String> listModel) {
		int book_amount=bks.size();
		for(int i=0; i<book_amount; i++)
			listModel.addElement(bks.get(i).getBook_name() + " by " + bks.get(i).getAuthor_name());
	}

	private void manageJList(DefaultListModel<String> lists_l12, ArrayList<Book> bks, JList<String> bk_list, JScrollPane scrollPane) {
		fillJList(bks, lists_l12);
		bk_list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(bk_list);
		add(scrollPane);
	}
	
	private Book findBook(ArrayList<Book> bk_list, JList<String> list) {
		int list_size = bk_list.size();
		String s = list.getSelectedValue();
		if (s == null)
			JOptionPane.showMessageDialog
			(null,"Please Select a Book", "Faild to loan", JOptionPane.ERROR_MESSAGE);
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == search_btn) { //search button was clicked
			searched_list_l3 = new DefaultListModel<>();
			searched_bk_list = new JList<String>(searched_list_l3);
			lib_label.setText("Searched Books");
			bookName = bk_name_tf.getText();
			authorName = auth_name_tf.getText();
			searchedBooks = new ArrayList<Book>();
			searchedBooks = UtilitysClass.searchBooks(bookName, authorName, library_bk_ArrayList);
			library_books_scrollPane.setVisible(false);
			manageJList(searched_list_l3, searchedBooks, searched_bk_list, searched_Book_scrollPane);
			searched_Book_scrollPane.setVisible(true);
		}
		
		if (e.getSource() == loan_bk_btn) { // loan book button was clicked
			
			Book book_to_loan = findBook(library_bk_ArrayList, lib_bk_list);
			
			/*
			 * member gui call mem.loanBook function
			 * mem.loanBook calls safranit.lendABook function 
			 * safranit.lendABook (if possible) adds book to member (calls mem.addBooktoLoaned),
			 * 					  decreases number of book availability and creates
			 * 					  loaned book instance mem.addBooktoLoaned adds book to his list
			 */
			
			if(mem.loanBook(book_to_loan)) {
				
				loan_books_List.add(new loan_book(mem, book_to_loan));
				updateBookList(book_to_loan, true);
				// add success message
			}
			else {
				String s = "There are no available copies of this book.\n Please try again later"; 
				JOptionPane.showMessageDialog
					(null,s, "Faild to loan", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (e.getSource() == retrn_bk_btn) { // return book button was clicked 

			// get the book from members loan list
			Book book_to_return = findBook(mem.getLoaned_books(), mem_bk_list);
			
			
			//remove from members loan list and update the list display
			mem.dumpBook(book_to_return);
			updateBookList(book_to_return, false);
			
			// add it to returned book list
			pend_return.add(new pending_books(book_to_return));				
		}
		
		if (e.getSource() == clearAll_btn) {
			lib_label.setText("Library Books");
			library_books_scrollPane.setVisible(true);
			searched_Book_scrollPane.setVisible(false);
			bk_name_tf.setText("");
			auth_name_tf.setText("");
		}
		
		if (e.getSource() == exit_btn) { //exit button was clicked
			dispose();
		}
		
	}
}