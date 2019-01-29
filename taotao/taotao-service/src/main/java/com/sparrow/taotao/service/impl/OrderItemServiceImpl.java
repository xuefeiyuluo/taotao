package com.sparrow.taotao.service.impl;

import com.sparrow.taotao.OrderItem;
import com.sparrow.taotao.dao.OrderItemMapper;
import com.sparrow.taotao.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuefeiyuluo on 2019/1/25.
 */


@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public Integer addOrderItem(OrderItem orderItem) {
        Integer result = orderItemMapper.insert(orderItem);

        return result;
    }
}
