package com.library.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.utils.tools.MxgsaTagHandler;
import cn.utils.tools.TasksCompletedView;

import com.library.activity.R;
import com.library.application.Options;
import com.library.bean.shfzhihu.ShfReply;
import com.library.bean.shfzhihu.ShfRoot;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class AdapterXinSheng extends BaseAdapter
{
	ArrayList<ShfRoot> rtValue;
	private LayoutInflater mInflater = null;
	private Context myContext;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	TasksCompletedView viw = null;
	View vi;

	public AdapterXinSheng(Context _context, LayoutInflater myInflater, ArrayList<ShfRoot> list)
	{
		myContext = _context;
		options = Options.getListOptions();
		mInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rtValue = list;
	}

	@Override
	public int getCount()
	{
		return rtValue != null ? rtValue.size() : 0;
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
		vi = convertView;
		ViewHolder holder = new ViewHolder();

		if (convertView == null)
		{
			vi = mInflater.inflate(R.layout.list_view_item_xsdh, null);

			holder = new ViewHolder();
			holder.textView = (TextView) vi.findViewById(R.id.txt_listitem_top_xsdh);
			holder.textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
			holder.taskView = (TextView) vi.findViewById(R.id.txt_listitem_btm_xsdh);
			vi.setTag(holder);
		} else
		{
			holder = (ViewHolder) vi.getTag();
		}

		if (!(rtValue.get(position).getTitle() == null))
		{
			holder.textView.setText("\u3000" + rtValue.get(position).getTitle());
		} else
		{
			holder.textView.setText("");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rtValue.get(position).get_listRtAll().size(); i++)
		{
			sb = new StringBuilder();
			ShfReply sf = new ShfReply();
			sf = rtValue.get(position).get_listRtAll().get(i);
			sb.append(sf.getConent()).append("\n");
		}
		holder.taskView.setText(Html.fromHtml(sb.toString(), null, new MxgsaTagHandler(myContext)));
		holder.taskView.setClickable(false);
		holder.textView.setTag(rtValue.get(position));
		// holder.imageView.setTag(rtValue.get(position));

		return vi;
	}

	public static class ViewHolder
	{
		public TextView textView;
		// public ImageView imageView;
		public TextView taskView;

	}
}
