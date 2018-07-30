package com.ap.aouter.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.ap.aouter.R;
import com.ap.aouter.base.BaseActivity;
import com.ap.aouter.model.person;

/**
 * 类描述：
 * 创建人：swallow.li
 * 创建时间：
 * Email: swallow.li@kemai.cn
 * 修改备注：
 */
@Route(path = "/ap/two", group = "activity")
public class ArouterActivity extends BaseActivity {

    @Autowired
    public int aLong;
    @Autowired
    public String name;

    @Override
    @SuppressLint("SetTextI18n")
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_arouter);

        //参数显示
        TextView textView = findViewById(R.id.main);
        textView.setText("跳转成功name=" + name + "_age=" + aLong);
    }


    public void toMain(View view) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(200);
            finish();
        }
        return false;
    }
}
