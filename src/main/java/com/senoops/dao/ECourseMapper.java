package com.senoops.dao;

import com.senoops.model.ECourse;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ECourseMapper {
    int deleteByPrimaryKey(Integer id);

    int delete(@Param("courseIdList") Integer[] courseIdList);

    int insertSelective(ECourse record);

    ECourse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ECourse record);

    List<ECourse> selectByName(@Param("courseName")String courseName);
    
    List<ECourse> selectAll(@Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    List<ECourse> selectByCategoryId(@Param("categoryId") Integer categoryId,@Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    List<ECourse> select(@Param("course") ECourse course, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    List<ECourse> selectByParam(@Param("course") ECourse course, @Param("hightPrice") Double hightPrice, @Param("lowestPrice") Double lowestPrice,
                                @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
}