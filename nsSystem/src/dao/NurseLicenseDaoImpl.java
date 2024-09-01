package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.NurseLicensePojo;

public class NurseLicenseDaoImpl implements NurseLicenseDao {

	@Override
	public NurseLicensePojo getNurseLicenseById(int nurseLicenseId) {
		NurseLicensePojo nurseLicense = null;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_NURSE_LICENSE_BY_ID)) {
			stmt.setInt(1, nurseLicenseId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				nurseLicense = new NurseLicensePojo(rs.getInt("nurse_license_id"), rs.getInt("sp_id"),
						rs.getString("nurse_license_url"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return nurseLicense;
	}

	@Override
	public boolean addNurseLicense(NurseLicensePojo nurseLicense) {
		boolean isAdded = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.ADD_NURSE_LICENSE)) {
			stmt.setInt(1, nurseLicense.getSpId());
			stmt.setString(2, nurseLicense.getNurseLicenseUrl());
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
	public boolean updateNurseLicense(NurseLicensePojo nurseLicense) {
		boolean isUpdated = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_NURSE_LICENSE)) {
			stmt.setInt(1, nurseLicense.getSpId());
			stmt.setString(2, nurseLicense.getNurseLicenseUrl());
			stmt.setInt(3, nurseLicense.getNurseLicenseId());
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
	public boolean deleteNurseLicense(int nurseLicenseId) {
		boolean isDeleted = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.DELETE_NURSE_LICENSE)) {
			stmt.setInt(1, nurseLicenseId);
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
	public List<NurseLicensePojo> getAllNurseLicenses() {
		List<NurseLicensePojo> nurseLicenses = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ALL_NURSE_LICENSES);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				NurseLicensePojo nurseLicense = new NurseLicensePojo(rs.getInt("nurse_license_id"), rs.getInt("sp_id"),
						rs.getString("nurse_license_url"));
				nurseLicenses.add(nurseLicense);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return nurseLicenses;
	}

}
