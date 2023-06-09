package com.example.yin.domain;

public class Friends {
//    用户id
    private Integer uid;
//    好友id
    private Integer prid;
// 好友头像
    private String pyurl;
// 好友名字
    private String pyname;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPrid() {
        return prid;
    }

    public void setPrid(Integer prid) {
        this.prid = prid;
    }

    public Friends() {
    }

    public String getPyurl() {
        return pyurl;
    }

    public void setPyurl(String pyurl) {
        this.pyurl = pyurl;
    }

    public String getPyname() {
        return pyname;
    }

    public void setPyname(String pyname) {
        this.pyname = pyname;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "uid=" + uid +
                ", prid=" + prid +
                '}';
    }
}
