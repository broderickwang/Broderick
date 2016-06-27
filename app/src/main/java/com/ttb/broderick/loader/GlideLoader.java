package com.ttb.broderick.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yancy.imageselector.ImageLoader;

/**
 * Created by Kevin on 16/6/23.
 */
public class GlideLoader implements ImageLoader {
	@Override
	public void displayImage(Context context, String path, ImageView imageView) {
		Glide.with(context)
				.load(path)
				.placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
				.centerCrop()
				.into(imageView);
	}
}
