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

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

import android.os.Bundle;
import cn.commonhelp.http.help.AsyncHttpClient;
import cn.commonhelp.http.help.BaseJsonHttpResponseHandler;
import cn.commonhelp.http.help.EntityJSON;
import cn.commonhelp.http.help.PersistentCookieStore;
import cn.commonhelp.http.help.RequestHandle;
import cn.commonhelp.http.help.ResponseHandlerInterface;
import cn.yy.sample.R;

public class PersistentCookiesSample extends SampleParentActivity {

	private static final String LOG_TAG = "PersistentCookiesSample";

	private CookieStore cookieStore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Use the application's context so that memory leakage doesn't occur.
		cookieStore = new PersistentCookieStore(getApplicationContext());

		// Set the new cookie store.
		getAsyncHttpClient().setCookieStore(cookieStore);

		super.onCreate(savedInstanceState);
	}

	@Override
	public int getSampleTitle() {
		return R.string.title_persistent_cookies;
	}

	@Override
	public boolean isRequestBodyAllowed() {
		return false;
	}

	@Override
	public boolean isRequestHeadersAllowed() {
		return true;
	}

	@Override
	public String getDefaultURL() {
		// The base URL for testing cookies.
		String url = PROTOCOL + "httpbin.org/cookies";

		// If the cookie store is empty, suggest a cookie.
		if (cookieStore.getCookies().isEmpty()) {
			url += "/set?time=" + System.currentTimeMillis();
		}

		return url;
	}

	@Override
	public ResponseHandlerInterface getResponseHandler() {
		return new BaseJsonHttpResponseHandler<EntityJSON>() {
			@Override
			public void onStart() {
				clearOutputs();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String rawJsonResponse, EntityJSON response) {
				debugHeaders(LOG_TAG, headers);
				debugStatusCode(LOG_TAG, statusCode);
				if (response != null) {
					debugResponse(LOG_TAG, rawJsonResponse);
				}
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					Throwable throwable, String rawJsonData,
					EntityJSON errorResponse) {
				debugHeaders(LOG_TAG, headers);
				debugStatusCode(LOG_TAG, statusCode);
				debugThrowable(LOG_TAG, throwable);
				if (errorResponse != null) {
					debugResponse(LOG_TAG, rawJsonData);
				}
			}

			@Override
			protected EntityJSON parseResponse(String rawJsonData,
					boolean isFailure) throws Throwable {
				return new ObjectMapper().readValue(
						new JsonFactory().createJsonParser(rawJsonData),
						EntityJSON.class);
			}
		};
	}

	@Override
	public RequestHandle executeSample(AsyncHttpClient client, String URL,
			Header[] headers, HttpEntity entity,
			ResponseHandlerInterface responseHandler) {
		client.setEnableRedirects(true);
		return client.get(this, URL, headers, null, responseHandler);
	}

}
