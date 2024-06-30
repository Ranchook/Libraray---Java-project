package classes;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * The base class for member and human.
 * The fields are id as  ת.ז and then the name split to first and last (no middle name).
 * */

public class Human implements Serializable  {

	private static final long serialVersionUID = 1L;
	private String id;
	private String firstName;
	private String lastName;
	 
	//________________________constructor___________________________
	public Human(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//______________________getters/setter______________________
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
