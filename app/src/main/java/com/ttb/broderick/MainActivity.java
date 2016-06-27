package com.ttb.broderick;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.ttb.broderick.activity.CardStackView;
import com.ttb.broderick.activity.HttpClicentActiviy;
import com.ttb.broderick.activity.Main2Activity;
import com.ttb.broderick.interfaces.GitHubService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
	ResideMenu resideMenu;
	@Bind(R.id.jump)
	Button jump;
	@Bind(R.id.lmenu)
	ImageView lmenu;
	@Bind(R.id.rmenu)
	ImageView rmenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemProfile;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemSettings;
	Resources res;//= getResources();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		CardView cardView = (CardView) findViewById(R.id.card1);
		setMenu();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
		res = getResources();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT); // or ResideMenu.DIRECTION_RIGHT
//		resideMenu.closeMenu();
		return super.dispatchTouchEvent(ev);
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			Toast.makeText(MainActivity.this, "menu opened", Toast.LENGTH_SHORT).show();
			lmenu.setImageBitmap(BitmapFactory.decodeResource(res, R.drawable.cancel));
		}

		@Override
		public void closeMenu() {
			Toast.makeText(MainActivity.this, "menu closed", Toast.LENGTH_SHORT).show();
			lmenu.setImageBitmap(BitmapFactory.decodeResource(res, R.drawable.leftmenu));
		}
	};

	private void setMenu() {
		// attach to current activity;
		resideMenu = new ResideMenu(this);

		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		//valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
		resideMenu.setScaleValue(0.6f);

		// create menu items;
		itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
		itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "Profile");
		itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
		itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");
		itemHome.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "clicked itemhome", Toast.LENGTH_SHORT).show();
			}
		});
		itemCalendar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, HttpClicentActiviy.class));
			}
		});
		itemSettings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl("https://api.github.com/")
						.build();
				GitHubService service = retrofit.create(GitHubService.class);
			}
		});
		itemProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this, CardStackView.class));
			}
		});

		/*itemHome.setOnClickListener(this);
		itemProfile.setOnClickListener(this);
		itemCalendar.setOnClickListener(this);
		itemSettings.setOnClickListener(this);
*/
		resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_RIGHT);
		resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_RIGHT);

		// You can disable a direction by setting ->
		// resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		/*findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
			}
		});*/
		lmenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
		rmenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
			}
		});
	}

	@OnClick(R.id.jump)
	public void onClick() {

		startActivity(new Intent(MainActivity.this, Main2Activity.class));
	}


	/*public void onClick() {
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
	}*/
}
