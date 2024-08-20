package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pojo.RequestPojo;

public class RequestDaoCollectionImpl implements RequestDao{
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
		 return requestData.values().stream()
		            .filter(request -> request.getCaretakerId() == caretakerId)
		            .collect(Collectors.toList());	}

}
