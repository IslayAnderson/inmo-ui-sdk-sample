package com.inmo.inmoair2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.VerticalGridView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.inmo.inmoair2.adapter.BaseItemBridgeAdapter;
import com.inmo.inmoair2.dialog.DialogActivity;
import com.inmo.inmoair2.gesture.GestureActivity;
import com.inmo.inmoair2.input.InputActivity;
import com.inmo.inmoair2.listview.ListViewActivity;
import com.inmo.inmoair2.presenter.MainPresenter;
import com.inmo.inmoair2.ring2.RingActivity;
import com.inmo.inmoair2.seekbar.SeekbarActivity;
import com.inmo.inmoair2.select.SelectActivity;
import com.inmo.inmoair2.switchs.SwitchActivity;
import com.inmo.inmoair2.tab.TabViewActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VerticalGridView dataView;
    private ArrayObjectAdapter mAdapter;

    private int selectPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取焦点
        dataView.requestFocus();
    }

    private void initView() {
        dataView = findViewById(R.id.data_vg);
        //设置列数
        dataView.setNumColumns(5);
        mAdapter = new ArrayObjectAdapter(new MainPresenter());
        ItemBridgeAdapter itemBridgeAdapter = new BaseItemBridgeAdapter(mAdapter) {
            @Override
            public OnItemViewClickedListener getOnItemViewClickedListener() {
                return (focusView, itemViewHolder, item) -> {
                    if (focusView.hasFocus()) {
                        click();
                    }
                };
            }

            @Override
            public OnItemFocusChangedListener getOnItemFocusChangedListener() {
                return (focusView, itemViewHolder, item, hasFocus, pos) -> {
                    if (hasFocus) {
                        selectPosition = pos;
                        focusView.animate().scaleX(1.075f).start();
                        focusView.animate().scaleY(1.075f).start();
                    } else {
                        focusView.animate().scaleX(1f).start();
                        focusView.animate().scaleY(1f).start();
                    }
                };
            }
        };
        dataView.setAdapter(itemBridgeAdapter);
    }

    private void initData() {
        List<MainBean> mainBeans = new ArrayList<>();
        mainBeans.add(new MainBean("Gesture"));
        mainBeans.add(new MainBean("Toast"));
        mainBeans.add(new MainBean("ListView"));
        mainBeans.add(new MainBean("Seekbar"));
        mainBeans.add(new MainBean("Select"));
        mainBeans.add(new MainBean("Switch"));
        mainBeans.add(new MainBean("INMO Ring2"));
        mainBeans.add(new MainBean("TabView"));
        mainBeans.add(new MainBean("Input"));
        mAdapter.setItems(mainBeans, null);
    }


    private void click() {
        switch (selectPosition) {
            case 0:
                goToActivity(GestureActivity.class);
                break;
            case 1:
                goToActivity(DialogActivity.class);
                break;
            case 2:
                goToActivity(ListViewActivity.class);
                break;
            case 3:
                goToActivity(SeekbarActivity.class);
                break;
            case 4:
                goToActivity(SelectActivity.class);
                break;
            case 5:
                goToActivity(SwitchActivity.class);
                break;
            case 6:
                goToActivity(RingActivity.class);
                break;
            case 7:
                goToActivity(TabViewActivity.class);
                break;
            case 8:
                goToActivity(InputActivity.class);
                break;
            default:
                break;
        }
    }

    private void goToActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}