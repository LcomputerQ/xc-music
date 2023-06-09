package com.example.yin.domain;
//好友审核
public class Manaudit {
//    发送者id
    private Integer myid;
//    接收者id
    private Integer pyid;
//    审核状态  1.审核 2.审核成功
    private Integer type;
//    判断是谁发送
    private String flag;

    public Manaudit() {
    }

    public Integer getMyid() {
        return myid;
    }

    public void setMyid(Integer myid) {
        this.myid = myid;
    }

    public Integer getPyid() {
        return pyid;
    }

    public void setPyid(Integer pyid) {
        this.pyid = pyid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Manaudit{" +
                "myid=" + myid +
                ", pyid=" + pyid +
                ", type=" + type +
                ", flag='" + flag + '\'' +
                '}';
    }
}
