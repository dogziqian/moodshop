package com.moodshop.mapper;

import com.moodshop.pojo.TbUserdetail;
import com.moodshop.pojo.TbUserdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUserdetailMapper {
    int countByExample(TbUserdetailExample example);

    int deleteByExample(TbUserdetailExample example);

    int deleteByPrimaryKey(Long userid);

    int insert(TbUserdetail record);

    int insertSelective(TbUserdetail record);

    List<TbUserdetail> selectByExample(TbUserdetailExample example);

    TbUserdetail selectByPrimaryKey(Long userid);

    int updateByExampleSelective(@Param("record") TbUserdetail record, @Param("example") TbUserdetailExample example);

    int updateByExample(@Param("record") TbUserdetail record, @Param("example") TbUserdetailExample example);

    int updateByPrimaryKeySelective(TbUserdetail record);

    int updateByPrimaryKey(TbUserdetail record);
}