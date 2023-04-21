package com.inmo.inmoair2.switchs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.inmo.inmoair2.R;
import com.inmo.inmoair2.gesture.GestureActivity;

/**------------------操控Switch控件有两种方式 1.如果该页面只有Switch需要获取焦点 那么直接让Switch控件获取焦点 然后单击右触控板即可---------------------*/

/**-----------------2.如果该页面还有其他控件需要获取焦点 那么重写右触控板单击事件 手动更改Switch控件---------------------*/

public class SwitchActivity extends GestureActivity {

    private Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        mSwitch = findViewById(R.id.switch_btn);
        //方式1
        //mSwitch.requestFocus();

        /*mSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
                Toast.makeText(SwitchActivity.this, "Switch isChecked:" + isChecked, Toast.LENGTH_SHORT).show());*/
    }

    @Override
    protected void onRightSingleTapConfirmed() {
        super.onRightSingleTapConfirmed();
        //方式2
        mSwitch.setChecked(!mSwitch.isChecked());
    }
}