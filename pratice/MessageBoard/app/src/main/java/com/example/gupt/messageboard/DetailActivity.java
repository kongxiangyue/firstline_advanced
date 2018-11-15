package com.example.gupt.messageboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Kong on 2018/11/14 0014.
 */

public class DetailActivity extends Activity {
    private TextView text_detail;
    private TextView text_author;
    private TextView text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        text_detail = (TextView) findViewById(R.id.text_detail);
        text_author = (TextView) findViewById(R.id.text_author);
        text_title = (TextView) findViewById(R.id.text_title);

        text_detail.setText(getIntent().getExtras().getString("detail"));
        text_author.setText(getIntent().getExtras().getString("author"));
        text_title.setText(getIntent().getExtras().getString("title"));

        Button button = (Button) findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
