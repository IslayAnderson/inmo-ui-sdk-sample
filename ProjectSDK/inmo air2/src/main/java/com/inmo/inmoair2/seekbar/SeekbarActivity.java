package com.inmo.inmoair2.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.inmo.inmoair2.R;
import com.inmo.inmoair2.gesture.GestureBaseActivity;

@SuppressLint("RestrictedApi")

/** ---------------------由于air2 滑动 触发的都是键值 所以只能手动设置seekbar进度 用户可以自己合理设置每次滑动更改的进度值--------------------*/

public class SeekbarActivity extends GestureBaseActivity {
    private SeekBar mSeekBarHorizontal;
    private TextView mProgressTvHorizontal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        mProgressTvHorizontal = findViewById(R.id.progress_tv_horizontal);
        mSeekBarHorizontal = findViewById(R.id.seek_bar_horizontal);
        mSeekBarHorizontal.setMax(100);
        mSeekBarHorizontal.setFocusable(false);
    }

    @Override
    protected void swipeLeft() {
        super.swipeLeft();
        int progress = mSeekBarHorizontal.getProgress();
        if (progress == 0) {
            return;
        }
        progress -= 10;
        mSeekBarHorizontal.setProgress(progress);
        mProgressTvHorizontal.setText(progress+"");
    }

    @Override
    protected void swipeRight() {
        super.swipeRight();
        int progress = mSeekBarHorizontal.getProgress();
        if (progress == 100) {
            return;
        }
        progress += 10;
        mSeekBarHorizontal.setProgress(progress);
        mProgressTvHorizontal.setText(progress+"");
    }
}