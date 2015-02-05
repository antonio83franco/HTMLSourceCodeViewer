package com.zupilupi.sourcecodeviewer.fragments.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zupilupi.sourcecodeviewer.R;

import java.util.ArrayList;

public class ResourcesArrayAdapter<T> extends ArrayAdapter<ListRow>{

	  private final Context context;
	  private final ArrayList<ListRow> values;

	  public ResourcesArrayAdapter(Context context, ArrayList<ListRow> values) {
	    super(context, R.layout.row_html_resources, values);
	    this.context = context;
	    this.values = values;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.row_html_resources, parent, false);
	    
	    TextView resType = (TextView) rowView.findViewById(R.id.res_type);
	    TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
	    TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
	    
	    resType.setText(values.get(position).getResType());
	    firstLine.setText(values.get(position).getResName());
	    secondLine.setText(values.get(position).getResUrl());

	    return rowView;
	  }
	}