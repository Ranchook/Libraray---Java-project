package classes;

import java.util.ArrayList;

public class Registration {
	private Human person_to_regstr;
    private ArrayList<Member> member_list;
    
	//==================constructor=============================
    
    public Registration(Human per, ArrayList<Member> mem_list)
	{
    	member_list = mem_list;
		person_to_regstr = per;
	}
//========================= functions ============================================
    
	// compare prospect members ID to the rest of the IDs in the list
    // if no match was found, return false -> ID is not used, no such member
    // else return true -> ID is already used, member already exists
  
    public boolean isMemExist() {
    	System.out.println("person id: " + person_to_regstr.getId());
    	int mem_amount = member_list.size();
		Member tmp;
		for(int i=0; i<mem_amount; i++) {
			tmp = member_list.get(i);
			System.out.println("tmp id: " + tmp.getId());	
			if(person_to_regstr.getId().equals(tmp.getId()))
				{
					System.out.println("id exist");
					return true;
				}
		}
		System.out.println("id not exist");
		return false;
	}
	
    //give new member his new member id
    //add him as member using member constructor
	public Member register_member(){
		int new_mem_id = member_list.size()+1;
		String fName = person_to_regstr.getFirstName();
		String lastName = person_to_regstr.getLastName();
		String id = person_to_regstr.getId();
		Member new_mem = new Member(id, fName, lastName, String.valueOf(new_mem_id));
		member_list.add(new_mem);
		return new_mem;
	}
}
