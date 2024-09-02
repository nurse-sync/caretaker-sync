package dao;

import java.util.List;

import model.StatusPojo;

public interface StatusDao {
	void addStatus(StatusPojo status) throws Exception;

	StatusPojo fetchStatusById(int statusId) throws Exception;

	List<StatusPojo> fetchAllStatuses() throws Exception;

	void updateStatus(StatusPojo status) throws Exception;

	void deleteStatus(int statusId) throws Exception;
}