
package com.ikmr.banbara23.listfragmentsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * トップページ
 */
public class TopActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        // ボタン押下
        findViewById(R.id.activity_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goList();
            }
        });

        // ボタン2 押下
        findViewById(R.id.activity_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goList2();
            }
        });
        // ボタン3 押下
        findViewById(R.id.activity_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goList3();
            }
        });
    }

    /**
     * 一覧に遷移
     */
    private void goList() {
        Intent intent = new Intent(this, StoreListActivity.class);
        startActivity(intent);
    }

    /**
     * 一覧に遷移
     */
    private void goList2() {
        Intent intent = new Intent(this, StoreListActivity2.class);
        startActivity(intent);
    }

    private void goList3() {
        Intent intent = new Intent(this, StoreListActivity3.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
