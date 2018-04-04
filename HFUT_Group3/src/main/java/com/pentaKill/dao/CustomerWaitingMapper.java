package com.pentaKill.dao;

import java.util.List;

import com.pentaKill.domain.WaitingCustomerBean;

public interface CustomerWaitingMapper {

    public List<Integer> selectCustomerIdByCsId(int csId);

    public WaitingCustomerBean selectCustomerByCsId(int customer_id);

}
