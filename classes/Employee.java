package classes;


public class Employee  extends Human{

	private String employee_id;
	protected int salary;
	
	//________________________constructor___________________________
	
	public Employee(String id, String firstName, String lastName, String emp_id) {
		super(id, firstName, lastName);
		this.setEmployee_id(emp_id);
		this.salary = 5000;
	}

	//______________________getters/setter______________________

	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}	
}
