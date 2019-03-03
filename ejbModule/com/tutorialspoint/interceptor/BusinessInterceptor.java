package com.tutorialspoint.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BusinessInterceptor {
	@AroundInvoke
	public Object methodInterceptor(InvocationContext invocationContext) throws Exception {
		System.out.println("*** BEFORE Intercepting call to LibraryBean method: " + invocationContext.getMethod().getName());
		Object result = invocationContext.proceed();
		System.out.println("*** AFTER Intercepting call to LibraryBean method: " + invocationContext.getMethod().getName());
		return result;
	}
}
