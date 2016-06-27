package com.ttb.broderick.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.loopeer.cardstack.AllMoveDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownAnimatorAdapter;
import com.loopeer.cardstack.UpDownStackAnimatorAdapter;
import com.ttb.broderick.R;
import com.ttb.broderick.adapter.TestStackAdapter;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CardStackView extends AppCompatActivity {

	@Bind(R.id.cardviewstack)
	com.loopeer.cardstack.CardStackView cardviewstack;
	private TestStackAdapter mTestStackAdapter;

	public static Integer[] TEST_DATAS = new Integer[]{
			R.color.color_1,
			R.color.color_2,
			R.color.color_3,
			R.color.color_4,
			R.color.color_5,
			R.color.color_6,
			R.color.color_7,
			R.color.color_8,
			R.color.color_9,
			R.color.color_10,
			R.color.color_11,
			R.color.color_12,
			R.color.color_13,
			R.color.color_14,
			R.color.color_15,
			R.color.color_16,
			R.color.color_17,
			R.color.color_18,
			R.color.color_19,
			R.color.color_20,
			R.color.color_21,
			R.color.color_22,
			R.color.color_23,
			R.color.color_24,
			R.color.color_25,
			R.color.color_26
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card_stack_view);
		ButterKnife.bind(this);

		mTestStackAdapter = new TestStackAdapter(this);
		cardviewstack.setAdapter(mTestStackAdapter);


		new Handler().postDelayed(
				new Runnable() {
					@Override
					public void run() {
						mTestStackAdapter.updateData(Arrays.asList(TEST_DATAS));
					}
				}
				, 200
		);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_all_down:
				cardviewstack.setAnimatorAdapter(new AllMoveDownAnimatorAdapter(cardviewstack));
				break;
			case R.id.menu_up_down:
				cardviewstack.setAnimatorAdapter(new UpDownAnimatorAdapter(cardviewstack));
				break;
			case R.id.menu_up_down_stack:
				cardviewstack.setAnimatorAdapter(new UpDownStackAnimatorAdapter(cardviewstack));
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
