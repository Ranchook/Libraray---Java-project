package classes;


import java.io.Serializable;
import java.time.LocalDate;


public class loan_book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Member mem;
	private Book bk;
	private LocalDate start_date;
	private LocalDate end_date;
	
	
	//=================== constructor===================
	public loan_book(Member mem1, Book bk1) {
		this.mem = mem1;
		this.bk = bk1;
		this.start_date = LocalDate.now(); // get todays date 
		this.end_date= start_date.plusDays(1); // a week from now or few minutes to see the thread	
	}


	//=======================functions====================
	
	public LocalDate getEndDate() {
		return end_date;
	}
	
	public Member get_loaner_mem() {
		return mem;
	}
	
	public Book get_loan_book() {
		return bk;
	}
	
	public String toString() {
		return "Loan book process: Book name: " + bk.getBook_name()+
				"\nTo Member: "+ mem.getFirstName() + " " + mem.getLastName() + " id: " + mem.getId()
				+ "\nStart date: "	+ start_date + "\nEnd date: " + end_date;
		}
}

