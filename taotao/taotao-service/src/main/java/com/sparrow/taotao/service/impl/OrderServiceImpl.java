package com.sparrow.taotao.service.impl;

import com.sparrow.taotao.Order;
import com.sparrow.taotao.dao.OrderMapper;
import com.sparrow.taotao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuefeiyuluo on 2019/1/23.
 */

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String queryOrderId(String id) {
        Order order = orderMapper.selectByPrimaryKey("0001");

        if (order == null) {
            return "数据为空";
        }
        return order.getPayment();
    }
}
