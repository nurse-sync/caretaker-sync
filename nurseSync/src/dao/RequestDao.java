package dao;

import java.util.List;

import pojo.RequestPojo;

public interface RequestDao {
	boolean addRequest(RequestPojo request);
	RequestPojo getRequestById(int requestId);
    boolean updateRequest(RequestPojo request);
    List<RequestPojo> getAllRequestsByCaretakerId(int caretakerId); // Method to get requests for a specific caretaker
}
