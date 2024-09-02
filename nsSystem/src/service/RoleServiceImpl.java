package service;

import java.util.List;

import dao.RoleDao;
import dao.RoleDaoImpl;
import model.RolePojo;

public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;

	public RoleServiceImpl() {
		this.roleDao = new RoleDaoImpl();
	}

	@Override
	public int addRole(RolePojo rolePojo) {
		return roleDao.addRole(rolePojo);
	}

	@Override
	public int updateRole(RolePojo rolePojo) {
		return roleDao.updateRole(rolePojo);
	}

	@Override
	public int deleteRole(int roleId) {
		return roleDao.deleteRole(roleId);
	}

	@Override
	public RolePojo getRoleById(int roleId) {
		return roleDao.getRoleById(roleId);
	}

	@Override
	public List<RolePojo> getAllRoles() {
		return roleDao.getAllRoles();
	}

}
