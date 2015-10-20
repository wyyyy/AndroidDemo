package com.library.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.utils.tools.TasksCompletedView;

import com.library.activity.R;
import com.library.bean.EntityZh.RootZhihu;

public class AdapterImgDongtai2 extends BaseAdapter
{
	private LayoutInflater mInflater = null;
	List<Uri> imageUri = new ArrayList<Uri>();
	List<Drawable> listDrawable;
	List<RootZhihu> listCatalogs;
	List<Map<String, Drawable>> rtValue;

	public AdapterImgDongtai2(Context _context2, List<RootZhihu> _listCatalogs, List<Drawable> _listDrawable)
	{
		listDrawable = _listDrawable;
		listCatalogs = _listCatalogs;
		mInflater = (LayoutInflater) _context2.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount()
	{
		if (listDrawable == null)
		{
			return 0;
		} else
		{
			return listDrawable.size();
		}
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View vi = convertView;
		ViewHolder holder = new ViewHolder();
		if (convertView == null)
		{
			vi = mInflater.inflate(R.layout.row_listview_item, null);
			holder = new ViewHolder();

			holder.textView = (TextView) vi.findViewById(R.id.row_text);
			holder.imageView = (ImageView) vi.findViewById(R.id.row_image);
			vi.setTag(holder);
		} else
		{
			holder = (ViewHolder) vi.getTag();
		}

		if (listDrawable == null)
		{
		} else
		{
			holder.imageView.setImageDrawable(listDrawable.get(position));
		}
		if (listCatalogs == null)
		{
			holder.textView.setText("listCatalogs == null");
		} else
		{
			holder.textView.setText(listCatalogs.size());
		}
		return vi;
	}

	public static class ViewHolder
	{
		public TextView textView;
		public ImageView imageView;
		public TasksCompletedView taskView;
	}
}
