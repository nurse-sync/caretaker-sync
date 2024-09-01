package model;

public class NurseLicensePojo {
	private int nurseLicenseId;
    private int spId;
    private String nurseLicenseUrl;

    public NurseLicensePojo(int nurseLicenseId, int spId, String nurseLicenseUrl) {
        this.nurseLicenseId = nurseLicenseId;
        this.spId = spId;
        this.nurseLicenseUrl = nurseLicenseUrl;
    }

	public int getNurseLicenseId() {
		return nurseLicenseId;
	}

	public void setNurseLicenseId(int nurseLicenseId) {
		this.nurseLicenseId = nurseLicenseId;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getNurseLicenseUrl() {
		return nurseLicenseUrl;
	}

	public void setNurseLicenseUrl(String nurseLicenseUrl) {
		this.nurseLicenseUrl = nurseLicenseUrl;
	}

	@Override
	public String toString() {
		return "NurseLicensePojo [nurseLicenseId=" + nurseLicenseId + ", spId=" + spId + ", nurseLicenseUrl="
				+ nurseLicenseUrl + "]";
	}
}
