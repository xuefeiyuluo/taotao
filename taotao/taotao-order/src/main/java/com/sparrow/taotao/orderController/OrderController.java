package com.sparrow.taotao.orderController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sparrow.taotao.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuefeiyuluo on 2019/1/23.
 */

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping("/testMethod")
    public JSON testMethod(HttpServletRequest req){
        System.out.println("1234567890");


        String str = orderService.queryOrderId("");


        JSONObject jsonData = new JSONObject();
        jsonData.put("key","13214321");
        jsonData.put("payment",str);
        return  jsonData;
    }
}
