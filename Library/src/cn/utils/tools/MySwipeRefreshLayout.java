package cn.utils.tools;

import java.lang.reflect.Field;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;

public class MySwipeRefreshLayout extends SwipeRefreshLayout
{
	// SwipeRefreshLayout 的下拉刷新距离-增加
	public MySwipeRefreshLayout(Context context)
	{
		super(context);
		ViewTreeObserver vto = this.getViewTreeObserver();
		final ViewTreeObserver obs = this.getViewTreeObserver();
		final ViewParent vp = this.getParent();
		vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
		{
			@Override
			public void onGlobalLayout()
			{

				final DisplayMetrics metrics = getResources().getDisplayMetrics();
				Float mDistanceToTriggerSync = Math.min(((View) vp.getParent()).getHeight() * 0.6f, 500 * metrics.density);

				try
				{
					Field field = SwipeRefreshLayout.class.getDeclaredField("mDistanceToTriggerSync");
					field.setAccessible(true);
					field.setFloat(this, mDistanceToTriggerSync);
				} catch (Exception e)
				{
					e.printStackTrace();
				}

				obs.removeOnGlobalLayoutListener(this);
			}
		});
	}

}
