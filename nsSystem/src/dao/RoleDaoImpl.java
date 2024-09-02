package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RolePojo;

public class RoleDaoImpl implements RoleDao {

	@Override
	public int addRole(RolePojo rolePojo) {
		int rowsAffected = 0;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.ADD_ROLE)) {

			stmt.setString(1, rolePojo.getRoleName());
			rowsAffected = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int updateRole(RolePojo rolePojo) {
		int rowsAffected = 0;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_ROLE)) {

			stmt.setString(1, rolePojo.getRoleName());
			stmt.setInt(2, rolePojo.getRoleId());
			rowsAffected = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public int deleteRole(int roleId) {
		int rowsAffected = 0;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.DELETE_ROLE)) {

			stmt.setInt(1, roleId);
			rowsAffected = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsAffected;
	}

	@Override
	public RolePojo getRoleById(int roleId) {
		RolePojo rolePojo = null;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ROLE_BY_ID)) {

			stmt.setInt(1, roleId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					rolePojo = new RolePojo(rs.getInt("role_id"), rs.getString("role_name"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rolePojo;
	}

	@Override
	public List<RolePojo> getAllRoles() {
		List<RolePojo> roles = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ALL_ROLES);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				RolePojo rolePojo = new RolePojo(rs.getInt("role_id"), rs.getString("role_name"));
				roles.add(rolePojo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
	}

}
