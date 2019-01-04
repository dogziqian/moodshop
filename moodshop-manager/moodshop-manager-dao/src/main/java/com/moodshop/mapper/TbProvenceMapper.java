package com.moodshop.mapper;

import com.moodshop.pojo.TbProvence;
import com.moodshop.pojo.TbProvenceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbProvenceMapper {
    int countByExample(TbProvenceExample example);

    int deleteByExample(TbProvenceExample example);

    int deleteByPrimaryKey(Integer provenceId);

    int insert(TbProvence record);

    int insertSelective(TbProvence record);

    List<TbProvence> selectByExample(TbProvenceExample example);

    TbProvence selectByPrimaryKey(Integer provenceId);

    int updateByExampleSelective(@Param("record") TbProvence record, @Param("example") TbProvenceExample example);

    int updateByExample(@Param("record") TbProvence record, @Param("example") TbProvenceExample example);

    int updateByPrimaryKeySelective(TbProvence record);

    int updateByPrimaryKey(TbProvence record);
}