package pojo;

public class RequestPojo {
	private int requestId;
    private int userId;
    private int caretakerId;
    private String status; // e.g., "Pending", "Accepted", "Rejected"

    public RequestPojo(int userId, int caretakerId, String status) {
        this.userId = userId;
        this.caretakerId = caretakerId;
        this.status = status;
    }

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(int caretakerId) {
		this.caretakerId = caretakerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequestPojo [requestId=" + requestId + ", userId=" + userId + ", caretakerId=" + caretakerId
				+ ", status=" + status + "]";
	}
    
    
}
