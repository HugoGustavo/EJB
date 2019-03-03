package com.tutorialspoint.txn.required;

import javax.ejb.Remote;

@Remote
public interface UserDetailBeanRemote {
	public void createUserDetail();
}
