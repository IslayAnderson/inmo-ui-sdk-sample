package com.inmo.inmoair2.tab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.OnChildViewHolderSelectedListener;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.inmo.inmoair2.MainBean;
import com.inmo.inmoair2.R;
import com.inmo.inmoair2.adapter.BaseItemBridgeAdapter;
import com.inmo.inmoair2.adapter.ContentViewPagerAdapter;
import com.inmo.inmoair2.presenter.TitlePresenter;

import java.util.ArrayList;
import java.util.List;

public class TabViewActivity extends AppCompatActivity implements ViewTreeObserver.OnGlobalFocusChangeListener{
    private HorizontalGridView titleView;
    private ViewPager contentVp;
    private ArrayObjectAdapter objectAdapter;
    private TextView mOldTitle;
    private int mCurrentPageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);
        titleView =findViewById(R.id.title_view);
        contentVp = findViewById(R.id.content_viewpager);
        objectAdapter = new ArrayObjectAdapter(new TitlePresenter());
        ItemBridgeAdapter itemBridgeAdapter = new BaseItemBridgeAdapter(objectAdapter) {
            @Override
            public OnItemViewClickedListener getOnItemViewClickedListener() {
                return null;
            }

            @Override
            public OnItemFocusChangedListener getOnItemFocusChangedListener() {
                return (focusView, itemViewHolder, item, hasFocus, pos) -> {
                    if (hasFocus) {
                        TextView textView = focusView.findViewById(R.id.tv_main_title);
                        textView.setTextColor(getColor(R.color.white));
                    } else {
                        mOldTitle = focusView.findViewById(R.id.tv_main_title);
                        mOldTitle.setTextColor(getColor(R.color.color_title_tv));
                    }
                };
            }
        };
        titleView.setAdapter(itemBridgeAdapter);
        titleView.addOnChildViewHolderSelectedListener(onChildViewHolderSelectedListener);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalFocusChangeListener(this);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        titleView.requestFocus();
    }

    private void initData(){
        List<MainBean> beanList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            beanList.add(new MainBean("Menu"+i));
        }
        objectAdapter.setItems(beanList,null);
        initViewPager(beanList);
    }

    private void initViewPager(List<MainBean> dataBeans) {
        ContentViewPagerAdapter viewPagerAdapter = new ContentViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setData(dataBeans);
        contentVp.setAdapter(viewPagerAdapter);
        contentVp.setOffscreenPageLimit(dataBeans.size());
        contentVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position != mCurrentPageIndex) {
                    //viewpage滑动 同步给titleView
                    titleView.setSelectedPosition(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private final OnChildViewHolderSelectedListener onChildViewHolderSelectedListener = new OnChildViewHolderSelectedListener() {
        @Override
        public void onChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
            super.onChildViewHolderSelected(parent, child, position, subposition);
            if (child != null & position != mCurrentPageIndex) {
                TextView currentTitle = child.itemView.findViewById(R.id.tv_main_title);
                if (mOldTitle != null) {
                    mOldTitle.setTextColor(getResources().getColor(R.color.color_title_tv));
                    Paint paint = mOldTitle.getPaint();
                    if (paint != null) {
                        paint.setFakeBoldText(false);
                        //viewpager切页标题不刷新，调用invalidate刷新
                        mOldTitle.invalidate();
                    }
                }
                currentTitle.setTextColor(getResources().getColor(R.color.white));
                Paint paint = currentTitle.getPaint();
                if (paint != null) {
                    paint.setFakeBoldText(true);
                    //viewpager切页标题不刷新，调用invalidate刷新
                    currentTitle.invalidate();
                }
                mOldTitle = currentTitle;
            }
            //设置当前选中的position
            setCurrentItemPosition(position);

        }
    };

    private void setCurrentItemPosition(int position) {
        if (contentVp != null && position != mCurrentPageIndex) {
            mCurrentPageIndex = position;
            contentVp.setCurrentItem(position);
        }
    }

    @Override
    public void onGlobalFocusChanged(View oldFocus, View newFocus) {
        if (newFocus == null || oldFocus == null) {
            return;
        }
        if (newFocus.getId() == R.id.tv_main_title && oldFocus.getId() == R.id.tv_main_title) {
            ((TextView) newFocus).setTextColor(getResources().getColor(R.color.white));
            ((TextView) newFocus).getPaint().setFakeBoldText(true);
            ((TextView) oldFocus).setTextColor(getResources().getColor(R.color.color_title_tv));
            ((TextView) oldFocus).getPaint().setFakeBoldText(false);
        } else if (newFocus.getId() == R.id.tv_main_title && oldFocus.getId() != R.id.tv_main_title) {
            ((TextView) newFocus).setTextColor(getResources().getColor(R.color.white));
            ((TextView) newFocus).getPaint().setFakeBoldText(true);
        } else if (newFocus.getId() != R.id.tv_main_title && oldFocus.getId() == R.id.tv_main_title) {
            ((TextView) oldFocus).setTextColor(getResources().getColor(R.color.white));
            ((TextView) oldFocus).getPaint().setFakeBoldText(true);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        titleView.removeOnChildViewHolderSelectedListener(onChildViewHolderSelectedListener);
    }
}