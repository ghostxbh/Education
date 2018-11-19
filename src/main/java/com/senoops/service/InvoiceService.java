package com.senoops.service;

import com.senoops.model.EInvoice;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import java.util.List;

public interface InvoiceService {

    int add(EInvoice invoice);

    int delete(Integer invoiceId);

    int deleteList(Integer[] invoiceIdList);

    int update(EInvoice invoice);

    EInvoice queryById(Integer id);
    
    EInvoice queryByOrderId(Integer orderId);

    List<EInvoice> queryAll(OrderUtil orderUtil,PageUtil pageUtil);
}
