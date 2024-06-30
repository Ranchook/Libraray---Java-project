package classes;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	private String book_name;
	private String author_name;
	private String genre;
	private int total_copies;
	private int avail_copies;
	
	//________________________constructor___________________________
	
	public Book(String bk_name, String auth_name, String gen, int cps) {
		setBook_name(bk_name);
		setAuthor_name(auth_name);
		setGenre(gen);
		setTotal_Copies(cps);
		setAvail_copies(cps); // when creating a new book all the copies are available
	}
	
	//---------------getters/setters---------------------
	
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String bk_name) {
		this.book_name = bk_name;
	}
	
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String auth_name) {
		this.author_name = auth_name;
	}

	public int getTotal_Copies() {
		return total_copies;
	}
	public void setTotal_Copies(int cps) {
		this.total_copies = cps;
	}

	public int getAvail_copies() {
		return avail_copies;
	}
	public void setAvail_copies(int avail_copies) {
		this.avail_copies = avail_copies;
	}
	public String getGenre() {
		return genre;
	}

	public void setGenre(String gen) {
		this.genre = gen;
	}
	
	//--------------------Functions--------------------

	public void inc_Total_copies(int num) {
		this.total_copies+=num;
	}
	
	public void inc_avail_copies(int num) {
		this.avail_copies+=num;
		}
	
	public void dec_avail_copies() {
		this.avail_copies--;
		}

	// Search book by author
	public boolean searchBookAth(String ath) {
	if(author_name == ath)
		return true;
	else
		return false;
	}

	// Search book by name
	public boolean searchBookName(String name) {  
		if(book_name == name)
			return true;
		else
			return false;
	}
	
	public String toString() {
		return "Book name: " + getBook_name()+
				"\nAuthor name: "+ getAuthor_name()+ 
				"\nTotal Copies: "	+ total_copies + "\nAvailble copies: " + avail_copies;
		}
}
