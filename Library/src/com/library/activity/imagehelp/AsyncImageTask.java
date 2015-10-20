package com.library.activity.imagehelp;

import java.io.File;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ImageView;

public final  class AsyncImageTask extends AsyncTask<String, Integer, Uri>
{
	private ContactService service;
	private ImageView iv_header;
	private File cache;

	public AsyncImageTask(ContactService _service, ImageView image, File _cache)
	{
		this.service = _service;
		this.iv_header = image;
		cache = _cache;
	}

	// 后台运行的子线程子线程
	@Override
	protected Uri doInBackground(String... params)
	{
		try
		{
			return service.getImageURI(params[0], cache);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// 这个放在在ui线程中执行
	@Override
	protected void onPostExecute(Uri result)
	{
		super.onPostExecute(result);
		System.out.println("Poost ok");
		// 完成图片的绑定
		if (iv_header != null && result != null)
		{
			iv_header.setImageURI(result);
		}
	}

}
