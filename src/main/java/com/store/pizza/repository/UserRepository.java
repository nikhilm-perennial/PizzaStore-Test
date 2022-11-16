package com.store.pizza.repository;

import com.store.pizza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findUserByUserName(String username);

}
