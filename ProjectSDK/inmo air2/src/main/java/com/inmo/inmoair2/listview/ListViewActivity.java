package com.inmo.inmoair2.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.VerticalGridView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.inmo.inmoair2.MainBean;
import com.inmo.inmoair2.R;
import com.inmo.inmoair2.adapter.BaseItemBridgeAdapter;
import com.inmo.inmoair2.presenter.ListViewPresenter;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private VerticalGridView dataView;
    private ArrayObjectAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
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
        dataView.setNumColumns(1);
        mAdapter = new ArrayObjectAdapter(new ListViewPresenter());
        ItemBridgeAdapter itemBridgeAdapter = new BaseItemBridgeAdapter(mAdapter) {
            @Override
            public OnItemViewClickedListener getOnItemViewClickedListener() {
                return (focusView, itemViewHolder, item) -> {
                    if (item instanceof MainBean) {
                        MainBean bean = (MainBean) item;
                        Toast.makeText(ListViewActivity.this, bean.getName(), Toast.LENGTH_SHORT).show();
                    }
                };
            }

            @Override
            public OnItemFocusChangedListener getOnItemFocusChangedListener() {
                return (focusView, itemViewHolder, item, hasFocus, pos) -> {
                    if (hasFocus) {
                        focusView.setScaleX(1.03f);
                        focusView.setScaleY(1.03f);
                    } else {
                        focusView.setScaleX(1.0f);
                        focusView.setScaleY(1.0f);
                    }
                };
            }

            @Override
            public OnItemViewLongClickedListener getOnItemViewLongClickedListener() {
                return (focusView, itemViewHolder, item) -> {
                    if (item instanceof MainBean) {
                        MainBean bean = (MainBean) item;
                        Toast.makeText(ListViewActivity.this, "LongClick:" + bean.getName(), Toast.LENGTH_SHORT).show();
                    }
                    return false;
                };
            }
        };
        dataView.setAdapter(itemBridgeAdapter);
    }

    private void initData() {
        for (int i = 0; i < 15; i++) {
            mAdapter.add(new MainBean("item" + i));
        }
    }

}