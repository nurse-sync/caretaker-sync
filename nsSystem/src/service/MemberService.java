package service;

import java.util.List;

import model.MemberPojo;

public interface MemberService {
	MemberPojo getMemberById(int memberId);

	boolean addMember(MemberPojo member);

	boolean updateMember(MemberPojo member);

	boolean deleteMember(int memberId);

	List<MemberPojo> getAllMembers();
}
