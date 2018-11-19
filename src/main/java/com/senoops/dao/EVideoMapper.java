package com.senoops.dao;

import com.senoops.model.EVideo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EVideoMapper {

	int deleteByPrimaryKey(Integer id);

	int delete(@Param("videoIdList") Integer[] videoIdList);

	int insertSelective(EVideo record);

	int insertList(@Param("videoList") List<EVideo> videoList);

	EVideo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EVideo record);

	List<EVideo> selectBySectionId(@Param("sectionId") Integer sectionId);
}