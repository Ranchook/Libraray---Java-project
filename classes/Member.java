package classes;

import java.io.Serializable;

//---------------------libraries----------------------

import java.util.*;


public class Member extends Human implements Serializable{

	private static final long serialVersionUID = 1L;
	private String memberID;
	protected ArrayList<Book> mem_loaned_books = new ArrayList<Book>();
	
	
	//________________________constructor___________________________
	public Member(String id, String firstName, String lastName, String Mem_id) {
		super(id, firstName, lastName);
		setMember_ID(Mem_id);
	}

	//---------------getters/setters---------------------
	
	public String getMember_ID() {
		return memberID;
	}
	public void setMember_ID(String member_ID) {
		this.memberID = member_ID;
	}

	public ArrayList<Book> getLoaned_books() {
		return mem_loaned_books;
	}	

	public String getMemID() {
		return this.memberID;
	}	
//--------------------Functions--------------------

	// calls "lend a book" function from librarian class
	public boolean loanBook(Book bk) {
		if(Librarian.lendABook(this, bk)) 
			return true;
		return false;
	}
	
	// adding book to array list of loaned books
	public void addBookToLoaned(Book bk) 
	{
		this.mem_loaned_books.add(bk);
	}

	// returning a book to the library
	public void dumpBook(Book bk) {
		this.mem_loaned_books.remove(bk);
	}

	public String getFullName() {
		return this.getFirstName()+" "+this.getLastName();
	}


}