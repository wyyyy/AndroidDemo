package com.news.adapter;

import java.util.ArrayList;
import java.util.List;

import com.news.bean.News.NewsEntity;
import com.news.dao.ConstantsDao;
import com.news.fragment.NewsFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter
{
  private List<NewsFragment> fragments;
  private FragmentManager fm;
  private NewsFragment mCurrentFragment;

  public NewsFragmentPagerAdapter(FragmentManager fm)
  {
    super(fm);
    this.fm = fm;
  }

  public NewsFragmentPagerAdapter(FragmentManager fm, List<NewsFragment> fragments)
  {
    super(fm);
    this.fm = fm;
    this.fragments = fragments;
  }

  @Override
  public int getCount()
  {
    return fragments.size();
  }

  @Override
  public Fragment getItem(int position)
  {
    return fragments.get(position);
  }

  @Override
  public int getItemPosition(Object object)
  {
    return POSITION_NONE;
  }

  public void setFragments(List<NewsFragment> fragments)
  {
    if (!this.fragments.isEmpty())
    {
      FragmentTransaction ft = fm.beginTransaction();
      for (Fragment f : this.fragments)
      {
        ft.remove(f);
      }
      ft.commit();
      ft = null;
      fm.executePendingTransactions();
    }
    this.fragments = fragments;
    notifyDataSetChanged();
  }

  @Override
  public Object instantiateItem(ViewGroup container, final int position)
  {
    Object obj = super.instantiateItem(container, position);
    return obj;
  }

  public void switchTo(final int position)
  {
    int len = fragments.size();
    for (int i = 0; i < len; i++)
    {
      NewsFragment fragment = fragments.get(i);
      if (i == position)
      {
        mCurrentFragment = fragment;
      } 
    }
  }

  public boolean checkCanDoRefresh()
  {
    if (mCurrentFragment == null)
    {
      return true;
    }
    return mCurrentFragment.checkCanDoRefresh();
  }

  public void updateData(final PtrClassicFrameLayout mPtrFrame)
  {
    final NewsFragment fragment = mCurrentFragment;

    if (fragment == mCurrentFragment)
    {
      ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
      newsList = ConstantsDao.getNewsList2();
      fragment.update(newsList);
      mPtrFrame.refreshComplete();

    }
  }

}
