package com.inmo.inmoair2.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.inmo.inmoair2.R;
import com.inmo.inmoair2.gesture.GestureBaseActivity;

public class DialogActivity extends GestureBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    @Override
    protected void onRightSingleTapConfirmed() {
        super.onRightSingleTapConfirmed();
        Toast.makeText(this, "This is a Toast", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRightLongPress() {
        super.onRightLongPress();
        showDialog();
    }

    private void showDialog() {
        MyDialog dialog = new MyDialog(this);
        dialog.show();
        dialog.setButtonClickListener(new MyDialog.OnButtonClickListener() {
            @Override
            public void onConfirm() {
                Toast.makeText(DialogActivity.this, "onConfirm", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(DialogActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
            }
        });

    }
}