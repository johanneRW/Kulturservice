package com.kulturservice.Repository;

import com.kulturservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findUserByName(String string);

}
