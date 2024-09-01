package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginPojo;

public class LoginDaoImpl implements LoginDao{

	@Override
	public LoginPojo getUserById(int userId) {
		LoginPojo user = null;
        try (Connection conn = DBUtil.makeConnection();
             PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_BY_USER_ID)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new LoginPojo(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
        return user;
	}

	@Override
	public LoginPojo getUserByUserName(String userName) {
		LoginPojo user = null;
        try (Connection conn = DBUtil.makeConnection();
             PreparedStatement stmt = conn.prepareStatement(DBQueries.GET_USER_BY_USERNAME)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new LoginPojo(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
        return user;
	}

	@Override
	public boolean addUser(LoginPojo user) {
		boolean isAdded = false;
        try (Connection conn = DBUtil.makeConnection();
             PreparedStatement stmt = conn.prepareStatement(DBQueries.CREATE_LOGIN)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
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
	public boolean updateUser(LoginPojo user) {
		boolean isUpdated = false;
        try (Connection conn = DBUtil.makeConnection();
             PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_LOGIN)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getUserId());
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
	public boolean deleteUser(int userId) {
		boolean isDeleted = false;
        try (Connection conn = DBUtil.makeConnection();
             PreparedStatement stmt = conn.prepareStatement(DBQueries.DELETE_LOGIN)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            isDeleted = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection();
        }
        return isDeleted;
    }
}
