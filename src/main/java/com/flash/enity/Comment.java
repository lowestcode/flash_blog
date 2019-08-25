package com.flash.enity;


import javax.persistence.Id;

public class Comment {

    @Id
    private String _id;
    private String articleId;
    private String articleName;
    private String content;
    private String createTime;
    private String nickname;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "_id='" + _id + '\'' +
                ", articleId='" + articleId + '\'' +
                ", articleName='" + articleName + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
