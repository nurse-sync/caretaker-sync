package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AddressPojo;

public class AddressDaoImpl implements AddressDao {

	@Override
	public AddressPojo getAddressById(int addressId) {
		AddressPojo address = null;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ADDRESS_BY_ID)) {
			stmt.setInt(1, addressId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				address = new AddressPojo(rs.getInt("address_id"), rs.getString("address_flat_number"),
						rs.getString("address_house_number"), rs.getString("address_street_name"),
						rs.getString("address_locality"), rs.getString("address_district"),
						rs.getInt("address_pincode"), rs.getString("address_country"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return address;
	}

	@Override
	public List<AddressPojo> getAllAddresses() {
		List<AddressPojo> addresses = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ALL_ADDRESSES);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				AddressPojo address = new AddressPojo(rs.getInt("address_id"), rs.getString("address_flat_number"),
						rs.getString("address_house_number"), rs.getString("address_street_name"),
						rs.getString("address_locality"), rs.getString("address_district"),
						rs.getInt("address_pincode"), rs.getString("address_country"));
				addresses.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return addresses;
	}

	@Override
	public boolean addAddress(AddressPojo address) {
		boolean isAdded = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.ADD_ADDRESS)) {
			stmt.setString(1, address.getAddressFlatNumber());
			stmt.setString(2, address.getAddressHouseNumber());
			stmt.setString(3, address.getAddressStreetName());
			stmt.setString(4, address.getAddressLocality());
			stmt.setString(5, address.getAddressDistrict());
			stmt.setInt(6, address.getAddressPincode());
			stmt.setString(7, address.getAddressCountry());
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
	public boolean updateAddress(AddressPojo address) {
		boolean isUpdated = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_ADDRESS)) {
			stmt.setString(1, address.getAddressFlatNumber());
			stmt.setString(2, address.getAddressHouseNumber());
			stmt.setString(3, address.getAddressStreetName());
			stmt.setString(4, address.getAddressLocality());
			stmt.setString(5, address.getAddressDistrict());
			stmt.setInt(6, address.getAddressPincode());
			stmt.setString(7, address.getAddressCountry());
			stmt.setInt(8, address.getAddressId());
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
	public boolean deleteAddress(int addressId) {
		boolean isDeleted = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.DELETE_ADDRESS)) {
			stmt.setInt(1, addressId);
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
