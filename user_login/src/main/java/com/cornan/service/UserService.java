package com.cornan.service;

import com.cornan.entity.User;

public interface UserService {
    void register(User user);

    User login(User user);
}
