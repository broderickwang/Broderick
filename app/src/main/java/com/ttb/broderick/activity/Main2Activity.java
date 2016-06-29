package com.ttb.broderick.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ttb.broderick.R;
import com.ttb.broderick.dialog.MyDialog;
import com.ttb.broderick.fragement.ContentFragement;
import com.ttb.broderick.fragement.TitleFragement;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
	private final String TAG = "TTTTTAAAAGGGG";
	static final String STATE_NAME = "playerName";
	static final String STATE_PWD = "playerPwd";
	@Bind(R.id.tocustomview)
	Button tocustomview;
	@Bind(R.id.showmydialog)
	Button showmydialog;

	private TitleFragement tf;
	private ContentFragement cf;
	FragmentTransaction trans;
	FragmentManager fm;

	@Bind(R.id.name)
	EditText name;
	@Bind(R.id.pwd)
	EditText pwd;
	@Bind(R.id.rb1)
	RadioButton rb1;
	@Bind(R.id.rb2)
	RadioButton rb2;
	@Bind(R.id.rg)
	RadioGroup rg;
	@Bind(R.id.frame)
	FrameLayout frame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		ButterKnife.bind(this);
		if (savedInstanceState != null) {
			name.setText(savedInstanceState.getCharSequence(STATE_NAME));
			pwd.setText(savedInstanceState.getCharSequence(STATE_PWD));
		}
		setDefFragement();
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int i) {
				Log.i(TAG, "onCheckedChanged: " + i);
				if (i == rb1.getId()) {
					trans = fm.beginTransaction();
					trans.replace(R.id.frame, cf);
					trans.commit();
				} else {
					trans = fm.beginTransaction();
					trans.replace(R.id.frame, tf);
					trans.commit();
				}
			}
		});
		showmydialog.setOnClickListener(this);
	}

	private void setDefFragement() {
		fm = getSupportFragmentManager();
		trans = fm.beginTransaction();

		tf = TitleFragement.newInstance();
		cf = ContentFragement.newInstance();
		trans.replace(R.id.frame, tf);
		trans.commit();
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.i(TAG, "onRestoreInstanceState: ");
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
		Log.i(TAG, "onSaveInstanceState: ");

		super.onSaveInstanceState(outState, outPersistentState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putCharSequence(STATE_NAME, name.getText().toString());
		outState.putCharSequence(STATE_PWD, pwd.getText().toString());
		super.onSaveInstanceState(outState);
	}

	@OnClick(R.id.tocustomview)
	public void onClick() {
		startActivity(new Intent(Main2Activity.this, PieChartAactivity.class));
	}


	public void showmydialog() {
		MyDialog myDialog = new MyDialog(this);
		WindowManager.LayoutParams params = myDialog.getWindow().getAttributes();
		params.alpha = 0.8f; //窗口透明度
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		int height = wm.getDefaultDisplay().getHeight();
		params.width =  width - 80;
		params.height =  height / 4;
		params.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
		myDialog.getWindow().setAttributes(params);
			//必须设置一个背景，否则会有系统的Dialog样式：外部白框
		myDialog.getWindow().setBackgroundDrawableResource(R.drawable.translate);
		myDialog.show();
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.showmydialog:
				showmydialog();
				break;
		}
	}
}
