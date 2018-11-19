package com.senoops.service;

import com.senoops.model.ESection;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import java.util.List;

public interface SectionService {

    int deleteById(Integer id);

    int delete(Integer[] sectionIdList);

    int add(ESection record);

    int addList(List<ESection> list);

    ESection selectById(Integer id);

    int update(ESection record);

    List<ESection> selectByCourseId(Integer courseId,OrderUtil orderUtil,PageUtil pageUtil);
}
