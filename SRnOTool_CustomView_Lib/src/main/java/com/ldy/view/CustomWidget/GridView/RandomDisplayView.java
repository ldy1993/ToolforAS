//package com.ldy.View.CustomWidget.GridView;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.util.AttributeSet;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.AbsListView;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//
//import com.ldy.View.CustomWidget.ViewPager.MenuLayout.IMenuPage;
//import com.ldy.View.CustomWidget.ViewPager.MenuLayout;
//import com.ldy.custom.view.lib.R;
//import me.jerry.framework.utils.ReflactUtils;
//
///**
// * 所有菜单的布局View，可以通过Adapter填充数据，并且以任意排版显示ItemView，具体排版形式由MenuPage指定
// * @author Jerry
// *
// */
//public class RandomDisplayView extends GridView implements IMenuPage {
//	private int pageIndex;
//
//
//
//	@SuppressLint("NewApi")
//	public RandomDisplayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//		super(context, attrs, defStyleAttr, defStyleRes);
//		initView();
//	}
//	public RandomDisplayView(Context context, AttributeSet attrs, int defStyleAttr) {
//		super(context, attrs, defStyleAttr);
//		initView();
//	}
//	public RandomDisplayView(Context context, AttributeSet attrs) {
//		super(context, attrs);
//		initView();
//	}
//	public RandomDisplayView(Context context) {
//		super(context);
//		initView();
//	}
//
//	public void initView() {
//		setSelector(new ColorDrawable(Color.TRANSPARENT));
//	}
//
//	@SuppressLint("ClickableViewAccessibility")
//	@Override
//	public boolean onTouchEvent(MotionEvent ev) {
//		if(ev.getAction() == MotionEvent.ACTION_MOVE) {
//			return false;
//		}
//		return super.onTouchEvent(ev);
//	}
//
//	@Override
//	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
////		int count = getChildCount();
////		PageLayout layout = menuPage.getLayout(count);
////		List<MenuPage.MenuItem> itemLayout = layout.items;
////		for(int i = 0; i < count; i ++) {
////			View child = getChildAt(i);
////			MenuItem item = itemLayout.get(i);
////			int width = (int) (getMeasuredWidth() * (item.right - item.left));
////			int height = (int) (getMeasuredWidth() * (item.bottom - item.top));
////			AbsListView.LayoutParams lp = (LayoutParams) child.getLayoutParams();
////			if(lp == null) {
////				lp = new LayoutParams(width, height);
////			}
////			lp.height = height;
////			lp.width = width;
////			child.setLayoutParams(lp);
////			child.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
////		}
//	}
//
//	@Override
//	protected void layoutChildren() {
//		super.layoutChildren();
////		if(menuPage == null) {
////			return;
////		}
////		int count = getChildCount();
////		PageLayout layout = menuPage.getLayout(count);
////		if(layout == null) return;
////		List<MenuPage.MenuItem> itemLayout = layout.items;
////		for(int i = 0; i < count; i ++) {
////			View child = getChildAt(i);
////			MenuItem item = itemLayout.get(i);
////
////			child.layout((int)(getWidth() * item.left),
////					(int)(getHeight() * item.top),
////					(int)(getWidth() * item.right),
////					(int)(getHeight() * item.bottom));
////			ImageView iv_icon = (ImageView) child.findViewById(R.id.iv_icon);
////			TextView tv_title = (TextView) child.findViewById(R.id.tv_title);
////			LinearLayout.LayoutParams txtParams = (android.widget.LinearLayout.LayoutParams) tv_title.getLayoutParams();
////			if(tv_title.getTextSize() != 18 * item.txtScale) {
////				tv_title.setTextSize((float) (18 * item.txtScale));
////			}
////			LinearLayout.LayoutParams imgParams = (android.widget.LinearLayout.LayoutParams) iv_icon.getLayoutParams();
////			if(imgParams.width != (int) (iv_icon.getDrawable().getMinimumWidth() * item.iconScale)
////					|| imgParams.height != (int) (iv_icon.getDrawable().getMinimumHeight() * item.iconScale)) {
////				imgParams.width = (int) (iv_icon.getDrawable().getMinimumWidth() * item.iconScale);
////				imgParams.height = (int) (iv_icon.getDrawable().getMinimumHeight() * item.iconScale);
//////				 mark max width, so that it can roll if the title is too long.
////				ReflactUtils.setFieldValue(TextView.class, tv_title, "mMaxWidth", child.getWidth() - imgParams.width - txtParams.leftMargin - 20);
////				iv_icon.setLayoutParams(imgParams);
////			}
////		}
//	}
//
//	@Override
//	public int getMaxItems() {
//		return 4;
//	}
//	@Override
//	public int getPageIndex() {
//		return pageIndex;
//	}
//	@Override
//	public void setPageIndex(int index) {
//		this.pageIndex = index;
//	}
//
//}
