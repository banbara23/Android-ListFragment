
package com.ikmr.banbara23.listfragmentsample;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * 一覧のカスタムアダプター
 */
public class StoreListAdapter extends ArrayAdapter<Shop> {
    ListItemClickListener mListItemClickListener;

    public StoreListAdapter(Context context, List<Shop> objects, FragmentActivity fragmentActivity) {
        super(context, R.layout.view_store_list, objects);
        mListItemClickListener = (ListItemClickListener) fragmentActivity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        StoreListView storeListView = null;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            storeListView = (StoreListView) inflater.inflate(R.layout.view_store_list, parent, false);
        } else {
            storeListView = (StoreListView) convertView;
        }

        storeListView.bindView(getItem(position));
        storeListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListItemClickListener.onItemClick(getItem(position));
            }
        });
        return storeListView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 通知用
     */
    public interface ListItemClickListener {
        void onItemClick(Shop shop);
    }
}
