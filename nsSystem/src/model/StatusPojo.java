package model;

public class StatusPojo {
	private int statusId;
    private String statusName;

    public StatusPojo(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "StatusPojo [statusId=" + statusId + ", statusName=" + statusName + "]";
	}
}
