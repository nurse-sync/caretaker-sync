package service;

import java.util.List;

import dao.CaretakerDao;
import pojo.CaretakerPojo;

public class CaretakerServiceImpl implements CaretakerService{

	private CaretakerDao caretakerDao;

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

}
