
package com.ikmr.banbara23.listfragmentsample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 一覧フラグメント
 */
public class StoreListFragment extends ListFragment implements ListView.OnScrollListener {

    StoreListAdapter mListAdapter;
    ProgressBar mProgressBar;
    ListView mListView;
    private OnMyScrollListener mMyScrollListener;
    boolean initFlag = true;

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
        mListView.setOnScrollListener(this);
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
        List<Shop> shops = new ArrayList<Shop>();
        Shop shop;

        for (int i = 1; i < 15; i++) {
            shop = new Shop();
            shop.setId(String.valueOf(i));
            shop.setName("label");
            shops.add(shop);
        }

        return shops;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
//        Log.d("StoreListFragment", "scrollState:" + scrollState);
//        if (initFlag && scrollState > 0) {
//            initFlag = false;
//        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        if (initFlag) {
//            return;
//        }
        mMyScrollListener.onScroll(firstVisibleItem, visibleItemCount, totalItemCount);
    }

    public void setOnScrollListener(Activity activity) {
        mMyScrollListener = (OnMyScrollListener) activity;
    }

    public interface OnMyScrollListener {
        public void onScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount);
    }
}
