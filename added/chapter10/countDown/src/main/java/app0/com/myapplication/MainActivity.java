package app0.com.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Handler handler;
    private static int countDownMsg = 0;
    private static int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.textView);

        handler = new Handler() {


            @Override
            public void handleMessage(Message msg) {
                if (countDownMsg ==  msg.what) {
                    textView.setText(String.valueOf(count--));
                }
            }
        };
    }

    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int run = count;
                    while (run-- > 0) {
                        Thread.sleep(1000);
                        Message message = new Message();
                        message.what = countDownMsg;
                        handler.sendMessage(message);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
