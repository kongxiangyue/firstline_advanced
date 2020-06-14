package com.example.gupt.messageboard;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gupt.messageboard.global.UserInfo;
import com.example.gupt.messageboard.model.Messege;
import com.example.gupt.messageboard.util.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Kong on 2018/11/14 0014.
 */

public class AddmsgActivity extends Activity {
    private EditText edit_detail;
    private EditText edit_author;
    private EditText edit_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmsg_activity);
        bindUI();
        edit_author.setText(UserInfo.getInstance().getUsername());
    }

    private void bindUI() {
        edit_detail = (EditText) findViewById(R.id.edit_detail);
        edit_author = (EditText) findViewById(R.id.edit_author);
        edit_title = (EditText) findViewById(R.id.edit_title);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit_get : handleSumitGet(); break;
            case R.id.btn_submit_post : break;
            case R.id.btn_back : finish(); break;
            default: break;
        }
    }

    private boolean getUIParamASMessege(Messege msg) {
        do {
            msg.setAuthor(edit_author.getText().toString());
            msg.setTitle(edit_title.getText().toString());
            msg.setDetail(edit_detail.getText().toString());

            if ("".equals(msg.getAuthor())
                    || "".equals(msg.getTitle())
                    || "".equals(msg.getDetail())) {
                break;
            }

            return true;
        } while (false);

         return false;
    }

    private void handleSumitGet() {
        Messege msg = new Messege();
        do {
            if (!getUIParamASMessege(msg)) {
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Request request = getGetRequest(msg);
                doRuquest(request);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }



        } while (false);

    }

    private Request getGetRequest(Messege msg) throws UnsupportedEncodingException {
        String url = Util.http + Util.server_ip + Util.add_msg
                + "?author=" + URLEncoder.encode(msg.getAuthor(), "UTF-8")
                + "&title=" + URLEncoder.encode(msg.getTitle(), "UTF-8")
                + "&detail=" + URLEncoder.encode(msg.getDetail(), "UTF-8");

        Request request = new Request.Builder()
                .url(url)
                .build();
        return request;
    }

    private void doRuquest(final Request request) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Response response = client.newCall(request).execute();
                    handleSumitResult(response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void handleSumitResult(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if ("success".equals(result)) {
                    Toast.makeText(AddmsgActivity.this
                            , "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddmsgActivity.this
                            , "提交失败", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}
