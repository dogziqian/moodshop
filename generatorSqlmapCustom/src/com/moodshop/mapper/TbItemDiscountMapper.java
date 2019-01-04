package com.moodshop.mapper;

import com.moodshop.pojo.TbItemDiscount;
import com.moodshop.pojo.TbItemDiscountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbItemDiscountMapper {
    int countByExample(TbItemDiscountExample example);

    int deleteByExample(TbItemDiscountExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(TbItemDiscount record);

    int insertSelective(TbItemDiscount record);

    List<TbItemDiscount> selectByExample(TbItemDiscountExample example);

    TbItemDiscount selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") TbItemDiscount record, @Param("example") TbItemDiscountExample example);

    int updateByExample(@Param("record") TbItemDiscount record, @Param("example") TbItemDiscountExample example);

    int updateByPrimaryKeySelective(TbItemDiscount record);

    int updateByPrimaryKey(TbItemDiscount record);
}