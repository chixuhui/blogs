package com.mehow.blog.service.impl;

import com.mehow.blog.NotFoundException;
import com.mehow.blog.dao.UserRepository;
import com.mehow.blog.entity.Type;
import com.mehow.blog.entity.User;
import com.mehow.blog.service.UserService;
import com.mehow.blog.utils.MD5Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: xuhui
 * @Date: 2020/8/4 13:34
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User checkUser(String username, String password) {
        User user= userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

    @Override
    public Page<User> listUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }
    @Override
    public List<User> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0,size,sort);
        return userRepository.findTop(pageable);
    }
    @Override
    public User updateUser(Long id, User user) {
        User t = userRepository.findById(id).orElse(null);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(user,t);
        return userRepository.save(t);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
