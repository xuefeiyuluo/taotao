package com.sparrow.taotao.service.impl;

import com.sparrow.taotao.ItemDesc;
import com.sparrow.taotao.dao.ItemDescMapper;
import com.sparrow.taotao.service.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xuefeiyuluo on 2019/2/19.
 */

@Service
public class ItemDetailServiceImpl implements ItemDetailService {
    @Autowired
    private ItemDescMapper itemDescMapper;

    @Override
    public ItemDesc queryItemDetailInfo(Long itemId) {
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        return itemDesc;
    }
}
