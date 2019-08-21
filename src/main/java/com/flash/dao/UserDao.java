package com.flash.dao;

import com.flash.enity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM `tb_user` where username=:username")
    public Integer isHave(@Param("username") String username);

    public User findByUsername(String username);
}
