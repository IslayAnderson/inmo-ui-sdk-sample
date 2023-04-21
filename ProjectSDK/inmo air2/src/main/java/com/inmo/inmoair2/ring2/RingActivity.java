package com.inmo.inmoair2.ring2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inmo.inmoair2.R;
import com.inmo.inmoair2.gesture.GestureBaseActivity;

/**
 * ------------------请先连接好INMO Ring2 戒指 再来体验引导相关功能---------------------------
 */

public class RingActivity extends GestureBaseActivity {
    private TextView mSateTv;
    private ImageView mRingIv;

    private int BACK_POS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
        mSateTv = findViewById(R.id.state_tv);
        mRingIv = findViewById(R.id.ring_iv);
        if (isRingConnected()) {
            mRingIv.setVisibility(View.VISIBLE);
            mRingIv.setImageLevel(0);
            mSateTv.setText("Inmo ring2 is Connected!");
        } else {
            mRingIv.setVisibility(View.INVISIBLE);
            mSateTv.setText("Inmo ring2 is Disconnected!");
        }
    }

    @Override
    protected void swipeTop() {
        super.swipeTop();
        mRingIv.setImageLevel(1);
    }

    @Override
    protected void swipeDown() {
        super.swipeDown();
        mRingIv.setImageLevel(2);
    }

    @Override
    protected void swipeLeft() {
        super.swipeLeft();
        mRingIv.setImageLevel(3);
    }

    @Override
    protected void swipeRight() {
        super.swipeRight();
        mRingIv.setImageLevel(4);
    }

    @Override
    protected void onRightSingleTapConfirmed() {
        super.onRightSingleTapConfirmed();
        mRingIv.setImageLevel(5);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (BACK_POS == 0) {
                finish();
            } else {
                mRingIv.setImageLevel(6);
                BACK_POS--;
                Toast.makeText(this, "Press Exit again！", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressLint("MissingPermission")
    private boolean isRingConnected() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter.isEnabled()) {
            int isConnected = adapter.getProfileConnectionState(4);
            if (isConnected == BluetoothProfile.STATE_CONNECTED) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}