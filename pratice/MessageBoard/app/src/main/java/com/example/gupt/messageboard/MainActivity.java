package com.example.gupt.messageboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_msg : break;
            case R.id.btn_refresh : break;
            case R.id.btn_other : break;
            default: break;
        }
    }
}
