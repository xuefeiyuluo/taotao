package com.sparrow.taotao.dao;

import com.sparrow.taotao.ItemParam;
import com.sparrow.taotao.ItemParamExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemParamMapper {
    long countByExample(ItemParamExample example);

    int deleteByExample(ItemParamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ItemParam record);

    int insertSelective(ItemParam record);

    List<ItemParam> selectByExampleWithBLOBs(ItemParamExample example);

    List<ItemParam> selectByExample(ItemParamExample example);

    ItemParam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByExampleWithBLOBs(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByExample(@Param("record") ItemParam record, @Param("example") ItemParamExample example);

    int updateByPrimaryKeySelective(ItemParam record);

    int updateByPrimaryKeyWithBLOBs(ItemParam record);

    int updateByPrimaryKey(ItemParam record);
}