package com.example.yujun.myapplication.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yujun.myapplication.R;
import com.example.yujun.myapplication.control.Fruit;

import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class ListAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public ListAdapter(Context context, int textViewResourceId,List<Fruit> objects){
        super(context, textViewResourceId,objects);
        resourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Fruit fruit = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView fruitImage =(ImageView) view.findViewById(R.id.fruit_image);
        TextView fruitName= (TextView) view.findViewById(R.id.fruit_name);
        fruitImage.setImageResource(fruit.getImageId());
        fruitName.setText(fruit.getName());
        return view;
    }
}
