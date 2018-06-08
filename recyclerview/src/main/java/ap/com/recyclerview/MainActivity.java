package ap.com.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnRecyclerViewItemClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //创建布局管理器
        //瀑布流布局<StaggeredGridLayoutManager>
        //Grid布局<GridLayoutManager>
        //横向布局<mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);>
        //List布局<LinearLayoutManager>
        //mLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //添加间隔
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.line));
        mRecyclerView.addItemDecoration(decoration);
        //mRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //mRecyclerView.setItemAnimator(newDefaultItemAnimator());
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        //mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        myAdapter = new MyAdapter(getData(), this);
        myAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(myAdapter);
        //动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.in);
        //得到一个LayoutAnimationController对象；
        LayoutAnimationController lac = new LayoutAnimationController(animation);
        //设置控件显示的顺序；
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
        //设置控件显示间隔时间；
        lac.setDelay(0.2f);
        //为ListView设置LayoutAnimationController属性；
        //mRecyclerView.setLayoutAnimation(lac);
        //拖动排序
        ItemTouchCallBack touchCallBack = new ItemTouchCallBack();
        touchCallBack.setOnItemTouchListener(myAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(touchCallBack);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        String[] strings = getResources().getStringArray(R.array.test);
        Collections.addAll(list, strings);
        return list;
    }

    @Override
    public void onItemClick(View view, String data) {
        Toast.makeText(MainActivity.this, "点击" + data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, String data) {
        Toast.makeText(MainActivity.this, "长按" + data, Toast.LENGTH_SHORT).show();
    }

    public void addItem(View view) {
        myAdapter.addItem("英雄联盟", 1);
    }

    public void deleteItem(View view) {
        myAdapter.removeItem("英雄联盟");
    }
}
