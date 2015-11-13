package cn.utils.tools;

import android.app.Activity;
import android.util.DisplayMetrics;

public class BaseTools
{

  /** 获取屏幕的宽度. */
  public static final int getWindowsWidth(Activity activity)
  {
    DisplayMetrics dm = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
    return dm.widthPixels;
  }
}
