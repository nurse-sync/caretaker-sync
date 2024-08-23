package service;

import java.util.List;
import pojo.RequestPojo;

public interface RequestService {
	boolean sendRequestToCaretaker(int userId, int caretakerId, String serviceLocation, String patientName,
			int patientAge, String patientGender);

	List<RequestPojo> getRequestsForCaretaker(int caretakerId);

	RequestPojo getRequestById(int requestId);

	boolean updateRequest(RequestPojo request);

	boolean approveOrRejectRequest(int requestId, boolean approve);

	List<RequestPojo> getRequestsByUserId(int userId);

}