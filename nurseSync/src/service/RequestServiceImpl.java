package service;

import java.util.List;
import dao.RequestDao;
import pojo.RequestPojo;
import service.RequestService;

public class RequestServiceImpl implements RequestService {

    private RequestDao requestDao;

    public RequestServiceImpl(RequestDao requestDao) {
        this.requestDao = requestDao;
    }
    
	@Override
	public boolean sendRequestToCaretaker(int userId, int caretakerId, String serviceLocation, String patientName, int patientAge, String patientGender) {
		
		int newRequestId = generateNewRequestId(); // Method to generate new request ID

		// Create a new RequestPojo object with the generated request ID
		RequestPojo request = new RequestPojo(newRequestId, userId, caretakerId, "Pending", serviceLocation, patientName, patientAge, patientGender);
		
		// Add the request to the DAO
		return requestDao.addRequest(request);
	}

	@Override
	public List<RequestPojo> getRequestsForCaretaker(int caretakerId) {
        return requestDao.getAllRequestsByCaretakerId(caretakerId);
	}

	@Override
	public RequestPojo getRequestById(int requestId) {
		 return requestDao.getRequestById(requestId);
	}

	@Override
	public boolean updateRequest(RequestPojo request) {
        return requestDao.updateRequest(request);
	}
	
	private int generateNewRequestId() {
        // Logic to generate a new unique request ID
        return requestDao.getMaxRequestId() + 1;
    }

	@Override
	public boolean approveOrRejectRequest(int requestId, boolean approve) {
		String newStatus = approve ? "Approved" : "Rejected";
        return requestDao.updateRequestStatus(requestId, newStatus);
	}

	@Override
	public List<RequestPojo> getRequestsByUserId(int userId) {
        return requestDao.getRequestsByUserId(userId);
	}
	
}
