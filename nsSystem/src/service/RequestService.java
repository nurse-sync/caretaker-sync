package service;

import java.util.List;

import model.RequestPojo;

public interface RequestService {
	RequestPojo getRequestById(int requestId);

	boolean addRequest(RequestPojo request);

	boolean updateRequest(RequestPojo request);

	boolean deleteRequest(int requestId);

	List<RequestPojo> getAllRequests();

	List<RequestPojo> getRequestsByClientId(int clientId);

	List<RequestPojo> getRequestsBySpId(int spId);

	List<RequestPojo> fetchRequestsByStatus(int statusId);

	boolean updateRequestStatus(int requestId, int statusId);

	List<RequestPojo> fetchRequestsByClientAndStatus(int clientId, int statusId);

	List<RequestPojo> fetchRequestsBySpAndStatus(int spId, int statusId);

}
