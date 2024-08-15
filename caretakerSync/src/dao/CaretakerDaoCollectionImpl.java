package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.CaretakerPojo;

public class CaretakerDaoCollectionImpl implements CaretakerDao{

    private Map<Integer, CaretakerPojo> caretakerData = new HashMap<>(); // HashMap to store caretakers

	@Override
	public boolean addCaretaker(CaretakerPojo caretaker) {
		 int newId = caretakerData.size() + 1;
	        if (caretakerData.containsKey(newId)) {
	            return false; // Indicate failure if ID already exists (though unlikely in this case)
	        }
	        CaretakerPojo newServant = new CaretakerPojo(newId, caretaker.getName(), caretaker.getPassword(), caretaker.getGender(), caretaker.getCategory(), caretaker.getWeeklyRate(), caretaker.getAvailabilityFrom(), caretaker.getAvailabilityTo(), caretaker.getLocation(), caretaker.getPhoneNumber(), caretaker.getQualifications(), caretaker.getIsLiveIn());
	        caretakerData.put(newId, newServant);
	        return true; // Indicate success
	}

	@Override
	public CaretakerPojo getCaretakerById(int caretakerId) {
		return caretakerData.get(caretakerId);
	}

	@Override
	public List<CaretakerPojo> getAllCaretaker() {
        return new ArrayList<>(caretakerData.values());
	}

	@Override
	public boolean updateCaretaker(CaretakerPojo caretaker) {
		 if (caretakerData.containsKey(caretaker.getCaretakerId())) {
			 CaretakerPojo updatedCaretaker = new CaretakerPojo(caretaker.getCaretakerId(), caretaker.getName(), caretaker.getPassword(), caretaker.getGender(), caretaker.getCategory(), caretaker.getWeeklyRate(), caretaker.getAvailabilityFrom(), caretaker.getAvailabilityTo(), caretaker.getLocation(), caretaker.getPhoneNumber(), caretaker.getQualifications(), caretaker.getIsLiveIn());
	            caretakerData.put(caretaker.getCaretakerId(), updatedCaretaker);
	            return true; // Indicate success
	        } else {
	            return false; // Indicate failure if servant ID not found
	        }
	}

	@Override
	public boolean deleteCaretaker(int caretakerId) {
		 if (caretakerData.containsKey(caretakerId)) {
			 caretakerData.remove(caretakerId);
	            return true; // Indicate success
	        } else {
	            return false; // Indicate failure if servant ID not found
	        }
	}

	@Override
	public CaretakerPojo getCaretakerByUsername(String username) {
		return caretakerData.values().stream()
                .filter(caretaker -> caretaker.getName().equals(username))
                .findFirst()
                .orElse(null);
	}

}
