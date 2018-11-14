package com.example.gupt.messageboard.network;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}