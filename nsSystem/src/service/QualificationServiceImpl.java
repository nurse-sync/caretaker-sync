package service;

import java.util.List;

import dao.QualificationDao;
import dao.QualificationDaoImpl;
import model.QualificationPojo;

public class QualificationServiceImpl implements QualificationService {

	private QualificationDao qualificationDao;

	public QualificationServiceImpl() {
		this.qualificationDao = new QualificationDaoImpl();
	}

	@Override
	public QualificationPojo getQualificationById(int spQualificationId) {
		return qualificationDao.getQualificationById(spQualificationId);
	}

	@Override
	public boolean addQualification(QualificationPojo qualification) {
		return qualificationDao.addQualification(qualification);
	}

	@Override
	public boolean updateQualification(QualificationPojo qualification) {
		return qualificationDao.updateQualification(qualification);
	}

	@Override
	public boolean deleteQualification(int spQualificationId) {
		return qualificationDao.deleteQualification(spQualificationId);
	}

	@Override
	public List<QualificationPojo> getAllQualifications() {
		return qualificationDao.getAllQualifications();
	}

}
