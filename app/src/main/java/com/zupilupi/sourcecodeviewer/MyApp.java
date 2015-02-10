package com.zupilupi.sourcecodeviewer;

import android.app.Application;
import android.util.Log;

import com.sbstrm.appirater.Appirater;

public class MyApp extends Application {

	public static String CACHE = "MYCACHE";
	public static String LAST_URL = "LAST_URL";
	public String HTML_CODE = "HTML_CODE";
	public String HTML_CODE_ESCAPED = "HTML_CODE_ESCAPED";
	public static String RETRY = "RETRY";
	public static String URL = "URL";
	public static String JS = "JS";
	public static String CSS = "CSS";
	public static String UKHOST = "UKHOST";

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("MyApp", "onCreate");
	}

	public void appLaunched(MainActivity activity) {
        //init App Rater
        Appirater.appLaunched(activity);
	}

}
