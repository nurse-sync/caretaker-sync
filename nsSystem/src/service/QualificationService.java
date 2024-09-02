package service;

import java.util.List;

import model.QualificationPojo;

public interface QualificationService {
	QualificationPojo getQualificationById(int spQualificationId);

	boolean addQualification(QualificationPojo qualification);

	boolean updateQualification(QualificationPojo qualification);

	boolean deleteQualification(int spQualificationId);

	List<QualificationPojo> getAllQualifications();
}
