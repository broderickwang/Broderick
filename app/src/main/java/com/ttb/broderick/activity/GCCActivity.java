package com.ttb.broderick.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.ttb.broderick.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GCCActivity extends AppCompatActivity {

	@Bind(R.id.button)
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gcc);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.button)
	public void onClick() {
		startAsyncTask();
	}
	public void startAsyncTask(){
		new AsyncTask<Void,Void,Void>(){
			@Override
			protected Void doInBackground(Void... params) {
				Log.v("家俊","doInBackground");
				SystemClock.sleep(10000);
				return null;
			}
		}.execute();
	}
}
