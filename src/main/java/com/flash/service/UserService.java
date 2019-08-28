package com.flash.service;

import com.flash.dao.UserDao;
import com.flash.enity.PageResult;
import com.flash.enity.User;
import com.flash.util.DateUtil;
import com.flash.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private DateUtil dateUtil;

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(String id) {
        return userDao.findById(id).get();
    }

    public void save(User user) {
        user.setId(idWorker.nextId() + "");
        user.setTime(dateUtil.returnDate());
        userDao.save(user);
    }

    public void update(User user) {
        userDao.save(user);
    }

    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    public Integer isHave(String username) {
        return userDao.isHave(username);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Page<User> findAllUser(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return userDao.findAll(pageable);
    }
}
