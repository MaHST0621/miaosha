package com.example.miaosha.miaosha001.service.Imp;

import com.example.miaosha.miaosha001.dao.StockDoMapper;
import com.example.miaosha.miaosha001.dao.StockOrderDoMapper;
import com.example.miaosha.miaosha001.dataobject.StockDo;
import com.example.miaosha.miaosha001.dataobject.StockOrderDo;
import com.example.miaosha.miaosha001.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    StockOrderDoMapper stockOrderDoMapper;
    @Autowired
    StockDoMapper stockDoMapper;


    @Override
    @Transactional
    public Integer createWrongOrder(StockOrderDo orderDo) {
        StockDo stockDo = checkStock(orderDo.getSid());
        saleStock(stockDo);
        int result = createOrder(orderDo);
        System.out.println(result);
        return result;
    }

    public StockDo checkStock(int stockId) {
        StockDo stockDo = stockDoMapper.selectByPrimaryKey(stockId);
        if (stockDo == null) {
            throw new RuntimeException("商品不存在");
        }
        if (stockDo.getSale().equals(stockDo.getCount())) {
            throw new RuntimeException("库存不足");
        }
        return stockDo;
    }

    public int saleStock(StockDo stockDo) {
        stockDo.setSale(stockDo.getSale() + 1);
        return stockDoMapper.updateByPrimaryKey(stockDo);
    }

    public int createOrder(StockOrderDo stockOrderDo) {
        System.out.println(stockOrderDo.toString());
        stockOrderDoMapper.insert(stockOrderDo);
        return stockOrderDo.getId();
    }
}
