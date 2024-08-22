package service;

import java.util.List;
import pojo.RequestPojo;

public interface RequestService {
    boolean sendRequestToCaretaker(int userId, int caretakerId, String serviceLocation, String patientName, int patientAge, String patientGender);
    List<RequestPojo> getRequestsForCaretaker(int caretakerId);
    RequestPojo getRequestById(int requestId);
    boolean updateRequest(RequestPojo request);
    boolean approveOrRejectRequest(int requestId, boolean approve);
    List<RequestPojo> getRequestsByUserId(int userId);

}


//package service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import pojo.CaretakerPojo;
//import pojo.RequestPojo;
//import pojo.UserPojo;
//import dao.RequestDao;
//
//public class RequestService {
//	
//	private Map<Integer, UserPojo> userData = new HashMap<>();
//    private Map<Integer, CaretakerPojo> caretakerData = new HashMap<>();
//    private int requestIdCounter = 1;
//    
//    private RequestDao requestDao;
//
//    public boolean sendRequest(int userId, int caretakerId) {
//        UserPojo user = userData.get(userId);
//        CaretakerPojo caretaker = caretakerData.get(caretakerId);
//
//        if (user == null || caretaker == null) {
//            return false; // User or Caretaker not found
//        }
//
//        RequestPojo request = new RequestPojo(requestIdCounter++, userId, caretakerId, "Pending");
//        user.addSentRequest(request.getRequestId());
//        caretaker.addRequest(request);
//
//        return true; // Request sent successfully
//    }
//    
//    public List<RequestPojo> getRequestsForCaretaker(int caretakerId) {
//        return requestDao.getAllRequestsByCaretakerId(caretakerId);
//    }
//
//}
