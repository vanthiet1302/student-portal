package dev.nlu.portal.service;

import dev.nlu.portal.dao.AdminDAOImpl;
import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.model.Admin;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.PasswordUtil;

import java.util.List;

public class AdminServiceImpl implements IService<Admin> {
    private final DAO<Admin> dao;

    public AdminServiceImpl() {
        this.dao = new AdminDAOImpl();
    }

    @Override
    public void save(Admin admin) {
        dao.save(admin);
    }

    @Override
    public void update(Admin admin) {
        dao.update(admin);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Admin findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Admin> findAll() {
        return dao.findAll();
    }

    @Override
    public Admin login(Long id, String password) {
        Admin admin = dao.findById(id);
        if (admin == null) return null;
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.findById(admin.getUserId());
        if (user != null && PasswordUtil.checkPassword(password, user.getPasswordHash())) {
            return admin;
        }
        return null;
    }
}
