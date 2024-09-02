package service;

import java.util.List;

import dao.StatusDao;
import dao.StatusDaoImpl;
import model.StatusPojo;

public class StatusServiceImpl implements StatusService {
	private StatusDao statusDao;

	public StatusServiceImpl() {
		this.statusDao = new StatusDaoImpl();
	}

	@Override
	public void addStatus(StatusPojo status) throws Exception {
		statusDao.addStatus(status);
	}

	@Override
	public StatusPojo fetchStatusById(int statusId) throws Exception {
		return statusDao.fetchStatusById(statusId);
	}

	@Override
	public List<StatusPojo> fetchAllStatuses() throws Exception {
		return statusDao.fetchAllStatuses();
	}

	@Override
	public void updateStatus(StatusPojo status) throws Exception {
		statusDao.updateStatus(status);
	}

	@Override
	public void deleteStatus(int statusId) throws Exception {
		statusDao.deleteStatus(statusId);
	}
}
