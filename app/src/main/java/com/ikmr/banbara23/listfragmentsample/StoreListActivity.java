
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
public class StoreListActivity extends AppCompatActivity implements StoreListAdapter.ListItemClickListener, StoreListFragment.OnMyScrollListener {

    FloatingActionButton mFloatingActionButton;
    Toolbar mToolbar;
    StoreListFragment storeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            storeListFragment = new StoreListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, storeListFragment)
                    .commit();
            storeListFragment.setOnScrollListener(this);

        }

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FabTransformation.with(mFloatingActionButton).transformFrom(storeListFragment.getHeaderView());
            }
        });
//        mToolbar = (Toolbar) findViewById(R.id.toolbar_footer);
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
        Log.d("StoreListActivity", "firstVisibleItem:" + firstVisibleItem + " visibleItemCount:" + visibleItemCount + " totalItemCount:" + totalItemCount);
        if (firstVisibleItem > 0) {
            showFloatingActionButton();
        } else {
            hideFloatingActionButton();
        }
    }

    private void showFloatingActionButton() {
        if (mFloatingActionButton.getVisibility() == View.VISIBLE) {
            return;
        }
        Log.d("StoreListActivity", "show");
        View header = storeListFragment.getHeaderView();
        FabTransformation.with(mFloatingActionButton).transformTo(header);
    }

    private void hideFloatingActionButton() {
        if (mFloatingActionButton.getVisibility() != View.VISIBLE) {
            return;
        }
        Log.d("StoreListActivity", "hide");
        View header = storeListFragment.getHeaderView();
        if (header == null) {
            return;
        }
        FabTransformation.with(mFloatingActionButton).transformFrom(header);
    }

    @Override
    public void onItemClick(Shop shop) {
        goStoreDetail(shop);
    }
}
