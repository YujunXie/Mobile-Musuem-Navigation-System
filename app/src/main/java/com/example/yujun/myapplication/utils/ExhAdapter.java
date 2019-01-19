package com.example.yujun.myapplication.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yujun.myapplication.R;
import com.example.yujun.myapplication.control.exhibitionroom;

import java.util.List;

/**
 * Created by liuhua on 2018/1/4.
 */
//柳华代码
public class ExhAdapter  extends ArrayAdapter<exhibitionroom> {
    private int resourceId;
    public ExhAdapter(Context context, int textViewResourceId,
                      List<exhibitionroom> objects) {
        super(context,textViewResourceId,objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View converView, ViewGroup parent) {
        exhibitionroom exhroom = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(converView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.exhroomImag = (ImageView) view.findViewById(R.id.exh_image);
            viewHolder.exhroomName = (TextView) view.findViewById(R.id.exh_name);
            view.setTag(viewHolder);
        } else {
            view = converView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.exhroomImag.setImageResource(exhroom.getImageId());
        viewHolder.exhroomName.setText(exhroom.getName());
        return view;
    }

    class ViewHolder {
        ImageView exhroomImag;
        TextView exhroomName;
    }

}
