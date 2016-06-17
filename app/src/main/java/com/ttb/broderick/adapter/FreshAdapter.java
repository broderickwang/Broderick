package com.ttb.broderick.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ttb.broderick.R;

import java.util.List;
import java.util.Random;

/**
 * Created by Kevin on 16/6/16.
 */
public class FreshAdapter extends RecyclerView.Adapter<FreshAdapter.ViewHold> {

	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);
		void onItemLongClick(View view , int position);
	}

	Context context;
	List<String> names;
	private OnItemClickLitener mOnItemClickLitener;
	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	public FreshAdapter(Context context, List<String> names) {
		this.context = context;
		this.names = names;
	}

	@Override
	public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
		ViewHold viewHold = new ViewHold(
				LayoutInflater.from(context).inflate(R.layout.simple_adapter,null));
		return  viewHold;
	}

	@Override
	public void onBindViewHolder(final ViewHold holder, int position) {
		Random ran =new Random(System.currentTimeMillis());
		holder.tv.setHeight( ran.nextInt(100)+300);
		holder.tv.setText(names.get(position));
		if(mOnItemClickLitener != null){
			holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					int pos = holder.getPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					return false;
				}
			});
		}
	}

	@Override
	public int getItemCount() {
		return names.size();
	}

	public void addData(int position) {
		names.add(position, "Insert One");
		notifyItemInserted(position);
	}

	public void removeData(int position) {
		names.remove(position);
		notifyItemRemoved(position);
	}
	class ViewHold extends RecyclerView.ViewHolder{
		TextView tv;

		public ViewHold(View itemView) {
			super(itemView);
			this.tv =(TextView)itemView.findViewById(R.id.tv);

		}
	}
}
