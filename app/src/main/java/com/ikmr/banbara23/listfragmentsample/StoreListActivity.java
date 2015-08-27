package com.ikmr.banbara23.listfragmentsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

/**
 * 一覧Activity
 * <p/>
 * 縮小アニメーションのお手本
 * https://github.com/2359media/EasyAndroidAnimations/blob/48741f599ce6090d81138c9d4423fca115340176/Library/src/com/easyandroidanimations/library/ScaleOutAnimation.java
 * <p/>
 * 丸角の情報
 * http://asky.sakura.ne.jp/wp/2015/05/17/051702/
 * <p/>
 * Morphアニメーションのお手本
 * https://github.com/ozodrukh/CircularReveal/tree/master/circualreveal/src/main/java/io/codetail/animation
 */
public class StoreListActivity extends ActionBarActivity implements StoreListAdapter.ListItemClickListener, StoreListFragment.OnMyScrollListener {

    boolean showFloating = true;
    int DURATION = 300;
    FloatingMiniSearchView mMiniSearchView;
    FloatingSearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            StoreListFragment storeListFragment = new StoreListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, storeListFragment)
                    .commit();
            storeListFragment.setOnScrollListener(this);
        }
        mMiniSearchView = (FloatingMiniSearchView) findViewById(R.id.fragment_mini_floating_search);
        mSearchView = (FloatingSearchView) findViewById(R.id.fragment_floating_search);
    }

    /**
     * 店舗詳細画面へ遷移
     *
     * @param shop
     */
    private void goStoreDetail(Shop shop) {
        Intent intent = new Intent(this, StoreDetailActivity.class);
        intent.putExtra(StoreDetailActivity.KEY_STORE_CODE, shop);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reload, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                return true;
            case R.id.action_reload:
                Toast.makeText(this, "更新処理", Toast.LENGTH_SHORT).show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(Shop shop) {
//        goStoreDetail(shop);
    }

    @Override
    public void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem > 0) {
            showFloatingActionButton();
        } else {
            hideFloatingActionButton();
        }
    }

    private void showFloatingActionButton() {
        if (showFloating) {
            return;
        }
        if (mSearchView.getVisibility() == View.VISIBLE) {
            return;
        }
        Log.d("StoreListActivity", "show");
        showFloating = true;
        showAnimation();
    }

    private void showAnimation() {
        // 開始横幅, 終了横幅, 開始縦幅, 終了横幅;
        // ABSOLUTE：原点
        // Animation.RELATIVE_TO_SELF :相対的
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.ABSOLUTE, 1.0f, Animation.ABSOLUTE, 0.5f);
        anim.setDuration(DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mSearchView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSearchView.startAnimation(anim);

    }

    private void hideFloatingActionButton() {
        if (!showFloating) {
            return;
        }
        if (mSearchView.getVisibility() == View.GONE) {
            return;
        }
        Log.d("StoreListActivity", "hide");
        showFloating = false;
        hideAnimation();
    }

    private void hideAnimation() {
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
                mSearchView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mSearchView.startAnimation(anim);
    }
}
