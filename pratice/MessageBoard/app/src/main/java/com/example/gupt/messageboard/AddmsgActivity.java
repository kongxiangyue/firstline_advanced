package com.example.gupt.messageboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Kong on 2018/11/14 0014.
 */

public class AddmsgActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmsg_activity);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_get : break;
            case R.id.btn_submit_post : break;
            case R.id.btn_back : finish(); break;
            default: break;
        }
    }
}
