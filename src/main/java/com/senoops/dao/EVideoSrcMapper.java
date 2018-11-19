package com.senoops.dao;

import com.senoops.model.EVideoSrc;

public interface EVideoSrcMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EVideoSrc record);

    int insertSelective(EVideoSrc record);

    EVideoSrc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EVideoSrc record);

    int updateByPrimaryKey(EVideoSrc record);
}