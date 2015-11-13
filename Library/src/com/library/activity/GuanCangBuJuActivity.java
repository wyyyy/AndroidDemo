package com.library.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class GuanCangBuJuActivity extends Activity
{
  private PtrClassicFrameLayout mPtrFrame;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gun_cang_bu_ju);
    mPtrFrame = (PtrClassicFrameLayout) this.findViewById(R.id.rotate_header_grid_view_frame);
    mPtrFrame.setLastUpdateTimeRelateObject(this);
    mPtrFrame.setPtrHandler(new PtrHandler()
    {

      @Override
      public void onRefreshBegin(PtrFrameLayout frame)
      {
        updateData();
      }

      @Override
      public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header)
      {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
      }
    });
    // the following are default settings
    mPtrFrame.setResistance(1.7f);
    mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
    mPtrFrame.setDurationToClose(200);
    mPtrFrame.setDurationToCloseHeader(1000);
    // default is false
    mPtrFrame.setPullToRefresh(false);
    // default is true
    mPtrFrame.setKeepHeaderWhenRefresh(true);
    mPtrFrame.postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        // mPtrFrame.autoRefresh();
      }
    }, 100);
    // updateData();
    setupViews(mPtrFrame);
  }

  protected void setupViews(final PtrClassicFrameLayout ptrFrame)
  {

  }

  protected void updateData()
  {

    mPtrFrame.postDelayed(new Runnable()
    {
      @Override
      public void run()
      {
        mPtrFrame.refreshComplete();
      }
    }, 0);
  }

  public void onClick3(View v)
  {
    this.finish();
    this.overridePendingTransition(R.anim.activity_move_in, R.anim.activity_move_out);

  }

}
