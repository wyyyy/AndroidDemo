package com.news.fragment;

import java.util.ArrayList;
import java.util.List;

import com.library.activity.R;
import com.news.activity.CityListActivity;
import com.news.activity.DetailsActivity;
import com.news.adapter.NewsAdapter;
import com.news.bean.News.NewsEntity;
import com.news.dao.ConstantsDao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class NewsFragment extends Fragment
{

  private static final String TAG = "NewsFragment";
  Activity activity;
  List<NewsEntity> newsList = new ArrayList<NewsEntity>();
  ListView mListView;
  NewsAdapter mAdapter;
  String text;
  int channel_id;
  public final static int SET_NEWSLIST = 0;

  // Toast提示框

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    Bundle args = getArguments();
    text = args != null ? args.getString("text") : "";
    channel_id = args != null ? args.getInt("id", 0) : 0;
    initData();
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onAttach(Activity activity)
  {
    this.activity = activity;
    super.onAttach(activity);
  }

  /** 此方法意思为fragment是否可见 ,可见时候加载数据 */

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser)
  {
    if (isVisibleToUser)
    {
      // fragment可见时加载数据
      if (!newsList.isEmpty())
      {
        handler.obtainMessage(SET_NEWSLIST).sendToTarget();
      } else
      {
        new Thread(new Runnable()
        {
          @Override
          public void run()
          {
            try
            {
              Thread.sleep(5);
            } catch (InterruptedException e)
            {
              e.printStackTrace();
            }
            handler.obtainMessage(SET_NEWSLIST).sendToTarget();
          }
        }).start();
      }
    } else
    {
      // fragment不可见时不执行操作
    }
    super.setUserVisibleHint(isVisibleToUser);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
    mListView = (ListView) view.findViewById(R.id.mNewsListView);
    return view;
  }

  private void initData()
  {
    newsList = ConstantsDao.getNewsList();
  }

  @SuppressLint("HandlerLeak")
  Handler handler = new Handler()
  {
    @Override
    public void handleMessage(Message msg)
    {
      // TODO Auto-generated method stub
      switch (msg.what)
      {
      case SET_NEWSLIST:

        if (mAdapter == null)
        {
          mAdapter = new NewsAdapter(activity, newsList);
          // 判断是不是城市的频道
          if (channel_id == ConstantsDao.CHANNEL_CITY)
          {
            // 是城市频道
            mAdapter.setCityChannel(true);
            initCityChannel();
          }
        }
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(mAdapter);

        mListView.setOnItemClickListener(new OnItemClickListener()
        {

          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id)
          {
            Intent intent = new Intent(activity, DetailsActivity.class);
            if (channel_id == ConstantsDao.CHANNEL_CITY)
            {
              if (position != 0)
              {
                intent.putExtra("news", mAdapter.getItem(position - 1));
                startActivity(intent);
                activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
              }
            } else
            {
              intent.putExtra("news", mAdapter.getItem(position));
              startActivity(intent);
              activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
          }
        });
        if (channel_id == 1)
        {
          initNotify();
        }
        break;
      default:
        break;
      }
      super.handleMessage(msg);
    }
  };

  /* 初始化选择城市的header */
  public void initCityChannel()
  {
    View headview = LayoutInflater.from(activity).inflate(R.layout.city_category_list_tip, null);
    TextView chose_city_tip = (TextView) headview.findViewById(R.id.chose_city_tip);
    chose_city_tip.setOnClickListener(new OnClickListener()
    {

      @Override
      public void onClick(View v)
      {
        Intent intent = new Intent(activity, CityListActivity.class);
        startActivity(intent);
      }
    });
    mListView.addHeaderView(headview);
  }

  /* 初始化通知栏目 */
  private void initNotify()
  {
    new Handler().postDelayed(new Runnable()
    {

      @Override
      public void run()
      {
        new Handler().postDelayed(new Runnable()
        {

          @Override
          public void run()
          {
          }
        }, 2000);
      }
    }, 1000);
  }

  /* 摧毁视图 */
  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    Log.d("onDestroyView", "channel_id = " + channel_id);
    mAdapter = null;
  }

  /* 摧毁该Fragment，一般是FragmentActivity 被摧毁的时候伴随着摧毁 */

  @Override
  public void onDestroy()
  {
    // TODO Auto-generated method stub
    super.onDestroy();
    Log.d(TAG, "channel_id = " + channel_id);
  }

  public void update(List<NewsEntity> arrayList)
  {
    newsList.addAll(0, arrayList);
    mAdapter.notifyDataSetChanged();
  }

  public boolean checkCanDoRefresh()
  {
    if (mAdapter.getCount() == 0 || mListView == null)
    {
      return true;
    }
    return mListView.getFirstVisiblePosition() == 0 && mListView.getChildAt(0).getTop() == 0;
  }
}
