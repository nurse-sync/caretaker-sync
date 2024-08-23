package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;

public class CaretakerDaoCollectionImpl implements CaretakerDao {

	private Map<Integer, CaretakerPojo> caretakerData = new HashMap<>(); // HashMap to store caretakers
	private List<CaretakerPojo> caretakerList = new ArrayList<>();

	@Override
	public boolean addCaretaker(CaretakerPojo caretaker) {
		int newId = caretakerData.size() + 1;
		if (caretakerData.containsKey(newId)) {
			return false; // Indicate failure if ID already exists (though unlikely in this case)
		}
		CaretakerPojo newServant = new CaretakerPojo(newId, caretaker.getName(), caretaker.getUserName(),
				caretaker.getPassword(), caretaker.getGender(), caretaker.getCategory(), caretaker.getWeeklyRate(),
				caretaker.getAvailabilityFrom(), caretaker.getAvailabilityTo(), caretaker.getLocation(),
				caretaker.getPhoneNumber(), caretaker.getQualifications(), caretaker.getLiveIn(),
				caretaker.getStatus());
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
			CaretakerPojo updatedCaretaker = new CaretakerPojo(caretaker.getCaretakerId(), caretaker.getName(),
					caretaker.getUserName(), caretaker.getPassword(), caretaker.getGender(), caretaker.getCategory(),
					caretaker.getWeeklyRate(), caretaker.getAvailabilityFrom(), caretaker.getAvailabilityTo(),
					caretaker.getLocation(), caretaker.getPhoneNumber(), caretaker.getQualifications(),
					caretaker.getLiveIn(), caretaker.getStatus());
			caretakerData.put(caretaker.getCaretakerId(), updatedCaretaker);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteCaretaker(int caretakerId) {
		if (caretakerData.containsKey(caretakerId)) {
			caretakerData.remove(caretakerId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public CaretakerPojo getCaretakerByUsername(String userName) {
		return caretakerData.values().stream().filter(caretaker -> caretaker.getUserName().equals(userName)).findFirst()
				.orElse(null);
	}

	@Override
	public boolean updateCaretakerStatus(int caretakerId, String status) {
		CaretakerPojo caretaker = caretakerData.get(caretakerId);

		if (caretaker != null) {
			caretaker.setStatus(status);
			return true;
		}
		return false;
	}

	@Override
	public List<CaretakerPojo> findCaretakersByPreferences(CaretakerPreferences preferences) {
		return caretakerData.values().stream().filter(caretaker -> {
			boolean matches = true;

			if (preferences.getCategory() != null && !preferences.getCategory().equals(caretaker.getCategory())) {
				matches = false;
			}

			if (preferences.getGenderPreference() != null
					&& !preferences.getGenderPreference().equals(caretaker.getGender())) {
				matches = false;
			}

			if (preferences.getMaxWeeklyRate() > 0 && preferences.getMaxWeeklyRate() < caretaker.getWeeklyRate()) {
				matches = false;
			}

			if (preferences.getRequiredFrom() != null && preferences.getRequiredTo() != null) {
				Date availabilityFrom = caretaker.getAvailabilityFrom();
				Date availabilityTo = caretaker.getAvailabilityTo();

				if (availabilityFrom.compareTo(preferences.getRequiredTo()) > 0
						|| availabilityTo.compareTo(preferences.getRequiredFrom()) < 0) {
					matches = false;
				}
			}

			if (preferences.isLiveIn() != caretaker.getLiveIn()) {
				matches = false;
			}

			return matches;
		}).collect(Collectors.toList());
	}

}
