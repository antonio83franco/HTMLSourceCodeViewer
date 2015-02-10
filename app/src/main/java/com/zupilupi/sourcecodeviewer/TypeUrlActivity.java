package com.zupilupi.sourcecodeviewer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.zupilupi.sourcecodeviewer.fragments.utils.SourceCodeLoader;
import com.zupilupi.sourcecodeviewer.fragments.utils.WebUtils;

public class TypeUrlActivity extends Activity {

	ProgressDialog progress;
	String enteredUrl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_type_url);
		
		EditText textField = (EditText) findViewById(R.id.field_url);
		setLastUrl(textField);
		
		Button buttonSendRequest = (Button) findViewById(R.id.button_show_source);
		buttonSendRequest.setOnClickListener(sendRequestAct);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		EditText textField = (EditText) findViewById(R.id.field_url);
		setLastUrl(textField);
        InputMethodManager imm = (InputMethodManager)this.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(textField, 0);
	}

	private String setLastUrl(EditText textField) {
		//Get the last entered URL from cache if any
		SharedPreferences prefs = getSharedPreferences(MyApp.CACHE, Activity.MODE_PRIVATE);
		String lastUrl = prefs.getString(MyApp.LAST_URL, "");
		
	    if(!"".equalsIgnoreCase(lastUrl)) {
			textField.setText(lastUrl);
	    }
	    
	    return lastUrl;
	}
	
	public void showSourceCode(String contentFromNetwork) {
		Log.d("ZUPILUPI", contentFromNetwork);

        Intent intent = new Intent(TypeUrlActivity.this, MainActivity.class);
        ((MyApp)this.getApplication()).HTML_CODE = contentFromNetwork;
        ((MyApp)this.getApplication()).HTML_CODE_ESCAPED = WebUtils.escapeForJs(contentFromNetwork);
        
        Bundle b = new Bundle();
        b.putString(MyApp.URL, this.enteredUrl);
        intent.putExtras(b);
        startActivity(intent);     
        
        if(progress != null) {
        	progress.dismiss();
        }
	}
	
	public void triggerSourceCodeLoader() {
        //start service to fetch the url's source code
		SourceCodeLoader loader = new SourceCodeLoader(this);
		loader.execute(WebUtils.checkHttpPrefix(enteredUrl));
		//calls showSourceCode when done
	}
	
	private OnClickListener sendRequestAct = new OnClickListener() {
		
		public void onClick(View v) {
			EditText textField = (EditText) findViewById(R.id.field_url);
			TypeUrlActivity.this.enteredUrl = textField.getText().toString();
			if (Patterns.WEB_URL.matcher(enteredUrl).matches()) {
				
				progress = ProgressDialog.show(TypeUrlActivity.this, "Please wait",
					    "Retrieving data from the server", true);
								
				triggerSourceCodeLoader();
				
		        //Put the URL in cache
		    	SharedPreferences prefs = getSharedPreferences(MyApp.CACHE, Activity.MODE_PRIVATE);
		    	SharedPreferences.Editor editor = prefs.edit();
		    	editor.putString(MyApp.LAST_URL, enteredUrl);
		    	editor.commit();  
		    	
				TypeUrlActivity.this.enteredUrl = enteredUrl;

			} else {
				new AlertDialog.Builder(TypeUrlActivity.this)
			    .setMessage(R.string.alert_url_invalid)
			    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // do nothing
			        }
			     })
			     .show();
			}
		}
	};

}
