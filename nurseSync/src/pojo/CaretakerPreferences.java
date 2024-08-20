package pojo;

//import java.time.LocalDateTime;
import java.time.LocalDate;


public class CaretakerPreferences {
	private String category; // e.g., Nurse, Caregiver, Dual-Role Nurse-Caretaker
    private String genderPreference; // e.g., Male, Female, Any
    private double maxWeeklyRate; // Maximum weekly rate the user is willing to pay
    private LocalDate requiredFrom;
    private LocalDate requiredTo;
    private String serviceLocation; // Location where the service is needed
    private boolean liveIn;
    private String forWhom; // e.g., Self, Mother, Father, Grandfather, Grandmother, wife, husband, sister, bother, friend.

    
    public CaretakerPreferences(String category, String genderPreference, double maxHourlyRate, 
            LocalDate requiredFrom, LocalDate requiredTo, String serviceLocation, 
            boolean liveIn, String forWhom) {
		this.category = category;
		this.genderPreference = genderPreference;
		this.maxWeeklyRate = maxHourlyRate;
		this.requiredFrom = requiredFrom;
		this.requiredTo = requiredTo;
		this.serviceLocation = serviceLocation;
		this.liveIn = liveIn;
		this.forWhom = forWhom;
    }


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getGenderPreference() {
		return genderPreference;
	}


	public void setGenderPreference(String genderPreference) {
		this.genderPreference = genderPreference;
	}


	public double getMaxWeeklyRate() {
		return maxWeeklyRate;
	}


	public void setMaxWeeklyRate(double maxWeeklyRate) {
		this.maxWeeklyRate = maxWeeklyRate;
	}


	public LocalDate getRequiredFrom() {
		return requiredFrom;
	}


	public void setRequiredFrom(LocalDate requiredFrom) {
		this.requiredFrom = requiredFrom;
	}


	public LocalDate getRequiredTo() {
		return requiredTo;
	}


	public void setRequiredTo(LocalDate requiredTo) {
		this.requiredTo = requiredTo;
	}


	public String getServiceLocation() {
		return serviceLocation;
	}


	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}


	public boolean isLiveIn() {
		return liveIn;
	}


	public void setLiveIn(boolean liveIn) {
		this.liveIn = liveIn;
	}


	public String getForWhom() {
		return forWhom;
	}


	public void setForWhom(String forWhom) {
		this.forWhom = forWhom;
	}


	@Override
	public String toString() {
		return "CaretakerPreferences [category=" + category + ", genderPreference=" + genderPreference
				+ ", maxWeeklyRate=" + maxWeeklyRate + ", requiredFrom=" + requiredFrom + ", requiredTo=" + requiredTo
				+ ", serviceLocation=" + serviceLocation + ", liveIn=" + liveIn + ", forWhom=" + forWhom + "]";
	}
	
}
