package com.zupilupi.sourcecodeviewer.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zupilupi.sourcecodeviewer.MainActivity;
import com.zupilupi.sourcecodeviewer.R;

public class EnterUrlFragment extends Fragment{

	View rootView;
	
	public EnterUrlFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_enter_url,
				container, false);
		
		Button buttonSendRequest = (Button) rootView.findViewById(R.id.button_show_source);
		buttonSendRequest.setOnClickListener(sendRequestAct);
		return rootView;
	}
		
	private OnClickListener sendRequestAct = new OnClickListener() {
		public void onClick(View v) {
			
			EditText textField = (EditText) rootView.findViewById(R.id.field_url);
			String enteredUrl = textField.getText().toString();
			if (Patterns.WEB_URL.matcher(enteredUrl).matches()) {
				((MainActivity) EnterUrlFragment.this.getActivity()).enteredUrl = enteredUrl;
				((MainActivity) EnterUrlFragment.this.getActivity()).goToPage(1);

			} else {
				new AlertDialog.Builder(EnterUrlFragment.this.getActivity())
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
