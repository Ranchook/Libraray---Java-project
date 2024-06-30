import java.io.*;
import java.util.ArrayList;

import classes.*;

public class main {

	public static void main(String[] args) throws Exception, IOException {
		
		Library librar;
		ArrayList<Member> mem_list = new ArrayList<Member>(); // List to hold library members
		ArrayList<Book> bk_list = new ArrayList<Book>(); // List to hold books in the library
		ArrayList<loan_book> lb_list = new ArrayList<loan_book>(); // List to hold loaned books
		ArrayList<pending_books> pb_list = new ArrayList<pending_books>(); // List to hold pending books
		File f = new File("Library.ser"); // File to store library data
		
		try {
			// Check if the file exists
			if(f.isFile()) {
				Library tmp = new Library(mem_list, bk_list, lb_list, pb_list);
				// If the file exists, read the library data from the file
				librar = new Library(tmp.readLibraryFile().get_lib_member_list(),
									 tmp.readLibraryFile().get_lib_book_list(),
									 tmp.readLibraryFile().get_loan_book_list(),
									 tmp.readLibraryFile().get_pending_book_list()); 
			} else { // If the file does not exist, it is the first time the program runs
				System.out.println("create initial file");
				
				librar = new Library(mem_list, bk_list, lb_list, pb_list);
				
				// Create initial instances of objects
				// Note that the librarian is created through the library constructor
				// If you want to act as librarian, her id is 0001; employee id is 1110
				
				int num = 5;
				// Create and add 5 new books to the library
				for (int i = 0; i < num; i++) {
					Book tmpBook = new Book("Harry Potter " + (i + 1), "J.K Rowling", "Fantasy", 10);
					add_book_to_library add_process = new add_book_to_library(bk_list, tmpBook);
					add_process.add_a_book(tmpBook.getTotal_Copies());
				}
				
				// Create and register 5 new members
				for (int i = 0; i < num; i++) {
					Human tmpHuman = new Human(String.valueOf(i + 1), "Mem" + (i + 1), "Mem_family" + (i + 1));
					Registration reg_process = new Registration(tmpHuman, mem_list);
					reg_process.register_member();
				}
				
				// Loan books to members
				for (int i = 0; i < num; i++) {
					Member tmpMember = mem_list.get(i);
					Book tmpBook = bk_list.get(i);
					tmpMember.loanBook(tmpBook);
					lb_list.add(new loan_book(tmpMember, tmpBook));
				}
				
				// Avoid creating instances of pending books to prevent thread problems that might occur
				librar.writeLibraryFile(mem_list, bk_list, lb_list, pb_list);
			}
			
			librar.welcome();
			
		} catch(IOException i) {
			i.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
}
