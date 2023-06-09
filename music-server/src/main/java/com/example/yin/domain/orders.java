package com.example.yin.domain;
//订单
public class orders {
    private Integer id;
    private  Integer order_id;
    private  Integer user_id;
    private  Integer product_id;
    private  Integer product_num;
    private  Integer product_price;
    private  Integer order_time;

    public orders() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getProduct_num() {
        return product_num;
    }

    public void setProduct_num(Integer product_num) {
        this.product_num = product_num;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public Integer getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Integer order_time) {
        this.order_time = order_time;
    }
}
