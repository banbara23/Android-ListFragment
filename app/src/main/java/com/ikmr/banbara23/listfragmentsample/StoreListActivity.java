package com.ikmr.banbara23.listfragmentsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * 一覧Activity
 */
public class StoreListActivity extends AppCompatActivity implements StoreListAdapter.ListItemClickListener {

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
        }
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
        goStoreDetail(shop);
    }
}
