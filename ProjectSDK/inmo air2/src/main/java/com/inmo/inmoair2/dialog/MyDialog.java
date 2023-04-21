package com.inmo.inmoair2.dialog;


import android.app.Dialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.inmo.inmoair2.R;

import org.jetbrains.annotations.NotNull;

public class MyDialog extends Dialog {
    private static final String TAG = "MyDialog";
    TextView tvCancel;
    TextView tvConfirm;
    TextView tv_content;
    private OnButtonClickListener listener;
    String cancelString = "", confirmString = "";
    Context mContext;
    int selectPosition = 0;

    public MyDialog(@NonNull Context context) {
        super(context, R.style.ConfirmMemoDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dialog);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.dimAmount = 1f;
        getWindow().setAttributes(lp);

        setCanceledOnTouchOutside(false);
        initView();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    private void initView() {
        tvCancel = findViewById(R.id.tvCancel);
        tvConfirm = findViewById(R.id.tvConfirm);
        tv_content = findViewById(R.id.tv_content);
        if (!TextUtils.isEmpty(cancelString)) {
            tvCancel.setText(cancelString);
            tvConfirm.setText(confirmString);
        }
        selectPosition = 0;
        tvCancel.setBackgroundResource(R.drawable.shape_left_selected_bg);
        tvConfirm.setBackgroundResource(R.drawable.shape_right_default_bg);
    }

    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        switch (event.getKeyCode()) {
            // 键盘左键 触控板后滑
            case KeyEvent.KEYCODE_DPAD_LEFT:
                setCancel();
                break;
            // 键盘右键 触控板前滑
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                setConfirm();
                break;
            // 键盘的enter键 触控板单击
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_SPACE:
            case KeyEvent.KEYCODE_NUMPAD_ENTER:
                onClick();
                break;
            default:
                break;
        }
        return super.dispatchKeyEvent(event);
    }

    private void onClick() {
        if (selectPosition == 0) {
            dismiss();
            listener.onCancel();
        } else {
            dismiss();
            listener.onConfirm();
        }
    }

    private void setCancel(){
        selectPosition = 0;
        tvCancel.setBackgroundResource(R.drawable.shape_left_selected_bg);
        tvConfirm.setBackgroundResource(R.drawable.shape_right_default_bg);
    }

    private void setConfirm(){
        selectPosition = 1;
        tvCancel.setBackgroundResource(R.drawable.shape_left_default_bg);
        tvConfirm.setBackgroundResource(R.drawable.shape_right_selected_bg);
    }

    public void setButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnButtonClickListener {
        void onConfirm();

        void onCancel();
    }


}

