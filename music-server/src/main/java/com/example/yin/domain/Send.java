package com.example.yin.domain;

import java.util.Date;

public class Send {
//    用户id
    private Integer uid;
//    我的聊天内容
    private String mycontent;
//    好友id
    private Integer frid;
//  好友聊天内容
    private String frcontent;
//    消息时间
    private Date cretime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMycontent() {
        return mycontent;
    }

    public void setMycontent(String mycontent) {
        this.mycontent = mycontent;
    }

    public Integer getFrid() {
        return frid;
    }

    public void setFrid(Integer frid) {
        this.frid = frid;
    }

    public String getFrcontent() {
        return frcontent;
    }

    public void setFrcontent(String frcontent) {
        this.frcontent = frcontent;
    }

    public Date getCretime() {
        return cretime;
    }

    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    @Override
    public String toString() {
        return "Send{" +
                "uid=" + uid +
                ", mycontent='" + mycontent + '\'' +
                ", frid=" + frid +
                ", frcontent='" + frcontent + '\'' +
                ", cretime=" + cretime +
                '}';
    }
}
