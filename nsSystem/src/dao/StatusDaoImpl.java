package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.StatusPojo;

public class StatusDaoImpl implements StatusDao {

	@Override
	public void addStatus(StatusPojo status) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.ADD_STATUS)) {

			ps.setInt(1, status.getStatusId());
			ps.setString(2, status.getStatusName());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error adding status: " + e.getMessage(), e);
		}
	}

	@Override
	public StatusPojo fetchStatusById(int statusId) throws Exception {
		StatusPojo status = null;
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_STATUS_BY_ID)) {

			ps.setInt(1, statusId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					status = mapResultSetToStatus(rs);
				}
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching status by ID: " + e.getMessage(), e);
		}
		return status;
	}

	@Override
	public List<StatusPojo> fetchAllStatuses() throws Exception {
		List<StatusPojo> statuses = new ArrayList<>();
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_ALL_STATUSES);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				statuses.add(mapResultSetToStatus(rs));
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching all statuses: " + e.getMessage(), e);
		}
		return statuses;
	}

	@Override
	public void updateStatus(StatusPojo status) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.UPDATE_STATUS)) {

			ps.setString(1, status.getStatusName());
			ps.setInt(2, status.getStatusId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error updating status: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteStatus(int statusId) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.DELETE_STATUS)) {

			ps.setInt(1, statusId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error deleting status: " + e.getMessage(), e);
		}
	}

	private StatusPojo mapResultSetToStatus(ResultSet rs) throws SQLException {
		return new StatusPojo(rs.getInt("status_id"), rs.getString("status_name"));
	}

}
