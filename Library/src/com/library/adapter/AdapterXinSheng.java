package com.library.adapter;

import java.util.List;

import com.library.activity.R;
import com.library.bean.shfzhihu.ShfReply;
import com.library.bean.shfzhihu.ShfRoot;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.common.application.Options;
import cn.utils.tools.MxgsaTagHandler;

public class AdapterXinSheng extends BaseAdapter
{
  List<ShfRoot> rtValue;
  private LayoutInflater mInflater = null;
  private Context myContext;
  protected ImageLoader imageLoader = ImageLoader.getInstance();
  DisplayImageOptions options;
  View vi;

  public AdapterXinSheng(Context _context, LayoutInflater myInflater, List<ShfRoot> list)
  {
    myContext = _context;
    options = Options.getListOptions();
    mInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    rtValue = list;
  }

  @Override
  public int getCount()
  {
    return rtValue.isEmpty() ? 0 : rtValue.size();
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
      holder.textReplaykView = (TextView) vi.findViewById(R.id.txt_rep_lsit_itme_xsdh);
      holder.imgView = (ImageView) vi.findViewById(R.id.img_rep_list_item_xsdh);
      // holder.textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);

      // holder.taskView = (TextView)
      // vi.findViewById(R.id.txt_listitem_btm_xsdh);
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
    StringBuilder sbImgUrl = new StringBuilder();
    for (int i = 0; i < rtValue.get(position).get_listRtAll().size(); i++)
    {
      sb = new StringBuilder();
      ShfReply sf = new ShfReply();
      sf = rtValue.get(position).get_listRtAll().get(i);
      sb.append(sf.getConent()).append("\n");
      if (rtValue.get(position).get_listRtAll().get(i).isHaveImg())
      {
        sbImgUrl.append(rtValue.get(position).get_listRtAll().get(i).getContentImgUrl());
      }
    }
    holder.textReplaykView
        .setText(Html.fromHtml(sb.toString(), null, new MxgsaTagHandler(myContext)));
    holder.textReplaykView.setClickable(false);
    holder.textView.setTag(rtValue.get(position));
    if ((sbImgUrl.toString()).equals(""))
    {
      holder.imgView.setVisibility(8);
    } else
    {
      holder.imgView.setVisibility(0);
      imageLoader.displayImage(sbImgUrl.toString(), holder.imgView, options,
          new ImageLoadingListener()
          {

            @Override
            public void onLoadingStarted(String imageUri, View view)
            {
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason)
            {
              view.setVisibility(View.GONE);
              @SuppressWarnings("unused")
              String message = null;
              switch (failReason.getType())
              { // 获取图片失败类型
              case IO_ERROR: // 文件I/O错误
                message = "Input/Output error";
                break;
              case DECODING_ERROR: // 解码错误
                message = "Image can't be decoded";
                break;
              case NETWORK_DENIED: // 网络延迟
                message = "Downloads are denied";
                break;
              case OUT_OF_MEMORY: // 内存不足
                message = "Out Of Memory error";
                break;
              case UNKNOWN: // 原因不明
                message = "Unknown error";
                break;
              }

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
            {
              view.setVisibility(View.VISIBLE);

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view)
            {
              view.setVisibility(View.GONE);

            }
          });
    }
    // holder.imageView.setTag(rtValue.get(position));

    return vi;
  }

  public static class ViewHolder
  {
    public TextView textView;
    // public ImageView imageView;
    public TextView textReplaykView;
    public ImageView imgView;

  }
}
