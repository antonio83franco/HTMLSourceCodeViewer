package com.zupilupi.sourcecodeviewer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.sbstrm.appirater.Appirater;
import com.zupilupi.sourcecodeviewer.fragments.utils.WebUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends FragmentActivity {

	private int numActiveFragments = 3;

	/*
	 * Properties used by fragments
	 */
	public String enteredUrl = "";
	public String htmlResult = "";
	public String escapedHtmlResult = "";
	public HashMap<String, ArrayList<String>> htmlResources;

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		((MyApp) this.getApplication()).appLaunched(this);

		Bundle bundle = this.getIntent().getExtras();
		this.enteredUrl = bundle.getString(MyApp.URL);
		this.htmlResult = ((MyApp)getApplication()).HTML_CODE;
		this.escapedHtmlResult = ((MyApp)getApplication()).HTML_CODE_ESCAPED;
		
		this.htmlResources = WebUtils.getHtmlResources(this.htmlResult,
				this.enteredUrl);

        //show a descriptive title on the action bar
		setTitle("HTML: "+this.enteredUrl);
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager(), numActiveFragments);

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	public void goToPage(int index) {
		mViewPager.setCurrentItem(index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.rate_app:
	    	Appirater.rateApp(this);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

}
