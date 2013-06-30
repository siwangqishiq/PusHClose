package com.xinlan.pushclose;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

public class PushClose extends RelativeLayout {
	public boolean isClosed = true;
	private LinearLayout mBottomView;
	private LinearLayout mTopView;
	private Scroller mScroller;
	private CanCloseListView mListView;
	private Context mContext;

	private boolean mIsBeingDragged = false;
	private float mLastMotionX;
	private float mLastMotionY;

	public PushClose(Context context) {
		super(context);
		init(context);
	}

	public PushClose(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		mScroller = new Scroller(getContext());
	}

	public void setContent(View top, View bottom) {
		mBottomView = (LinearLayout) findViewById(R.id.bottom);
		mTopView = (LinearLayout) findViewById(R.id.top);
		mBottomView.addView(bottom);
		mTopView.addView(top);
		
		mListView = (CanCloseListView)mTopView.findViewById(R.id.date_list);
	}

	@Override
	public void computeScroll() {
		if (!mScroller.isFinished()) {
			if (mScroller.computeScrollOffset()) {
				mTopView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
				postInvalidate();
			}
		}
	}

	public void smoothScrollBy(int dx, int dy) {
		mScroller.startScroll(mScroller.getFinalX(), mScroller.getFinalY(), dx,
				dy);
		invalidate();
	}

	public void smoothScrollTo(int fx, int fy) {
		int dx = fx - mScroller.getFinalX();
		int dy = fy - mScroller.getFinalY();
		smoothScrollBy(dx, dy);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		final int action = event.getAction();
		final float x = event.getX();
		final float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			int dy = (int) (mLastMotionY - y);
			if(mScroller.getCurrY()+dy>0){
				smoothScrollBy(0, 0);
			}else if(mScroller.getCurrY()+dy<-mBottomView.getChildAt(0).getHeight()){
				smoothScrollTo(0, -mBottomView.getChildAt(0).getHeight());
			}else{
				smoothScrollBy(0, dy);
			}
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_UP:
			int bottomHeight = mBottomView.getChildAt(0).getHeight();
			if (mTopView.getScrollY() > -bottomHeight / 2) {
				smoothScrollTo(0, 0);
				isClosed = true;
				mListView.isHead = false;
			} else {
				smoothScrollTo(0, -bottomHeight);
				isClosed = false;
				mListView.isHead = true;
			}
			break;
		}
		return true;
	}

}// end class
