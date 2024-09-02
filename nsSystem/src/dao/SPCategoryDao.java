package dao;

import java.util.List;

import model.SPCategoryPojo;

public interface SPCategoryDao {
	void addSPCategory(SPCategoryPojo spCategory) throws Exception;

	SPCategoryPojo fetchSPCategoryById(int spCategoryId) throws Exception;

	List<SPCategoryPojo> fetchAllSPCategories() throws Exception;

	void updateSPCategory(SPCategoryPojo spCategory) throws Exception;

	void deleteSPCategory(int spCategoryId) throws Exception;
}
