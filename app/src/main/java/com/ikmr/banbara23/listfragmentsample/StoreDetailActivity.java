
package com.ikmr.banbara23.listfragmentsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 詳細アクティビティ
 */
public class StoreDetailActivity extends ActionBarActivity {
    public static final String KEY_STORE_CODE = "key_store_code";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            StoreDetailFragment storeDetailFragment = StoreDetailFragment.NewInstance(getStoreCode());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, storeDetailFragment)
                    .commit();
        }
    }

    /**
     * Intentから店舗コード取得
     *
     * @return 店舗コード
     */
    private Shop getStoreCode() {
        return (Shop) getIntent().getSerializableExtra(KEY_STORE_CODE);
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
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
