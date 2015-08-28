package com.ikmr.banbara23.listfragmentsample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;

/**
 *
 */
public class FloatingSearchView extends FrameLayout {
    View mBackView;
    EditText mEditText;
    int DURATION = 500;

    public FloatingSearchView(Context context) {
        super(context);
    }

    public FloatingSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View layout = LayoutInflater.from(context).inflate(R.layout.view_floating_search, this);
        mBackView = layout.findViewById(R.id.view_search_corner_round_large);
        mEditText = (EditText) layout.findViewById(R.id.view_search_edit_text);
    }

    public void startShowAnimation() {
        // 開始横幅, 終了横幅, 開始縦幅, 終了横幅;
        // ABSOLUTE：原点
        // Animation.RELATIVE_TO_SELF :相対的
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.ABSOLUTE, 1.0f, Animation.ABSOLUTE, 0.5f);
        anim.setDuration(DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mEditText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mEditText.startAnimation(anim);
    }

    public void startHideAnimation() {
        // 開始横幅, 終了横幅, 開始縦幅, 終了横幅;
        // ABSOLUTE：原点
        // Animation.RELATIVE_TO_SELF :相対的
        ScaleAnimation anim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, Animation.ABSOLUTE, 1.0f, Animation.ABSOLUTE, 0.5f);
        anim.setDuration(DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mEditText.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mEditText.startAnimation(anim);
    }


}