package com.example.weatherdemo;

import com.example.entity.Root;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	private Context mycContext;
	Root status;


	public MyAdapter(Context cconContext, Root _status) {
		mycContext = cconContext;
		status = _status;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(status!=null &&status.getData()!=null &&status.getData().getForecast()!=null)
		{
return status.getData().getForecast().length;
		}else
			return 0;
		
	}

	@Override
	public Object getItem(int itemID) {
		// TODO Auto-generated method stub
		return itemID;
	}

	@Override
	public long getItemId(int postion) {
		// TODO Auto-generated method stub
		return postion;
	}

	@Override
	public View getView(int postion, View view, ViewGroup praent) {
		// TODO Auto-generated method stub
		LayoutInflater factory = LayoutInflater.from(mycContext);
		View iniView = (View) factory.inflate(R.layout.gvcell, null);
		ImageView imageView = (ImageView) iniView.findViewById(R.id.icon);
		TextView txtCloud = (TextView) iniView.findViewById(R.id.gcTxtCloud);
		TextView txtTemperature = (TextView) iniView.findViewById(R.id.gcTxtTemperature);
		TextView txtDate = (TextView) iniView.findViewById(R.id.gcTxtDate);
		
		imageView.setImageResource(R.id.icon);// picture
		StringBuilder sb=new StringBuilder();
	
		sb.append(status.getData().getForecast()[postion].getFengxiang());
		sb.append(status.getData().getForecast()[postion].getFengli());
		txtDate.setText(status.getData().getForecast()[postion].getDate());
		txtCloud.setText(sb.toString());// pictureName
		StringBuffer rtTemperature = new StringBuffer();
		rtTemperature.append(status.getData().getForecast()[postion].getLow().substring(2).toString().trim());
		rtTemperature.append("~");
		rtTemperature.append(status.getData().getForecast()[postion].getHigh().substring(2).toString().trim());
		txtTemperature.setText(rtTemperature);// pictureName
		return iniView;
	}

}