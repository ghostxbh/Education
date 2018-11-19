package com.senoops.service.impl;

import com.senoops.dao.EInvoiceMapper;
import com.senoops.model.EInvoice;
import com.senoops.service.InvoiceService;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private EInvoiceMapper dao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int add(EInvoice invoice) {
        return dao.insertSelective(invoice);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int delete(Integer invoiceId) {
        return dao.deleteByPrimaryKey(invoiceId);
    }
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteList(Integer[] invoiceIdList) {
        return dao.delete(invoiceIdList);
    }
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(EInvoice invoice) {
        return dao.updateByPrimaryKeySelective(invoice);
    }

    @Override
    public EInvoice queryByOrderId(Integer orderId) {
        return dao.selectByOrderId(orderId);
    }

    @Override
    public List<EInvoice> queryAll(OrderUtil orderUtil, PageUtil pageUtil) {
        return dao.selectAll(orderUtil, pageUtil);
    }

	@Override
	public EInvoice queryById(Integer id) {		
		return dao.selectByPrimaryKey(id);
	}
}
