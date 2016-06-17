package com.ttb.broderick.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ttb.broderick.MainActivity;
import com.ttb.broderick.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kevin on 16/6/17.
 */
public class TwoFragement extends Fragment {
	@Bind(R.id.complete)
	FloatingActionButton complete;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.two, null);
		ButterKnife.bind(this, v);
		return v;
	}

	public static TwoFragement newInstance() {

		Bundle args = new Bundle();

		TwoFragement fragment = new TwoFragement();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}

	@OnClick(R.id.complete)
	public void onClick() {
		startActivity(new Intent(this.getContext(), MainActivity.class));
		getActivity().finish();
	}
}
