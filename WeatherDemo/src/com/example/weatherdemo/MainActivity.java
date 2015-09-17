package com.example.weatherdemo;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.entity.Root;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends Activity implements OnClickListener {
	private Button mSendReqHandler = null;// ��������İ�ť
	private Button mSendRAsync = null;// ��������İ�ť
	private Button mSendRAsyncTask = null;// ��������İ�ť
	private GridView gridview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mSendReqHandler = (Button) findViewById(R.id.btnHandle);
		mSendReqHandler.setOnClickListener(this);
		mSendRAsync = (Button) findViewById(R.id.btnAsync);
		mSendRAsync.setOnClickListener(this);
		mSendRAsyncTask = (Button) findViewById(R.id.btnAsyncTask);
		mSendRAsyncTask.setOnClickListener(this);
		// txtView = (TextView) findViewById(R.id.txtInfo);
		// txtTime = (TextView) findViewById(R.id.txtTime);
		// txtTime.setText(System.currentTimeMillis() / 10000L + "");
		gridview = (GridView) findViewById(R.id.gridview);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String result = (String) msg.getData().get("value");
			Gson gson;
			Root status = new Root();
			try {
				gson = new Gson();
				java.lang.reflect.Type type = new TypeToken<Root>() {
				}.getType();
				status = gson.fromJson(result, type);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//
			MyAdapter adapter = new MyAdapter(getApplicationContext(), status);

			gridview.setAdapter(adapter);
		}
	};
	Runnable runnable = new Runnable() {
		@Override
		public void run() {
			// TODO: http request.
			Message msg = new Message();
			Bundle data = new Bundle();

			String st = HttpUtils.getRequest();
			data.putString("value", st);
			msg.setData(data);
			handler.sendMessage(msg);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnHandle:
			new Thread(runnable).start();
			break;
		// �������ݿ�
		case R.id.btnAsync:
			AsyncHttp2 asyncHttp2 = new AsyncHttp2();
			asyncHttp2.get(null, null, null);
			Toast.makeText(getApplicationContext(), asyncHttp2.sb.toString(),
					Toast.LENGTH_LONG).show();
			break;
		// �������ݿ�
		case R.id.btnAsyncTask:
			Map<String, String> map = new HashMap<String, String>();
			map.put("sa", "dd");
			AsyncTaskUtils mTask = new AsyncTaskUtils(getApplicationContext());
			mTask.execute(map);
			break;
		// �������ݿ�

		default:
			break;
		}

	}

}
