package com.sparrow.taotao.service.impl;

import com.sparrow.taotao.Item;
import com.sparrow.taotao.ItemDesc;
import com.sparrow.taotao.ItemParam;
import com.sparrow.taotao.ItemParamItem;
import com.sparrow.taotao.dao.ItemDescMapper;
import com.sparrow.taotao.dao.ItemMapper;
import com.sparrow.taotao.dao.ItemParamItemMapper;
import com.sparrow.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/24.
 */

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;
    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    @Override
    public List itemList(HashMap map) {
        List<Item> itemList = itemMapper.queryItemList(map);

        return itemList;
    }


    @Override
    public Integer addItem(Item item) {
        itemMapper.insert(item);

        // 产品详情 产品的规格参数
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc("fdsjbvjdfsbvjdfsbvjfdsbjkvfbdsjkvfbdjksvbjdfksvjkdfs");
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        itemDescMapper.insert(itemDesc);


        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setParamData("[{\"test1\":\"12\",\"test2\":[\"12\"]}]");
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        itemParamItem.setItemId(item.getId());


        // 产品的规格参数
        return itemParamItemMapper.insert(itemParamItem);
    }
}
