package com.example.miaosha.miaosha001.service.Imp;

import com.example.miaosha.miaosha001.dao.StockDoMapper;
import com.example.miaosha.miaosha001.dao.StockOrderDoMapper;
import com.example.miaosha.miaosha001.dataobject.StockDo;
import com.example.miaosha.miaosha001.dataobject.StockOrderDo;
import com.example.miaosha.miaosha001.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    StockOrderDoMapper stockOrderDoMapper;
    @Autowired
    StockDoMapper stockDoMapper;

    /**
     * @param stockOrderDo
     * @return
     */
    public int createOrder(StockOrderDo stockOrderDo) {
        stockOrderDoMapper.insert(stockOrderDo);
        return stockOrderDo.getId();
    }

    @Override
    //第二版本，加入乐观锁
    public Integer createWrongOrder(StockOrderDo orderDo) {
        StockDo stockDo = checkStock(orderDo.getSid());
        //第一版本，无并发考虑
        //saleStock(stockDo);
        salesStockOptimise(stockDo);
        return createOrder(orderDo);
    }

    //悲观锁
    //Transaction 无法做到绝对的线程同步
    @Override
    public synchronized Integer  createPessimisticOrder(StockOrderDo orderDo) {
        StockDo stockDo = checkStock(orderDo.getSid());
        saleStock(stockDo);
        return createOrder(orderDo);
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

    /**
     * 直接减去库存，不考虑并发
     * @param stockDo
     * @return
     */
    public int saleStock(StockDo stockDo) {
        stockDo.setSale(stockDo.getSale() + 1);
        System.out.println(stockDo.getSale() + 1 + "  " + Thread.currentThread().getName());
        return stockDoMapper.updateByPrimaryKey(stockDo);
    }

    /**
     * 数据库内部通过Version更新数据 ， 乐观锁
     *
     * 每次减去库存前 检查Vertion 是否是我们想要的version
     * @param stockDo
     * @return
     */
    public int salesStockOptimise(StockDo stockDo) {
        int count = stockDoMapper.updateByoptimisetic(stockDo);
        if (count == 0) {
            throw new RuntimeException("库存vertion不匹配");
        }
        return count;
    }

}
