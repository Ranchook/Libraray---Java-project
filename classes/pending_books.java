package classes;

import java.io.Serializable;

public class pending_books implements Serializable{
	private static final long serialVersionUID = 1L;
	private Book pend_bk;
	
	public pending_books(Book bk){
		pend_bk = bk;
	}

	public Book get_pending_book() {
		return pend_bk;
	}
}