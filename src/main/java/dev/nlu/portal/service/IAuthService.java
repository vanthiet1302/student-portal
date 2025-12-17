package dev.nlu.portal.service;

public interface IAuthService {
    public boolean authenticate(String username, String password);
}
