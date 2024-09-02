package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SPCategoryPojo;

public class SPCategoryDaoImpl implements SPCategoryDao {

	@Override
	public void addSPCategory(SPCategoryPojo spCategory) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.ADD_SP_CATEGORY)) {

			ps.setInt(1, spCategory.getSpCategoryId());
			ps.setString(2, spCategory.getCategory());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error adding service provider category: " + e.getMessage(), e);
		}
	}

	@Override
	public SPCategoryPojo fetchSPCategoryById(int spCategoryId) throws Exception {
		SPCategoryPojo spCategory = null;
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_SP_CATEGORY_BY_ID)) {

			ps.setInt(1, spCategoryId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					spCategory = mapResultSetToSPCategory(rs);
				}
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching service provider category by ID: " + e.getMessage(), e);
		}
		return spCategory;
	}

	@Override
	public List<SPCategoryPojo> fetchAllSPCategories() throws Exception {
		List<SPCategoryPojo> spCategories = new ArrayList<>();
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.FETCH_ALL_SP_CATEGORIES);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				spCategories.add(mapResultSetToSPCategory(rs));
			}
		} catch (SQLException e) {
			throw new Exception("Error fetching all service provider categories: " + e.getMessage(), e);
		}
		return spCategories;
	}

	@Override
	public void updateSPCategory(SPCategoryPojo spCategory) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.UPDATE_SP_CATEGORY)) {

			ps.setString(1, spCategory.getCategory());
			ps.setInt(2, spCategory.getSpCategoryId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error updating service provider category: " + e.getMessage(), e);
		}
	}

	@Override
	public void deleteSPCategory(int spCategoryId) throws Exception {
		try (Connection connection = DBUtil.makeConnection();
				PreparedStatement ps = connection.prepareStatement(DBQueries.DELETE_SP_CATEGORY)) {

			ps.setInt(1, spCategoryId);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new Exception("Error deleting service provider category: " + e.getMessage(), e);
		}
	}

	private SPCategoryPojo mapResultSetToSPCategory(ResultSet rs) throws SQLException {
		return new SPCategoryPojo(rs.getInt("sp_category_id"), rs.getString("category"));
	}
}
