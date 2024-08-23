package dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exceptions.GlobalExceptionHandler;
import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;

public class CaretakerDaoCollectionImpl implements CaretakerDao {

	private Map<Integer, CaretakerPojo> caretakerData = new HashMap<>(); // HashMap to store caretakers
	private List<CaretakerPojo> caretakerList = new ArrayList<>();

	@Override
	public boolean addCaretaker(CaretakerPojo caretaker) {
		try {
			if (caretaker == null || caretaker.getUserName() == null || caretaker.getPassword() == null) {
				throw new IllegalArgumentException("Invalid caretaker details provided.");
			}
			int newId = caretakerData.size() + 1;
			if (caretakerData.containsKey(newId)) {
				return false; // Indicate failure if ID already exists (though unlikely in this case)
			}
			CaretakerPojo newCaretaker = new CaretakerPojo(newId, caretaker.getName(), caretaker.getUserName(),
					caretaker.getPassword(), caretaker.getGender(), caretaker.getCategory(), caretaker.getWeeklyRate(),
					caretaker.getAvailabilityFrom(), caretaker.getAvailabilityTo(), caretaker.getLocation(),
					caretaker.getPhoneNumber(), caretaker.getQualifications(), caretaker.getLiveIn(),
					caretaker.getStatus());
			caretakerData.put(newId, newCaretaker);
			return true; // Indicate success
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput(); // Handle invalid caretaker details
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle other unexpected exceptions
			return false;
		}
	}

	@Override
	public CaretakerPojo getCaretakerById(int caretakerId) {
		try {
			CaretakerPojo caretaker = caretakerData.get(caretakerId);
			if (caretaker == null) {
				throw new IllegalArgumentException("Caretaker with ID " + caretakerId + " not found.");
			}
			return caretaker;
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput(); // Handle caretaker not found scenario
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle other unexpected exceptions
			return null;
		}
	}

	@Override
	public List<CaretakerPojo> getAllCaretaker() {
		try {
			return new ArrayList<>(caretakerData.values());
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle any errors while fetching caretakers
			return new ArrayList<>();
		}
	}

	@Override
	public boolean updateCaretaker(CaretakerPojo caretaker) {
		try {
			if (caretaker == null || !caretakerData.containsKey(caretaker.getCaretakerId())) {
				throw new IllegalArgumentException(
						"Caretaker with ID " + caretaker.getCaretakerId() + " does not exist.");
			}
			CaretakerPojo updatedCaretaker = new CaretakerPojo(caretaker.getCaretakerId(), caretaker.getName(),
					caretaker.getUserName(), caretaker.getPassword(), caretaker.getGender(), caretaker.getCategory(),
					caretaker.getWeeklyRate(), caretaker.getAvailabilityFrom(), caretaker.getAvailabilityTo(),
					caretaker.getLocation(), caretaker.getPhoneNumber(), caretaker.getQualifications(),
					caretaker.getLiveIn(), caretaker.getStatus());
			caretakerData.put(caretaker.getCaretakerId(), updatedCaretaker);
			return true;
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput(); // Handle invalid caretaker details or non-existent ID
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle other unexpected exceptions
			return false;
		}
	}

	@Override
	public boolean deleteCaretaker(int caretakerId) {
		try {
			if (caretakerData.containsKey(caretakerId)) {
				caretakerData.remove(caretakerId);
				return true;
			} else {
				throw new IllegalArgumentException("Caretaker with ID " + caretakerId + " does not exist.");
			}
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput(); // Handle non-existent caretaker ID
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle other unexpected exceptions
			return false;
		}
	}

	@Override
	public CaretakerPojo getCaretakerByUsername(String userName) {
		try {
			return caretakerData.values().stream().filter(caretaker -> caretaker.getUserName().equals(userName))
					.findFirst().orElse(null);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle any errors while searching for caretaker by username
			return null;
		}
	}

	@Override
	public boolean updateCaretakerStatus(int caretakerId, String status) {
		try {
			CaretakerPojo caretaker = caretakerData.get(caretakerId);
			if (caretaker != null) {
				caretaker.setStatus(status);
				return true;
			}
			throw new IllegalArgumentException("Caretaker with ID " + caretakerId + " not found.");
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput(); // Handle non-existent caretaker ID
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle other unexpected exceptions
			return false;
		}
	}

	@Override
	public List<CaretakerPojo> findCaretakersByPreferences(CaretakerPreferences preferences) {
		try {
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
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle errors while finding caretakers by preferences
			return new ArrayList<>();
		}

	}

}
