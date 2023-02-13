package classes;

import java.util.ArrayList;

public class UtilitysClass {	
		 
	/**
	 * check if the book names and the author name  is in the book list and if the strings are empty then they will return 
	 * the original list 
	 * @param bname- book name
	 * @param aname author name
	 * @param books book list
	 * @return
	 */
	
	// check if book exist
	static public int isBookExist(ArrayList<Book> lib_bks, Book bk) {
			int list_len = lib_bks.size();
			Book tmp;
			for(int i=0; i<list_len; i++) {
				tmp = lib_bks.get(i);
				if(tmp.getBook_name().equals(bk.getBook_name()) && tmp.getAuthor_name().equals(bk.getAuthor_name()))	
					return i;
			}
			return -1;
		}
			
	static public ArrayList<Book> searchBooks(String bname, String aname, ArrayList<Book>  books) 
	{
		ArrayList<Book> targetList1= new ArrayList<Book>();
		//add all the books that goes by the chosen name if he's not Null
		if(!bname.isEmpty())
		{
			for(int i=0;i<books.size();i++) 
			{
				if(books.get(i).getBook_name().equals(bname))
				{
					targetList1.add((books.get(i)));
				}
			}
		}
		//if the bnames is null just copy all the books and move to the autor name
		else
		{
			targetList1=books;	
		}
		
		ArrayList<Book> targetList2= new ArrayList<Book>();

		//add all the books that goes by the chosen name if he's not Null
		if(!aname.isEmpty())
		{
			for(int i=0;i<targetList1.size();i++) 
			{
				if(targetList1.get(i).getAuthor_name().equals(aname))
				{
					targetList2.add((targetList1.get(i)));
				}
			}
		}
		//if the anames is null just return the existing arrylist
		else
		{
			targetList2=targetList1;
		}
		// need to see what to do if the books list is empty
		return targetList2;
	}
}
