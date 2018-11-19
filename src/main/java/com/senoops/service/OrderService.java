package com.senoops.service;

import java.util.List;

import com.senoops.model.EOrder;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

public interface OrderService {

    EOrder searchById(Integer id);

    int insert(EOrder order);

    int update(EOrder order);

    int delete(Integer id);

    int deleteList(Integer[] idList);

    List<EOrder> queryByUserId(Integer userId,OrderUtil orderUtil,PageUtil pageUtil);

    List<EOrder> queryAll(OrderUtil orderUtil,PageUtil pageUtil);
    
    List<EOrder> queryByParam(EOrder order,OrderUtil orderUtil,PageUtil pageUtil);
}