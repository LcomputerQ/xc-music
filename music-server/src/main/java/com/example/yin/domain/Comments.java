package com.example.yin.domain;

import java.util.Date;

//评论
public class Comments {
    private Integer commentId;
    private Integer uid;
    private String songId;
    private Date createtime;
    private Integer like;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commentIid=" + commentId +
                ", uid=" + uid +
                ", songId=" + songId +
                ", createtime=" + createtime +
                ", like=" + like +
                '}';
    }

    public Comments() {
    }
}

