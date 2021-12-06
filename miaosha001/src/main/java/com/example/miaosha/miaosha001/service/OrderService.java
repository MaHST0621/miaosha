package com.example.miaosha.miaosha001.service;

import com.example.miaosha.miaosha001.dataobject.StockOrderDo;



public interface OrderService {

    /**
     * 订单信息
     * @param orderDo
     * @return
     */
    Integer createWrongOrder(StockOrderDo orderDo);
}
