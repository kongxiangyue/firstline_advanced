package com.example.gupt.messageboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gupt.messageboard.model.Messege;
import com.example.gupt.messageboard.network.HttpUtil;
import com.example.gupt.messageboard.ui.ListAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        getAllMsgAndShow();
    }

    public  void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_msg : break;
            case R.id.btn_refresh : break;
            case R.id.btn_other : break;
            default: break;
        }
    }

    private void bindUI() {
        listView = (ListView) findViewById(R.id.listview);
    }

    private void getAllMsgAndShow() {
        String address = getResources().getString(R.string.http)
                + getResources().getString(R.string.server_ip)
                + getResources().getString(R.string.get_all_msg);

        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this
                                , MainActivity.this.getResources().getString(R.string.all_msg_error)
                                , Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            handleAllMsgResponse(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    void handleAllMsgResponse(String body_str) {
        Gson gson = new Gson();
        List<Messege> msg_list = gson.fromJson(body_str
                , new TypeToken<List<Messege>>() {}.getType());
        listView.setAdapter(new ListAdapter(this, msg_list));


    }



}
