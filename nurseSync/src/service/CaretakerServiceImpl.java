package service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dao.CaretakerDao;
//import dao.CaretakerDaoCollectionImpl;
import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;
import pojo.RequestPojo;
import dao.RequestDao;
import exceptions.GlobalExceptionHandler;

public class CaretakerServiceImpl implements CaretakerService {

	private CaretakerDao caretakerDao;
	private RequestDao requestDao;

	public CaretakerServiceImpl(CaretakerDao caretakerDao) {
		this.caretakerDao = caretakerDao;
	}

	@Override
	public boolean createCaretaker(CaretakerPojo caretaker) {
		try {
			return caretakerDao.addCaretaker(caretaker);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public CaretakerPojo getCaretakerById(int caretakerId) {
		try {
			CaretakerPojo caretaker = caretakerDao.getCaretakerById(caretakerId);
			if (caretaker == null) {
				throw new NoSuchElementException("Caretaker not found with ID: " + caretakerId);
			}
			return caretaker;
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}

	}

	@Override
	public List<CaretakerPojo> getAllCaretakers() {
		try {
			return caretakerDao.getAllCaretaker();
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>(); // Return an empty list
		}
	}

	@Override
	public boolean updateCaretaker(CaretakerPojo caretaker) {
		try {
			return caretakerDao.updateCaretaker(caretaker);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public boolean deleteCaretaker(int caretakerId) {
		try {
			return caretakerDao.deleteCaretaker(caretakerId);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public CaretakerPojo getCaretakerByUsername(String userName) {
		try {
			CaretakerPojo caretaker = caretakerDao.getCaretakerByUsername(userName);
			if (caretaker == null) {
				throw new NoSuchElementException("Caretaker not found with username: " + userName);
			}
			return caretaker;
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}
	}

	@Override
	public boolean updateCaretakerStatus(int caretakerId, String status) {
		try {
			return caretakerDao.updateCaretakerStatus(caretakerId, status);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // Indicate failure
		}
	}

	@Override
	public boolean handleRequest(int requestId, boolean accept) {
		try {
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
			} else {
				throw new NoSuchElementException("Request not found with ID: " + requestId);
			}
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // Indicate failure
		}
	}

	@Override
	public List<CaretakerPojo> findCaretakersByPreferences(CaretakerPreferences preferences) {
		try {
			if (preferences == null) {
				throw new IllegalArgumentException("Preferences cannot be null");
			}
			return caretakerDao.findCaretakersByPreferences(preferences);
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleIllegalArgumentException(e);
			return new ArrayList<>(); // Return an empty list
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>(); // Return an empty list
		}
	}
}
