package dev.nlu.portal.service;

import dev.nlu.portal.dao.AnnouncementDAOImpl;
import dev.nlu.portal.dao.DAO;
import dev.nlu.portal.model.Announcement;

import java.util.List;

public class AnnouncementServiceImpl implements IService<Announcement> {
    private final DAO<Announcement> dao;

    public AnnouncementServiceImpl() {
        this.dao = new AnnouncementDAOImpl();
    }

    @Override
    public void save(Announcement announcement) {
        dao.save(announcement);
    }

    @Override
    public void update(Announcement announcement) {
        dao.update(announcement);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public Announcement findById(Long id) {
        return dao.findById(id);
    }

    @Override
    public List<Announcement> findAll() {
        return dao.findAll();
    }

    @Override
    public Announcement login(Long id, String password) {
        return null; // Not applicable
    }
}
