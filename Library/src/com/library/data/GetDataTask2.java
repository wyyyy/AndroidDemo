package com.library.data;


import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.os.AsyncTask;



	class GetDataTask2 extends AsyncTask<Void, Void, String[]>
	{

		private String[] mStrings =
			{ "Aaaaaa", "Bbbbbb", "Cccccc", "Dddddd", "Eeeeee", "Ffffff", "Gggggg",
					"Hhhhhh", "Iiiiii", "Jjjjjj", "Kkkkkk", "Llllll", "Mmmmmm",
					"Nnnnnn", };
		private boolean isDropDown;

		public GetDataTask2(boolean isDropDown)
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

		@SuppressLint("SimpleDateFormat") @SuppressWarnings("unused")
		@Override
		protected void onPostExecute(String[] result)
		{

			if (isDropDown)
			{
				//listItems.addFirst("Added after drop down");
				///adapter.notifyDataSetChanged();

				// should call onDropDownComplete function of DropDownListView
				// at end of drop down complete.
			SimpleDateFormat dateFormat = new SimpleDateFormat(
						"MM-dd HH:mm:ss");
				////listView.onDropDownComplete(getString(R.string.update_at)
				//		+ dateFormat.format(new Date()));
			} else
			{
				//moreDataCount++;
				//listItems.add("Added after on bottom");
				//adapter.notifyDataSetChanged();

				//if (moreDataCount >= MORE_DATA_MAX_COUNT)
				//{
				//	listView.setHasMore(false);
				//}

				// should call onBottomComplete function of DropDownListView at
				// end of on bottom complete.
				//listView.onBottomComplete();
			}

			super.onPostExecute(result);
		}
	}

