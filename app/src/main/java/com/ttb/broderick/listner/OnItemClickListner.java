package com.ttb.broderick.listner;

import android.content.ClipData;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kevin on 16/6/17.
 */
public abstract class OnItemClickListner implements RecyclerView.OnItemTouchListener {
	private GestureDetectorCompat gestureDetectorCompat;
	private RecyclerView recyclerView;
	public abstract   void onItemClick(RecyclerView.ViewHolder viewHolder,int position);
	public static void onLongPress(RecyclerView.ViewHolder viewHolder,int position){}

	public OnItemClickListner(RecyclerView recyclerView) {
		this.recyclerView = recyclerView;
		gestureDetectorCompat = new GestureDetectorCompat(recyclerView.getContext(),
				new ItemTouchHelperGestureListener());
	}

	@Override
	public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
		gestureDetectorCompat.onTouchEvent(e);
		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView rv, MotionEvent e) {
		gestureDetectorCompat.onTouchEvent(e);
	}

	class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener{
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
			if(child != null){
				int position = recyclerView.indexOfChild(child);
				onItemClick(recyclerView.getChildViewHolder(child),position);
			}
			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
			if(child != null){
				int position = recyclerView.indexOfChild(child);
				OnItemClickListner.onLongPress(recyclerView.getChildViewHolder(child),position);
			}
			super.onLongPress(e);
		}
	}
}
