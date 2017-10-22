package com.yarten.experiment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yarten on 2017/10/16.
 * GoodsManager存储和管理商品信息
 */

public class GoodsManager
{
    static class GoodsInfo
    {
        public String name;
        public float price;
    }

    public static List<GoodsInfo> goods;

    public static void loadGoodsInfo()
    {
        String[] names = {
                "Enchated Forest",
                "Arla Milk",
                "Devondale Milk",
                "Kindle Oasis",
                "waitrose 早餐麦片",
                "Mcvitie's 饼干",
                "Ferrero Rocher",
                "Maltesers",
                "Lindt",
                "Borggreve"
        };

        float[] prices = {
                5.00f,
                59.00f,
                79.00f,
                2399.00f,
                179.00f,
                14.90f,
                132.59f,
                141.43f,
                139.43f,
                28.90f
        };

        goods = new ArrayList<>();
        for (int i = 0, size = prices.length; i < size; i++)
        {
            GoodsInfo info = new GoodsInfo();
            info.name = names[i];
            info.price = prices[i];
            goods.add(info);
        }
    }
}
