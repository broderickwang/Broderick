package com.ttb.broderick.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.ttb.broderick.R;
import com.ttb.broderick.adapter.FreshAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class FreshActivity extends AppCompatActivity {

	@Bind(R.id.test_list_view)
	ListView testListView;
	@Bind(R.id.test_list_view_frame)
	PtrClassicFrameLayout testListViewFrame;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fresh);
		ButterKnife.bind(this);
		setListnrer();
	}
	private void setListnrer(){
		List<String> ns = new ArrayList<>();
		for(int i=0;i<10;i++){
			ns.add(""+i);
		}
		getView(ns);
		testListViewFrame.setPtrHandler(new PtrHandler() {
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
				List<String> ns2 = new ArrayList<>();
				for(int i=0;i<100;i++){
					ns2.add("a "+i);
				}
				getView(ns2);
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
			}

			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {
				frame.postDelayed(new Runnable() {
					@Override
					public void run() {
						testListViewFrame.refreshComplete();
					}
				},1800);
			}
		});
	}

	private void getView(List<String> list){
		testListView.setAdapter(new FreshAdapter(FreshActivity.this,list));
	}
}
