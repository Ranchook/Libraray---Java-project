package classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String LibraryName;
	public Librarian safranit;
	protected Employee emp;
	
	protected ArrayList<Member> member_list = new ArrayList<Member>();
	protected ArrayList<Book> book_list = new ArrayList<Book>();
	protected ArrayList<loan_book> loan_book = new ArrayList<loan_book>();
	protected ArrayList<pending_books> pend_return = new ArrayList<pending_books>();	
	
	//________________________constructor___________________________
	 
	public Library(ArrayList<Member> m, ArrayList<Book> b, ArrayList<loan_book> lb, ArrayList<pending_books> pr) {
		this.member_list = m;
		this.book_list = b;
		this.loan_book = lb;
		this.pend_return = pr;
		this.safranit = new Librarian("0001", "Hedva", "HaSafranit", "1110", loan_book);
		LibraryName = "Sifriya";
		
		new Thread(new Runnable() {
   	  @Override
    	 public void run() {
          while(true){

        	  this.bookRetuner();
			  try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
     }

	private void bookRetuner() {
		System.out.println(pend_return.size());
		if(pend_return.size() >= 5)
		{
			System.out.println("IN IF");
			for(int i=0; i< pend_return.size(); i++) {
				Book tmpBook = pend_return.get(i).get_pending_book();
				System.out.println("returned book: " + tmpBook.toString());
				
				int bk_indx = UtilitysClass.isBookExist(get_lib_book_list(), tmpBook); 
				tmpBook = get_lib_book_list().get(bk_indx);

				tmpBook.inc_avail_copies(1);		
				if(tmpBook.getAvail_copies() > tmpBook.getTotal_Copies())
					tmpBook.setAvail_copies(tmpBook.getAvail_copies());
				
				get_lib_book_list().set(bk_indx, tmpBook);
			}
			while(pend_return.size()>0)
				pend_return.remove(pend_return.size());
		}
		else
			System.out.println("less than 5 books");
		System.out.println("threaded return process");
	}

	}).start();
		
	}
	
	//----------------------------getter/setters----------------
		

	public String getLibraryName() {
		return LibraryName;
	}
	public ArrayList<Book> get_lib_book_list(){
		return this.book_list;
	}
	public ArrayList<Member> get_lib_member_list(){
		return this.member_list;
	}
	public ArrayList<loan_book> get_loan_book_list(){
		return this.loan_book;
	}
	public ArrayList<pending_books> get_pending_book_list(){
		return this.pend_return;
	}
	
	//-------------------------Functions------------------------------
	
		
	public void welcome() {
		new gui.WelcomeGui(this);
	}
	
	public void writeLibraryFile(ArrayList<Member> m, ArrayList<Book> b, ArrayList<loan_book> lb, ArrayList<pending_books> pr) throws IOException{
		try {
			FileOutputStream fileOut = new FileOutputStream("Library.ser"); //creates file with its name
			
			 ObjectOutputStream out = new ObjectOutputStream(fileOut); //output to put in the file
			 out.writeObject(m);
			 out.writeObject(b);
			 out.writeObject(lb);
			 out.writeObject(pr);
			 out.close();
			 fileOut.close();
			 System.out.println("Library info saved");
			 }
			 catch(IOException i) {
			 i.printStackTrace();
			 }
		}	
	
	public Library readLibraryFile() throws IOException ,ClassNotFoundException {

	ArrayList<Member> tmpMem = new ArrayList<Member>();
	ArrayList<Book> tmpBk = new ArrayList<Book>();
	ArrayList<loan_book> tmpLndBk = new ArrayList<loan_book>();
	ArrayList<pending_books> tmPnBk = new ArrayList<pending_books>();
	

		try {
			FileInputStream fi = new FileInputStream("Library.ser"); //input to what file to read
			ObjectInputStream os = new ObjectInputStream(fi);
			ArrayList<Member> memList = (ArrayList<Member>) os.readObject(); //reading members
			ArrayList<Book> bkList = (ArrayList<Book>) os.readObject(); //reading books
			ArrayList<loan_book> lndBk_List = (ArrayList<loan_book>) os.readObject(); //reading loan books
			ArrayList<pending_books> pndBk_List = (ArrayList<pending_books>) os.readObject(); //reading pending books

			for (Member i : memList) { //saving items from file to new arg
				tmpMem.add(i);
			}
			for (Book i : bkList) {
				tmpBk.add(i);
			}
			for (loan_book i : lndBk_List) {
				tmpLndBk.add(i);
			}
			for (pending_books i : pndBk_List) {
				tmPnBk.add(i);
			}
			os.close();
		}
		
		 catch(IOException i) {
			 i.printStackTrace();
			 }
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		Library tmp = new Library(tmpMem,tmpBk,tmpLndBk,tmPnBk); //insert new args to new library to return and work on it
		return tmp;
		
	}
}
