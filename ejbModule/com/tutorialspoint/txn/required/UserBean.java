package com.tutorialspoint.txn.required;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.tutorialspoint.entity.UserDetail;

@Stateless
public class UserBean implements UserBeanRemote {
	private UserDetail userDetaisl;
	
	@EJB
	private UserDetailBeanRemote userDetailBean;
	
	public void createUser() {
		// create user
		// ...
		// create user details
		userDetailBean.createUserDetail();
	}

}
