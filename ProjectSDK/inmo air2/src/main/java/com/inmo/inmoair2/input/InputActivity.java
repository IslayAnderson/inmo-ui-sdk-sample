package com.inmo.inmoair2.input;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.Toast;

import com.blankj.utilcode.util.KeyboardUtils;
import com.inmo.inmoair2.R;
import com.inmo.inmoair2.gesture.GestureBaseActivity;

/**----------------键盘的交互方式 滑动右触控板 单击选择-----------------**/

public class InputActivity extends GestureBaseActivity {
    private EditText mEdit;

    @Override
    protected void onResume() {
        super.onResume();
        mEdit.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        mEdit = findViewById(R.id.edit);
        mEdit.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                if (KeyboardUtils.isSoftInputVisible(this)) {
                    Toast.makeText(this, mEdit.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    KeyboardUtils.showSoftInput(mEdit);
                }
                return true;
            }
            return false;
        });
    }

    @Override
    protected void onRightSingleTapConfirmed() {
        super.onRightSingleTapConfirmed();
        //弹出键盘
        KeyboardUtils.showSoftInput(mEdit);
    }
}