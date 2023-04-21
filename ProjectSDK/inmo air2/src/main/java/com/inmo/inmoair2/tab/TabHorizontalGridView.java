package com.inmo.inmoair2.tab;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import androidx.leanback.widget.HorizontalGridView;

public class TabHorizontalGridView extends HorizontalGridView {

    public TabHorizontalGridView(Context context) {
        this(context, null);
    }

    public TabHorizontalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabHorizontalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private static final int TAG_FEATURE_POSITION = 0;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    //退出时 先回到默认焦点
                    if (getSelectedPosition() != TAG_FEATURE_POSITION) {
                        setSelectedPositionSmooth(TAG_FEATURE_POSITION);
                        return true;
                    }
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    break;
                //防止快速按下左右键 焦点转移的情况
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    if (getSelectedPosition() == 0) {
                        return true;
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    if (getAdapter()!=null && getSelectedPosition() == getAdapter().getItemCount()-1){
                        return true;
                    }
                    break;
                default:
                    break;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}
