package com.flash.service;

import com.flash.dao.ArticleDao;
import com.flash.enity.Article;
import com.flash.util.DateUtil;
import com.flash.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private DateUtil dateUtil;

    /**
     * 保存文章
     *
     * @param article
     */
    public void saveArticle(Article article) {
        article.setId(idWorker.nextId() + "");
        article.setTime(dateUtil.returnDate());
        articleDao.save(article);
    }

    /**
     * 返回文章详情
     * @param articleId
     * @return
     */
    public Article returnArticleDetails(String articleId) {
        return articleDao.findById(articleId).get();
    }

    /**
     * 返回文章列表
     * @param baseid
     * @param userid
     * @param page
     * @param size
     * @return
     */
    public Page<Article> pageQuery(String baseid, String userid, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.findByBaseid(baseid, userid, pageable);
    }

    /**
     * 删除文章
     * @param articleId
     */
    public void deleteArticle(String articleId){
        articleDao.deleteById(articleId);
    }

    /**
     *  更新文章
     */
    public void updateArticle(Article article){
        article.setTime(dateUtil.returnDate());
        articleDao.save(article);
    }
}
