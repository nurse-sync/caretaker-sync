package dao;

import java.util.List;

import pojo.CaretakerPojo;


public interface CaretakerDao {
	boolean addCaretaker(CaretakerPojo caretaker);
	CaretakerPojo getCaretakerById(int caretaker);
	CaretakerPojo getCaretakerByUsername(String username);
    List<CaretakerPojo> getAllCaretaker();
    boolean updateCaretaker(CaretakerPojo caretaker);
    boolean deleteCaretaker(int caretakerId);
}