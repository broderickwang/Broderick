package com.ttb.broderick.view;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ttb.broderick.R;

/**
 * Created by Kevin on 16/6/28.
 */
public class PieChart extends View {
	private boolean mShowText;
	private Integer mTextPos;
	private Paint mTextPaint,mPiePaint,mShadowPaint;
	private float mTextHeight,mTextWidth;
	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.getTheme().obtainStyledAttributes(
				attrs,
				R.styleable.PieChart,
				0,0);
		try {
			mShowText = a.getBoolean(R.styleable.PieChart_showText11, false);
			mTextPos = a.getInteger(R.styleable.PieChart_labelPosition11, 0);
		}finally {
			a.recycle();
		}
	}

	public boolean ismShowText() {
		return mShowText;
	}

	public void setmShowText(boolean mShowText) {
		this.mShowText = mShowText;
		invalidate();
		requestLayout();
	}

	public Integer getmTextPos() {
		return mTextPos;
	}

	public void setmTextPos(Integer mTextPos) {
		this.mTextPos = mTextPos;
		invalidate();
		requestLayout();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int minw = getPaddingLeft()+getPaddingRight()+getSuggestedMinimumWidth();
		int w = resolveSizeAndState(minw,widthMeasureSpec,1);
		// Whatever the width ends up being, ask for a height that would let the pie
		// get as big as it can
		int minh = MeasureSpec.getSize(w) - (int)mTextWidth + getPaddingBottom() + getPaddingTop();
		int h = resolveSizeAndState(MeasureSpec.getSize(w) - (int)mTextWidth, heightMeasureSpec, 0);

		setMeasuredDimension(w, h);
	}
	private void init(){
		mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mTextPaint.setColor(Color.RED);
		if (mTextHeight == 0) {
			mTextHeight = mTextPaint.getTextSize();
		} else {
			mTextPaint.setTextSize(mTextHeight);
		}

		mPiePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPiePaint.setStyle(Paint.Style.FILL);
		mPiePaint.setTextSize(mTextHeight);

		mShadowPaint = new Paint(0);
		mShadowPaint.setColor(0xff101010);
		mShadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// Account for padding
		float xpad = (float)(getPaddingLeft() + getPaddingRight());
		float ypad = (float)(getPaddingTop() + getPaddingBottom());

		// Account for the label
		if (mShowText) xpad += mTextWidth;

		float ww = (float)w - xpad;
		float hh = (float)h - ypad;

		// Figure out how big we can make the pie.
		float diameter = Math.min(ww, hh);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		/*canvas.drawOval(
				mShadowBounds,
				mShadowPaint);
		canvas.drawText(mData.get(mCurrentItem).mLabel, mTextX, mTextY, mTextPaint);

		// Draw the pie slices
		for (int i = 0; i < mData.size(); ++i) {
			ClipData.Item it = mData.get(i);
			mPiePaint.setShader(it.mShader);
			canvas.drawArc(mBounds,
					360 - it.mEndAngle,
					it.mEndAngle - it.mStartAngle,
					true, mPiePaint);
		}

		// Draw the pointer
		canvas.drawLine(mTextX, mPointerY, mPointerX, mPointerY, mTextPaint);
		canvas.drawCircle(mPointerX, mPointerY, mPointerSize, mTextPaint);*/
	}
}
