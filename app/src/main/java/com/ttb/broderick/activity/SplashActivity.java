package com.ttb.broderick.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.ttb.broderick.MainActivity;
import com.ttb.broderick.R;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		ImageView logoImage = (ImageView) this.findViewById(R.id.splash);
		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(4000);
		logoImage.startAnimation(alphaAnimation);
		alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			@Override
			public void onAnimationEnd(Animation animation) {
//                if (isStart)
//                    return;
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				SplashActivity.this.finish();
			}
		});
	}
}
