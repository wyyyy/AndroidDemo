package cn.commonhelp;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import cn.commonhelp.ddlistview.DropDownListView;
import cn.commonhelp.ddlistview.DropDownListView.OnDropDownListener;
import cn.commonhelp.utils.ToastUtils;

public class ExampleFragment extends Fragment
{

	private LinkedList<String> listItems = null;
	private DropDownListView listView = null;
	private ArrayAdapter<String> adapter;
	private String[] mStrings =
	{ "Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee", "Ffffff", "Gggggg",
			"Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm",
			"Nnnnnn", };
	public static final int MORE_DATA_MAX_COUNT = 3;
	public int moreDataCount = 0;
	public Context context;

	public ExampleFragment(Context applicationContext)
	{
		// TODO Auto-generated constructor stub
		context = applicationContext;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		System.out.println("ExampleFragment--onCreate");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		System.out.println("ExampleFragment--onCreateView");
		View view = inflater.inflate(R.layout.fragment_layout_a, container,
				false);
		//

		listView = (DropDownListView) view.findViewById(R.id.list_view);

		// set drop down listener
		listView.setOnDropDownListener(new OnDropDownListener()
		{

			@Override
			public void onDropDown()
			{
				new GetDataTask(true).execute();
			}
		});

		// set on bottom listener
		listView.setOnBottomListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				new GetDataTask(false).execute();
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				ToastUtils.show(context, R.string.drop_down_tip);
			}
		});
		// listView.setShowFooterWhenNoMore(true);
		listItems = new LinkedList<String>();
		listItems.addAll(Arrays.asList(mStrings));
		adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, listItems);
		listView.setAdapter(adapter);

		//

		return view;

	}

	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("ExampleFragment--onPause");
	}

	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("ExampleFragment--onResume");
	}

	@Override
	public void onStop()
	{
		// TODO Auto-generated method stub
		super.onStop();
		System.out.println("ExampleFragment--onStop");
	}

	//
	private class GetDataTask extends AsyncTask<Void, Void, String[]>
	{

		private boolean isDropDown;

		public GetDataTask(boolean isDropDown)
		{
			this.isDropDown = isDropDown;
		}

		@Override
		protected String[] doInBackground(Void... params)
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				;
			}
			return mStrings;
		}

		@SuppressLint("SimpleDateFormat") @Override
		protected void onPostExecute(String[] result)
		{

			if (isDropDown)
			{
				listItems.addFirst("Added after drop down");
				adapter.notifyDataSetChanged();

				// should call onDropDownComplete function of DropDownListView
				// at end of drop down complete.
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"MM-dd HH:mm:ss");
				listView.onDropDownComplete(getString(R.string.update_at)
						+ dateFormat.format(new Date()));
			} else
			{
				moreDataCount++;
				listItems.add("Added after on bottom");
				adapter.notifyDataSetChanged();

				if (moreDataCount >= MORE_DATA_MAX_COUNT)
				{
					listView.setHasMore(false);
				}

				// should call onBottomComplete function of DropDownListView at
				// end of on bottom complete.
				listView.onBottomComplete();
			}

			super.onPostExecute(result);
		}
	}

	//
}