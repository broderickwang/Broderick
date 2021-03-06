package com.ttb.broderick.fragement;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.StatedFragment;
import com.ttb.broderick.R;
import com.ttb.broderick.activity.FreshActivity;
import com.ttb.broderick.bean.Person;

/**
 * Created by Kevin on 16/6/16.
 */
public class TitleFragement extends StatedFragment {
	final static String TAG = "TitleFragement";
	EditText name;
	@Nullable
	@Override
	public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.titlefragement, null);
		name = (EditText) view.findViewById(R.id.name);
		ImageButton idtitleleftbtn = (ImageButton) view.findViewById(R.id.id_title_left_btn);
		idtitleleftbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(getActivity(), "i am a imagebutton in titlefragement", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getContext(), FreshActivity.class);
				Person p = new Person("a",2,true);
				intent.putExtra("person",p);

				startActivity(intent,
						ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
				getActivity().finish();
				}
		});

		return view;
	}

	public static TitleFragement newInstance() {
		
		Bundle args = new Bundle();
		
		TitleFragement fragment = new TitleFragement();
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	protected void onSaveState(Bundle outState) {
		super.onSaveState(outState);
		outState.putCharSequence("name",name.getText().toString());
		Log.i(TAG, "onSaveState ...........");
	}

	@Override
	protected void onRestoreState(Bundle savedInstanceState) {
		super.onRestoreState(savedInstanceState);
		name.setText(savedInstanceState.getCharSequence("name"));
		Log.i(TAG, "onRestoreState .........");
	}
}
