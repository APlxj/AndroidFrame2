package com.ap.aouter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ap.aouter.R;
import com.ap.aouter.api.ApNavigationCallback;
import com.ap.aouter.base.BaseActivity;

@Route(path = "/ap/main", group = "activity")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
    }

    public void toArouter(View view) {
        ARouter.getInstance()
                .build("/ap/two", "activity")
                .withInt("aLong", 10)
                .withString("name", "APLxj")
                .navigation(this, 2001, new ApNavigationCallback(this));
    }

    public void toUri(View view) {
        ARouter.getInstance()
                .build("/ap/touri", "activity")
                .navigation(this, new ApNavigationCallback(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2001 && resultCode == 200) {
            Toast.makeText(this, "返回成功", Toast.LENGTH_SHORT).show();
        }
    }
}
