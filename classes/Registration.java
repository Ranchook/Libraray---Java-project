package classes;

import java.util.ArrayList;

public class Registration {
	private Human person_to_regstr; // The person to be registered
    private ArrayList<Member> member_list; // List of members
    
//==================constructor=============================
    
    public Registration(Human per, ArrayList<Member> mem_list) {
    	member_list = mem_list;
		person_to_regstr = per;
	}
	
//========================= functions ============================================
    
	// Compare the prospective member's ID to the rest of the IDs in the list
    	// If no match is found, return false -> ID is not used, no such member
	// Else return true -> ID is already used, member already exists
    public boolean isMemExist() {
    	System.out.println("person id: " + person_to_regstr.getId());
    	int mem_amount = member_list.size();
		Member tmp;
		for (int i = 0; i < mem_amount; i++) {
			tmp = member_list.get(i);
			System.out.println("tmp id: " + tmp.getId());	
			if (person_to_regstr.getId().equals(tmp.getId())) {
				System.out.println("id exist");
				return true;
			}
		}
		System.out.println("id not exist");
		return false;
	}
	
    // Assign a new member ID to the new member
    // Add them as a member using the Member constructor
	public Member register_member() {
		int new_mem_id = member_list.size() + 1; // Assign a new member ID
		String fName = person_to_regstr.getFirstName();
		String lastName = person_to_regstr.getLastName();
		String id = person_to_regstr.getId();
		Member new_mem = new Member(id, fName, lastName, String.valueOf(new_mem_id)); // Create a new Member object
		member_list.add(new_mem); // Add the new member to the member list
		return new_mem; // Return the newly created member
	}
}
