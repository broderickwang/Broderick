package com.ttb.broderick.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.ttb.broderick.R;
import com.ttb.broderick.activity.GCCActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Kevin on 16/6/28.
 */
public class MyDialog extends Dialog {
	private Animation mShowAnim;
	private Animation mHideAnim;
	private View mMainLayout;
	private Context context;
	private Button gcc;

	public MyDialog(final Context context) {
		super(context);
		this.context = context;
		mMainLayout = LayoutInflater.from(context).inflate(R.layout.mydialog_layout, null);
		createShowAnim();
		createHideAnim();
		gcc = (Button)mMainLayout.findViewById(R.id.gcc);
		gcc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				context.startActivity(new Intent(context,GCCActivity.class));
			}
		});
	}

	public MyDialog(Context context, int themeResId) {
		super(context, themeResId);
	}

	private void createShowAnim() {
		mShowAnim = new TranslateAnimation(1, 0, 1, 0, 1, 1, 1, 0);
		mShowAnim.setDuration(500);
	}

	private void createHideAnim() {
		mHideAnim = new TranslateAnimation(1, 0, 1, 0, 1, 0, 1, 1);
		mHideAnim.setDuration(500);
	}

	private void dismissDialog() {
		super.dismiss();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(mMainLayout);
	}

	@Override
	public void show() {
		super.show();
		mMainLayout.startAnimation(mShowAnim);
	}

	@Override
	public void dismiss() {
		mHideAnim.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				dismissDialog();
			}
		});
//		mMainLayout.startAnimation(mHideAnim);
		super.dismiss();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			dismiss();
		}
		return super.onKeyDown(keyCode, event);
	}

}
