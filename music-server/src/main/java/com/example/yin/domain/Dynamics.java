package com.example.yin.domain;

import java.util.Date;

    //动态
public class Dynamics {
    private String username;
    // 动态id
    private Integer id;
    //用户id
    private Integer uid;
    //用户头像
    private String uimg;
    //创建时间
    private Date creatTime;
    //内容
    private String content;
    //动态发表图片类型还是视频类型
    private String type;
    //点赞数量
    private Integer like;
    //收藏数量
    private Integer collect;
    //评论条数
    private Integer comment;
    //话题id
    private Integer topicid;
//    内容图片路径
    private String contentUrl;
    public Dynamics() {
    }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public String getUimg() {
            return uimg;
        }

        public void setUimg(String uimg) {
            this.uimg = uimg;
        }

        public Date getCreatTime() {
            return creatTime;
        }

        public void setCreatTime(Date creatTime) {
            this.creatTime = creatTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getLike() {
            return like;
        }

        public void setLike(Integer like) {
            this.like = like;
        }

        public Integer getCollect() {
            return collect;
        }

        public void setCollect(Integer collect) {
            this.collect = collect;
        }

        public Integer getComment() {
            return comment;
        }

        public void setComment(Integer comment) {
            this.comment = comment;
        }

        public Integer getTopicid() {
            return topicid;
        }

        public void setTopicid(Integer topicid) {
            this.topicid = topicid;
        }

        public String getContentUrl() {
            return contentUrl;
        }

        public void setContentUrl(String contentUrl) {
            this.contentUrl = contentUrl;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "Dynamics{" +
                    "id=" + id +
                    ", uid=" + uid +
                    ", uimg='" + uimg + '\'' +
                    ", creatTime=" + creatTime +
                    ", content='" + content + '\'' +
                    ", type='" + type + '\'' +
                    ", like=" + like +
                    ", collect=" + collect +
                    ", comment=" + comment +
                    ", topicid=" + topicid +
                    ", contentUrl='" + contentUrl + '\'' +
                    '}';
        }
    }
