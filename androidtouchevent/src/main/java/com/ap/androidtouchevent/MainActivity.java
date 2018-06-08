package com.ap.androidtouchevent;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements MyView.ISetText {

    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyView childView = findViewById(R.id.tv_content);
        childView.setiSetText(this);
        content = findViewById(R.id.content);
    }

    @Override
    public void setText() {
        content.setText(Log.buffer);
        Log.clear();
    }
}
