package com.xinchan.dao.domain;

/**
 * Goods 表对应的 JavaBean
 * @author xinchan
 * @version 1.0.1 2022-02-20
 */
public class Goods {
    private Integer id;
    private String goods_name;
    private Double price;

    public Goods() {

    }

    public Goods(Integer id, String goods_name, Double price) {
        this.id = id;
        this.goods_name = goods_name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goods_name='" + goods_name + '\'' +
                ", price=" + price +
                '}';
    }
}
