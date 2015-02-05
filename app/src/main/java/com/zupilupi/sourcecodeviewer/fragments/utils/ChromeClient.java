package com.zupilupi.sourcecodeviewer.fragments.utils;

import android.webkit.WebChromeClient;

import com.zupilupi.sourcecodeviewer.MainActivity;

public class ChromeClient extends WebChromeClient{
	
    private final String TAG = "EvChromeClient";
    private MainActivity currentActivity;
    
    public ChromeClient(MainActivity webViewActivity) {
    	this.currentActivity = webViewActivity;
    }
}
