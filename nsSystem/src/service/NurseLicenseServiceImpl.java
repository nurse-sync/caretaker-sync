package service;

import java.util.List;

import dao.NurseLicenseDao;
import dao.NurseLicenseDaoImpl;
import model.NurseLicensePojo;

public class NurseLicenseServiceImpl implements NurseLicenseService {
	private NurseLicenseDao nurseLicenseDao;

	public NurseLicenseServiceImpl() {
		this.nurseLicenseDao = new NurseLicenseDaoImpl(); 
	}

	@Override
	public NurseLicensePojo getNurseLicenseById(int nurseLicenseId) {
        return nurseLicenseDao.getNurseLicenseById(nurseLicenseId);
	}

	@Override
	public boolean addNurseLicense(NurseLicensePojo nurseLicense) {
        return nurseLicenseDao.addNurseLicense(nurseLicense);
	}

	@Override
	public boolean updateNurseLicense(NurseLicensePojo nurseLicense) {
        return nurseLicenseDao.updateNurseLicense(nurseLicense);
	}

	@Override
	public boolean deleteNurseLicense(int nurseLicenseId) {
        return nurseLicenseDao.deleteNurseLicense(nurseLicenseId);
	}

	@Override
	public List<NurseLicensePojo> getAllNurseLicenses() {
        return nurseLicenseDao.getAllNurseLicenses();
	}

}
