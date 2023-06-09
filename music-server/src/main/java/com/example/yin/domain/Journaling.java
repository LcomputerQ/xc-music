package com.example.yin.domain;

import java.util.List;

//访问量
public class Journaling {

    private int id;
    private int online ;   // 今日访问量
    private int accumulate ;  //积累访问量
    public static List visitList = null; //一周访问量


    public Journaling(int id, int online, int accumulate, List visitList) {
        this.id = id;
        this.online = online;
        this.accumulate = accumulate;
        this.visitList = visitList;
    }

    public Journaling() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getAccumulate() {
        return accumulate;
    }

    public void setAccumulate(int accumulate) {
        this.accumulate = accumulate;
    }

    public List getVisitList() {
        return visitList;
    }

    public void setVisitList(List visitList) {
        this.visitList = visitList;
    }

    @Override
    public String toString() {
        return "Journaling{" +
                "id=" + id +
                ", online=" + online +
                ", accumulate=" + accumulate +
                ", visitList=" + visitList +
                '}';
    }
}
