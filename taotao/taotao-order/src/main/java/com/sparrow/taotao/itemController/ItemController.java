package com.sparrow.taotao.itemController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sparrow.taotao.Item;
import com.sparrow.taotao.ItemCat;
import com.sparrow.taotao.OrderItem;
import com.sparrow.taotao.service.ItemCatSerVice;
import com.sparrow.taotao.service.ItemService;
import com.sparrow.taotao.service.OrderItemService;
import com.sparrow.taotao.utils.DateUtils;
import com.sparrow.taotao.utils.JsonFormat;
import com.sparrow.taotao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/24.
 */

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemCatSerVice itemCatSerVice;
    @Autowired
    private OrderItemService orderItemService;



    // http://localhost:8080/item/searchItemContent?content=手机
    @ResponseBody
    @RequestMapping("/searchItemContent")
    public JSON searchItemContent(HttpServletRequest req) {
        JSONObject json = new JSONObject();
        String content = req.getParameter("content");
        if (!StringUtils.isEmpty(content)) {
            return JsonFormat.jsonSuccessWithMsgResult("请输入正确的查询内容",json);
        }

        String temp = content.replace(" ","");
        StringBuffer stringBuffer = new StringBuffer("%");
        for (int i = 0; i < temp.length(); i++) {
            stringBuffer.append(temp.charAt(i) + "%");
        }

        String newContent = stringBuffer.toString();

        List<Item> searchList = itemService.queryItemContent(newContent);

        if (searchList.size() > 0) {
            List<JSONObject> jsonList = new ArrayList<>();
            for (Item item: searchList) {
                JSONObject itemJson = new JSONObject();
                itemJson.put("title",item.getTitle());
                itemJson.put("sellPoint",item.getSellPoint());
                itemJson.put("price",item.getPrice());
                itemJson.put("num",item.getNum());
                itemJson.put("barcode",item.getBarcode());
                itemJson.put("image",item.getImage());
                itemJson.put("cid",item.getCid());
                itemJson.put("status",item.getStatus());
                itemJson.put("created", DateUtils.formatDate(item.getCreated(),null));
                itemJson.put("updated", DateUtils.formatDate(item.getUpdated(),null));
                jsonList.add(itemJson);
            }
            return JsonFormat.jsonSuccessWithMsgResult("查询成功",jsonList);
        } else {
            return JsonFormat.jsonSuccessWithMsgResult("查询的结果为空",json);
        }
    }



    // http://localhost:8080/item/addItem?title=手机&sellPoint=便宜&price=200&num=1&image=www.baidu.com&cid=560&status=1
    @ResponseBody
    @RequestMapping("/addItem")
    public JSON addItem(HttpServletRequest req) {
        JSONObject json = new JSONObject();

        String title = req.getParameter("title");
        if (req.getParameter("sellPoint") == null || req.getParameter("sellPoint") == ""){
            return JsonFormat.jsonFailWithMsgResult("商品的卖点不能为空",json);
        }
        String sellPoint = req.getParameter("sellPoint");

        if (req.getParameter("price") == null || req.getParameter("price") == ""){
            return JsonFormat.jsonFailWithMsgResult("商品的价格不能为空",json);
        }
        Long price = Long.valueOf(req.getParameter("price"));

        if (req.getParameter("num") == null || req.getParameter("num") == ""){
            return JsonFormat.jsonFailWithMsgResult("商品的数量不能为空",json);
        }
        Integer num = Integer.valueOf(req.getParameter("num"));
        String barcode = req.getParameter("barcode");
        String image = req.getParameter("image");
        if (req.getParameter("cid") == null || req.getParameter("cid") == ""){
            return JsonFormat.jsonFailWithMsgResult("商品的cid不能为空",json);
        }
        Long cid = Long.valueOf(req.getParameter("cid"));
        Integer status = req.getParameter("status") == null ? 1 :Integer.valueOf(req.getParameter("status"));

        Item item = new Item();
        item.setTitle(title);
        item.setSellPoint(sellPoint);
        item.setPrice(price);
        item.setNum(num);
        item.setBarcode(barcode);
        item.setImage(image);
        item.setCid(cid);
        item.setStatus(status);
        item.setCreated(new Date());
        item.setUpdated(new Date());

        Integer result = itemService.addItem(item);

        if (result == 1) {
            return JsonFormat.jsonSuccessResult(json);
        } else {
            return JsonFormat.jsonFailWithMsgResult("提交失败",json);
        }
    }


    // http://localhost:8080/item/addOrderItem?parentId=11&orderId=2000&num=1&title=电子书&price=200&totalFee=200&picPath=www.baidu.com
    @ResponseBody
    @RequestMapping("/addOrderItem")
    public JSON addOrderItem(HttpServletRequest req) {
        String itemId = req.getParameter("parentId");
        String orderId = req.getParameter("orderId");
        Integer num = req.getParameter("num") == null ? 0 : Integer.valueOf(req.getParameter("num"));
        String title = req.getParameter("title");
        Long price = Long.valueOf(req.getParameter("price"));
        Long totalFee = Long.valueOf(req.getParameter("totalFee"));
        String picPath = req.getParameter("picPath");

        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(itemId);
        orderItem.setOrderId(orderId);
        orderItem.setNum(num);
        orderItem.setTitle(title);
        orderItem.setPrice(price);
        orderItem.setTotalFee(totalFee);
        orderItem.setPicPath(picPath);

        Integer result = orderItemService.addOrderItem(orderItem);

        JSONObject json = new JSONObject();
        if (result == 1) {
            return JsonFormat.jsonSuccessResult(json);
        } else {
            return JsonFormat.jsonFailWithMsgResult("添加失败",json);
        }
    }


    // http://localhost:8080/item/itemCatList?parentId=1
    @ResponseBody
    @RequestMapping("/itemCatList")
    public JSON itemCatList(HttpServletRequest req) {
        String parentId = req.getParameter("parentId") == null ? "0" : req.getParameter("parentId");

        List<ItemCat> itemCatList= itemCatSerVice.requestByParentId(parentId);

        List<JSONObject> jsonList = new ArrayList<>();
        for (ItemCat cat: itemCatList) {
            JSONObject json = new JSONObject();
            json.put("itemCatId",cat.getId());
            json.put("parentId",cat.getParentId());
            json.put("name",cat.getName());
            json.put("status",cat.getStatus());
            json.put("sortOrder",cat.getSortOrder());
            json.put("isParent",cat.getIsParent());
            json.put("created",cat.getCreated());
            json.put("updated",cat.getUpdated());
            jsonList.add(json);
        }

        if (jsonList.size() > 0) {
            return JsonFormat.jsonSuccessResult(jsonList);
        } else {
            return JsonFormat.jsonSuccessWithMsgResult("没有数据", jsonList);
        }
    }



    // http://localhost:8080/item/itemList?pageNumber=2
    @ResponseBody
    @RequestMapping("/itemList")
    public JSON itemList(HttpServletRequest req) {
        Integer pageNumber = req.getParameter("pageNumber") == null ? 1 : Integer.valueOf(req.getParameter("pageNumber"));
        Integer pageSize = req.getParameter("pageSize") == null ? 10 : Integer.valueOf(req.getParameter("pageSize"));
        Integer start = (pageNumber - 1) * pageSize;

        HashMap paraMap = new HashMap();
        paraMap.put("start",start);
        paraMap.put("end",pageSize);

        List<Item> itemList = itemService.itemList(paraMap);

        List<JSONObject> jsonList = new ArrayList<>();
        for (Item item: itemList) {
            JSONObject json = new JSONObject();
            json.put("itemId", item.getId());
            json.put("title", item.getTitle());
            json.put("sellPoint", item.getSellPoint());
            json.put("price", item.getPrice());
            json.put("num", item.getNum());
            json.put("barcode", item.getBarcode());
            json.put("image", item.getImage());
            json.put("cid", item.getCid());
            json.put("status", item.getStatus());
            json.put("created", item.getCreated());
            json.put("updated", item.getUpdated());
            jsonList.add(json);
        }

        if (jsonList.size() >= 10) {
            return JsonFormat.jsonSuccessResult(jsonList);
        } else {
            return JsonFormat.jsonSuccessWithMsgResult("没有更多数据了",jsonList);
        }
    }
}
