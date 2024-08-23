package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dao.RequestDao;
import exceptions.GlobalExceptionHandler;
import pojo.RequestPojo;
import service.RequestService;

public class RequestServiceImpl implements RequestService {

	private RequestDao requestDao;

	public RequestServiceImpl(RequestDao requestDao) {
		this.requestDao = requestDao;
	}

	@Override
	public boolean sendRequestToCaretaker(int userId, int caretakerId, String serviceLocation, String patientName,
			int patientAge, String patientGender, Date startDate, Date endDate) {

		try {
			int newRequestId = generateNewRequestId(); // Method to generate new request ID

			// Create a new RequestPojo object with the generated request ID
			RequestPojo request = new RequestPojo(newRequestId, userId, caretakerId, "Pending", serviceLocation,
					patientName, patientAge, patientGender, startDate, endDate);

			return requestDao.addRequest(request);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public List<RequestPojo> getRequestsForCaretaker(int caretakerId) {
		try {
			return requestDao.getAllRequestsByCaretakerId(caretakerId);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>();
		}
	}

	@Override
	public RequestPojo getRequestById(int requestId) {
		try {
			RequestPojo request = requestDao.getRequestById(requestId);
			if (request == null) {
				throw new NoSuchElementException("Request not found with ID: " + requestId);
			}
			return request;
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}
	}

	@Override
	public boolean updateRequest(RequestPojo request) {
		try {
			return requestDao.updateRequest(request);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // Indicate failure
		}
	}

	private int generateNewRequestId() {
		try {
			// Logic to generate a new unique request ID
			return requestDao.getMaxRequestId() + 1;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return -1; // failure
		}
	}

	@Override
	public boolean approveOrRejectRequest(int requestId, boolean approve) {
		try {
			String newStatus = approve ? "Approved" : "Rejected";
			return requestDao.updateRequestStatus(requestId, newStatus);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public List<RequestPojo> getRequestsByUserId(int userId) {
		try {
			return requestDao.getRequestsByUserId(userId);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>(); // Return an empty list
		}
	}

	@Override
	public boolean hasDuplicateRequest(int userId, String patientName, String startDateStr, String endDateStr) {
		try {
			// Fetch all requests for the user
			List<RequestPojo> userRequests = requestDao.getRequestsByUserId(userId);

			// Convert string dates to java.sql.Date objects
			Date startDate = Date.valueOf(startDateStr);
			Date endDate = Date.valueOf(endDateStr);

			// Check each request for duplicates
			for (RequestPojo request : userRequests) {
				if (request.getPatientName().equals(patientName) && request.getStartDate().equals(startDate)
						&& request.getEndDate().equals(endDate)) {
					return true; // Duplicate found
				}
			}
			return false; // No duplicate found
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleIllegalArgumentException(e);
			return false; // failure
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}
}
