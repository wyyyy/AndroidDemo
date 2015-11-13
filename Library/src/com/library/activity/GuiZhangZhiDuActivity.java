package com.library.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

public class GuiZhangZhiDuActivity extends FragmentActivity
{

  /** 请求CODE */
  public final static int CHANNELREQUEST = 1;
  /** 调整返回的RESULTCODE */
  public final static int CHANNELRESULT = 10;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_gui_zhang_zhidu);

  }

  private long mExitTime;

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    if (keyCode == KeyEvent.KEYCODE_BACK)
    {

      if ((System.currentTimeMillis() - mExitTime) > 2000)
      {
        Toast.makeText(this, "在按一次退出", Toast.LENGTH_SHORT).show();
        mExitTime = System.currentTimeMillis();
      } else
      {
        finish();
      }
      return true;
    }
    // 拦截MENU按钮点击事件，让他无任何操作
    if (keyCode == KeyEvent.KEYCODE_MENU)
    {
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    switch (requestCode)
    {
    case CHANNELREQUEST:
      if (resultCode == CHANNELRESULT)
      {
      }
      break;

    default:
      break;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
}