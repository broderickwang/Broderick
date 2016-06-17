package com.ttb.broderick.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.ttb.broderick.fragement.OneFragement;
import com.ttb.broderick.fragement.TwoFragement;

/**
 * Created by Kevin on 16/6/17.
 */
public class Welcome extends AppIntro {
	@Override
	public void init(@Nullable Bundle savedInstanceState) {
		addSlide(OneFragement.newInstance());
		addSlide(TwoFragement.newInstance());

//		addSlide(AppIntroFragment.newInstance(title, description, image, background_colour));

		// OPTIONAL METHODS
		// Override bar/separator color.
		setBarColor(Color.parseColor("#3F51B5"));
		setSeparatorColor(Color.parseColor("#2196F3"));

		// Hide Skip/Done button.
		showSkipButton(false);
		setProgressButtonEnabled(false);

		// Turn vibration on and set intensity.
		// NOTE: you will probably need to ask VIBRATE permisssion in Manifest.
		setVibrate(true);
		setVibrateIntensity(30);
	}

	@Override
	public void onSkipPressed() {
		Toast.makeText(Welcome.this, "skip", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNextPressed() {
		Toast.makeText(Welcome.this, "next", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDonePressed() {
		Toast.makeText(Welcome.this, "done", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSlideChanged() {
		Toast.makeText(Welcome.this, "change", Toast.LENGTH_SHORT).show();
	}
}
