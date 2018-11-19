package com.senoops.dao;

import com.senoops.model.EInvoice;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface EInvoiceMapper {
    int deleteByPrimaryKey(Integer id);

    int delete(@Param("invoiceIdList")Integer[] invoiceIdList);

    int insertSelective(EInvoice record);

    EInvoice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EInvoice record);
    
    EInvoice selectByOrderId(@Param("orderId")Integer orderId);

    List<EInvoice> selectAll(@Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

}