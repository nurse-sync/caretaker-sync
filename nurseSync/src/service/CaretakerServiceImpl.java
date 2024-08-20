package service;

import java.util.List;

import dao.CaretakerDao;
//import dao.CaretakerDaoCollectionImpl;
import pojo.CaretakerPojo;
import pojo.RequestPojo;
import dao.RequestDao;

public class CaretakerServiceImpl implements CaretakerService{

	private CaretakerDao caretakerDao;
    private RequestDao requestDao;

//    public CaretakerServiceImpl(CaretakerDao caretakerDao, RequestDao requestDao) {
//        this.caretakerDao = caretakerDao;
//        this.requestDao = requestDao;
//    }
    
    public CaretakerServiceImpl(CaretakerDao caretakerDao) {
        this.caretakerDao = caretakerDao;
    }
    
	@Override
	public boolean createCaretaker(CaretakerPojo caretaker) {
        return caretakerDao.addCaretaker(caretaker);
	}

	@Override
	public CaretakerPojo getCaretakerById(int caretakerId) {
        return caretakerDao.getCaretakerById(caretakerId);
	}

	@Override
	public List<CaretakerPojo> getAllCaretakers() {
        return caretakerDao.getAllCaretaker();
	}

	@Override
	public boolean updateCaretaker(CaretakerPojo caretaker) {
        return caretakerDao.updateCaretaker(caretaker);
	}

	@Override
	public boolean deleteCaretaker(int caretakerId) {
        return caretakerDao.deleteCaretaker(caretakerId);
	}

	@Override
	public CaretakerPojo getCaretakerByUsername(String username) {
		return caretakerDao.getCaretakerByUsername(username);
	}

	@Override
	public boolean updateCaretakerStatus(int caretakerId, String status) {
        return caretakerDao.updateCaretakerStatus(caretakerId, status);
	}

	@Override
	public boolean handleRequest(int requestId, boolean accept) {
		RequestPojo request = requestDao.getRequestById(requestId);
       
		if (request != null) {
            if (accept) {
                caretakerDao.updateCaretakerStatus(request.getCaretakerId(), "Booked");
                request.setStatus("Accepted");
            } else {
                request.setStatus("Rejected");
            }
            requestDao.updateRequest(request);
            return true;
        }
        return false;
	}

}
