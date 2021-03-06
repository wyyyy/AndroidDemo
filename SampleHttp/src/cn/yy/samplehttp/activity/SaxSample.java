/*
    Android Asynchronous Http Client Sample
    Copyright (c) 2014 Marek Sebera <marek.sebera@gmail.com>
    https://loopj.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */

package cn.yy.samplehttp.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import cn.commonhelp.http.help.AsyncHttpClient;
import cn.commonhelp.http.help.RequestHandle;
import cn.commonhelp.http.help.ResponseHandlerInterface;
import cn.commonhelp.http.help.SaxAsyncHttpResponseHandler;
import cn.yy.sample.R;

public class SaxSample extends SampleParentActivity
{

	private static final String LOG_TAG = "SaxSample";
	private final SaxAsyncHttpResponseHandler saxAsyncHttpResponseHandler = new SaxAsyncHttpResponseHandler<SAXTreeStructure>(
			new SAXTreeStructure())
	{
		@Override
		public void onStart()
		{
			clearOutputs();
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				SAXTreeStructure saxTreeStructure)
		{
			debugStatusCode(LOG_TAG, statusCode);
			debugHeaders(LOG_TAG, headers);
			debugHandler(saxTreeStructure);
		}

		@Override
		public void onFailure(int statusCode, Header[] headers,
				SAXTreeStructure saxTreeStructure)
		{
			debugStatusCode(LOG_TAG, statusCode);
			debugHeaders(LOG_TAG, headers);
			debugHandler(saxTreeStructure);
		}

		private void debugHandler(SAXTreeStructure handler)
		{
			for (Tuple t : handler.responseViews)
			{
				addView(getColoredView(t.color, t.text));
			}
		}
	};

	@Override
	public ResponseHandlerInterface getResponseHandler()
	{
		return saxAsyncHttpResponseHandler;
	}

	@Override
	public String getDefaultURL()
	{
		return "http://bin-iin.com/sitemap.xml";
	}

	@Override
	public boolean isRequestHeadersAllowed()
	{
		return false;
	}

	@Override
	public boolean isRequestBodyAllowed()
	{
		return false;
	}

	@Override
	public int getSampleTitle()
	{
		return R.string.title_sax_example;
	}

	@Override
	public RequestHandle executeSample(AsyncHttpClient client, String URL,
			Header[] headers, HttpEntity entity,
			ResponseHandlerInterface responseHandler)
	{
		return client.get(this, URL, headers, null, responseHandler);
	}

	private class Tuple
	{
		public final Integer color;
		public final String text;

		public Tuple(int _color, String _text)
		{
			this.color = _color;
			this.text = _text;
		}
	}

	private class SAXTreeStructure extends DefaultHandler
	{

		public final List<Tuple> responseViews = new ArrayList<Tuple>();

		@Override
		public void startElement(String namespaceURI, String localName,
				String rawName, Attributes atts)
		{
			responseViews
					.add(new Tuple(LIGHTBLUE, "Start Element: " + rawName));
		}

		@Override
		public void endElement(String namespaceURI, String localName,
				String rawName)
		{
			responseViews
					.add(new Tuple(LIGHTBLUE, "End Element  : " + rawName));
		}

		@Override
		public void characters(char[] data, int off, int length)
		{
			if (length > 0 && data[0] != '\n')
			{
				responseViews.add(new Tuple(LIGHTGREEN, "Characters  :  "
						+ new String(data, off, length)));
			}
		}
	}
}
