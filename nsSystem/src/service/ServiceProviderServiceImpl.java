package service;

import java.util.List;

import dao.ServiceProviderDao;
import dao.ServiceProviderDaoImpl;
import model.ServiceProviderPojo;

public class ServiceProviderServiceImpl implements ServiceProviderService {
	private ServiceProviderDao serviceProviderDao;

	public ServiceProviderServiceImpl() {
		this.serviceProviderDao = new ServiceProviderDaoImpl(); // Initialize the DAO implementation
	}

	@Override
	public void addServiceProvider(ServiceProviderPojo serviceProvider) throws Exception {
		serviceProviderDao.addServiceProvider(serviceProvider);

	}

	@Override
	public ServiceProviderPojo fetchServiceProviderById(int spId) throws Exception {
		// TODO Auto-generated method stub
		return serviceProviderDao.fetchServiceProviderById(spId);
	}

	@Override
	public List<ServiceProviderPojo> fetchAllServiceProviders() throws Exception {
		// TODO Auto-generated method stub
		return serviceProviderDao.fetchAllServiceProviders();
	}

	@Override
	public void updateServiceProvider(ServiceProviderPojo serviceProvider) throws Exception {
		serviceProviderDao.updateServiceProvider(serviceProvider);

	}

	@Override
	public void deleteServiceProvider(int spId) throws Exception {
		serviceProviderDao.deleteServiceProvider(spId);

	}

	@Override
	public List<ServiceProviderPojo> fetchServiceProvidersByCategory(int category) throws Exception {
		// TODO Auto-generated method stub
		return serviceProviderDao.fetchServiceProvidersByCategory(category);
	}

	@Override
	public List<ServiceProviderPojo> fetchServiceProvidersByStatus(int statusId) throws Exception {
		// TODO Auto-generated method stub
		return serviceProviderDao.fetchServiceProvidersByStatus(statusId);
	}

	@Override
	public void updateServiceProviderStatus(int spId, int statusId) throws Exception {
		serviceProviderDao.updateServiceProviderStatus(spId, statusId);

	}

}
