package service;

import java.util.List;

import model.ServiceProviderPojo;

public interface ServiceProviderService {
	void addServiceProvider(ServiceProviderPojo serviceProvider) throws Exception;

	ServiceProviderPojo fetchServiceProviderById(int spId) throws Exception;

	List<ServiceProviderPojo> fetchAllServiceProviders() throws Exception;

	void updateServiceProvider(ServiceProviderPojo serviceProvider) throws Exception;

	void deleteServiceProvider(int spId) throws Exception;

	List<ServiceProviderPojo> fetchServiceProvidersByCategory(int category) throws Exception;

	List<ServiceProviderPojo> fetchServiceProvidersByStatus(int statusId) throws Exception;

	void updateServiceProviderStatus(int spId, int statusId) throws Exception;
}
