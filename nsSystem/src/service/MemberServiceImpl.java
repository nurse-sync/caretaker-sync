package service;

import java.util.List;

import dao.MemberDao;
import dao.MemberDaoImpl;
import model.MemberPojo;

public class MemberServiceImpl implements MemberService{

	private MemberDao memberDao;

    public MemberServiceImpl() {
        this.memberDao = new MemberDaoImpl(); 
    }
    
	@Override
	public MemberPojo getMemberById(int memberId) {
        return memberDao.getMemberById(memberId);
	}

	@Override
	public boolean addMember(MemberPojo member) {
        return memberDao.addMember(member);
	}

	@Override
	public boolean updateMember(MemberPojo member) {
        return memberDao.updateMember(member);
	}

	@Override
	public boolean deleteMember(int memberId) {
        return memberDao.deleteMember(memberId);
	}

	@Override
	public List<MemberPojo> getAllMembers() {
        return memberDao.getAllMembers();
	}

}
