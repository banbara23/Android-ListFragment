
package com.ikmr.banbara23.listfragmentsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 詳細フラグメント
 */
public class StoreDetailFragment extends Fragment {
    static final String KEY_SHOP = "shop";
    StoreDetailView mStoreDetailView;

    public static StoreDetailFragment NewInstance(Shop shop) {
        StoreDetailFragment storeDetailFragment = new StoreDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_SHOP, shop);
        storeDetailFragment.setArguments(bundle);
        return storeDetailFragment;
    }

    /**
     * 店舗IDを取得
     *
     * @return 店舗ID
     */
    private Shop getShop() {
        return (Shop) getArguments().getSerializable(KEY_SHOP);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_detail, container, false);
        mStoreDetailView = (StoreDetailView) view.findViewById(R.id.fragment_detail_view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        callApi();
    }

    private void callApi() {
        //詳細APIの処理

        //結果を受け取ったとして...
        mStoreDetailView.bindView(getShop(), createListener());
    }

    private View.OnClickListener createListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProgressDialogFragment myProgressDialogFragment = MyProgressDialogFragment.NewInstance("考え中");
                myProgressDialogFragment.show(getActivity().getFragmentManager(), null);
            }
        };
    }
}
