package com.senoops.service;

import com.senoops.model.ECategory;
import com.senoops.utils.PageUtil;

import java.util.List;

public interface CategoryService {

    int add(ECategory category);

    int delete(Integer categoryId);

    int update(ECategory category);

    ECategory query(Integer categoryId);

    List<ECategory> queryAll(String name, PageUtil pageUtil);
}
