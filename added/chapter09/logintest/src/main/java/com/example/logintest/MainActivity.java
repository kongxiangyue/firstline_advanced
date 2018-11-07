package com.example.logintest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText usernameEd;
    EditText passwordEd;
    Button loginBtn;
    String url;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();

        url = new String("http://172.16.33.191:5000/login");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                username = usernameEd.getText().toString();
                password = passwordEd.getText().toString();

                loginGet();
            }
        });
    }

    private void bindUI() {
        usernameEd = (EditText) findViewById(R.id.editText);
        passwordEd = (EditText) findViewById(R.id.editText2);
        loginBtn = (Button) findViewById(R.id.button);
    }

    private void loginGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url + "?username="
                                    + username + "&password=" + password)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showResponse(parseJSONWithGSON(responseData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }



    private LoginMsg parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        LoginMsg ret = gson.fromJson(jsonData
                , new TypeToken<LoginMsg>() {}.getType());

        return ret;
    }

    private void  showResponse(final LoginMsg loginMsg) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String toast = "login failed";
                if (loginMsg.isLogined()) {
                    toast = "login success";
                }

                Toast.makeText(MainActivity.this
                        , toast
                        , Toast.LENGTH_SHORT).show();

            }
        });
    }




}
