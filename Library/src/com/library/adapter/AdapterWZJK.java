package com.library.adapter;

import java.util.List;

import com.library.activity.R;
import com.library.bean.XiaoHua.XHData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import cn.common.application.Options;
import cn.utils.tools.TasksCompletedView;

public class AdapterWZJK extends BaseAdapter
{
  private LayoutInflater mInflater = null;
  protected ImageLoader imageLoader = ImageLoader.getInstance();
  private DisplayImageOptions options;
  private TasksCompletedView viw = null;
  private List<XHData> rtValue;
  View vi;

  public AdapterWZJK(Context _context, LayoutInflater inflater, List<XHData> list)
  {
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
      vi = mInflater.inflate(R.layout.list_view_item_tongzhi, null);

      holder = new ViewHolder();
      holder.textView = (TextView) vi.findViewById(R.id.txt_listitem_tongzhi);
      holder.imageView = (ImageView) vi.findViewById(R.id.img_listitem_tongzhi);
      holder.imageView.setVisibility(View.GONE);
      holder.taskView = (TasksCompletedView) vi.findViewById(R.id.tasks_view_listitem_tongzhi);
      viw = (TasksCompletedView) vi.findViewById(R.id.tasks_view_listitem_tongzhi);
      vi.setTag(holder);
    } else
    {
      holder = (ViewHolder) vi.getTag();
    }

    if (!(rtValue.get(position).getContent() == null))
    {
      holder.textView.setText(rtValue.get(position).getContent());
    } else
    {
      holder.textView.setText("");
    }
    holder.textView.setTag(rtValue.get(position));
    holder.imageView.setTag(rtValue.get(position));
    imageLoader.displayImage(rtValue.get(position).getContent(), holder.imageView, options,
        new ImageLoadingListener()
        {

          @Override
          public void onLoadingStarted(String imageUri, View view)
          {
            viw.setVisibility(View.VISIBLE);
          }

          @Override
          public void onLoadingFailed(String imageUri, View view, FailReason failReason)
          {
            viw.setVisibility(View.GONE);
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
            viw.setVisibility(View.GONE);

          }

          @Override
          public void onLoadingCancelled(String imageUri, View view)
          {
            viw.setVisibility(View.GONE);

          }
        });
    return vi;
  }

  public static class ViewHolder
  {
    public TextView textView;
    public ImageView imageView;
    public TasksCompletedView taskView;
  }
}
