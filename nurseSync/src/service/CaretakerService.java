package service;

import java.util.List;

import pojo.CaretakerPojo;

public interface CaretakerService {
	boolean createCaretaker(CaretakerPojo caretaker);
	CaretakerPojo getCaretakerById(int caretakerId);
	CaretakerPojo getCaretakerByUsername(String username);
    List<CaretakerPojo> getAllCaretakers();
    boolean updateCaretaker(CaretakerPojo caretaker);
    boolean deleteCaretaker(int caretaker);
    boolean updateCaretakerStatus(int caretakerId, String status);
    boolean handleRequest(int requestId, boolean accept);
}
