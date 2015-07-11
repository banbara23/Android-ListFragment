
package com.ikmr.banbara23.listfragmentsample;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 一覧のビュー
 */
public class StoreListView extends RelativeLayout {

    TextView mShopIdText;
    TextView mNameText;
    ImageView mPhotoImage;

    public StoreListView(Context context) {
        super(context);
    }

    public StoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mShopIdText = (TextView) findViewById(R.id.view_store_list_id_text);
        mNameText = (TextView) findViewById(R.id.view_store_list_name_text);
        mPhotoImage = (ImageView) findViewById(R.id.view_store_list_photo_image);
    }

    private boolean isEmpty(String value) {
        return TextUtils.isEmpty(value);
    }

    public void bindView(Shop shop) {
        if (shop == null) {
            return;
        }
        setShopId(shop.getId());
        setPhoto();
        setName(shop.getName());
    }

    private void setShopId(String id) {
        if (TextUtils.isEmpty(id)) {
            mShopIdText.setVisibility(GONE);
            return;
        }
        mShopIdText.setVisibility(VISIBLE);
        mShopIdText.setText(id);
    }

    private void setPhoto() {

    }

    private void setName(String name) {
        if (isEmpty(name)) {
            mNameText.setVisibility(GONE);
            return;
        }
        mNameText.setVisibility(VISIBLE);
        mNameText.setText(name);
    }
}
