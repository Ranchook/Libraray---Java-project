import java.io.*;
import java.util.ArrayList;

import classes.*;


/*
 * Ran Shneor 206011645
 * Bat Yadgar 313265944 
 * Ariel Vaintraub 316512581
 */


public class maybe_main {

	public static void main(String[] args) throws Exception, IOException {
		
		  Library librar;
		  ArrayList<Member> mem_list = new ArrayList<Member>();
		  ArrayList<Book> bk_list = new ArrayList<Book>();
		  ArrayList<loan_book> lb_list = new ArrayList<loan_book>(); //reading loan books
		  ArrayList<pending_books> pb_list = new ArrayList<pending_books>();
		  File f = new File("Library.ser"); 
		  try {
			  
		  if(f.isFile()) {
			  Library tmp = new Library(mem_list, bk_list,lb_list, pb_list);
			  librar = new Library(tmp.readLibraryFile().get_lib_member_list(),
					  			   tmp.readLibraryFile().get_lib_book_list(),
					  			   tmp.readLibraryFile().get_loan_book_list(),
					  			   tmp.readLibraryFile().get_pending_book_list()); 
		  }
		  
		  else{ // no file - first time run
			  System.out.println("create initial file");
			  
			  librar = new Library(mem_list,bk_list,lb_list, pb_list);
			  
			  //creating instances of objects
			  //please note that the librarian is created through the library constructor
			  //if you want to act as librarian, her id is 0001; employee id is 1110
			  
			  int num = 5;
			  //create and add 5 new books
			  for (int i=0; i<num; i++) {
				  Book tmpBook = new Book("Harry Potter " + (i+1), "J.K Rowling", "Fantasy", 10);
				  add_book_to_library add_process = new add_book_to_library(bk_list,tmpBook);
				  add_process.add_a_book(tmpBook.getTotal_Copies());
			  }
			  
			  //create and register 5 new members
			  for (int i=0; i<num; i++) {
				  Human tmpHuman = new Human(String.valueOf(i+1), "Mem"+(i+1), "Mem_family"+(i+1));
				  Registration reg_process = new Registration(tmpHuman, mem_list);
				  reg_process.register_member();
			  }
			  
			  for (int i=0; i<num; i++) {
				  Member tmpMember = mem_list.get(i);
				  Book tmpBook = bk_list.get(i);
				  tmpMember.loanBook(tmpBook);
				  lb_list.add(new loan_book(tmpMember, tmpBook));
			  }
			  
			  // did not create instance of pending book to avoid thread problems
			  // that might happen
			  librar.writeLibraryFile(mem_list, bk_list,lb_list, pb_list);
		  }
		  		  
		  librar.welcome();
		  }

		 
		  catch(IOException i) {
				 i.printStackTrace();
				 }
			catch(Exception e) {
				e.printStackTrace();
			} 
	}
}