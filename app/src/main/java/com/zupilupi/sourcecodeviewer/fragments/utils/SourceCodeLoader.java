package com.zupilupi.sourcecodeviewer.fragments.utils;

import android.os.AsyncTask;

import com.zupilupi.sourcecodeviewer.MyApp;
import com.zupilupi.sourcecodeviewer.TypeUrlActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SourceCodeLoader extends AsyncTask<String, Integer, String> {

	private TypeUrlActivity myFragment;
	private String encoding = "UTF-8";

	public SourceCodeLoader(TypeUrlActivity webViewActivity) {
		this.myFragment = webViewActivity;
	}

	@Override
	protected String doInBackground(String... url) {

		StringBuilder sb = new StringBuilder();
		String result = "";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url[0]); //serve http
			HttpResponse httpResponse;
			httpResponse = httpClient.execute(httpPost);

			if (httpResponse != null) {
				// Get the data in the entity
				InputStream in = httpResponse.getEntity().getContent();

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(in, this.encoding));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				in.close();
			}
			result = sb.toString();
		} catch (Throwable e) {
			e.printStackTrace();
			result = MyApp.UKHOST;
		}
		return result;
	}

	protected void onPostExecute(String content) {
		this.myFragment.showSourceCode(content);
	}
}
