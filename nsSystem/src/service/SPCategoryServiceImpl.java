package service;

import java.util.List;

import dao.SPCategoryDao;
import dao.SPCategoryDaoImpl;
import model.SPCategoryPojo;

public class SPCategoryServiceImpl implements SPCategoryService {
	private SPCategoryDao spCategoryDao;

	public SPCategoryServiceImpl() {
		this.spCategoryDao = new SPCategoryDaoImpl(); // Initialize the DAO implementation
	}

	@Override
	public void addSPCategory(SPCategoryPojo spCategory) throws Exception {
		spCategoryDao.addSPCategory(spCategory);

	}

	@Override
	public SPCategoryPojo fetchSPCategoryById(int spCategoryId) throws Exception {
		// TODO Auto-generated method stub
		return spCategoryDao.fetchSPCategoryById(spCategoryId);
	}

	@Override
	public List<SPCategoryPojo> fetchAllSPCategories() throws Exception {
		// TODO Auto-generated method stub
		return spCategoryDao.fetchAllSPCategories();
	}

	@Override
	public void updateSPCategory(SPCategoryPojo spCategory) throws Exception {
		spCategoryDao.updateSPCategory(spCategory);

	}

	@Override
	public void deleteSPCategory(int spCategoryId) throws Exception {
		spCategoryDao.deleteSPCategory(spCategoryId);

	}

}
