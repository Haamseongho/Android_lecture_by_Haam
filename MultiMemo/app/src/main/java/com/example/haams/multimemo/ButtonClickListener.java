package com.example.haams.multimemo;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;

/**
 * Created by haams on 2017-08-15.
 */
public class ButtonClickListener implements View.OnClickListener {
    private AlertDialog.Builder dlg;
    private Context context;
    MainActivity mActivity;

    public ButtonClickListener(Context context, AlertDialog.Builder dlg) {
        this.context = context;
        this.dlg = dlg;
        mActivity = new MainActivity();
    }


    @Override
    public void onClick(View v) {

    }
}
