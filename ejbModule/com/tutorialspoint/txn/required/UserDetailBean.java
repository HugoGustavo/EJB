package com.tutorialspoint.txn.required;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.tutorialspoint.entity.UserDetail;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserDetailBean implements UserDetailBeanRemote {
    private UserDetail userDetail;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createUserDetail() {}   
    
}
