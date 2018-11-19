package com.senoops.dao;

import com.senoops.model.ESection;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ESectionMapper {
    int deleteByPrimaryKey(Integer id);

    int delete(@Param("sectionIdList")Integer[] sectionIdList);

    int insertSelective(ESection record);

    int insertList(@Param("list") List<ESection> list);

    ESection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ESection record);

    List<ESection> selectByCourseId(@Param("courseId") Integer courseId,@Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
}