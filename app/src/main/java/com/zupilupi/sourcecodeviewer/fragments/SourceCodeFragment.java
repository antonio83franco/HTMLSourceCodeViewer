package com.zupilupi.sourcecodeviewer.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.zupilupi.sourcecodeviewer.MainActivity;
import com.zupilupi.sourcecodeviewer.R;
import com.zupilupi.sourcecodeviewer.fragments.utils.ChromeClient;
import com.zupilupi.sourcecodeviewer.fragments.utils.JsInterface;
import com.zupilupi.sourcecodeviewer.fragments.utils.WebClient;
import com.zupilupi.sourcecodeviewer.fragments.utils.WebUtils;

public class SourceCodeFragment extends WebViewFragment{

	public Button find;
	public EditText text;
	public String htmlResult = "";
	public String escapedHtmlResult = "";
	public String enteredUrl = "";
	
	public SourceCodeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.rootView = inflater.inflate(R.layout.fragment_source_code,
				container, false);
		
		initView();
		return rootView;
	}
	
	public void initView() {
		MainActivity mainActivity = (MainActivity)this.getActivity();
		
		this.enteredUrl = mainActivity.enteredUrl;
		this.htmlResult = mainActivity.htmlResult;
		
        this.find = (Button) rootView.findViewById(R.id.button_find);
        this.find.setOnClickListener(buttonSearchOnClickListener);        
        this.text = (EditText) rootView.findViewById(R.id.field_find);
        
		webView = (WebView) rootView.findViewById(R.id.source_code);		
		webView.loadUrl("file:///android_asset/html_source.html");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebClient(this));
		webView.setWebChromeClient(new ChromeClient(mainActivity));
		webView.addJavascriptInterface(new JsInterface(this), "Android");
		
		this.escapedHtmlResult = mainActivity.escapedHtmlResult;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d("SourceCodeFragment", "onResume");
		this.webWiewReady = false;
		initView();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.d("SourceCodeFragment", "onDestroy");
		this.webWiewReady = false;
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d("SourceCodeFragment", "onPause");
		this.webWiewReady = false;
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.d("SourceCodeFragment", "onStop");
		this.webWiewReady = false;
	}

	public void showEmptySourceOnView() {
		webView.loadUrl("javascript:setTimeout(function(){ showEmptySource('"+WebUtils.escapeForJs(this.enteredUrl)+"'); }, 500)");
	}
	
	public void showSourceOnView() {
		Log.d("ESCAPED HTML ******************************************", this.escapedHtmlResult);
		webView.loadUrl("javascript:setTimeout(function(){ setHtmlSource('"+this.escapedHtmlResult+"'); }, 500)");
	}
	
	public void showFindBar() {
		this.text.setVisibility(View.VISIBLE);
		this.find.setVisibility(View.VISIBLE);
	}
	
	Button.OnClickListener buttonSearchOnClickListener = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			String findMe = text.getText().toString();
			if (!"".equalsIgnoreCase(findMe)) {
				find.setOnClickListener(buttonSearchOnClickListener);
				webView.loadUrl("javascript:findString('" + WebUtils.escapeForJs(findMe) + "')");
			}
		}
	};
}
