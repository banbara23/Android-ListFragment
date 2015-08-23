
package com.ikmr.banbara23.listfragmentsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.konifar.fab_transformation.FabTransformation;

/**
 * 一覧Activity
 */
public class StoreListActivity2 extends AppCompatActivity implements
        StoreListAdapter.ListItemClickListener, StoreListFragment.OnMyScrollListener {

    FloatingActionButton mFloatingActionButton;
    Toolbar mToolbar;
    FloatingSearchView mFloatingSearchView;
    StoreListFragment storeListFragment;
    boolean showFloating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            storeListFragment = new StoreListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, storeListFragment)
                    .commit();
            storeListFragment.setOnScrollListener(this);

        }
        // 検索ビュー
        mFloatingSearchView = (FloatingSearchView) findViewById(R.id.activity_floating_search2);
        mFloatingSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        // フローティングボタン
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab2);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        // mToolbar = (Toolbar) findViewById(R.id.toolbar_footer);
    }

    private void search() {
        Toast.makeText(this, "検索", Toast.LENGTH_SHORT).show();
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
    public void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d("StoreListActivity", "firstVisibleItem:" + firstVisibleItem + " visibleItemCount:"
                + visibleItemCount + " totalItemCount:" + totalItemCount);
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
        Log.d("StoreListActivity", "show");
        showFloating = true;
        FabTransformation.with(mFloatingActionButton).transformFrom(mFloatingSearchView);
    }

    private void hideFloatingActionButton() {
        if (!showFloating) {
            return;
        }
        Log.d("StoreListActivity", "hide");
        showFloating = false;
        FabTransformation.with(mFloatingActionButton).transformTo(mFloatingSearchView);
    }

    @Override
    public void onItemClick(Shop shop) {
        goStoreDetail(shop);
    }
}
