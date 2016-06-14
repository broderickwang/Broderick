package com.ttb.broderick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends AppCompatActivity {
	ResideMenu resideMenu;
	private ResideMenuItem itemHome;
	private ResideMenuItem itemProfile;
	private ResideMenuItem itemCalendar;
	private ResideMenuItem itemSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CardView cardView = (CardView) findViewById(R.id.card1);
		setMenu();
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT); // or ResideMenu.DIRECTION_RIGHT
//		resideMenu.closeMenu();
		return super.dispatchTouchEvent(ev);
	}
	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			Toast.makeText(MainActivity.this, "menu opened", Toast.LENGTH_SHORT).show();
		}

		@Override
		public void closeMenu() {
			Toast.makeText(MainActivity.this, "menu closed", Toast.LENGTH_SHORT).show();
		}
	};
	private void setMenu(){
// attach to current activity;
		resideMenu = new ResideMenu(this);
//		resideMenu.setUse3D(true);

		resideMenu.setBackground(R.drawable.menu_background);
		resideMenu.attachToActivity(this);
		resideMenu.setMenuListener(menuListener);
		//valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
		resideMenu.setScaleValue(0.6f);

		// create menu items;
		itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     "Home");
		itemProfile  = new ResideMenuItem(this, R.drawable.icon_profile,  "Profile");
		itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
		itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");

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
	}
}
