package dao;

import java.util.List;

import pojo.MemberPojo;

public class MemberDaoCollectionImpl implements MemberDao{

	@Override
	public boolean createMember(MemberPojo member) {
		return false;
	}

	@Override
	public MemberPojo getMemberById(int memberId) {
		return null;
	}

	@Override
	public List<MemberPojo> getAllMembers() {
		return null;
	}

	@Override
	public boolean updateMember(MemberPojo member) {
		return false;
	}

	@Override
	public boolean deleteMember(int memberId) {
		return false;
	}

}
