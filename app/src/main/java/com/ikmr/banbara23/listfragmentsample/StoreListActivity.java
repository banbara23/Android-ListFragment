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
import android.widget.EditText;
import android.widget.Toast;

/**
 * 一覧Activity
 */
public class StoreListActivity extends ActionBarActivity implements StoreListAdapter.ListItemClickListener, StoreListFragment.OnMyScrollListener {

    boolean showFloating = true;
    int DURATION = 150;

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
        //検索アイコン
        findViewById(R.id.activity_search_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StoreListActivity.this, "検索", Toast.LENGTH_SHORT).show();
            }
        });
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
            openFloatingSearch();
        } else {
            closeFloatingSearch();
        }
    }

    /**
     * フローティング検索を開く
     */
    private void openFloatingSearch() {
        if (showFloating) {
            return;
        }
        Log.d("StoreListActivity", "show");
        showFloating = true;
        openTextAnimation();
        openIconBackAnimation();
    }

    /**
     * 開くアニメーション EditText
     */
    private void openTextAnimation() {

        final EditText editText = (EditText) findViewById(R.id.activity_search_edit_text);
        // 開始横幅, 終了横幅, 開始縦幅, 終了横幅;
        // ABSOLUTE：原点
        // Animation.RELATIVE_TO_SELF :相対的
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, Animation.ABSOLUTE, 1.0f, Animation.ABSOLUTE, 0.5f);
        anim.setDuration(DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                editText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        editText.startAnimation(anim);
    }

    /**
     * 開くアニメーション Icon背景
     */
    private void openIconBackAnimation() {
        final View iconBackView = findViewById(R.id.activity_search_corner_round_large);
        // 開始横幅, 終了横幅, 開始縦幅, 終了横幅;
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                iconBackView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iconBackView.startAnimation(anim);
    }

    /**
     * フローティング検索を閉じる
     */
    private void closeFloatingSearch() {
        if (!showFloating) {
            return;
        }
        Log.d("StoreListActivity", "hide");
        showFloating = false;
        closeTextAnimation();
        closeIconBackAnimation();
    }

    /**
     * 閉じるアニメーション EditText
     */
    private void closeTextAnimation() {
        final EditText editText = (EditText) findViewById(R.id.activity_search_edit_text);
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
                editText.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        editText.startAnimation(anim);
    }

    /**
     * 閉じるアニメーション Icon背景
     */
    private void closeIconBackAnimation() {
        final View iconBackView = findViewById(R.id.activity_search_corner_round_large);
        // 開始横幅, 終了横幅, 開始縦幅, 終了横幅;
        ScaleAnimation anim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(DURATION);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iconBackView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iconBackView.startAnimation(anim);
    }
}
