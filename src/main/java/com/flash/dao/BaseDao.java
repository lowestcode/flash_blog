package com.flash.dao;

import com.flash.enity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseDao extends JpaRepository<Base,String>, JpaSpecificationExecutor<Base> {

    public List<Base> findByUserid(String userid);

}
