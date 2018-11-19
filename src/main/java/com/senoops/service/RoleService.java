package com.senoops.service;


import java.util.List;

import com.senoops.model.ERole;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

public interface RoleService {

    ERole queryById(Integer id);

    int insert(ERole role);

    int update(ERole role);

    int delete(Integer id);
    
    ERole queryByUserId(Integer userId);
    
    List<ERole> queryByParam(ERole role,OrderUtil orderUtil,PageUtil pageUtil);
}
