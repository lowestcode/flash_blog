package com.flash.service;

import com.flash.dao.BaseDao;
import com.flash.enity.Base;
import com.flash.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BaseService {

    @Autowired
    private BaseDao baseDao;
    @Autowired
    private IdWorker idWorker;

    public List<Base> findByUserid(String userid){
        return baseDao.findByUserid(userid);
    }

    public void save(Base base) {
        base.setId(idWorker.nextId()+"");
        baseDao.save(base);
    }
}
