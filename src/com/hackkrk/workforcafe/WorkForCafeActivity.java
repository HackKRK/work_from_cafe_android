package com.hackkrk.workforcafe;

import com.hackkrk.workforcafe.cafes.GetCafesCommand;
import com.hackkrk.workforcafe.model.Cafe;
import com.hackkrk.workforcafe.network.ResponseHandler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WorkForCafeActivity extends Activity {

  List<Cafe> mCafes = new ArrayList<Cafe>();
  private GetCafesCommand getCafesCommand;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    TextView messanger = (TextView) findViewById(R.id.helloTextView);
    messanger.setText("Cafe list");

    final ListView cafeList = (ListView) findViewById(R.id.lv_cafes);

    final CafeAdapter cafeAdapter = new CafeAdapter(this, 1, mCafes);
    //        cafeAdapter.add(object)
    cafeList.setAdapter(cafeAdapter);
    //        new GetRestaurantsTask()
    getCafesCommand = new GetCafesCommand(new ResponseHandler<Cafe[]>() {

      public void onResult(Cafe[] object) {
        mCafes.clear();
        mCafes.addAll(new ArrayList<Cafe>(Arrays.asList(object)));
        cafeAdapter.notifyDataSetChanged();
      }

      public void onError(Throwable th) {
        Toast.makeText(getApplicationContext(), "err" + th.getMessage(),
            Toast.LENGTH_LONG).show();
      }
    });

    getCafesCommand.execute();
  }

  class CafeAdapter extends ArrayAdapter<Cafe> {

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

      StringBuffer sb = new StringBuffer();
      Map<String, String> description = cafe.getDescription();

      for (String s : description.keySet()) {
        sb.append(s + ": " + description.get(s) + "\n");
      }

      viewCache.name.setText(cafe.getName());
      viewCache.desc.setText(sb.toString());
      viewCache.adderss.setText(cafe.getAddress());

      return view;

    }

    class ViewCache {
      TextView name;
      TextView desc;
      TextView adderss;
    }

  }
}