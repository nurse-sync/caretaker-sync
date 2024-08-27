package dao;

import java.util.List;

import pojo.MemberPojo;

public interface MemberDao {
	boolean createMember(MemberPojo member); // Create a new member

	MemberPojo getMemberById(int memberId); // Retrieve a member by ID

	List<MemberPojo> getAllMembers(); // Retrieve all members

	boolean updateMember(MemberPojo member); // Update an existing member

	boolean deleteMember(int memberId); // Delete a member by ID

}
