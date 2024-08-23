package dao;

import java.util.List;

import pojo.RequestPojo;

public interface RequestDao {
	boolean addRequest(RequestPojo request);

	RequestPojo getRequestById(int requestId);

	boolean updateRequest(RequestPojo request);

	List<RequestPojo> getAllRequestsByCaretakerId(int caretakerId);

	int getMaxRequestId();

	RequestPojo findById(int requestId);

	boolean updateRequestStatus(int requestId, String status);

	int getNextRequestId();

	List<RequestPojo> getRequestsByUserId(int userId);
}
