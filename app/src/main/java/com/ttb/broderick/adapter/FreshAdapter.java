package com.ttb.broderick.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kevin on 16/6/16.
 */
public class FreshAdapter extends BaseAdapter {
	Context context;
	List<String> names;

	public FreshAdapter(Context context, List<String> names) {
		this.context = context;
		this.names = names;
	}

	@Override
	public int getCount() {
		return names.size();
	}

	@Override
	public Object getItem(int i) {
		return names.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {
		ViewHold hold;
		if(view == null){
			hold = new ViewHold();
			view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,null);
			view.setTag(hold);
		}else
			hold = (ViewHold)view.getTag();
		hold.tv = (TextView)view.findViewById(android.R.id.text1);
		hold.tv.setText(names.get(i));
		return view;
	}
	class ViewHold{
		TextView tv;
	}
}
