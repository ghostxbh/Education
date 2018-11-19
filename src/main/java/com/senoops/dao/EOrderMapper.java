package com.senoops.dao;

import com.senoops.model.EOrder;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;


import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int delete(@Param("orderIdList") Integer[] orderIdList);

    int insertSelective(EOrder record);

    EOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EOrder record);

    List<EOrder> selectAll(@Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    List<EOrder> selectByUserId(@Param("userId") Integer userId, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
    
    List<EOrder> selectByParam(@Param("order") EOrder order, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
}