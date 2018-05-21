package ap.com.materialdrawer;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MainActivity extends AppCompatActivity {

    private Drawer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PrimaryDrawerItem home = buildMenu(R.string.home, Color.rgb(43, 43, 43), R.drawable.home, false);
        PrimaryDrawerItem settings = buildMenu(R.string.settings, Color.rgb(43, 43, 43), R.drawable.br, false);
        PrimaryDrawerItem wifi = buildMenu(R.string.ar, Color.rgb(43, 43, 43), R.drawable.ar, false);
        PrimaryDrawerItem msg = buildMenu(R.string.msg, Color.rgb(43, 43, 43), R.drawable.msg, false);
        PrimaryDrawerItem rp = buildMenu(R.string.rp, Color.rgb(43, 43, 43), R.drawable.rp, false);
        PrimaryDrawerItem dzdp = buildMenu(R.string.dzdp, Color.rgb(43, 43, 43), R.drawable.dzdp, false);
        final PrimaryDrawerItem exit = buildMenu(R.string.exit, Color.rgb(43, 43, 43), R.drawable.exit, false);
        PrimaryDrawerItem cp = buildMenu(R.string.cp, Color.rgb(43, 43, 43), R.drawable.cp, false);
        final PrimaryDrawerItem more = buildMenu(R.string.more, Color.rgb(43, 43, 43), R.drawable.more, false);
        PrimaryDrawerItem read = buildMenu(R.string.read, Color.rgb(43, 43, 43), R.drawable.read, false);
        final PrimaryDrawerItem history = buildMenu(R.string.history, Color.rgb(43, 43, 43), R.drawable.history, false);
        final PrimaryDrawerItem didi = buildMenu(R.string.didi, Color.rgb(43, 43, 43), R.drawable.didi, false);
        final PrimaryDrawerItem gift = buildMenu(R.string.gift, Color.rgb(43, 43, 43), R.drawable.gift, false);
        //间隔线
        DividerDrawerItem line = new DividerDrawerItem();
        // 创建一个 AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header_back)
                .withTextColor(Color.RED)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("美团")
                                .withEmail("http://www.meituan.com")
                                .withTextColor(Color.BLUE)
                                .withIcon(R.drawable.icon)
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        //创建drawer，并且基础它的返回值
        result = new DrawerBuilder()
                .withActivity(this)
                .addDrawerItems(
                        home, settings, more, line,
                        wifi,
                        msg,
                        rp,
                        cp,
                        dzdp,
                        read,
                        exit
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT).show();
                        if (drawerItem == home) {
                            //修改条目
                            home.withName("主页被点击")
                                    .withTextColor(Color.RED)
                                    .withIcon(R.mipmap.ic_launcher)
                                    .withBadge("20");
                            //通知drawer有条目更新，它会帮你处理好的
                            result.updateItem(home);
                        }
                        if (drawerItem == exit) result.closeDrawer();
                        if (drawerItem == more) {
                            result.addItem(history);
                            result.addItem(didi);
                            result.addItem(gift);
                        }
                        return true;
                    }
                })
                .withAccountHeader(headerResult)
                .withCloseOnClick(true)
                .build();
        result.setSelection(-1);
        result.setOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
            @Override
            public boolean onNavigationClickListener(View clickedView) {
                result.removeItem(10);
                result.removeItem(11);
                result.removeItem(12);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public PrimaryDrawerItem buildMenu(@StringRes int name, @ColorInt int textColor, @DrawableRes int iconRes
            , boolean selectable) {
        PrimaryDrawerItem primaryDrawerItem = new PrimaryDrawerItem();
        primaryDrawerItem.withName(name);
        if (textColor != -1) primaryDrawerItem.withTextColor(textColor);
        if (iconRes != -1) primaryDrawerItem.withIcon(iconRes);
        primaryDrawerItem.withSelectable(selectable);
        return primaryDrawerItem;
    }
}
