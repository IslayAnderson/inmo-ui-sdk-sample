package com.inmo.inmoair2.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

import com.inmo.inmoair2.MainBean;
import com.inmo.inmoair2.R;

public class MainPresenter extends Presenter {
    private Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        if (item instanceof MainBean) {
            ViewHolder vh = (ViewHolder) viewHolder;
            MainBean bean =(MainBean) item;
            vh.title.setText(bean.getName());
        }
    }

    @Override
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {

    }


    public static class ViewHolder extends Presenter.ViewHolder {
        private final TextView title;

        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_tv);
        }
    }

}
