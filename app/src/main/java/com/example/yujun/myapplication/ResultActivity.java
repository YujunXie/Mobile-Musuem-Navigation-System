package com.example.yujun.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class ResultActivity extends Activity {

    private WebView mResultWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Bundle extras = getIntent().getExtras();

        mResultWebView = (WebView) findViewById(R.id.result_webview);

        if (null != extras) {
            mResultWebView.loadUrl(extras.getString("result"));
            mResultWebView.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    // TODO Auto-generated method stub
                    //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                    view.loadUrl(url);
                    return true;
                }
            });
        }

	}
}
