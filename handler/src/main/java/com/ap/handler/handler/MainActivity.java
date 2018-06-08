package com.ap.handler.handler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ap.handler.R;

public class MainActivity extends AppCompatActivity {

    private MyThread thread = null;
    private StringBuffer buffer = null;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread = new MyThread(handler);
        start = findViewById(R.id.start);
        UserManager.USERID = 2;
        TestLog.d("MainActivity:" + UserManager.USERID);
    }

    public void onBtnClick(View v) {
        startActivity(new Intent(this, ThrActivity.class));
        /*start.setEnabled(false);
        buffer = new StringBuffer();
        thread.start();*/
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void dispatchMessage(Message msg) {
            buffer.append("dispatchMessage").append("\n");
            super.dispatchMessage(msg);
        }

        @Override
        public void handleMessage(Message msg) {
            buffer.append(msg.obj.toString());
            start.setText(buffer);
            start.setEnabled(true);
            super.handleMessage(msg);
        }
    };

    public void seta() {
    }
}
