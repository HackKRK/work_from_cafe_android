package com.hackkrk.workforcafe.cafes;

import com.hackkrk.workforcafe.model.Cafe;
import com.hackkrk.workforcafe.network.AbstractWSCommand;
import com.hackkrk.workforcafe.network.ResponseHandler;

public class GetCafesCommand extends AbstractWSCommand<Cafe[]> {

	public GetCafesCommand(ResponseHandler<Cafe[]> responseHandler) {
		super(responseHandler);
	}

	@Override
	protected Class<Cafe[]> getTargetParseClass() {
		return Cafe[].class;
	}

	@Override
	protected String getMethodName() {
		return "index.json";
	}
	
	
}
