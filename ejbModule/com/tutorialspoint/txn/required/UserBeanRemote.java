package com.tutorialspoint.txn.required;

import javax.ejb.Remote;

@Remote
public interface UserBeanRemote {
	public void createUser();
}
