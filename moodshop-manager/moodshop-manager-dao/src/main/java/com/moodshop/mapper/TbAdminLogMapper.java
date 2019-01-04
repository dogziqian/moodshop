package com.moodshop.mapper;

import com.moodshop.pojo.TbAdminLog;
import com.moodshop.pojo.TbAdminLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbAdminLogMapper {
    int countByExample(TbAdminLogExample example);

    int deleteByExample(TbAdminLogExample example);

    int insert(TbAdminLog record);

    int insertSelective(TbAdminLog record);

    List<TbAdminLog> selectByExample(TbAdminLogExample example);

    int updateByExampleSelective(@Param("record") TbAdminLog record, @Param("example") TbAdminLogExample example);

    int updateByExample(@Param("record") TbAdminLog record, @Param("example") TbAdminLogExample example);
}