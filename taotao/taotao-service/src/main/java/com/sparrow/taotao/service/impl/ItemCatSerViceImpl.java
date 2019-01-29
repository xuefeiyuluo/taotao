package com.sparrow.taotao.service.impl;

import com.sparrow.taotao.ItemCat;
import com.sparrow.taotao.dao.ItemCatMapper;
import com.sparrow.taotao.service.ItemCatSerVice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/24.
 */

@Service
public class ItemCatSerViceImpl implements ItemCatSerVice {

    @Autowired
    ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> requestByParentId(String parentId) {
        List<ItemCat> catList = itemCatMapper.reqestParentById(parentId);

        return catList;
    }
}
