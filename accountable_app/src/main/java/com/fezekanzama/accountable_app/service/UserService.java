package com.fezekanzama.accountable_app.service;

import com.fezekanzama.accountable_app.entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
