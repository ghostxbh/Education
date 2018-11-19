package com.senoops.dao;

import com.senoops.model.ECategory;
import com.senoops.utils.PageUtil;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ECategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ECategory record);

    ECategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ECategory record);

    List<ECategory> selectAll(@Param("name")String name, @Param("pageUtil")PageUtil pageUtil);
}