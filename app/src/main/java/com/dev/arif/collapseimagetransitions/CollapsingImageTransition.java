package com.dev.arif.collapseimagetransitions;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;


public class CollapsingImageTransition extends FrameLayout {

    private static final String TAG = "CollapsingImageLayout";

    private WindowInsetsCompat mLastInsets;
    private int mImageLeftExpanded;
    private int mImageTopExpanded;
    private int mLayoutTopExpanded;
    private int mLayoutLeftExpanded;
    private int mTitleLeftExpanded;
    private int mTitleTopExpanded;
    private int mSubtitleLeftExpanded;
    private int mSubtitleTopExpanded;
    private int mImageLeftCollapsed;
    private int mImageTopCollapsed;
    private int mLayoutTopCollapsed;
    private int mLayoutLeftCollapsed;
    private int mTitleLeftCollapsed;
    private int mTitleTopCollapsed;
    private int mSubtitleLeftCollapsed;
    private int mSubtitleTopCollapsed;
    private int mDetailTopCollapsed;
    private int mDetailLeftCollapse;
    private int mDetailTopExpanded;
    private int mDetailLeftExpanded;


    private OnOffsetChangedListener mOnOffsetChangedListener;


    public CollapsingImageTransition(Context context) {
        this(context, null);
    }

    public CollapsingImageTransition(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsingImageTransition(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mImageLeftCollapsed = getResources().getDimensionPixelOffset(R.dimen.image_left_margin_collapsed);
        mImageTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.image_top_margin_collapsed);
        mTitleLeftCollapsed = getResources().getDimensionPixelOffset(R.dimen.title_left_margin_collapsed);
        mTitleTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.title_top_margin_collapsed);
        mSubtitleLeftCollapsed = getResources().getDimensionPixelOffset(R.dimen.subtitle_left_margin_collapsed);
        mSubtitleTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.subtitle_top_margin_collapsed);
        mLayoutTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.layout_top_margin_collapsed);
        mLayoutLeftCollapsed = getResources().getDimensionPixelOffset(R.dimen.layout_left_margin_collapsed);
        mDetailLeftCollapse = getResources().getDimensionPixelOffset(R.dimen.details_left_margin_collapsed);
        mDetailTopCollapsed = getResources().getDimensionPixelOffset(R.dimen.detasils_top_margin_collapsed);

        ViewCompat.setOnApplyWindowInsetsListener(this,
                new android.support.v4.view.OnApplyWindowInsetsListener() {
                    @Override
                    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                        return setWindowInsets(insets);
                    }
                });
    }

    @TargetApi(21)
    public CollapsingImageTransition(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            if (mOnOffsetChangedListener == null) {
                mOnOffsetChangedListener = new OnOffsetChangedListener();
            }
            ((AppBarLayout) parent).addOnOffsetChangedListener(mOnOffsetChangedListener);
        }
        ViewCompat.requestApplyInsets(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        final ViewParent parent = getParent();
        if (mOnOffsetChangedListener != null && parent instanceof AppBarLayout) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(mOnOffsetChangedListener);
        }

        super.onDetachedFromWindow();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 0, z = getChildCount(); i < z; i++) {
            final View child = getChildAt(i);

            if (mLastInsets != null && !ViewCompat.getFitsSystemWindows(child)) {
                final int insetTop = mLastInsets.getSystemWindowInsetTop();
                if (child.getTop() < insetTop) {
                    ViewCompat.offsetTopAndBottom(child, insetTop);
                }
            }

            getViewOffsetHelper(child).onViewLayout();
            switch (child.getId()) {
                case R.id.profilepic:
                    mImageLeftExpanded = child.getLeft();
                    mImageTopExpanded = child.getTop();
                    break;
                case R.id.username:
                    mTitleLeftExpanded = child.getLeft();
                    mTitleTopExpanded = child.getTop();
                    break;
                case R.id.name:
                    mSubtitleLeftExpanded = child.getLeft();
                    mSubtitleTopExpanded = child.getTop();
                    break;

                case R.id.detaillayout:
                    mLayoutLeftExpanded = child.getLeft();
                    mLayoutTopExpanded = child.getTop();
                    break;
            }
        }
    }

    private WindowInsetsCompat setWindowInsets(WindowInsetsCompat insets) {
        if (mLastInsets != insets) {
            mLastInsets = insets;
            requestLayout();
        }
        return insets.consumeSystemWindowInsets();
    }
    class OnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {

        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            final int insetTop = mLastInsets != null ? mLastInsets.getSystemWindowInsetTop() : 0;
            final int scrollRange = appBarLayout.getTotalScrollRange();
            float offsetFactor = (float) (-verticalOffset) / (float) scrollRange;
            final int heightDiff = getHeight() - getMinimumHeight();

            Log.d(TAG, "onOffsetChanged(), offsetFactor = " + offsetFactor);
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                final ViewOffsetHelper offsetHelper = getViewOffsetHelper(child);

                if (child instanceof Toolbar) {
                    if (getHeight() - insetTop + verticalOffset >= child.getHeight()) {
                        offsetHelper.setTopAndBottomOffset(-verticalOffset);
                    }

                }
                if (child.getId() == R.id.background) {
                    int offset = Math.round(-verticalOffset * .2F);
                    offsetHelper.setTopAndBottomOffset(offset);
                }

                if (child.getId() == R.id.profilepic) {

                    float scaleFactor = 1F - offsetFactor * .65F;
                    child.setScaleX(scaleFactor);
                    child.setScaleY(scaleFactor);

                    int topOffset = (int) ((mImageTopCollapsed - mImageTopExpanded) * offsetFactor) - verticalOffset;
                    int leftOffset = (int) ((mImageLeftCollapsed - mImageLeftExpanded) * offsetFactor);
                    child.setPivotX(0);
                    child.setPivotY(0);
                    offsetHelper.setTopAndBottomOffset(topOffset);
                    offsetHelper.setLeftAndRightOffset(leftOffset);
                }
                if (child.getId() == R.id.username) {

                    int topOffset = (int) ((mTitleTopCollapsed - mTitleTopExpanded) * offsetFactor) - verticalOffset;
                    int leftOffset = (int) ((mTitleLeftCollapsed - mTitleLeftExpanded) * offsetFactor);
                    offsetHelper.setTopAndBottomOffset(topOffset);
                    offsetHelper.setLeftAndRightOffset(leftOffset);
                    Log.d(TAG, "onOffsetChanged(), offsetting title top = " + topOffset + ", left = " + leftOffset);
                    Log.d(TAG, "onOffsetChanged(), offsetting title mTitleLeftCollapsed = " + mTitleLeftCollapsed + ", mTitleLeftExpanded = " + mTitleLeftExpanded);
                }
                if (child.getId() == R.id.detaillayout) {
                    int offset = Math.round(-verticalOffset * .4F);
                    offsetHelper.setTopAndBottomOffset(offset);
                }
            }
        }
    }
    private static ViewOffsetHelper getViewOffsetHelper(View view) {
        ViewOffsetHelper offsetHelper = (ViewOffsetHelper) view.getTag(R.id.view_offset_helper);
        if (offsetHelper == null) {
            offsetHelper = new ViewOffsetHelper(view);
            view.setTag(R.id.view_offset_helper, offsetHelper);
        }
        return offsetHelper;
    }
    static class ViewOffsetHelper {
        private final View mView;
        private int mLayoutTop;
        private int mLayoutLeft;
        private int mOffsetTop;
        private int mOffsetLeft;

        public ViewOffsetHelper(View view) {
            mView = view;
        }

        public void onViewLayout() {
            mLayoutTop = mView.getTop();
            mLayoutLeft = mView.getLeft();

            updateOffsets();
        }

        private void updateOffsets() {
            ViewCompat.offsetTopAndBottom(mView, mOffsetTop - (mView.getTop() - mLayoutTop));
            ViewCompat.offsetLeftAndRight(mView, mOffsetLeft - (mView.getLeft() - mLayoutLeft));

            if (Build.VERSION.SDK_INT < 23) {
                tickleInvalidationFlag(mView);
                final ViewParent vp = mView.getParent();
                if (vp instanceof View) {
                    tickleInvalidationFlag((View) vp);
                }
            }
        }

        private static void tickleInvalidationFlag(View view) {
            final float y = ViewCompat.getTranslationY(view);
            ViewCompat.setTranslationY(view, y + 1);
            ViewCompat.setTranslationY(view, y);
        }

        /**
         * Set the top and bottom offset for this {@link ViewOffsetHelper}'s view.
         *
         * @param offset the offset in px.
         * @return true if the offset has changed
         */
        public boolean setTopAndBottomOffset(int offset) {
            if (mOffsetTop != offset) {
                mOffsetTop = offset;
                updateOffsets();
                return true;
            }
            return false;
        }

        /**
         * Set the left and right offset for this {@link ViewOffsetHelper}'s view.
         *
         * @param offset the offset in px.
         * @return true if the offset has changed
         */
        public boolean setLeftAndRightOffset(int offset) {
            if (mOffsetLeft != offset) {
                mOffsetLeft = offset;
                updateOffsets();
                return true;
            }
            return false;
        }

        public int getTopAndBottomOffset() {
            return mOffsetTop;
        }

        public int getLeftAndRightOffset() {
            return mOffsetLeft;
        }
    }
}

