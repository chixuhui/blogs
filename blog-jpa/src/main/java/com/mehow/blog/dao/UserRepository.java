package com.mehow.blog.dao;


import com.mehow.blog.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by limi on 2017/10/15.
 */
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsernameAndPassword(String username, String password);

    @Query("select u from User u")
    List<User> findTop(Pageable pageable);
}
