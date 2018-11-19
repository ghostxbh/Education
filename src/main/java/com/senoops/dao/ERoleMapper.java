package com.senoops.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.senoops.model.ERole;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

public interface ERoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ERole record);

    ERole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ERole record);
    
    ERole selectByUserId(@Param("userId")Integer userId);
    
    List<ERole> select(@Param("role")ERole role,@Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
}