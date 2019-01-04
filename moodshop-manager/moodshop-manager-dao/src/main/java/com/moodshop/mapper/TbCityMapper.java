package com.moodshop.mapper;

import com.moodshop.pojo.TbCity;
import com.moodshop.pojo.TbCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCityMapper {
    int countByExample(TbCityExample example);

    int deleteByExample(TbCityExample example);

    int deleteByPrimaryKey(Integer cityId);

    int insert(TbCity record);

    int insertSelective(TbCity record);

    List<TbCity> selectByExample(TbCityExample example);

    TbCity selectByPrimaryKey(Integer cityId);

    int updateByExampleSelective(@Param("record") TbCity record, @Param("example") TbCityExample example);

    int updateByExample(@Param("record") TbCity record, @Param("example") TbCityExample example);

    int updateByPrimaryKeySelective(TbCity record);

    int updateByPrimaryKey(TbCity record);
}