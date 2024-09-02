package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.RequestPojo;

public class RequestDaoImpl implements RequestDao {

	@Override
	public RequestPojo getRequestById(int requestId) {
		RequestPojo request = null;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_REQUEST_BY_ID)) {
			stmt.setInt(1, requestId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				request = new RequestPojo(rs.getInt("request_id"), rs.getInt("client_id"), rs.getInt("sp_id"),
						rs.getInt("address_id"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("member_id"), rs.getString("message_to_sp"), rs.getString("message_from_sp"),
						rs.getInt("status_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return request;
	}

	@Override
	public boolean addRequest(RequestPojo request) {
		boolean isAdded = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.ADD_REQUEST)) {
			stmt.setInt(1, request.getClientId());
			stmt.setInt(2, request.getSpId());
			stmt.setInt(3, request.getAddressId());
			stmt.setDate(4, request.getStartDate());
			stmt.setDate(5, request.getEndDate());
			stmt.setInt(6, request.getMemberId());
			stmt.setString(7, request.getMessageToSp());
			stmt.setString(8, request.getMessageFromSp());
			stmt.setInt(9, request.getStatusId());
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
	public boolean updateRequest(RequestPojo request) {
		boolean isUpdated = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_REQUEST)) {
			stmt.setInt(1, request.getClientId());
			stmt.setInt(2, request.getSpId());
			stmt.setInt(3, request.getAddressId());
			stmt.setDate(4, request.getStartDate());
			stmt.setDate(5, request.getEndDate());
			stmt.setInt(6, request.getMemberId());
			stmt.setString(7, request.getMessageToSp());
			stmt.setString(8, request.getMessageFromSp());
			stmt.setInt(9, request.getStatusId());
			stmt.setInt(10, request.getRequestId());
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
	public boolean deleteRequest(int requestId) {
		boolean isDeleted = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.DELETE_REQUEST)) {
			stmt.setInt(1, requestId);
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
	public List<RequestPojo> getAllRequests() {
		List<RequestPojo> requests = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ALL_REQUESTS);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				RequestPojo request = new RequestPojo(rs.getInt("request_id"), rs.getInt("client_id"),
						rs.getInt("sp_id"), rs.getInt("address_id"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("member_id"), rs.getString("message_to_sp"), rs.getString("message_from_sp"),
						rs.getInt("status_id"));
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return requests;
	}

	@Override
	public List<RequestPojo> getRequestsByClientId(int clientId) {
		List<RequestPojo> requests = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_REQUESTS_BY_CLIENT_ID)) {
			stmt.setInt(1, clientId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RequestPojo request = new RequestPojo(rs.getInt("request_id"), rs.getInt("client_id"),
						rs.getInt("sp_id"), rs.getInt("address_id"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("member_id"), rs.getString("message_to_sp"), rs.getString("message_from_sp"),
						rs.getInt("status_id"));
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return requests;
	}

	@Override
	public List<RequestPojo> getRequestsBySpId(int spId) {
		List<RequestPojo> requests = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_REQUESTS_BY_SERVICE_PROVIDER_ID)) {
			stmt.setInt(1, spId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RequestPojo request = new RequestPojo(rs.getInt("request_id"), rs.getInt("client_id"),
						rs.getInt("sp_id"), rs.getInt("address_id"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("member_id"), rs.getString("message_to_sp"), rs.getString("message_from_sp"),
						rs.getInt("status_id"));
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return requests;
	}

	@Override
	public List<RequestPojo> fetchRequestsByStatus(int statusId) {
		List<RequestPojo> requests = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_REQUESTS_BY_STATUS)) {
			stmt.setInt(1, statusId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RequestPojo request = new RequestPojo(rs.getInt("request_id"), rs.getInt("client_id"),
						rs.getInt("sp_id"), rs.getInt("address_id"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("member_id"), rs.getString("message_to_sp"), rs.getString("message_from_sp"),
						rs.getInt("status_id"));
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return requests;
	}

	@Override
	public boolean updateRequestStatus(int requestId, int statusId) {
		boolean isUpdated = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_REQUEST_STATUS)) {
			stmt.setInt(1, statusId);
			stmt.setInt(2, requestId);
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
	public List<RequestPojo> fetchRequestsByClientAndStatus(int clientId, int statusId) {
		List<RequestPojo> requests = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_REQUESTS_BY_CLIENT_AND_STATUS)) {
			stmt.setInt(1, clientId);
			stmt.setInt(2, statusId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RequestPojo request = new RequestPojo(rs.getInt("request_id"), rs.getInt("client_id"),
						rs.getInt("sp_id"), rs.getInt("address_id"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("member_id"), rs.getString("message_to_sp"), rs.getString("message_from_sp"),
						rs.getInt("status_id"));
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return requests;
	}

	@Override
	public List<RequestPojo> fetchRequestsBySpAndStatus(int spId, int statusId) {
		List<RequestPojo> requests = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_REQUESTS_BY_SP_AND_STATUS)) {
			stmt.setInt(1, spId);
			stmt.setInt(2, statusId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				RequestPojo request = new RequestPojo(rs.getInt("request_id"), rs.getInt("client_id"),
						rs.getInt("sp_id"), rs.getInt("address_id"), rs.getDate("start_date"), rs.getDate("end_date"),
						rs.getInt("member_id"), rs.getString("message_to_sp"), rs.getString("message_from_sp"),
						rs.getInt("status_id"));
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return requests;
	}

}
