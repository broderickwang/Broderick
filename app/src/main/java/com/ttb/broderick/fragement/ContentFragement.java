package com.ttb.broderick.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ttb.broderick.R;
import com.ttb.broderick.activity.RecycleViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Kevin on 16/6/16.
 */
public class ContentFragement extends Fragment {
	Bundle saveState;
	EditText contentname;
	@Bind(R.id.jump2)
	Button jump2;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.contentfragement, null);
		contentname = (EditText) view.findViewById(R.id.contentname);
		//restore state here
		if (!restoreStateFromArguments()) {
			//First time running ,Initialize something here
		}
		ButterKnife.bind(this, view);
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveStateToArguments();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		saveStateToArguments();
	}

	private void saveStateToArguments() {
		saveState = saveState();
		if (saveState != null) {
			Bundle b = getArguments();
			b.putBundle("internalSavedViewState8954201239547", saveState);
		}
	}

	public static ContentFragement newInstance() {

		Bundle args = new Bundle();

		ContentFragement fragment = new ContentFragement();
		fragment.setArguments(args);
		return fragment;
	}

	private boolean restoreStateFromArguments() {
		Bundle b = getArguments();
		saveState = b.getBundle("internalSavedViewState8954201239547");
		if (saveState != null) {
			restoreState();
			return true;
		}
		return false;
	}

	//////////////////////////
	//取出状态数据
	/////////////////////////
	private void restoreState() {
		if (saveState != null) {
			contentname.setText(saveState.getCharSequence("contentname"));
		}
	}

	//////////////////////////
	//保存状态数据
	/////////////////////////
	private Bundle saveState() {
		Bundle b = new Bundle();
		b.putCharSequence("contentname", contentname.getText().toString());
		return b;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		ButterKnife.unbind(this);
	}

	@OnClick(R.id.jump2)
	public void onClick() {
		startActivity(new Intent(this.getContext(), RecycleViewActivity.class));
		this.getActivity().finish();
	}
}
