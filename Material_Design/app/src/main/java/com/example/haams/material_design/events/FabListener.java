package com.example.haams.material_design.events;

import android.content.Context;
import android.view.View;

import com.example.haams.material_design.MainActivity;
import com.example.haams.material_design.R;

/**
 * Created by haams on 2017-08-22.
 */
public class FabListener implements View.OnClickListener {
    private Context context;

    public FabListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_fab:

                break;

            case R.id.spot_fab:

                break;
            case R.id.chat_fab:

                break;
        }
    }
}
