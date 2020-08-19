package com.mehow.blog.service;

import com.mehow.blog.entity.Type;
import com.mehow.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: xuhui
 * @Date: 2020/8/4 13:32
 */
public interface UserService {
    User checkUser(String username, String password);
    Page<User> listUser(Pageable pageable);
    User saveUser(User user);
    User getUser(Long id);
    User getUserByName(String name);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> listTypeTop(Integer size);
}
