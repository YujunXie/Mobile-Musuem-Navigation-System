package com.example.yujun.myapplication.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yujun.myapplication.DisplayActivity;
import com.example.yujun.myapplication.DisplayActivity3;
import com.example.yujun.myapplication.DisplayActivity4;
import com.example.yujun.myapplication.DisplayActivity5;
import com.example.yujun.myapplication.DisplayActivity6;
import com.example.yujun.myapplication.R;
import com.example.yujun.myapplication.control.Fruit;

import java.util.List;


/**
 * Created by Administrator on 2018/1/3.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;
        public ViewHolder(View view){
            super(view);
            fruitView = view;
            fruitImage =(ImageView) view.findViewById(R.id.fruit_image);
            fruitName = (TextView) view.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapter (List<Fruit>  fruitList, Context context) {
        mFruitList = fruitList;
        mContext = context;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Fruit fruit =mFruitList.get(position);
                Toast.makeText(v.getContext(), "you clicked view"+fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.fruitImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int position = holder.getAdapterPosition();
                Fruit fruit = mFruitList.get(position);
                Intent intent = new Intent();
                String s1 = fruit.getName();
                Log.d("FruitAdapter",s1);
                //Num.1
                if(s1.equals("阙楼图(东壁)")){
                    intent.setClass(mContext, DisplayActivity.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }
                else if(s1.equals("阙楼图(西壁)")){
                    intent.setClass(mContext, DisplayActivity.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);}
                else if(s1.equals("葡萄花鸟纹银香囊")){
                    intent.setClass(mContext, DisplayActivity.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);}
                else if(s1.equals("杜虎符")){
                    intent.setClass(mContext,DisplayActivity.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);}
                else if(s1.equals("天鹅穿莲纹玉佩")){
                    intent.setClass(mContext,DisplayActivity.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);}
                //Num.2
                else if(s1.equals("镂空铜豆")){
                    intent.setClass(mContext,DisplayActivity3.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                   }
                else if(s1.equals("彩绘骑马乐俑")){
                    intent.setClass(mContext,DisplayActivity3.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }
                else if(s1.equals("琉璃围棋子")){
                    intent.setClass(mContext,DisplayActivity3.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }
                else if(s1.equals("蟠虺纹提梁铜盉")){
                    intent.setClass(mContext,DisplayActivity3.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }
                else if(s1.equals("二人抬箱图")){
                    intent.setClass(mContext,DisplayActivity3.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }

                //Num.3
                else if(s1.equals("陶楼")){
                    intent.setClass(mContext,DisplayActivity4.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }
                else if(s1.equals("彩绘陶钫")){
                    intent.setClass(mContext,DisplayActivity4.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }
                else if(s1.equals("鱼纹葫芦瓶")){
                    intent.setClass(mContext,DisplayActivity4.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }
                else if(s1.equals("玄武图")){
                    intent.setClass(mContext,DisplayActivity4.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }
                else if(s1.equals("绿琉璃瓶")){
                    intent.setClass(mContext,DisplayActivity4.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }
                //Num.4
                else if(s1.equals("鎏金飞狮宝相花纹")){
                    intent.setClass(mContext,DisplayActivity5.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                   }
                else if(s1.equals("高昌吉利铜钱")){
                    intent.setClass(mContext,DisplayActivity5.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                   }
                else if(s1.equals("鎏金石榴花纹银盒")){
                    intent.setClass(mContext,DisplayActivity5.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }
                else if(s1.equals("乐舞图")){
                    intent.setClass(mContext,DisplayActivity5.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }
                else if(s1.equals("拊狻猊罗汉")){
                    intent.setClass(mContext,DisplayActivity5.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                }
                //Num.5
                else if(s1.equals("树下侍女图屏风")){
                    intent.setClass(mContext,DisplayActivity6.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }
                else if(s1.equals("鎏金折枝花纹银盖")){
                    intent.setClass(mContext,DisplayActivity6.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                  }
                else if(s1.equals("贝币")){
                    intent.setClass(mContext,DisplayActivity6.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                  }
                else if(s1.equals("鎏金花鸟纹银碗")){
                    intent.setClass(mContext,DisplayActivity6.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }
                else if(s1.equals("狩猎出行图之二")){
                    intent.setClass(mContext,DisplayActivity6.class);
                    intent.putExtra("item_data",s1);
                    mContext.startActivity(intent);
                    }

                else
                    Toast.makeText(v.getContext(), "暂无详情", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder,int position){
        Fruit fruit =mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }
    public  int getItemCount(){
        return mFruitList.size();
    }

}
