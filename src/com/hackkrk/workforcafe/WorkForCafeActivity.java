package com.hackkrk.workforcafe;

import com.hackkrk.workforcafe.cafes.GetCafesCommand;
import com.hackkrk.workforcafe.model.Cafe;
import com.hackkrk.workforcafe.network.ResponseHandler;

import android.app.Activity;
import android.os.Bundle;

public class WorkForCafeActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        GetCafesCommand cafes = new GetCafesCommand(new ResponseHandler<Cafe[]>() {
			
			public void onResult(Cafe[] object) {
				System.out.println("Teeeeest");
			}
			
			public void onError(Throwable th) {
				// TODO Auto-generated method stub
				
			}
		});
        cafes.execute();
    }
	
	
	
}