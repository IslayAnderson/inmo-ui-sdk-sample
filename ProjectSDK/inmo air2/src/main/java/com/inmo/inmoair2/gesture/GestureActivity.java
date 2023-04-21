package com.inmo.inmoair2.gesture;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.inmo.inmoair2.R;


public class GestureActivity extends GestureBaseActivity{
    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        tv = findViewById(R.id.tv);

//        tv.setText("Please touch the TouchPad");
    }

    @Override
    protected void swipeRight() {
        super.swipeRight();
        tv.setText("OnSwipeForward");
    }

    @Override
    protected void swipeLeft() {
        super.swipeLeft();
        tv.setText("OnSwipeBackward");
    }

    @Override
    protected void onRightSingleTapConfirmed() {
        super.onRightSingleTapConfirmed();
        tv.setText("OnSingleTap");
    }

    @Override
    protected void onRightLongPress() {
        tv.setText("onLongPress");
    }

    @Override
    protected void swipeTop() {
        super.swipeTop();
        tv.setText("onSwipeUp");
    }

    @Override
    protected void swipeDown() {
        super.swipeDown();
        tv.setText("onSwipeDown");
    }

}
