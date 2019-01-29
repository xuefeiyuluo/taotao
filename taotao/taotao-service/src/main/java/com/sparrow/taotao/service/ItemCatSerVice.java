package com.sparrow.taotao.service;

import com.sparrow.taotao.ItemCat;

import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/24.
 */
public interface ItemCatSerVice {

    List<ItemCat> requestByParentId(String parentId);



}
