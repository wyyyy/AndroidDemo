package cn.commonhelp.http_ansyn.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import cn.commonhelp.R;
import cn.commonhelp.http_ansyn.utils.Network;

public class BaseActivity extends Activity
{

	public Map<String, String> map;

	public static List<Activity> activityList = new ArrayList<Activity>();
	public String tag = this.getClass().getSimpleName(); // tag 用于测试log用
	public Context context;

	public static boolean isChangeLangage = true;

	protected ListFragment mFrag;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// 初始化
		if (activityList == null)
			activityList = new ArrayList<Activity>();
		activityList.add(this);
		context = this;

		this.setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);

		Network.checkNetWork(getApplicationContext());

	}

	public void onCreate(Bundle savedInstanceState, boolean isCheckNetwork)
	{
		super.onCreate(savedInstanceState);
		/*
		 * if (!(MainCommon.display.widthPixels > 0 &&
		 * MainCommon.display.heightPixels > 0)) { DisplayMetrics dm = new
		 * DisplayMetrics();
		 * getWindowManager().getDefaultDisplay().getMetrics(dm); //
		 * 获得手机的宽带和高度像素单位为px MainCommon.display.widthPixels = dm.widthPixels;
		 * MainCommon.display.heightPixels = dm.heightPixels; }
		 */

		// 初始化
		if (activityList == null)
			activityList = new ArrayList<Activity>();
		activityList.add(this);
		context = this;

		this.setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
		if (isCheckNetwork)
			Network.checkNetWork(getApplicationContext());
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		try
		{
			if (activityList != null && activityList.size() > 0
					&& activityList.contains(this))
				activityList.remove(this);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 退出程序
	 */
	public static void exit()
	{
		isChangeLangage = true;
		for (int i = 0; i < activityList.size(); i++)
		{
			if (null != activityList.get(i))
			{
				activityList.get(i).finish();
			}
		}
		activityList = null;
	}

	public int sendType = 2;

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null)
		{

			// App.area_name = savedInstanceState.getString("App.area_name");
			// App.area_num = savedInstanceState.getString("App.area_num");
			// App.latitude = savedInstanceState.getDouble("App.latitude",
			// App.latitude);
			// App.listAdvs = (ArrayList<Adv>)
			// savedInstanceState.getSerializable("App.listAdvs");
			// App.listAreas = (ArrayList<Area>)
			// savedInstanceState.getSerializable("App.listAreas");
			// App.listMenus = (ArrayList<Menu>)
			// savedInstanceState.getSerializable("App.listMenus");
			// App.longitude = savedInstanceState.getDouble("App.longitude",
			// App.longitude);
			// App.network_name =
			// savedInstanceState.getString("App.network_name");
			// App.nonelatitude =
			// savedInstanceState.getDouble("App.nonelatitude",
			// App.nonelatitude);
			// App.nonelongitude =
			// savedInstanceState.getDouble("App.nonelongitude",
			// App.nonelongitude);
			// MainCommon.display.heightPixels =
			// savedInstanceState.getInt("MainCommon.display.heightPixels");
			// MainCommon.display.widthPixels =
			// savedInstanceState.getInt("MainCommon.display.widthPixels");
			// // App.user = (User)
			// savedInstanceState.getSerializable("App.user");
			// // if(App.user == null) App.user = new User();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		// outState.putString("App.area_name", App.area_name);
		// outState.putString("App.area_num", App.area_num);
		// outState.putDouble("App.latitude", App.latitude);
		// outState.putSerializable("App.listAdvs", App.listAdvs);
		// outState.putSerializable("App.listAreas", App.listAreas);
		// outState.putSerializable("App.listMenus", App.listMenus);
		// outState.putDouble("App.longitude", App.longitude);
		// outState.putString("App.network_name", App.network_name);
		// outState.putDouble("App.nonelatitude", App.nonelatitude);
		// outState.putDouble("App.nonelongitude", App.nonelongitude);
		// outState.putSerializable("App.user", App.user);
		// outState.putInt("MainCommon.display.heightPixels",
		// MainCommon.display.heightPixels);
		// outState.putInt("MainCommon.display.widthPixels",
		// MainCommon.display.widthPixels);
		// // outState.putSerializable("App.mApp", App.mApp);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		Network.checkNetWork(getApplicationContext());
	}

	@SuppressLint("InflateParams")
	@Override
	public Dialog onCreateDialog(int id)
	{
		// String show_msg = "";
		int show_title = 0;
		LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(
				LAYOUT_INFLATER_SERVICE);
		View dv = li.inflate(R.layout.dialog_progress, null);
		switch (id)
		{
		case 1:
			// TextView loading = (TextView)dv.findViewById(R.id.loading);
			// loading.setText(R.string.progress_loading);
			break;
		case 2:
			// TextView loading2 = (TextView)dv.findViewById(R.id.loading);
			// loading2.setText(R.string.progress_login);
			break;
		case 3:
			// TextView loading3 = (TextView)dv.findViewById(R.id.loading);
			// loading3.setText(R.string.progress_comment);
			break;
		case 4:
			// show_msg = "获取数据失败";
			show_title = R.string.dialog_title_getDataFail;
			break;
		case 5:
			// show_msg = "网络超时，服务器断开或者请检查网络连接是否正常";
			show_title = R.string.dialog_title_newwork_request_timeout;
			break;
		case 6:
			// show_msg = "暂无数据";
			show_title = R.string.dialog_title_nowData;
			break;
		case 7:
			// show_msg = "获取用户信息失败";
			show_title = R.string.dialog_title_getUserInfoDataFail;
			break;
		case 8:
			// show_msg = "发送数据失败";
			break;
		default:
			return null;
		}
		switch (id)
		{
		case 1:
		case 2:
		case 3:
			return new AlertDialog.Builder(this).setView(dv).create();
		default:
			return new AlertDialog.Builder(this)
					.setMessage(show_title)
					.setPositiveButton(R.string.confirm,
							new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog,
										int which)
								{
									dialog.dismiss();
								}
							}).create();
		}
	}
}