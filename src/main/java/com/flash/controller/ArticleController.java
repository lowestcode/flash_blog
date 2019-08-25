package com.flash.controller;

import com.flash.enity.Article;
import com.flash.enity.PageResult;
import com.flash.enity.Result;
import com.flash.enity.StatusCode;
import com.flash.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 保存
     * @param article
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result saveArticle(@RequestBody Article article){
        articleService.saveArticle(article);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    /**
     * 返回文章列表
     * @param baseid
     */
    @RequestMapping(value = "/search/{baseid}/{userid}/{page}/{size}",method = RequestMethod.GET)
    public Result returnArticleList(@PathVariable("baseid") String baseid,@PathVariable("userid") String userid,@PathVariable("page") int page,@PathVariable("size") int size){
        Page<Article> pageData = articleService.pageQuery(baseid,userid,page,size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Article>(pageData.getTotalElements(),pageData.getContent()));
    }

    /***
     * 返回文章详情
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/{articleId}",method = RequestMethod.GET)
    public Result returnArticleDetails(@PathVariable("articleId") String articleId){
        return new Result(true, StatusCode.OK,"保存成功",articleService.returnArticleDetails(articleId));
    }

    /***
     * 删除文章
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/{articleId}",method = RequestMethod.DELETE)
    public Result deleteArticle(@PathVariable("articleId") String articleId){
        articleService.deleteArticle(articleId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    /**
     * 更新文章
     * @return
     */
    @RequestMapping(value = "/{articleId}",method = RequestMethod.PUT)
    public Result updateArticle(@PathVariable("articleId") String articleId,@RequestBody Article article){
        article.setId(articleId);
        articleService.updateArticle(article);
        return new Result(true, StatusCode.OK,"更新文章成功");

    }

}
