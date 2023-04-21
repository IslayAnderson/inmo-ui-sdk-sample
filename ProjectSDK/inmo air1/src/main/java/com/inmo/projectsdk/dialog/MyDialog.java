package com.inmo.projectsdk.dialog;


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
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;

import com.inmo.projectsdk.R;

import org.jetbrains.annotations.NotNull;

public class MyDialog extends Dialog {
    private static final String TAG = "MyDialog";
    TextView tvCancel;
    TextView tvConfirm;
    private MyDialog.OnButtonClickListener listener;
    MotionLayout mLayout;
    private GestureDetector mDetector;
    String cancelString = "", confirmString = "";
    Context mContext;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;

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

        initSensor();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        destroySensor();
    }

    private void initSensor() {
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_UI);
    }

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            smartAwake(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void destroySensor() {
        if(sensorManager != null) {
            sensorManager.unregisterListener(sensorEventListener);
            accelerometerSensor = null;
        }
    }

    private static final float THRESHOLD = 3.0f;
    // 抬手亮屏逻辑
    private void smartAwake(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        Log.i(TAG, "detect x :" + x + " y: " + y + " z: " + z);
//        if (y < 1.5 && z < 1.5) {
        if(Math.abs(x) >= THRESHOLD) {
            if(x < 0 && Math.abs(y) < 5) {
                Log.i(TAG, "ljw右边");
                tvCancel.setBackgroundResource(R.drawable.shape_left_default_bg);
                tvConfirm.setBackgroundResource(R.drawable.shape_right_selected_bg);
            } else if(x > 0 && Math.abs(y) < 5) {
                Log.i(TAG, "ljw左边");
                tvCancel.setBackgroundResource(R.drawable.shape_left_selected_bg);
                tvConfirm.setBackgroundResource(R.drawable.shape_right_default_bg);
            }
        }
//        }

    }

    private ImageView mIvAppIcon;
    private TextView tv_content;


    private void initView() {
        initGesture();
        tvCancel = findViewById(R.id.tvCancel);
        tvConfirm = findViewById(R.id.tvConfirm);
        mIvAppIcon = findViewById(R.id.ivAppIcon);
        tv_content = findViewById(R.id.tv_content);
        if (!TextUtils.isEmpty(cancelString)) {
            tvCancel.setText(cancelString);
            tvConfirm.setText(confirmString);
        }

        mLayout = findViewById(R.id.mlrootlayout);
    }

    private boolean isConfirm = true;

    private void initGesture() {
        mDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {

            //单击
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (listener != null) {
                    if (isConfirm)
                        listener.onConfirm();
                    else
                        listener.onCancel();
                    dismiss();
                }
                return super.onSingleTapConfirmed(e);
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }


            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2,
                                   float velocityX, float velocityY) {
                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {//双击事件
                if (listener != null) {
                    listener.onCancel();
                    dismiss();
                }
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (e1.getX() - e2.getX() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                        && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() > 0) {
                    Log.i(TAG, "左下滑动");
                    tvCancel.setBackgroundResource(R.drawable.shape_left_selected_bg);
                    tvConfirm.setBackgroundResource(R.drawable.shape_right_default_bg);
                    isConfirm = false;
                    return true;
                }
                if (e1.getX() - e2.getX() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                        && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() < 0) {
                    Log.i(TAG, "左上滑动");
                    tvCancel.setBackgroundResource(R.drawable.shape_left_selected_bg);
                    tvConfirm.setBackgroundResource(R.drawable.shape_right_default_bg);
                    isConfirm = false;
                    return true;
                }
                if (e2.getX() - e1.getX() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                        && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() > 0) {
                    Log.i(TAG, "右下滑动");
                    tvCancel.setBackgroundResource(R.drawable.shape_left_default_bg);
                    tvConfirm.setBackgroundResource(R.drawable.shape_right_selected_bg);
                    isConfirm = true;
                    return true;
                }
                if (e2.getX() - e1.getX() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 0.5
                        && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 2 && e2.getY() - e1.getY() < 0) {
                    Log.i(TAG, "右上滑动");
                    tvCancel.setBackgroundResource(R.drawable.shape_left_default_bg);
                    tvConfirm.setBackgroundResource(R.drawable.shape_right_selected_bg);
                    isConfirm = true;
                    return true;
                }
                if (e1.getX() - e2.getX() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 2) {
                    Log.i(TAG, "向左滑动");
                    tvCancel.setBackgroundResource(R.drawable.shape_left_selected_bg);
                    tvConfirm.setBackgroundResource(R.drawable.shape_right_default_bg);
                    isConfirm = false;
                    return true;
                }
                if (e2.getX() - e1.getX() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) > 2) {
                    Log.i(TAG, "向右滑动");
                    tvCancel.setBackgroundResource(R.drawable.shape_left_default_bg);
                    tvConfirm.setBackgroundResource(R.drawable.shape_right_selected_bg);
                    isConfirm = true;
                    return true;
                }
                if (e1.getY() - e2.getY() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 0.5) {
                    return true;
                }
                if (e2.getY() - e1.getY() > 130 && Math.abs(e1.getX() - e2.getX()) / Math.abs(e1.getY() - e2.getY()) < 0.5) {
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(@NotNull MotionEvent ev) {
        mDetector.onTouchEvent(ev);
        return true;
    }

    public void setButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }

    public interface OnButtonClickListener {
        void onConfirm();

        void onCancel();
    }


}

