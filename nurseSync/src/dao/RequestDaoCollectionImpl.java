package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pojo.RequestPojo;

public class RequestDaoCollectionImpl implements RequestDao {
	private Map<Integer, RequestPojo> requestData = new HashMap<>();
	private static int idCounter = 1;

	@Override
	public boolean addRequest(RequestPojo request) {
		request.setRequestId(idCounter++);
		requestData.put(request.getRequestId(), request);
		return true;
	}

	@Override
	public RequestPojo getRequestById(int requestId) {
		return requestData.get(requestId);
	}

	@Override
	public boolean updateRequest(RequestPojo request) {
		if (requestData.containsKey(request.getRequestId())) {
			requestData.put(request.getRequestId(), request);
			return true;
		}
		return false;
	}

	@Override
	public List<RequestPojo> getAllRequestsByCaretakerId(int caretakerId) {
		return requestData.values().stream().filter(request -> request.getCaretakerId() == caretakerId)
				.collect(Collectors.toList());
	}

	@Override
	public int getMaxRequestId() {
		if (requestData.isEmpty()) {
			return 0;
		}
		return requestData.keySet().stream().max(Integer::compare).orElse(0);
	}

	@Override
	public RequestPojo findById(int requestId) {
		return requestData.get(requestId);
	}

	@Override
	public int getNextRequestId() {
		return requestData.size() + 1;
	}

	@Override
	public boolean updateRequestStatus(int requestId, String status) {
		RequestPojo request = requestData.get(requestId);
		if (request != null) {
			request.setStatus(status);
			requestData.put(requestId, request);
			return true;
		}
		return false;
	}

	@Override
	public List<RequestPojo> getRequestsByUserId(int userId) {
		return requestData.values().stream().filter(request -> request.getUserId() == userId)
				.collect(Collectors.toList());
	}
}
