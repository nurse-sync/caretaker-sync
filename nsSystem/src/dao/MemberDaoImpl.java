package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MemberPojo;

public class MemberDaoImpl implements MemberDao {

	@Override
	public MemberPojo getMemberById(int memberId) {
		MemberPojo member = null;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_MEMBER_BY_ID)) {
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				member = new MemberPojo(rs.getInt("member_id"), rs.getString("full_name"), rs.getInt("age"),
						rs.getString("gender"), rs.getString("identification_url"), rs.getInt("admin_id"),
						rs.getInt("status_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return member;
	}

	@Override
	public boolean addMember(MemberPojo member) {
		boolean isAdded = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.ADD_MEMBER)) {
			stmt.setString(1, member.getFullName());
			stmt.setInt(2, member.getAge());
			stmt.setString(3, member.getGender());
			stmt.setString(4, member.getIdentificationUrl());
			stmt.setInt(5, member.getAdminId());
			stmt.setInt(6, member.getStatusId());
			int rowsAffected = stmt.executeUpdate();
			isAdded = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return isAdded;
	}

	@Override
	public boolean updateMember(MemberPojo member) {
		 boolean isUpdated = false;
	        try (Connection conn = DBUtil.makeConnection();
	             PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_MEMBER)) {
	            stmt.setString(1, member.getFullName());
	            stmt.setInt(2, member.getAge());
	            stmt.setString(3, member.getGender());
	            stmt.setString(4, member.getIdentificationUrl());
	            stmt.setInt(5, member.getAdminId());
	            stmt.setInt(6, member.getStatusId());
	            stmt.setInt(7, member.getMemberId());
	            int rowsAffected = stmt.executeUpdate();
	            isUpdated = rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.closeConnection();
	        }
	        return isUpdated;
	}

	@Override
	public boolean deleteMember(int memberId) {
		boolean isDeleted = false;
        try (Connection conn = DBUtil.makeConnection();
             PreparedStatement stmt = conn.prepareStatement(DBQueries.DELETE_MEMBER)) {
            stmt.setInt(1, memberId);
            int rowsAffected = stmt.executeUpdate();
            isDeleted = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
        return isDeleted;
	}

	@Override
	public List<MemberPojo> getAllMembers() {
		 List<MemberPojo> members = new ArrayList<>();
	        try (Connection conn = DBUtil.makeConnection();
	             PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ALL_MEMBERS);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	            	MemberPojo member = new MemberPojo(
	                    rs.getInt("member_id"),
	                    rs.getString("full_name"),
	                    rs.getInt("age"),
	                    rs.getString("gender"),
	                    rs.getString("identification_url"),
	                    rs.getInt("admin_id"),
	                    rs.getInt("status_id")
	                );
	                members.add(member);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBUtil.closeConnection();
	        }
	        return members;
	}
}
