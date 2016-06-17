package com.ttb.broderick.listner;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.ttb.broderick.adapter.FreshAdapter;

/**
 * Created by Kevin on 16/6/17.
 */
public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {
	private  FreshAdapter adapter ;

	public SimpleItemTouchHelperCallback(FreshAdapter adapter) {
		this.adapter = adapter;
	}

	public interface ItemTouchHelperAdapter {

		void onItemMove(int fromPosition, int toPosition);

		void onItemDismiss(int position);
	}
	@Override
	public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
		final int dragFlags;
		final int swipeFlags;
		if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
			dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
			swipeFlags = 0;
		} else {
			dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
			swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
		}
		return makeMovementFlags(dragFlags, swipeFlags);
	}

	@Override
	public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
		adapter.onItemMove(viewHolder.getAdapterPosition(),
				target.getAdapterPosition());
		return true;
	}

	@Override
	public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
		adapter.onItemDismiss(viewHolder.getAdapterPosition());
	}

	@Override
	public boolean isLongPressDragEnabled() {
		return true;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		return true;
	}
}
