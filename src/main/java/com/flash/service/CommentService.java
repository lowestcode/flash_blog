package com.flash.service;


import com.flash.dao.CommentDao;
import com.flash.enity.Comment;
import com.flash.util.DateUtil;
import com.flash.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private DateUtil dateUtil;

    /**
     * 查询全部记录
     *
     * @return
     */
    public List<Comment> findAll() {
        return commentDao.findAll();
    }


    /**
     * 增加
     *
     * @param comment
     */
    public void add(Comment comment) {
        comment.set_id(idWorker.nextId() + "");
        comment.setCreateTime(dateUtil.returnDate());
        commentDao.save(comment);
    }


    /**
     * 根据文章id查找
     *
     * @param articleId
     */
    public Page<Comment> findByArticleIdAndStateEquals(String articleId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return commentDao.findByArticleId(articleId,pageRequest);
    }


}
