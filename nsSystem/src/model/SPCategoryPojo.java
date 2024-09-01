package model;

public class SPCategoryPojo {
	private int spCategoryId;
    private String category;

    public SPCategoryPojo(int spCategoryId, String category) {
        this.spCategoryId = spCategoryId;
        this.category = category;
    }

	public int getSpCategoryId() {
		return spCategoryId;
	}

	public void setSpCategoryId(int spCategoryId) {
		this.spCategoryId = spCategoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "SPCategoryPojo [spCategoryId=" + spCategoryId + ", category=" + category + "]";
	}
}
