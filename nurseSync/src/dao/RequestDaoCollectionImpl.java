package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import exceptions.GlobalExceptionHandler;
import pojo.RequestPojo;

public class RequestDaoCollectionImpl implements RequestDao {
	private Map<Integer, RequestPojo> requestData = new HashMap<>();
	private static int idCounter = 1;

	@Override
	public boolean addRequest(RequestPojo request) {
		try {
			request.setRequestId(idCounter++);
			requestData.put(request.getRequestId(), request);
			return true;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public RequestPojo getRequestById(int requestId) {
		try {
			RequestPojo request = requestData.get(requestId);
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
			if (requestData.containsKey(request.getRequestId())) {
				requestData.put(request.getRequestId(), request);
				return true;
			} else {
				throw new NoSuchElementException("Request not found with ID: " + request.getRequestId());
			}
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public List<RequestPojo> getAllRequestsByCaretakerId(int caretakerId) {
		try {
			return requestData.values().stream().filter(request -> request.getCaretakerId() == caretakerId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return List.of(); // Return an empty list in case of an error
		}
	}

	@Override
	public int getMaxRequestId() {
		try {
			if (requestData.isEmpty()) {
				return 0;
			}
			return requestData.keySet().stream().max(Integer::compare).orElse(0);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return 0;
		}
	}

	@Override
	public RequestPojo findById(int requestId) {
		try {
			RequestPojo request = requestData.get(requestId);
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
	public int getNextRequestId() {
		try {
			return requestData.size() + 1;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return 0;
		}
	}

	@Override
	public boolean updateRequestStatus(int requestId, String status) {
		try {
			RequestPojo request = requestData.get(requestId);
			if (request != null) {
				request.setStatus(status);
				requestData.put(requestId, request);
				return true;
			} else {
				throw new NoSuchElementException("Request not found with ID: " + requestId);
			}
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public List<RequestPojo> getRequestsByUserId(int userId) {
		try {
			return requestData.values().stream().filter(request -> request.getUserId() == userId)
					.collect(Collectors.toList());
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return List.of(); // Return an empty list in case of an error
		}
	}
}
