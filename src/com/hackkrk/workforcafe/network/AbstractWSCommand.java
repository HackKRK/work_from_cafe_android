package com.hackkrk.workforcafe.network;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import android.os.AsyncTask;

public abstract class AbstractWSCommand<T> extends AsyncTask<Void, Void, T> {

	private static final String WS_URL = "https://raw.github.com/kaaes/work_from_cafe/gh-pages/";
	private ResponseHandler<T> responseHandler;
	private Gson gson;
	private Throwable error;
	
	public AbstractWSCommand(ResponseHandler<T> responseHandler) {
		this.responseHandler = responseHandler;
	}
	
	@Override
	protected T doInBackground(Void... arg) {
		try {
			HttpGet httpGet = new HttpGet(WS_URL + getMethodName());
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse response = httpClient.execute(httpGet);
			int responseCode = response.getStatusLine().getStatusCode();
			if(responseCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String jsonResult = EntityUtils.toString(entity);
				int start = jsonResult.indexOf("[");
//				int end = jsonResult.indexOf("]");
				String processedResult = jsonResult.substring(start);
				
        T object = getGsonParser().fromJson(processedResult, getTargetParseClass());
				return object;
			} else {
				throw new Exception("Error during downloading data from webservice, status code: " + responseCode);
			}
		} catch(Throwable e) {
			this.error = e;
		}
		return null;
	}

	protected void onPostExecute(T result) {
		if(error != null) {
			responseHandler.onError(error);
		} else {
			responseHandler.onResult(result);
		}
	}
	
	private Gson getGsonParser() {
		if(gson == null) {
			gson = new Gson();
		}
		return gson;
	}
	
	protected abstract Class<T> getTargetParseClass();
	protected abstract String getMethodName();
	
	
}
