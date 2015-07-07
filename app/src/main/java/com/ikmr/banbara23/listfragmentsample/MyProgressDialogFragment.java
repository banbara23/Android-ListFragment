package com.ikmr.banbara23.listfragmentsample;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.os.Bundle;

/**
 * Created by banbara23 on 15/07/08.
 */
public class MyProgressDialogFragment extends DialogFragment {

    private static ProgressDialog progressDialog = null;

    public static MyProgressDialogFragment NewInstance(String title) {
        MyProgressDialogFragment myProgressDialogFragment = new MyProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        myProgressDialogFragment.setArguments(bundle);
        return myProgressDialogFragment;
    }

    public MyProgressDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (progressDialog != null) {
            return progressDialog;
        }
        String title = getArguments().getString("title");
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(title);
        progressDialog.setMessage("処理中...");
        //スタイルを円タイプに指定
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        //クリアボタンでも消せない指定、個人的にかなりウザい
        setCancelable(false);

        return progressDialog;
    }

    @Override
    public Dialog getDialog() {
        return progressDialog;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.dismiss();
        progressDialog = null;
    }
}
