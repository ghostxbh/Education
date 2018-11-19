package com.senoops.service.impl;


import org.springframework.stereotype.Service;

import com.senoops.dao.LastIDMapper;
import com.senoops.service.LastIDService;

import javax.annotation.Resource;

@Service
public class LastIDServiceImpl implements LastIDService {
    private LastIDMapper lastIDMapper;

    @Resource(name = "lastIDMapper")
    public void setLastIDMapper(LastIDMapper lastIDMapper) {
        this.lastIDMapper = lastIDMapper;
    }

    @Override
    public int selectLastID() {
        return lastIDMapper.selectLastID();
    }
}
