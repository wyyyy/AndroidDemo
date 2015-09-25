package cn.commonhelp.http_ansyn.app;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import cn.commonhelp.R;
import cn.commonhelp.http_ansyn.http.AnsynHttpRequest;
import cn.commonhelp.http_ansyn.http.MainCommon;
import cn.commonhelp.http_ansyn.http.ObserverCallBack;
import cn.commonhelp.http_ansyn.utils.MapData;

public class MainActivity extends BaseActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// by get
		map = new HashMap<String, String>();
		// map.put("method", "adv_list");
		// map.put("regionid", "320200");
		AnsynHttpRequest.requestByGet(context, callbackData,
				MainCommon.http.http_adv, map, true, true);

		// by post
		map = new HashMap<String, String>();
		map = new MapData().addData(map, context);
		AnsynHttpRequest.requestByPost(context, MainCommon.http.http_test_bb,
				callbackData, MainCommon.http.http_area, map, false, false);

		// http初始化
		map = new HashMap<String, String>();
		// map.put("method", "businesscategory_list");
		AnsynHttpRequest.requestByPost(context, MainCommon.http.http_test_cc,
				callbackData, MainCommon.http.http_area, map, false, false);

	}

	/**
	 * 异步回调回来并处理数据
	 */
	private ObserverCallBack callbackData = new ObserverCallBack()
	{
		@Override
		public void back(String data, int url)
		{
			switch (url)
			{
			case MainCommon.http.http_area: // 进行数据解析
				if (data == null)
				{
					return;
				}
				try
				{
					// Index index = new Index();
					// index = index.convertHttpHead(context,data);
					// // App.index = index;
					// Log.i(tag, index.toString());
					Message msg = new Message();
					msg.what = 1;
					// msg.obj = index;
					mHandler.sendMessage(msg);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				break;
			default:
				Message msg = new Message();
				msg.what = url;
				mHandler.sendMessage(msg);
				break;
			}
		}
	};

	/**
	 * 处理UI线程中数据
	 */
	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch (msg.what)
			{
			case 1:
				Toast.makeText(context, "测试数据 数据编号：" + msg.what,
						Toast.LENGTH_SHORT).show();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				// setListViewHeightBasedOnChildren(mListView);
				// setListViewHeightBasedOnChildren(mListView);
				break;
			case 8:
				// Bitmap bmp = (Bitmap)msg.obj;
				// SendMsg.weixin(context, mCategory, bmp);
				break;
			default:
				Toast.makeText(context, "测试数据 数据编号：" + msg.what,
						Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};
}
