package com.zupilupi.sourcecodeviewer;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

//import com.sbstrm.appirater.Appirater;

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
		new LongOperation().execute(activity);
	}

	private class LongOperation extends AsyncTask<MainActivity, Void, MainActivity> {

		@Override
		protected MainActivity doInBackground(MainActivity... params) {

			try {
				Thread.sleep((long) MyApp.this.getResources().getInteger(R.integer.appirator_delay_before_launch));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return params[0];
		}

		@Override
		protected void onPostExecute(final MainActivity act) {
//			act.runOnUiThread(new Runnable() {
//				public void run() {
//					Appirater.appLaunched(act);
//				}
//			});
		}

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}

}
