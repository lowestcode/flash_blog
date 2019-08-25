package com.flash.controller;


import com.flash.enity.Comment;
import com.flash.enity.PageResult;
import com.flash.enity.Result;
import com.flash.enity.StatusCode;
import com.flash.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", commentService.findAll());
    }


    /**
     * 增加
     *
     * @param comment
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "增加成功");
    }


    /**
     * 通过文章id查找评论
     */
    @RequestMapping(value = "/{articleId}/{page}/{size}", method = RequestMethod.GET)
    public Result findyArticleId(@PathVariable String articleId, @PathVariable("page") int page, @PathVariable("size") int size) {
        Page<Comment> comments = commentService.findByArticleIdAndStateEquals(articleId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Comment>(comments.getTotalElements(),comments.getContent()));
    }

}
