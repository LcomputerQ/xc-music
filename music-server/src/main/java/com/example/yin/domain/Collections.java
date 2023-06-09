package com.example.yin.domain;
//用户收藏
public class Collections {
    private Integer uId;   //用户id
    private Integer songId; //歌曲id

    private Integer type;  //  1 收藏
    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Collections() {
    }
    public Collections(Integer uId, Integer songId, Integer type) {
        this.uId = uId;
        this.songId = songId;
        this.type = type;
    }
    @Override
    public String toString() {
        return "Collection{" +
                "uId=" + uId +
                ", songId=" + songId +
                '}';
    }
}
