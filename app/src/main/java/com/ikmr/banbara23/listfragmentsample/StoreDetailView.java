
package com.ikmr.banbara23.listfragmentsample;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 店舗詳細カスタムビュー
 */
public class StoreDetailView extends LinearLayout {
    // ジャンル
    TextView mShopIdText;
    // タイトル
    TextView mNameText;
    //ボタン
    Button mButton;

    public StoreDetailView(Context context) {
        super(context);
    }

    public StoreDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View layout = LayoutInflater.from(context).inflate(R.layout.view_store_detail, this);

        mShopIdText = (TextView) layout.findViewById(R.id.view_store_detail_genre_text);
        mNameText = (TextView) layout.findViewById(R.id.view_store_detail_name_text);
        mButton = (Button) layout.findViewById(R.id.view_store_detail_button);
    }

    /**
     * ビューに店舗詳細を反映
     *
     * @param shop
     * @param listener
     */
    public void bindView(Shop shop, OnClickListener listener) {
        if (shop == null) {
            return;
        }
        // ID
        setShopId(shop.getId());
        // 名前
        setName(shop.getName());
        //ボタン
        setButtonListener(listener);

    }

    /**
     * ID
     *
     * @param id
     */
    private void setShopId(String id) {
        if (TextUtils.isEmpty(id)) {
            mShopIdText.setVisibility(GONE);
            return;
        }
        mShopIdText.setText(id);
    }

    /**
     * 名前を設定
     *
     * @param name 名前
     */
    private void setName(String name) {
        if (TextUtils.isEmpty(name)) {
            mNameText.setVisibility(GONE);
            return;
        }
        mNameText.setText(name);
    }

    /**
     * ボタン設定
     *
     * @param listener
     */
    private void setButtonListener(OnClickListener listener) {
        mButton.setOnClickListener(listener);
    }
}
