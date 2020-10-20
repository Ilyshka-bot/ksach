package com.psu.service;

import org.springframework.stereotype.Service;

@Service

public interface SecurityServices {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
