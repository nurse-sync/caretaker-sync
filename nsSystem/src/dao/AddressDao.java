package dao;

import java.util.List;

import model.AddressPojo;

public interface AddressDao {
	AddressPojo getAddressById(int addressId);

	List<AddressPojo> getAllAddresses();

	boolean addAddress(AddressPojo address);

	boolean updateAddress(AddressPojo address);

	boolean deleteAddress(int addressId);
}
