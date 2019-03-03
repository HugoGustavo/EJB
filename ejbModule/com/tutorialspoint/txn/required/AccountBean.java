package com.tutorialspoint.txn.required;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

import com.tutorialspoint.entity.Account;



@Stateless
@TransactionManagement(value=TransactionManagementType.BEAN)
public class AccountBean implements AccountBeanLocal {

	@Resource
	private UserTransaction userTransaction;
	
	public void transfeerFund(Account fromAccount, double fund, Account toAccount) throws Exception {
		try {
			userTransaction.begin();
			confirmAccountDetail(fromAccount);
			withdrawAmount(fromAccount, fund);
			confirmAccountDetail(toAccount);
			depositAmout(toAccount, fund);
			userTransaction.commit();
		} catch(Exception e) {
			userTransaction.rollback();
			throw new RuntimeException(e);
		}
	}

	private void depositAmout(Account toAccount, double fund) {
		// TODO Auto-generated method stub
		
	}

	private void withdrawAmount(Account fromAccount, double fund) {
		// TODO Auto-generated method stub
		
	}

	private void confirmAccountDetail(Account fromAccount) {
		// TODO Auto-generated method stub
		
	}
}
