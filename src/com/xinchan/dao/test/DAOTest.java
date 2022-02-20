package com.xinchan.dao.test;

import com.xinchan.dao.daos.GoodsDAO;
import com.xinchan.dao.domain.Goods;
import org.junit.Test;

import java.util.List;

/**
 * @author xinchan
 * @version 1.0.1 2022-02-20
 */
public class DAOTest {
    @Test
    public void daoDMLTest() {
        GoodsDAO goodsDAO = new GoodsDAO();
        int affectedRows = 0;
        // insert
        String insert = "insert into goods values(?, ?, ?)";
        affectedRows += goodsDAO.update(insert, 10, "huawei", 2000);
        affectedRows += goodsDAO.update(insert, 20, "iphone", 6000);
        affectedRows += goodsDAO.update(insert, 30, "xiaomi", 1999);
        affectedRows += goodsDAO.update(insert, 40, "oppo", 2500);
        affectedRows += goodsDAO.update(insert, 50, "vivo", 2800);
        // update
        String update = "update goods set price = ? where goods_name = ?";
        affectedRows += goodsDAO.update(update, 4000, "huawei");
        affectedRows += goodsDAO.update(update, 3000, "oppo");
        // delete
        String delete = "delete from goods where goods_name = ?";
        affectedRows += goodsDAO.update(delete, "iphone");

        if (affectedRows == 8) {
            System.out.println("操作成功");
        } else {
            System.out.println("操作可能有误");
        }
    }

    @Test
    public void daoSelectTest() {
        GoodsDAO goodsDAO = new GoodsDAO();

        // select multi
        String selectMulti = "select * from goods";
        List<Goods> list = goodsDAO.selectMulti(selectMulti, Goods.class);
        for (Goods goods : list) {
            System.out.println(goods);
        }
        // select single
        String selectSingle = "select * from goods where goods_name = ?";
        Goods huawei = goodsDAO.selectSingle(selectSingle, Goods.class, "huawei");
        Goods xiaomi = goodsDAO.selectSingle(selectSingle, Goods.class, "xiaomi");
        System.out.println(huawei);
        System.out.println(xiaomi);
        // select column
        String selectColumn = "select goods_name from goods where id = ?";
        String goods_name = (String) goodsDAO.selectColumn(selectColumn, 10);
        System.out.println(10 + "\t" + goods_name);
    }
}
