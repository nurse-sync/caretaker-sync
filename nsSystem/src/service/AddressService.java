package service;

import java.util.List;

import model.AddressPojo;

public interface AddressService {
	AddressPojo getAddressById(int addressId);

	List<AddressPojo> getAllAddresses();

	boolean addAddress(AddressPojo address);

	boolean updateAddress(AddressPojo address);

	boolean deleteAddress(int addressId);
}
