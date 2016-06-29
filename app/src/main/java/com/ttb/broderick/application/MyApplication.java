package com.ttb.broderick.application;

import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Kevin on 16/6/29.
 */
public class MyApplication extends MultiDexApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		LeakCanary.install(this);
	}
}
