
package com.ikmr.banbara23.listfragmentsample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.annotation.DimenRes;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageButton;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

/**
 * Created by ikemurakazutaka on 2015/08/21.
 */
public class FloatingActionButton extends ImageButton {
    private static final int TRANSLATE_DURATION_MILLIS = 200;
    private int mLastScrollY;
    private boolean mVisible;
    private boolean mShadow;
    private int mScrollThreshold;

    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        mVisible = true;
        mShadow = true;
        mScrollThreshold = getResources().getDimensionPixelOffset(R.dimen.fab_scroll_threshold);
        if (attributeSet != null) {
            initAttributes(context, attributeSet);
        }
        updateBackground();
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.FloatingActionButton);
        if (attr != null) {
            try {
                mShadow = attr.getBoolean(R.styleable.FloatingActionButton_fab_shadow, true);
            } finally {
                attr.recycle();
            }
        }
    }

    private void updateBackground() {
        StateListDrawable drawable = new StateListDrawable();
        setBackgroundCompat(drawable);
    }

    private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    private int getDimension(@DimenRes int id) {
        return getResources().getDimensionPixelSize(id);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    private void setBackgroundCompat(Drawable drawable) {
        if (hasLollipopApi()) {
            float elevation;
            if (mShadow) {
                elevation = getElevation() > 0.0f ? getElevation() : getDimension(R.dimen.fab_elevation_lollipop);
            } else {
                elevation = 0.0f;
            }
            setElevation(elevation);
        } else if (hasJellyBeanApi()) {
            setBackground(drawable);
        } else {
            setBackgroundDrawable(drawable);
        }
    }

    private int getMarginBottom() {
        int marginBottom = 0;
        final ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            marginBottom = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return marginBottom;
    }

    public boolean isVisible() {
        return mVisible;
    }

    public void show() {
        show(true);
    }

    public void hide() {
        hide(true);
    }

    public void show(boolean animate) {
        toggle(true, animate, false);
    }

    public void hide(boolean animate) {
        toggle(false, animate, false);
    }

    private void toggle(final boolean visible, final boolean animate, boolean force) {
        if (mVisible != visible || force) {
            mVisible = visible;
            int height = getHeight();
            if (height == 0 && !force) {
                ViewTreeObserver vto = getViewTreeObserver();
                if (vto.isAlive()) {
                    vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            ViewTreeObserver currentVto = getViewTreeObserver();
                            if (currentVto.isAlive()) {
                                currentVto.removeOnPreDrawListener(this);
                            }
                            toggle(visible, animate, true);
                            return true;
                        }
                    });
                    return;
                }
            }
            int translationY = visible ? 0 : height + getMarginBottom();
            if (animate) {
                ViewPropertyAnimator.animate(this).setInterpolator(mInterpolator).setDuration(TRANSLATE_DURATION_MILLIS).translationY(translationY);
            } else {
                ViewHelper.setTranslationY(this, translationY);
            }

            if (!hasHoneycombApi()) {
                setClickable(visible);
            }
        }
    }

    // public void attachToScrollView(@NonNull ObservableScrollView scrollView)
    // {
    // attachToScrollView(scrollView, null, null);
    // }
    //
    // public void attachToScrollView(@NonNull ObservableScrollView scrollView,
    // ScrollDirectionListener scrollDirectionListener,
    // ObservableScrollView.OnScrollChangedListener onScrollChangedListener) {
    // ScrollViewScrollDetectorImpl scrollDetector = new
    // ScrollViewScrollDetectorImpl();
    // scrollDetector.setScrollDirectionListener(scrollDirectionListener);
    // scrollDetector.setOnScrollChangedListener(onScrollChangedListener);
    // scrollDetector.setScrollThreshold(mScrollThreshold);
    // scrollView.setOnScrollChangedListener(scrollDetector);
    // }

    private boolean hasLollipopApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    private boolean hasJellyBeanApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }

    private boolean hasHoneycombApi() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }
    //
    // private class ScrollViewScrollDetectorImpl implements
    // ObservableScrollView.OnScrollChangedListener {
    //
    // private ScrollDirectionListener mScrollDirectionListener;
    //
    // private ObservableScrollView.OnScrollChangedListener
    // mOnScrollChangedListener;
    //
    // private void setScrollDirectionListener(ScrollDirectionListener
    // scrollDirectionListener) {
    // mScrollDirectionListener = scrollDirectionListener;
    // }
    //
    // public void
    // setOnScrollChangedListener(ObservableScrollView.OnScrollChangedListener
    // onScrollChangedListener) {
    // mOnScrollChangedListener = onScrollChangedListener;
    // }
    //
    // public void setScrollThreshold(int scrollThreshold) {
    // mScrollThreshold = scrollThreshold;
    // }
    //
    // public void onScrollDown() {
    // show();
    // if (mScrollDirectionListener != null) {
    // mScrollDirectionListener.onScrollDown();
    // }
    // }
    //
    // public void onScrollUp() {
    // hide();
    // if (mScrollDirectionListener != null) {
    // mScrollDirectionListener.onScrollUp();
    // }
    // }
    //
    // @Override
    // public void onScrollChanged(ScrollView who, int l, int t, int oldl, int
    // oldt) {
    // if (mOnScrollChangedListener != null) {
    // mOnScrollChangedListener.onScrollChanged(who, l, t, oldl, oldt);
    // }
    //
    // boolean isSignificantDelta = Math.abs(t - mLastScrollY) >
    // mScrollThreshold;
    // if (isSignificantDelta) {
    // if (t > mLastScrollY) {
    // onScrollUp();
    // } else {
    // onScrollDown();
    // }
    // }
    // mLastScrollY = t;
    // }
    // }
}
