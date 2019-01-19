package com.example.yujun.myapplication.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yujun.myapplication.R;
import com.example.yujun.myapplication.control.ShowItem;

import java.util.List;

/**
 * Created by zhuoyuan on 2018/1/6.
 */

public class Adapter extends ArrayAdapter<ShowItem> {

    private int resourceId;
    public Adapter(Context context, int textViewResourceId, List<ShowItem> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View converView, ViewGroup parent) {
            ShowItem exhroom = getItem(position);
            View view;
            ViewHolder viewHolder;
            if (converView == null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.ShowImage = (ImageView) view.findViewById(R.id.exhibition_image_view);
                viewHolder.ShowName1 = (TextView) view.findViewById(R.id.text1_view);
                viewHolder.ShowName2= (TextView)view.findViewById(R.id.text3_view);
                view.setTag(viewHolder);
            } else {
                view = converView;
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.ShowImage.setImageResource(exhroom.getImageId());
            viewHolder.ShowName1.setText(exhroom.getName1());
            viewHolder.ShowName2.setText(exhroom.getName2());
            return view;
        }

    class ViewHolder {
            ImageView ShowImage;
            TextView ShowName1;
            TextView ShowName2;
        }

}
