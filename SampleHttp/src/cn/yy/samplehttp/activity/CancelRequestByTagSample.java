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

import android.util.Log;
import cn.commonhelp.http.help.AsyncHttpClient;
import cn.commonhelp.http.help.AsyncHttpResponseHandler;
import cn.commonhelp.http.help.RequestHandle;
import cn.commonhelp.http.help.ResponseHandlerInterface;
import cn.yy.sample.R;

public class CancelRequestByTagSample extends ThreadingTimeoutSample {

    private static final String LOG_TAG = "CancelRequestByTagSample";
    private static final Integer REQUEST_TAG = 132435;

    @Override
    public int getSampleTitle() {
        return R.string.title_cancel_tag;
    }

    @Override
    public void onCancelButtonPressed() {
        Log.d(LOG_TAG, "Canceling requests by TAG: " + REQUEST_TAG);
        getAsyncHttpClient().cancelRequestsByTAG(REQUEST_TAG, false);
    }

    @Override
    public ResponseHandlerInterface getResponseHandler() {
        return new AsyncHttpResponseHandler() {

            private final int id = counter++;

            @Override
            public void onStart() {
                setStatus(id, "TAG:" + getTag() + ", START");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                setStatus(id, "SUCCESS");
            }

            @Override
            public void onFinish() {
                setStatus(id, "FINISH");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                setStatus(id, "FAILURE");
            }

            @Override
            public void onCancel() {
                setStatus(id, "CANCEL");
            }
        };
    }

    @Override
    public RequestHandle executeSample(AsyncHttpClient client, String URL, Header[] headers, HttpEntity entity, ResponseHandlerInterface responseHandler) {
        return client.get(this, URL, headers, null, responseHandler).setTag(REQUEST_TAG);
    }
}
