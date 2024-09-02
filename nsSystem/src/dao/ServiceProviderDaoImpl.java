package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ServiceProviderPojo;

public class ServiceProviderDaoImpl implements ServiceProviderDao {

	@Override
	public void addServiceProvider(ServiceProviderPojo serviceProvider) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.ADD_SERVICE_PROVIDER)) {

			ps.setInt(1, serviceProvider.getSpId());
			ps.setString(2, serviceProvider.getGender());
			ps.setInt(3, serviceProvider.getCategory());
			ps.setDouble(4, serviceProvider.getWeeklySalary());
			ps.setDate(5, serviceProvider.getAvailableFrom());
			ps.setDate(6, serviceProvider.getAvailableTo());
			ps.setInt(7, serviceProvider.getAddressId());
			ps.setInt(8, serviceProvider.getQualificationId());
			ps.setBoolean(9, serviceProvider.isLiveIn());
			ps.setInt(10, serviceProvider.getAdminId());
			ps.setInt(11, serviceProvider.getStatusId());
			ps.setString(12, serviceProvider.getIdProofUrl());
			if (serviceProvider.getNurseLicenseId() != null) {
				ps.setInt(13, serviceProvider.getNurseLicenseId());
			} else {
				ps.setNull(13, java.sql.Types.INTEGER);
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error adding service provider: " + e.getMessage(), e);
		}
	}

	@Override
	public ServiceProviderPojo fetchServiceProviderById(int spId) throws Exception {
		ServiceProviderPojo serviceProvider = null;
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_SERVICE_PROVIDER_BY_ID)) {

			ps.setInt(1, spId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					serviceProvider = mapResultSetToServiceProvider(rs);
				}
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching service provider by ID: " + e.getMessage(), e);
		}
		return serviceProvider;
	}

	@Override
	public List<ServiceProviderPojo> fetchAllServiceProviders() throws Exception {
		List<ServiceProviderPojo> serviceProviders = new ArrayList<>();
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_ALL_SERVICE_PROVIDERS);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				serviceProviders.add(mapResultSetToServiceProvider(rs));
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching all service providers: " + e.getMessage(), e);
		}
		return serviceProviders;
	}

	@Override
	public void updateServiceProvider(ServiceProviderPojo serviceProvider) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.UPDATE_SERVICE_PROVIDER)) {

			ps.setString(1, serviceProvider.getGender());
			ps.setInt(2, serviceProvider.getCategory());
			ps.setDouble(3, serviceProvider.getWeeklySalary());
			ps.setDate(4, serviceProvider.getAvailableFrom());
			ps.setDate(5, serviceProvider.getAvailableTo());
			ps.setInt(6, serviceProvider.getAddressId());
			ps.setInt(7, serviceProvider.getQualificationId());
			ps.setBoolean(8, serviceProvider.isLiveIn());
			ps.setInt(9, serviceProvider.getAdminId());
			ps.setInt(10, serviceProvider.getStatusId());
			ps.setString(11, serviceProvider.getIdProofUrl());
			if (serviceProvider.getNurseLicenseId() != null) {
				ps.setInt(12, serviceProvider.getNurseLicenseId());
			} else {
				ps.setNull(12, java.sql.Types.INTEGER);
			}
			ps.setInt(13, serviceProvider.getSpId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error updating service provider: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteServiceProvider(int spId) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.DELETE_SERVICE_PROVIDER)) {

			ps.setInt(1, spId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error deleting service provider: " + e.getMessage(), e);
		}
	}

	@Override
	public List<ServiceProviderPojo> fetchServiceProvidersByCategory(int category) throws Exception {
		List<ServiceProviderPojo> serviceProviders = new ArrayList<>();
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_SERVICE_PROVIDERS_BY_CATEGORY)) {

			ps.setInt(1, category);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					serviceProviders.add(mapResultSetToServiceProvider(rs));
				}
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching service providers by category: " + e.getMessage(), e);
		}
		return serviceProviders;
	}

	@Override
	public List<ServiceProviderPojo> fetchServiceProvidersByStatus(int statusId) throws Exception {
		List<ServiceProviderPojo> serviceProviders = new ArrayList<>();
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_SERVICE_PROVIDERS_BY_STATUS)) {

			ps.setInt(1, statusId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					serviceProviders.add(mapResultSetToServiceProvider(rs));
				}
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching service providers by status: " + e.getMessage(), e);
		}
		return serviceProviders;
	}

	@Override
	public void updateServiceProviderStatus(int spId, int statusId) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.UPDATE_SERVICE_PROVIDER_STATUS)) {

			ps.setInt(1, statusId);
			ps.setInt(2, spId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error updating service provider status: " + e.getMessage(), e);
		}
	}

	private ServiceProviderPojo mapResultSetToServiceProvider(ResultSet rs) throws SQLException {
		return new ServiceProviderPojo(rs.getInt("sp_id"), rs.getString("gender"), rs.getInt("category"),
				rs.getDouble("weekly_salary"), rs.getDate("available_from"), rs.getDate("available_to"),
				rs.getInt("address_id"), rs.getInt("qualification_id"), rs.getBoolean("live_in"), rs.getInt("admin_id"),
				rs.getInt("status_id"), rs.getString("id_proof_url"),
				rs.getObject("nurse_license_id") != null ? rs.getInt("nurse_license_id") : null);
	}

}
