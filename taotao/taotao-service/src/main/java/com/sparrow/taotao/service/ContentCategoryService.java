package com.sparrow.taotao.service;

import com.sparrow.taotao.ContentCategory;

import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/30.
 */
public interface ContentCategoryService {

    List<ContentCategory> queryContentCategory(ContentCategory contentCategory);

    Integer addContentCategory(ContentCategory contentCategory);

    Integer updateContentCategory(ContentCategory contentCategory);

}
