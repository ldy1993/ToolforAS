package com.ldy.View.CustomWidget.ViewPager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.ConditionVariable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.bumptech.glide.Glide;
import com.ldy.Utils.UnitUtils;
import com.ldy.View.adapter.ViewAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import SRnO.Tool.aar.R;

public class AdContainer extends ViewPager {
	private boolean isCanceled = true;
	//动画时间
	private int animTime = 4000;
	private List<Integer> drawableIds = Arrays.asList(R.drawable.ad1,
			R.drawable.ad3);

	public AdContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public AdContainer(Context context) {
		super(context);
		init();
	}

	private String isCheckFilesDir(Context context, String str) {
		String str2 = "";
		File file = new File(context.getFilesDir(), str);
		if (file.exists()) {
			return file.getPath();
		}
		return str2;
	}

	public void init() {
		List<String> imgs = new ArrayList<String>();
		ArrayList<String> adImageUrls = new ArrayList<String>(
				Arrays.asList(new String[] { "banner_1.png", "banner_2.png",
						"banner_3.png", "banner_4.png", "banner_5.png" }));
		for (int i = 0; i < adImageUrls.size(); i++) {
			CharSequence a2 = isCheckFilesDir(getContext(), adImageUrls.get(i));
			if (!TextUtils.isEmpty(a2)) {
				imgs.add(adImageUrls.get(i));
			}
		}
		List<View> views = new ArrayList<View>();
		if (imgs == null || imgs.size() == 0) {
			for (int i = 0; i < drawableIds.size(); i++) {
				ImageView iv = new ImageView(getContext());
				iv.setScaleType(ScaleType.FIT_XY);
				iv.setImageResource(drawableIds.get(i));
				views.add(iv);
			}
		} else {
			for (int i = 0; i < imgs.size(); i++) {
				ImageView iv = new ImageView(getContext());
				iv.setScaleType(ScaleType.FIT_XY);
				Glide.with(getContext())
						.load(new File(getContext().getFilesDir(), imgs.get(i)))
						.into(iv);
				/*
				 * Bitmap bitmap = getLoacalBitmap(imgs.get(i));
				 * iv.setImageBitmap(bitmap);
				 */
				views.add(iv);
			}
		}
		setAdapter(new ViewAdapter(views));
	}


	private ConditionVariable cv = new ConditionVariable();
	private boolean needPause = false;
	protected boolean annimationStopped=true;;

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			Log.e("ldy", "ACTION_DOWN: ");
			needPause = true;
		}
		if (ev.getAction() == MotionEvent.ACTION_UP
				|| ev.getAction() == MotionEvent.ACTION_CANCEL) {
			Log.e("ldy", "ACTION_UP: ");
			needPause = false;
			cv.open();
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		int count = getAdapter().getCount();
		Paint selectedPaint = new Paint();
		selectedPaint.setColor(Color.argb(204, 66, 214, 236));
		selectedPaint.setStyle(Style.FILL);
		selectedPaint.setAntiAlias(true);
		Paint normalPaint = new Paint();
		normalPaint.setColor(Color.argb(0xCC, 0x99, 0x99, 0x99));
		normalPaint.setStyle(Style.FILL);
		normalPaint.setAntiAlias(true);
		int paddingRight = 15;
		int paddingBottom = 6;
		int width = 11;
		int height = 3;
		int divide = 4;
		int left = (int) (UnitUtils.px2dp(canvas.getWidth()) - (paddingRight
				+ (width + divide) * (count - 1) + divide));
		int bottom = (int) (UnitUtils.px2dp(canvas.getHeight()) - paddingBottom);
		int top = bottom - height;
		canvas.save();
		canvas.translate(getScrollX(), 0);
		for (int i = 0; i < count; i++) {
			canvas.drawRoundRect((float) UnitUtils.dp2px(left + i
					* (divide + width)), (float) UnitUtils.dp2px(top),
					(float) UnitUtils
							.dp2px(left + i * (divide + width) + width),
					(float) UnitUtils.dp2px(bottom), (float) UnitUtils
							.dp2px(height * 1.0 / 2), (float) UnitUtils
							.dp2px(height * 1.0 / 2),
					getCurrentItem() == i ? selectedPaint : normalPaint);
		}
		canvas.restore();
	}

	@Override
	protected void onDetachedFromWindow() {
		isCanceled = true;
		super.onDetachedFromWindow();
		init();
	}

	@Override
	protected void onAttachedToWindow() {
		if (annimationStopped) {
			isCanceled = false;
			autoAnimate();
		} else {
			isCanceled = false;
		}
		super.onAttachedToWindow();
	}

	public void autoAnimate() {
		annimationStopped = false;
		new Thread() {
			@Override
			public void run() {
				while (!isCanceled) {
//					Log.e("isCanceled", "-----------");
					try {
						Thread.sleep(animTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (needPause) {
						cv.close();
						cv.block();
						try {
							Thread.sleep(animTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					post(new Runnable() {
						@Override
						public void run() {
							setCurrentItem((getCurrentItem() + 1)
									% getAdapter().getCount(), true);
						}
					});
				}
				annimationStopped = true;
			}
		}.start();
	}

}
