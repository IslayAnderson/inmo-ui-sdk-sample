package com.inmo.inmoair2.gesture;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * 手势相关
 */
public class GestureBaseActivity extends AppCompatActivity {
    private static final int LONG_CLICK = 289;
    private static final int DOUBLE_REFERS_LONG_CLICK = 290;

    /**
     *
     * ------------在2代上 目前交互方式为键值操控 并同时适配2代戒指------------
     * ----------左触控板为系统功能 用户无需关注 也无法进行处理 所有交互方式都是在右触控板进行----------------
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            // 键盘左键 触控板后滑
            case KeyEvent.KEYCODE_DPAD_LEFT:
                swipeLeft();
                break;
            // 键盘右键 触控板前滑
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                swipeRight();
                break;
            // 键盘上键 触控板上滑
            case KeyEvent.KEYCODE_DPAD_UP:
                swipeTop();
                break;
            // 键盘上键 触控板下滑
            case KeyEvent.KEYCODE_DPAD_DOWN:
                swipeDown();
                break;
            // 键盘的enter键 触控板单击
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
            case KeyEvent.KEYCODE_SPACE:
            case KeyEvent.KEYCODE_NUMPAD_ENTER:
                onRightSingleTapConfirmed();
                break;
            // 触控板长按
            case LONG_CLICK:
            case DOUBLE_REFERS_LONG_CLICK:
                onRightLongPress();
                break;
            //返回键 双击右触控板 除非页面需要拦截返回功能 否则无需处理
            case KeyEvent.KEYCODE_BACK:
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 右滑一次
     */
    protected void swipeRight() {
    }

    /**
     * 左滑一次
     */
    protected void swipeLeft() {
    }

    /**
     * 上滑一次
     */
    protected void swipeTop() {

    }

    /**
     * 下滑一次
     */
    protected void swipeDown() {

    }

    /**
     * 右tp单击手势
     */
    protected void onRightSingleTapConfirmed() {

    }

    /**
     * 右tp长按手势
     */
    protected void onRightLongPress() {

    }
}

