package service;

import java.util.List;

import dao.AddressDao;
import dao.AddressDaoImpl;
import model.AddressPojo;

public class AddressServiceImpl implements AddressService{

	private AddressDao addressDao;

    public AddressServiceImpl() {
        this.addressDao = new AddressDaoImpl(); 
    }
    
	@Override
	public AddressPojo getAddressById(int addressId) {
        return addressDao.getAddressById(addressId);
	}

	@Override
	public List<AddressPojo> getAllAddresses() {
        return addressDao.getAllAddresses();
	}

	@Override
	public boolean addAddress(AddressPojo address) {
        return addressDao.addAddress(address);
	}

	@Override
	public boolean updateAddress(AddressPojo address) {
        return addressDao.updateAddress(address);
	}

	@Override
	public boolean deleteAddress(int addressId) {
        return addressDao.deleteAddress(addressId);
	}
}
