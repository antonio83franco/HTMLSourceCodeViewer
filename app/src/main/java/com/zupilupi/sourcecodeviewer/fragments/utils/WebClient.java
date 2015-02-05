package com.zupilupi.sourcecodeviewer.fragments.utils;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zupilupi.sourcecodeviewer.MyApp;
import com.zupilupi.sourcecodeviewer.fragments.SourceCodeFragment;
import com.zupilupi.sourcecodeviewer.fragments.WebViewFragment;

public class WebClient extends WebViewClient{
	
    private WebViewFragment fragment;
    
    public WebClient (WebViewFragment myFragment) {
    	this.fragment = myFragment;
    }
    
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {              
        view.loadUrl(url);
        return true;
    }
    
	@Override
	public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
	    super.onReceivedError(webView, errorCode, description, failingUrl);
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		this.fragment.webWiewReady = true;
		super.onPageFinished(view, url);
		
		if(fragment instanceof SourceCodeFragment) {
			SourceCodeFragment f = (SourceCodeFragment) fragment;
			if (f.escapedHtmlResult.equals(MyApp.UKHOST)) {
				f.showEmptySourceOnView();
			} else {
				f.showSourceOnView();
			}
		}
	}

	
}
