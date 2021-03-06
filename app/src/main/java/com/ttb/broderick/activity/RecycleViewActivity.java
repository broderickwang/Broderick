package com.ttb.broderick.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ttb.broderick.R;
import com.ttb.broderick.adapter.FreshAdapter;
import com.ttb.broderick.listner.OnItemClickListner;
import com.ttb.broderick.listner.SimpleItemTouchHelperCallback;
import com.ttb.broderick.recycleviewdecoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecycleViewActivity extends AppCompatActivity {

	FreshAdapter mAdapter;
	@Bind(R.id.recycle)
	RecyclerView recycle;
	ItemTouchHelper.Callback callback;
	ItemTouchHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycle_view);
		ButterKnife.bind(this);
		//设置Item增加、移除动画
		recycle.setItemAnimator(new DefaultItemAnimator());
		//添加分割线
		recycle.addItemDecoration(new DividerItemDecoration(this));
		setListner();
		setAdapter();
		setDrag();
	}
	private void setAdapter(){
		List<String> namse= new ArrayList<>();
		for(int i=0;i<10;i++){
			namse.add(i+"");
		}
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
		RecyclerView.LayoutManager gridManager = new GridLayoutManager(this,4);
		RecyclerView.LayoutManager stagreManager =new StaggeredGridLayoutManager(3,
				StaggeredGridLayoutManager.VERTICAL);
		recycle.setLayoutManager(stagreManager);
		mAdapter =new FreshAdapter(this,namse);
		mAdapter.setOnItemClickLitener(new FreshAdapter.OnItemClickLitener() {
			@Override
			public void onItemClick(View view, int position) {

			}

			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(RecycleViewActivity.this, position + " long click",Toast.LENGTH_SHORT).show();
//				mAdapter.removeData(position);
				helper.startSwipe(recycle.getChildViewHolder(view));
			}
		});
		recycle.setAdapter(mAdapter);
	}
	private void setListner(){
		recycle.addOnItemTouchListener(new OnItemClickListner(recycle) {
			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

			}

			@Override
			public void onItemClick(RecyclerView.ViewHolder viewHolder, int position) {
				Toast.makeText(RecycleViewActivity.this, "clicked "+position, Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.recycle_menu,menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.add:
				mAdapter.addData(1);
				break;
			case R.id.delete:
				mAdapter.removeData(1);
				break;
			case R.id.guide:
				SharedPreferences preferences = this.getSharedPreferences("check",MODE_PRIVATE);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putBoolean("fristload", true);
				editor.commit();
				break;
		}
		return true;
	}

	private void setDrag(){
		callback = new SimpleItemTouchHelperCallback(mAdapter);
		helper = new ItemTouchHelper(callback);
		helper.attachToRecyclerView(recycle);
	}

}
