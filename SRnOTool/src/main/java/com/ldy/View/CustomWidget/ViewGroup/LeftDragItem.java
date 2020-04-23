package com.ldy.View.CustomWidget.ViewGroup;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;


import com.ldy.View.CustomWidget.ListView.DirectiveMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import SRnO.Tool.aar.R;

public abstract class LeftDragItem extends ViewGroup implements OnGestureListener {
	private View content;
	protected View rightView;
	private GestureDetector detector;
	
	private LeftDragWrapperBean wrapperBean;
	
	private boolean rightViewShowing = false;
	private boolean draggable = true;
	
	public void turnoff() {
		draggable = false;
	}
	
	public void turnon() {
		draggable = true;
	}
	
	public LeftDragItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		detector = new GestureDetector(getContext(), this);
	}
	public LeftDragItem(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		detector = new GestureDetector(getContext(), this);
	}
	public LeftDragItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		detector = new GestureDetector(getContext(), this);
	}
	public LeftDragItem(Context context) {
		super(context);
		detector = new GestureDetector(getContext(), this);
	}
	
	public void setRightViewShowing(boolean showing) {
		rightViewShowing = showing;
		setScrollX(showing ? rightView.getWidth() : 0);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		this.content = findViewById(R.id.content);
	}
	
	public void setContent(View view) {
		if(this.content != null) {
			removeView(content);
		}
		this.content = view;
		super.addView(content, 0);
	}
	
	public void setRightView(View view) {
		if(this.rightView != null) {
			removeView(rightView);
		}
		this.rightView = view;
		super.addView(rightView);
	}
	
	@Override
	public void addView(View child) {
		return;
	}
	
	public void setWrapperBean(LeftDragWrapperBean wrapperBean) {
		this.wrapperBean = wrapperBean;
		setRightViewShowing(wrapperBean.isDragged);
	}
	
	float startX;
	float startY;
	/** 是否已接收处理此次手势 **/
	Boolean selfHandled = null;
	boolean clickable = true;
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(draggable) {
			boolean superResult = super.onInterceptTouchEvent(ev);
			if(ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
				startX = ev.getX();
				startY = ev.getY();
				detector.onTouchEvent(ev);
				if(rightViewShowing) {
					if(ev.getX() > getWidth() - rightView.getWidth()) {
						return superResult;
					} else {
						return true;
					}
				} else {
					return superResult;
				}
			} else if(ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
				if(selfHandled == null) {
					if(Math.abs(ev.getX() - startX) >= Math.abs(ev.getY() - startY)) {
						if(Math.abs(ev.getX() - startX) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
							clickable = false;
							selfHandled = true;
							return true;
						} else {
							return false;
						}
					} else {
						if(superResult) {
							return superResult;
						} else {
							return superResult;
						}
					}
				} else {
					if(selfHandled != null && selfHandled.booleanValue()) {
						return true;
					} else {
						return superResult;
					}
				}
			} else if(ev.getActionMasked() == MotionEvent.ACTION_UP){
				selfHandled = null;
				if(selfHandled != null && selfHandled.booleanValue()) {
					return true;
				} else {
					if(rightViewShowing) {
						if(ev.getX() > getWidth() - rightView.getWidth()) {
							return superResult;
						} else {
							return true;
						}
					} else {
						return superResult;
					}
				}
			} else {
				selfHandled = null;
				return superResult;
			}
		} else {
			return super.onInterceptTouchEvent(ev);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(draggable) {
			if(event.getActionMasked() == MotionEvent.ACTION_DOWN) {
				startX = event.getX();
				startY = event.getY();
			} else if(event.getActionMasked() == MotionEvent.ACTION_MOVE) {
				if(selfHandled == null) {
					if(Math.abs(event.getX() - startX) >= Math.abs(event.getY() - startY)) {
						if(Math.abs(event.getX() - startX) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
							clickable = false;
							selfHandled = true;
						}
					}
				}
			} else if(event.getActionMasked() == MotionEvent.ACTION_UP){
				selfHandled = null;
			} else {
				selfHandled = null;
			}
			boolean gesResult = true;
			if(!clickable) {
				if(event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
					clickable = true;
				}
				gesResult = detector.onTouchEvent(event);
				if((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) && !gesResult) {
					if(getScrollX() != 0 && getScrollX() != rightView.getWidth()) {
						int start = getScrollX();
						int end;
						if(getScrollX() > (rightView.getWidth() + 0) / 2) {
							end = rightView.getWidth();
						} else {
							end = 0;
						}
						animateScroll(start, end);
					} else {
						rightViewShowing = (getScrollX() != 0);
						wrapperBean.isDragged = rightViewShowing;
					}
					return true;
				}
			} else {
				if(event.getAction() == MotionEvent.ACTION_UP) {
					if(rightViewShowing) {
						if(event.getX() <= getWidth() - rightView.getWidth()) {
							// 删除按钮以外的部分被点击
							animateScroll(getScrollX(), 0);
						}
					} else {
						performClick();
					}
					return true;
				}
			}
			return gesResult;
		} else {
			return super.onTouchEvent(event);
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if(content == null) {
			content = findViewById(R.id.content);
		}
		content.measure(widthMeasureSpec, heightMeasureSpec);
		rightView.measure(0, MeasureSpec.makeMeasureSpec(content.getMeasuredHeight(), MeasureSpec.EXACTLY));
		setMeasuredDimension(content.getMeasuredWidth(), content.getMeasuredHeight());
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if(content == null) {
			content = findViewById(R.id.content);
		}
		content.layout(0, 0, r - l, b - t);
		rightView.layout(r - l + 1, 0, r - l + rightView.getMeasuredWidth() + 1, b - t);
	}
	@Override
	public boolean onDown(MotionEvent e) {
		return true;
	}
	@Override
	public void onShowPress(MotionEvent e) {
	}
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return true;
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		if(getScrollX() + distanceX >= 0 && getScrollX() + distanceX <= rightView.getWidth()) {
			setScrollX((int) (getScrollX() + distanceX));
		} else if(getScrollX() + distanceX < 0 && getScrollX() > 0) {
			setScrollX(0);
		} else if(getScrollX() + distanceX > rightView.getWidth() && getScrollX() < rightView.getWidth()) {
			setScrollX(rightView.getWidth());
		}
		return true;
	}
	@Override
	public void onLongPress(MotionEvent e) {
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		if(getScrollX() != 0 && getScrollX() != rightView.getWidth()) {
			int start = getScrollX();
			int end;
			if(velocityX < 0) {
				if(getScrollX() > (rightView.getWidth() + 0) / 5) {
					end = rightView.getWidth();
				} else {
					end = 0;
				}
			} else {
				if(getScrollX() < (rightView.getWidth() + 0) * 4 / 5) {
					end = 0;
				} else {
					end = rightView.getWidth();
				}
			}
			animateScroll(start, end);
		} else {
			rightViewShowing = (getScrollX() != 0);
			wrapperBean.isDragged = rightViewShowing;
		}
		return true;
	}
	
	public void animateScroll(int start, int end) {
		ObjectAnimator animator = new ObjectAnimator();
		animator.setPropertyName("scrollX");
		animator.setIntValues(start, end);
		animator.setInterpolator(new DecelerateInterpolator());
		animator.setDuration(200);
		animator.setTarget(this);
		animator.addListener(new AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
			}
			@Override
			public void onAnimationRepeat(Animator animation) {
			}
			@Override
			public void onAnimationEnd(Animator animation) {
				rightViewShowing = (getScrollX() != 0);
				wrapperBean.isDragged = rightViewShowing;
			}
			@Override
			public void onAnimationCancel(Animator animation) {
			}
		});
		animator.start();
	}
	
	public static class LeftDragWrapperBean<T> {
		public boolean isDragged;
		public T value;
		
		@Override
		public boolean equals(Object o) {
			return (o instanceof LeftDragWrapperBean) && value.equals(((LeftDragWrapperBean)o).value);
		}
	}
	


}
