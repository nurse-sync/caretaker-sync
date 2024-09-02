package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.UserPojo;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean createUser(UserPojo user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.CREATE_USER);
			pstmt.setString(1, user.getFullName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhoneNumber());
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
	public UserPojo fetchUserById(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.FETCH_USER_BY_ID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new UserPojo(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("email"),
						rs.getString("phone_number"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return null;

	}

	@Override
	public List<UserPojo> fetchAllUsers() {
		List<UserPojo> users = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.FETCH_ALL_USERS);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(new UserPojo(rs.getInt("user_id"), rs.getString("full_name"), rs.getString("email"),
						rs.getString("phone_number")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return users;
	}

	@Override
	public boolean updateUser(UserPojo user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.UPDATE_USER);
			pstmt.setString(1, user.getFullName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPhoneNumber());
			pstmt.setInt(4, user.getUserId());
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
	public boolean deleteUser(int userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.makeConnection();
			pstmt = conn.prepareStatement(DBQueries.DELETE_USER);
			pstmt.setInt(1, userId);
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
