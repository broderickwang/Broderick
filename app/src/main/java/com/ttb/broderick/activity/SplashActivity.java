package com.ttb.broderick.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ttb.broderick.MainActivity;

public class SplashActivity extends AppCompatActivity {

	private SharedPreferences.Editor editor;
	private SharedPreferences preferences;
	boolean fristload;
	/**
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		checkLoad();
		if(fristload){
			editor.putBoolean("fristload", false);
			editor.commit();
			startActivity(new Intent(SplashActivity.this, Welcome.class));
		}else{
			editor.putBoolean("fristload", false);
			editor.commit();
			startActivity(new Intent(SplashActivity.this, MainActivity.class));
		}

		finish();
	}
	private void checkLoad(){
		preferences = this.getSharedPreferences("check",MODE_PRIVATE);
		editor = preferences.edit();
		fristload=preferences.getBoolean("fristload", true);
	}

}
