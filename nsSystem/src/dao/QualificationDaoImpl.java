package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.QualificationPojo;

public class QualificationDaoImpl implements QualificationDao {

	@Override
	public QualificationPojo getQualificationById(int spQualificationId) {
		QualificationPojo qualification = null;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_QUALIFICATION_BY_ID)) {
			stmt.setInt(1, spQualificationId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				qualification = new QualificationPojo(rs.getInt("sp_qualification_id"), rs.getInt("sp_id"),
						rs.getInt("experience_in_years"), rs.getString("role_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return qualification;
	}

	@Override
	public boolean addQualification(QualificationPojo qualification) {
		boolean isAdded = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.ADD_QUALIFICATION)) {
			stmt.setInt(1, qualification.getSpId());
			stmt.setInt(2, qualification.getExperienceInYears());
			stmt.setString(3, qualification.getRoleName());
			int rowsAffected = stmt.executeUpdate();
			isAdded = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return isAdded;
	}

	@Override
	public boolean updateQualification(QualificationPojo qualification) {
		boolean isUpdated = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.UPDATE_QUALIFICATION)) {
			stmt.setInt(1, qualification.getSpId());
			stmt.setInt(2, qualification.getExperienceInYears());
			stmt.setString(3, qualification.getRoleName());
			stmt.setInt(4, qualification.getSpQualificationId());
			int rowsAffected = stmt.executeUpdate();
			isUpdated = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return isUpdated;
	}

	@Override
	public boolean deleteQualification(int spQualificationId) {
		boolean isDeleted = false;
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.DELETE_QUALIFICATION)) {
			stmt.setInt(1, spQualificationId);
			int rowsAffected = stmt.executeUpdate();
			isDeleted = rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return isDeleted;
	}

	@Override
	public List<QualificationPojo> getAllQualifications() {
		List<QualificationPojo> qualifications = new ArrayList<>();
		try (Connection conn = DBUtil.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(DBQueries.FETCH_ALL_QUALIFICATIONS);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				QualificationPojo qualification = new QualificationPojo(rs.getInt("sp_qualification_id"),
						rs.getInt("sp_id"), rs.getInt("experience_in_years"), rs.getString("role_name"));
				qualifications.add(qualification);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection();
		}
		return qualifications;
	}

}
