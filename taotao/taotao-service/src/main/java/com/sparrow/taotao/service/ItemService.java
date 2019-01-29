package com.sparrow.taotao.service;

import com.sparrow.taotao.Item;

import java.util.HashMap;
import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/24.
 */
public interface ItemService {
    List itemList(HashMap map);
    Integer addItem(Item item);

}
