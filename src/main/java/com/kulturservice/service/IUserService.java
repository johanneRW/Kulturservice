package com.kulturservice.service;

import com.kulturservice.model.User;

import java.util.List;

public interface IUserService extends ICrudService<User, Long> {
    public List<User> findUserByName(String string);
}
