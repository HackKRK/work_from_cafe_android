package com.hackkrk.workforcafe.network;

public interface ResponseHandler<T> {
	
	public void onResult(T object);
	public void onError(Throwable th);	
	
}
