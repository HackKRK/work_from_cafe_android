package com.hackkrk.workforcafe;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkForCafeActivity extends Activity {
  
  List<Cafe> mCafes = new ArrayList<Cafe>();

  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView messanger = (TextView) findViewById(R.id.helloTextView);
        messanger.setText("Cafe list");
        
        ListView cafeList = (ListView) findViewById(R.id.helloTextView);
        
        
        CafeAdapter cafeAdapter = new CafeAdapter(this, 1, mCafes);
//        cafeAdapter.add(object)
        cafeList.setAdapter(cafeAdapter);
//        new GetRestaurantsTask()
        
    }
  
  class CafeAdapter extends ArrayAdapter<Cafe>{

    LayoutInflater mInflater;
    List<Cafe> mCafes;
    
    public CafeAdapter(Context context, int textViewResourceId, List<Cafe> cafeList) {
      super(context, textViewResourceId, cafeList);
      
      mInflater = LayoutInflater.from(context);
      mCafes = cafeList;
      // TODO Auto-generated constructor stub
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      View view;
      ViewCache viewCache;
      if (convertView == null) {
        view = mInflater.inflate(R.layout.cafe_item, null);
        viewCache = new ViewCache();
       
        viewCache.name = (TextView) view.findViewById(R.id.name);
        viewCache.desc = (TextView) view.findViewById(R.id.desc);
        viewCache.adderss = (TextView) view.findViewById(R.id.address);
        
        view.setTag(viewCache);
        
      } else {
        view = convertView;
        viewCache = (ViewCache) view.getTag();
        
      }
      
      Cafe cafe = mCafes.get(position);
      
      viewCache.name.setText(cafe.getName());
      viewCache.desc.setText(cafe.getDesc());
      viewCache.adderss.setText(cafe.getAddress());
      
      
    }
    
    class ViewCache{
      TextView name;
      TextView desc;
      TextView adderss;
    }
    
    
  }
}