
package com.ikmr.banbara23.listfragmentsample;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 一覧フラグメント
 */
public class StoreListFragment extends ListFragment {

    StoreListAdapter mListAdapter;
    ProgressBar mProgressBar;
    ListView mListView;

    /**
     * Fragmentには空コンストラクタを必ず作る
     */
    public StoreListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new StoreListAdapter(getActivity().getApplicationContext(), new ArrayList<Shop>(), getActivity());
        setListAdapter(mListAdapter);
    }

    /**
     * Fragmentのレイアウトを設定
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mListView = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        callApi();
    }

    /**
     * APIを呼び出す
     */
    private void callApi() {
        mListAdapter.clear();

        //API call

        //結果を受け取ったとして
        mListAdapter.addAll(getShop());
        mListView.setVisibility(View.VISIBLE);
    }

    /**
     * 結果を作成
     *
     * @return
     */
    private List<Shop> getShop() {
        List<Shop> shops = new ArrayList<>();
        Shop shop = new Shop();

        shop.setId("1");
        shop.setName("first");
        shops.add(shop);

        shop = new Shop();
        shop.setId("2");
        shop.setName("second");
        shops.add(shop);

        shop = new Shop();
        shop.setId("3");
        shop.setName("Third");
        shops.add(shop);

        return shops;
    }


}
