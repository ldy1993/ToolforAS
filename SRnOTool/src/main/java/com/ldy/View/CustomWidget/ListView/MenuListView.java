package com.ldy.View.CustomWidget.ListView;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.ListView;


import com.ldy.View.CustomWidget.ViewGroup.LeftDragItem.*;
import com.ldy.View.adapter.FrameAdapter;
import com.ldy.View.set.WrapperList;
import com.ldy.function.Log.Log;

import SRnO.Tool.aar.R;

public class MenuListView<T> extends ListView {
	private WrapperList<T> menuList;
	public final static int STATE_NORMAL = 0;
	private final static int STATE_ADJUSTING = 1;
	public int currentState = STATE_NORMAL;
	/** 选中要移动位置的Menu对应的View在ListView可视范围内的排序 */
	private int selectedMoveViewIndex;
	/** 选中要移动位置的Menu在TransMenu列表中的序号 **/
	private int selectedPosition;
	/** 第一个子View的纵坐标偏移量 **/
	private float firstOffset;
	/** 当前空出来的栏位 **/
	private int emptyField;

	@SuppressLint("NewApi")
	public MenuListView(Context context, AttributeSet attrs, int defStyleAttr,
                        int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	public MenuListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public MenuListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MenuListView(Context context) {
		super(context);
		init();
	}

	private void init() {
		ShapeDrawable divider = new ShapeDrawable();
		divider.setShape(new RectShape());
		divider.setIntrinsicHeight(1);
		setDivider(divider);
		setBackgroundResource(R.color.main_background);
	}
	public void setMenuList(WrapperList<T> menuList,FrameAdapter frameAdapter) {
		this.menuList = menuList;
		setAdapter(frameAdapter);
	}
	public WrapperList<T> getMenuList() {
		return (WrapperList<T>) this.menuList;
	}

	float startX;
	float startY;
	Boolean moveHandledByChilden = null; // 子控件已承包此次手势

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (currentState == STATE_NORMAL) {
			boolean superResult = super.onInterceptTouchEvent(ev);
			if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
				moveHandledByChilden = null;
				startX = ev.getX();
				startY = ev.getY();
				return superResult;
			} else if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
				if (moveHandledByChilden == null) {
					if (Math.abs(ev.getX() - startX) >= Math.abs(ev.getY()
							- startY)) {
						if (Math.abs(ev.getX() - startX) > ViewConfiguration
								.get(getContext()).getScaledTouchSlop()) {
							moveHandledByChilden = true;
							return false;
						} else {
							return false;
						}
					} else {
						if (superResult) {
							moveHandledByChilden = false;
							return superResult;
						} else {
							return superResult;
						}
					}
				} else {
					if (moveHandledByChilden != null
							&& moveHandledByChilden.booleanValue()) {
						return false;
					} else {
						return superResult;
					}
				}
			} else if (ev.getActionMasked() == MotionEvent.ACTION_UP) {
				moveHandledByChilden = null;
				if (moveHandledByChilden != null
						&& moveHandledByChilden.booleanValue()) {
					return false;
				} else {
					return superResult;
				}
			} else {
				moveHandledByChilden = null;
				return superResult;
			}
		} else if (currentState == STATE_ADJUSTING) {
			return true;
		}
		return false;
	}

	/** 判断该View是否在指定的栏位中 **/
	private boolean isInField(int fieldIndex, View view) {
		float fieldTop = firstOffset + (view.getHeight() + getDividerHeight())
				* fieldIndex;
		float viewY = view.getY();
		return Math.abs(fieldTop - viewY) < view.getHeight() / 2;
	}

	/** 根据tag查找栏位中的View(View会先于动画被指定到栏位中) **/
	private View findViewInFieldByTag(int fieldIndex) {
		View view = null;
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			Object index = child.getTag(R.id.field);
			if (index == null) {
				continue;
			}
			if ((Integer) index == fieldIndex) {
				view = child;
				break;
			}
		}
		if (view == null) {
			view = getChildAt(fieldIndex);
		}
		return view;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (currentState == STATE_ADJUSTING) {
			final View view = getChildAt(selectedMoveViewIndex);
			if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
				view.setTranslationY(ev.getY() - startY);
				int childCount = getChildCount();
				/**
				 * 每个子View对应一个栏位，遍历所有栏位查询被移动的View当前处于哪个栏位，
				 * 然后将该栏位中已存在的View移开到已存在的空栏位
				 **/
				for (int i = 0; i < childCount; i++) {
					// 规避空栏位(即被移动的View当前所处的栏位，被移动的View悬空，所以该栏位是空栏位)
					if (i != emptyField) {
						if (isInField(i, view)) {
							Log.i("moveitem", "old: " + emptyField + "; new: "
									+ i + "; y0: " + view.getY() + "; y1: "
									+ getChildAt(i).getY());
							int ef = emptyField;
							emptyField = i;
							animateView(ef, findViewInFieldByTag(i));
						}
					}
				}
			} else if (ev.getActionMasked() == MotionEvent.ACTION_UP) {
				ObjectAnimator animator = new ObjectAnimator();
				animator.setPropertyName("translationY");
				float end = getChildAt(emptyField).getTop() - view.getTop();
				animator.setFloatValues(view.getTranslationY(), end);
				animator.setDuration(150);
				animator.setInterpolator(new DecelerateInterpolator());
				animator.setTarget(view);
				animator.addListener(new AnimatorListener() {
					@Override
					public void onAnimationStart(Animator animation) {
					}

					@Override
					public void onAnimationRepeat(Animator animation) {
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						view.setScaleX(1);
						view.setScaleY(1);
						view.setTranslationY(0);
						resetTranslation();
						LeftDragWrapperBean<T> menu = menuList
								.get(selectedPosition);
						menuList.remove(menu);
						menuList.add(selectedPosition + emptyField
								- selectedMoveViewIndex, menu);
						Log.e("ldy","ACTION_UP-selectedPosition"+selectedPosition);
						Log.e("ldy","ACTION_UP-emptyField"+emptyField);
						Log.e("ldy","ACTION_UP-selectedMoveViewIndex"+selectedMoveViewIndex);

						((FrameAdapter)getAdapter()).refresh(menuList);
					}

					@Override
					public void onAnimationCancel(Animator animation) {
					}
				});
				animator.start();
				currentState = STATE_NORMAL;
			} else {
				ObjectAnimator animator = new ObjectAnimator();
				animator.setPropertyName("translationY");
				float end = getChildAt(emptyField).getTop() - view.getTop();
				animator.setFloatValues(view.getTranslationY(), end);
				animator.setDuration(150);
				animator.setInterpolator(new DecelerateInterpolator());
				animator.setTarget(view);
				animator.addListener(new AnimatorListener() {
					@Override
					public void onAnimationStart(Animator animation) {
					}

					@Override
					public void onAnimationRepeat(Animator animation) {
					}

					@Override
					public void onAnimationEnd(Animator animation) {
						view.setScaleX(1);
						view.setScaleY(1);
						view.setTranslationY(0);
						resetTranslation();
						LeftDragWrapperBean<T> menu = menuList
								.get(selectedPosition);
						menuList.remove(menu);
						menuList.add(selectedPosition + emptyField
								- selectedMoveViewIndex, menu);
						Log.e("ldy","selectedPosition"+selectedPosition);
						Log.e("ldy","emptyField"+emptyField);
						Log.e("ldy","selectedMoveViewIndex"+selectedMoveViewIndex);

						((FrameAdapter)getAdapter()).refresh(menuList);
					}

					@Override
					public void onAnimationCancel(Animator animation) {
					}
				});
				animator.start();
				currentState = STATE_NORMAL;
			}
			return true;
		} else {
			return super.onTouchEvent(ev);
		}
	}

	@SuppressLint("NewApi")
	public void resetTranslation() {
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			getChildAt(i).setTranslationY(0);
			getChildAt(i).setTranslationZ(0);
			Object obj = getChildAt(i).getTag(R.id.animator);
			if (obj != null) {
				((ObjectAnimator) obj).cancel();
			}
		}
	}

	@SuppressLint("NewApi")
	public void moveItem(int position) {
		for (int i = 0; i < getChildCount(); i++) {
			View child = getChildAt(i);
			child.setTag(R.id.field, null);
		}
		selectedPosition = position;
		selectedMoveViewIndex = position - getFirstVisiblePosition();
		firstOffset = getChildAt(0).getY();
		emptyField = selectedMoveViewIndex;
		getChildAt(selectedMoveViewIndex).setScaleX(0.9f);
		getChildAt(selectedMoveViewIndex).setScaleY(0.9f);
		getChildAt(selectedMoveViewIndex).setTranslationZ(1000);
		currentState = STATE_ADJUSTING;
	}

	/** 获取一个Item栏位的纵坐标 **/
	private float getYOffsetByIndex(int index) {
		return firstOffset + (getChildAt(0).getHeight() + getDividerHeight())
				* index;
	}

	public void animateView(int targetField, View view) {
		if (view.getTag(R.id.animator) != null) {
			ObjectAnimator orig = (ObjectAnimator) view.getTag(R.id.animator);
			orig.cancel();
		}
		final ObjectAnimator animator = new ObjectAnimator();
		animator.setPropertyName("translationY");
		animator.setDuration(300);
		animator.setFloatValues(view.getTranslationY(),
				getYOffsetByIndex(targetField) - view.getTop());
		animator.setInterpolator(new DecelerateInterpolator());
		animator.setTarget(view);
		view.setTag(R.id.animator, animator);
		view.setTag(R.id.field, targetField);
		animator.start();
	}


}
