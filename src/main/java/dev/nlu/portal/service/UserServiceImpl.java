package dev.nlu.portal.service;

import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.dao.UserDao;
import dev.nlu.portal.model.User;
import dev.nlu.portal.utils.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements IService<User> {
    DAO<User> dao;

    public UserServiceImpl() {
        this.dao = new UserDao();
    }

    @Override
    public void save(User user) {
        dao.save(user);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(Long id, String password) {
        User user = dao.findById(id);
        if (user != null) {
            if (PasswordUtil.checkPassword(password, user.getPasswordHash())) {
                return user;
            }
        }
        return null;
    }

    public User findByUsername(String username) {
        List<User> users = dao.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
