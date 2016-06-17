package com.ttb.broderick.fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttb.broderick.R;

/**
 * Created by Kevin on 16/6/17.
 */
public class OneFragement extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v =inflater.inflate(R.layout.one,null);
		return v;
	}

	public static OneFragement newInstance() {
		
		Bundle args = new Bundle();
		
		OneFragement fragment = new OneFragement();
		fragment.setArguments(args);
		return fragment;
	}
}
