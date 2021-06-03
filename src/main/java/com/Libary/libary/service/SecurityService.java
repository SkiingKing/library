package com.Libary.libary.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
