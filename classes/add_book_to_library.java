package classes;

import java.io.IOException;
import java.util.ArrayList;

public class add_book_to_library {
	
	private ArrayList<Book> bk_list;
	private Book bk;
	
//==========================constructor===========================

	public add_book_to_library(ArrayList<Book> bk_list1, Book bk1) {
	bk_list = bk_list1;
	bk = bk1;
	}

//====================functions==============


	public void add_a_book(int quan) throws IOException {
	
		int bk_indx = UtilitysClass.isBookExist(bk_list, bk); 
		if(bk_indx == -1) { // it's a new book
		
			System.out.println("new book");
			bk_list.add(bk);
		}
	
		else {		// book already exists
	
			System.out.println("book exists!");
			Book tmp_book = bk_list.get(bk_indx);		
			tmp_book.inc_Total_copies(quan);		
			tmp_book.inc_avail_copies(quan);		
			bk_list.set(bk_indx, tmp_book);
		}
	}
}
