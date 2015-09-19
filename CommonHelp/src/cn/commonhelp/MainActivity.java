package cn.commonhelp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import cn.commonhelp.ddlistview.DropDownListView;

public class MainActivity extends Activity
{
	String[] mStrings =
	{ "Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee", "Ffffff", "Gggggg",
			"Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm",
			"Nnnnnn", };
	LinkedList<String> listItems = null;
	ArrayAdapter<String> adapter;
	DropDownListView listView = null;

	int MORE_DATA_MAX_COUNT = 3;
	int moreDataCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setDefaultFragment();
	}

	private void setDefaultFragment()
	{

		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		ExampleFragment fragment = new ExampleFragment(getApplicationContext());

		fragmentTransaction.add(R.id.id_content, fragment);
		fragmentTransaction.commit();

		/*
		 * listView = (DropDownListView)findViewById(R.id.list_view);
		 * 
		 * // set drop down listener listView.setOnDropDownListener(new
		 * OnDropDownListener() {
		 * 
		 * @Override public void onDropDown() { new GetDataTask(true).execute();
		 * } });
		 * 
		 * // set on bottom listener listView.setOnBottomListener(new
		 * OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { new
		 * GetDataTask(false).execute(); } });
		 * listView.setOnItemClickListener(new OnItemClickListener() {
		 * 
		 * @Override public void onItemClick(AdapterView<?> parent, View view,
		 * int position, long id) { ToastUtils .show(getApplicationContext(),
		 * R.string.drop_down_tip); } }); //
		 * listView.setShowFooterWhenNoMore(true); listItems = new
		 * LinkedList<String>(); listItems.addAll(Arrays.asList(mStrings));
		 * adapter = new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_1, listItems);
		 * listView.setAdapter(adapter);
		 */
	}

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

		@Override
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
}
