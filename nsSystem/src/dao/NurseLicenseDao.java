package dao;

import java.util.List;

import model.NurseLicensePojo;

public interface NurseLicenseDao {
	NurseLicensePojo getNurseLicenseById(int nurseLicenseId);

	boolean addNurseLicense(NurseLicensePojo nurseLicense);

	boolean updateNurseLicense(NurseLicensePojo nurseLicense);

	boolean deleteNurseLicense(int nurseLicenseId);

	List<NurseLicensePojo> getAllNurseLicenses();
}
