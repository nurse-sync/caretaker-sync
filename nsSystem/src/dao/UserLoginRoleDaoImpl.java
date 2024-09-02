package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserLoginRolePojo;

public class UserLoginRoleDaoImpl implements UserLoginRoleDao {

	@Override
	public boolean assignRoleToUser(UserLoginRolePojo userRole) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.ASSIGN_ROLE_TO_USER);
			pstmt.setInt(1, userRole.getUserId());
			pstmt.setInt(2, userRole.getRoleId());
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closeConnection();
		}
	}

	@Override
	public List<String> fetchRolesByUserId(int userId) {
		List<String> roles = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.FETCH_ROLES_BY_USER_ID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roles.add(rs.getString("role_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return roles;
	}

	@Override
	public boolean removeRoleFromUser(UserLoginRolePojo userRole) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.REMOVE_ROLE_FROM_USER);
			pstmt.setInt(1, userRole.getUserId());
			pstmt.setInt(2, userRole.getRoleId());
			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closeConnection();
		}
	}

}
