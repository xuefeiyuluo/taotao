package com.sparrow.taotao.service.impl;

import com.sparrow.taotao.ContentCategory;
import com.sparrow.taotao.dao.ContentCategoryMapper;
import com.sparrow.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/30.
 */

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private ContentCategoryMapper contentCategoryMapper;


    @Override
    public Integer updateContentCategory(ContentCategory contentCategory) {
        return contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
    }

    @Override
    public List<ContentCategory> queryContentCategory(ContentCategory contentCategory) {
        List<ContentCategory> categoryList = contentCategoryMapper.selectByParentId(contentCategory);
        return categoryList;
    }

    @Override
    public Integer addContentCategory(ContentCategory contentCategory) {

        Integer result = contentCategoryMapper.insert(contentCategory);

        return result;
    }
}
