package classes;

import java.util.ArrayList;

public class Librarian extends Employee {
	
	//--------------Constructor------------------------------	

	public Librarian(String id, String firstName, String lastName, String employee_id, ArrayList<loan_book> lb_list) {
		super(id, firstName, lastName, employee_id);
		this.salary=5750;
	}

	//--------------------Functions--------------------
	
	// Check if there are available copies to lend and if so - lend member a copy
	public static boolean lendABook(Member mem,Book bk)
	{
		if(bk == null)
			return false;
		if(bk.getAvail_copies() > 0)
		{
			mem.addBookToLoaned(bk);
			bk.dec_avail_copies();			
			return true;
		}
		else {
			System.out.println("No more copies of book");
			return false;
		}
	}
}
