package com.senoops.service;

import java.util.List;
import com.senoops.model.EVideo;


public interface VideoService {

    int deleteById(Integer id);

    int delete(Integer[] videoIdList);

    int add(EVideo record);
    
    int addList(List<EVideo> video);

    EVideo selectById(Integer id);

    int update(EVideo record);

    List<EVideo> selectBySectionId(Integer sectionId);

}
