package com.inmo.inmoair2.select;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.inmo.inmoair2.R;
import com.inmo.inmoair2.gesture.GestureBaseActivity;

public class SelectActivity extends GestureBaseActivity {
    private RadioGroup mRadioGroup;

    private int selectPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        mRadioGroup = findViewById(R.id.radio_group);
        setRadioChecked();
    }

    @Override
    protected void swipeLeft() {
        super.swipeLeft();
        if (selectPosition > 0){
            selectPosition --;
        }
        setRadioChecked();
    }

    @Override
    protected void swipeRight() {
        super.swipeRight();
        if (selectPosition<mRadioGroup.getChildCount()-1){
            selectPosition ++;
        }
        setRadioChecked();
    }

    private void setRadioChecked(){
        RadioButton button = (RadioButton) mRadioGroup.getChildAt(selectPosition);
        button.setChecked(true);
    }
}