package service;

import java.util.List;

import dao.RequestDao;
import dao.RequestDaoImpl;
import model.RequestPojo;

public class RequestServiceImpl implements RequestService {
	private RequestDao requestDao;

	public RequestServiceImpl() {
		this.requestDao = new RequestDaoImpl();
	}

	@Override
	public RequestPojo getRequestById(int requestId) {
		return requestDao.getRequestById(requestId);
	}

	@Override
	public boolean addRequest(RequestPojo request) {
		return requestDao.addRequest(request);
	}

	@Override
	public boolean updateRequest(RequestPojo request) {
		return requestDao.updateRequest(request);
	}

	@Override
	public boolean deleteRequest(int requestId) {
		return requestDao.deleteRequest(requestId);
	}

	@Override
	public List<RequestPojo> getAllRequests() {
		return requestDao.getAllRequests();
	}

	@Override
	public List<RequestPojo> getRequestsByClientId(int clientId) {
		return requestDao.getRequestsByClientId(clientId);
	}

	@Override
	public List<RequestPojo> getRequestsBySpId(int spId) {
		return requestDao.getRequestsBySpId(spId);
	}

	@Override
	public List<RequestPojo> fetchRequestsByStatus(int statusId) {
		return requestDao.fetchRequestsByStatus(statusId);
	}

	@Override
	public boolean updateRequestStatus(int requestId, int statusId) {
		return requestDao.updateRequestStatus(requestId, statusId);
	}

	@Override
	public List<RequestPojo> fetchRequestsByClientAndStatus(int clientId, int statusId) {
		return requestDao.fetchRequestsByClientAndStatus(clientId, statusId);
	}

	@Override
	public List<RequestPojo> fetchRequestsBySpAndStatus(int spId, int statusId) {
		return requestDao.fetchRequestsBySpAndStatus(spId, statusId);
	}

}
