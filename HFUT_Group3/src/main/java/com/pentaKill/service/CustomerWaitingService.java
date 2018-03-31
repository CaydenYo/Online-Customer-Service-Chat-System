package com.pentaKill.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pentaKill.dao.CustomerWaitingMapper;
import com.pentaKill.domain.WaitingCustomerBean;

@Service
@Scope
public class CustomerWaitingService {
    
    @Resource
    private CustomerWaitingMapper customerWaitingMapper;
    
    public void inserCustomerWaitingTeam(int customerId, int csId){
        customerWaitingMapper.insertWaitingCustomer(customerId, csId);
    }
    
    public List<WaitingCustomerBean> selectCustomerByCsId(int csId){
        
        List<Integer> customerIdList= new LinkedList<Integer>();
        customerIdList = customerWaitingMapper.selectCustomerIdByCsId(csId);
        List<WaitingCustomerBean> list = new LinkedList<WaitingCustomerBean>();
        for(Integer i : customerIdList){
            WaitingCustomerBean wcb = customerWaitingMapper.selectCustomerByCsId(i.intValue());
            list.add(wcb);
        }
        return list;
    }
    
    
}
