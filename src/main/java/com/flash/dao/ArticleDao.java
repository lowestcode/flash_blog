package com.flash.dao;

import com.flash.enity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

    @Query(value = "SELECT * FROM `tb_article` WHERE baseid=:baseid and userid=:userid",
            countQuery = "SELECT count(*) FROM `tb_article` WHERE baseid = :baseid",
            nativeQuery = true)
    Page<Article> findByBaseid(@Param("baseid") String baseid, @Param("userid") String userid, Pageable pageable);
}
