package com.example.yujun.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yujun.myapplication.control.Fruit;
import com.example.yujun.myapplication.utils.FruitAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends Activity implements View.OnClickListener {

    private EditText editText;
    private String Rnum = null;

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String displayroomnum = intent.getStringExtra("button");
        Rnum = displayroomnum;
        initFruits(displayroomnum);//加入对应展厅展品

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fruits_recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList, ThirdActivity.this);
        recyclerView.setAdapter(adapter);

        Button button = (Button) findViewById(R.id.search_fruit_button);
        editText = (EditText) findViewById(R.id.edit_text_search);
        button.setOnClickListener(this);
    }

    private void initFruits(String s){
        //private void initFruits() {
        if(s.equals("first")){
            Fruit p1 = new Fruit("阙楼图(东壁)", R.drawable.p1);
            fruitList.add(p1);
            Fruit p2 = new Fruit("阙楼图(西壁)", R.drawable.p2);
            fruitList.add(p2);
            Fruit p3 = new Fruit("葡萄花鸟纹银香囊", R.drawable.p3);
            fruitList.add(p3);
            Fruit p4 = new Fruit("杜虎符", R.drawable.p4);
            fruitList.add(p4);
            Fruit p5 = new Fruit("天鹅穿莲纹玉佩", R.drawable.p5);
            fruitList.add(p5);
            Fruit p6 = new Fruit("彩绘持果盘女立俑", R.drawable.p6);
            fruitList.add(p6);
            Fruit p7 = new Fruit("侍女与侏儒图", R.drawable.p7);
            fruitList.add(p7);
            Fruit p8 = new Fruit("执马球杆男侍图", R.drawable.p8);
            fruitList.add(p8);
            Fruit p9 = new Fruit("彩绘持镜女立俑", R.drawable.p9);
            fruitList.add(p9);
            Fruit p10 = new Fruit("陶井", R.drawable.p10);
            fruitList.add(p10);
        }
        else if(s.equals("second")){
            Fruit p1 = new Fruit("镂空铜豆", R.drawable.p11);
            fruitList.add(p1);
            Fruit p2 = new Fruit("彩绘骑马乐俑", R.drawable.p12);
            fruitList.add(p2);
            Fruit p3 = new Fruit("琉璃围棋子", R.drawable.p13);
            fruitList.add(p3);
            Fruit p4 = new Fruit("蟠虺纹提梁铜盉", R.drawable.p14);
            fruitList.add(p4);
            Fruit p5 = new Fruit("二人抬箱图", R.drawable.p15);
            fruitList.add(p5);
            Fruit p6 = new Fruit("玉串", R.drawable.p16);
            fruitList.add(p6);
            Fruit p7 = new Fruit("越式鼎", R.drawable.p17);
            fruitList.add(p7);
            Fruit p8 = new Fruit("蟠龙纹提梁铜盉", R.drawable.p18);
            fruitList.add(p8);
            Fruit p9 = new Fruit("安邑二釿币", R.drawable.p19);
            fruitList.add(p9);
            Fruit p10 = new Fruit("鎏金蔓草纹银羽觞", R.drawable.p20);
            fruitList.add(p10);
        }
        else if(s.equals("third")){
            Fruit p1 = new Fruit("陶楼", R.drawable.p21);
            fruitList.add(p1);
            Fruit p2 = new Fruit("彩绘陶钫", R.drawable.p22);
            fruitList.add(p2);
            Fruit p3 = new Fruit("鱼纹葫芦瓶", R.drawable.p23);
            fruitList.add(p3);
            Fruit p4 = new Fruit("玄武图", R.drawable.p24);
            fruitList.add(p4);
            Fruit p5 = new Fruit("绿琉璃瓶", R.drawable.p25);
            fruitList.add(p5);
            Fruit p6 = new Fruit("鎏金蔓草蝴蝶纹银", R.drawable.p26);
            fruitList.add(p6);
            Fruit p7 = new Fruit("青铜双耳杯", R.drawable.p27);
            fruitList.add(p7);
            Fruit p8 = new Fruit("陶簋", R.drawable.p28);
            fruitList.add(p8);
            Fruit p9 = new Fruit("捧盆景侍女图", R.drawable.p29);
            fruitList.add(p9);
            Fruit p10 = new Fruit("金箔", R.drawable.p30);
            fruitList.add(p10);
        }
        else  if(s.equals("fourth")){
            Fruit p1 = new Fruit("鎏金飞狮宝相花纹", R.drawable.p31);
            fruitList.add(p1);
            Fruit p2 = new Fruit("乐舞图", R.drawable.p32);
            fruitList.add(p2);
            Fruit p3 = new Fruit("高昌吉利铜钱", R.drawable.p33);
            fruitList.add(p3);
            Fruit p4 = new Fruit("鎏金石榴花纹银盒", R.drawable.p34);
            fruitList.add(p4);
            Fruit p5 = new Fruit("拊狻猊罗汉", R.drawable.p35);
            fruitList.add(p5);
            Fruit p6 = new Fruit("裸体俑", R.drawable.p36);
            fruitList.add(p6);
            Fruit p7 = new Fruit("托果盘侍女图", R.drawable.p37);
            fruitList.add(p7);
            Fruit p8 = new Fruit("仪仗出行图之四", R.drawable.p38);
            fruitList.add(p8);
            Fruit p9 = new Fruit("双狮纹金铛", R.drawable.p39);
            fruitList.add(p9);
            Fruit p10 = new Fruit("鎏金熊纹六曲银盘", R.drawable.p40);
            fruitList.add(p10);
        }
        else if(s.equals("fifth")){
            Fruit p1 = new Fruit("树下侍女图屏风", R.drawable.p41);
            fruitList.add(p1);
            Fruit p2 = new Fruit("鎏金折枝花纹银盖", R.drawable.p42);
            fruitList.add(p2);
            Fruit p3 = new Fruit("贝币", R.drawable.p43);
            fruitList.add(p3);
            Fruit p4 = new Fruit("鎏金花鸟纹银碗", R.drawable.p44);
            fruitList.add(p4);
            Fruit p5 = new Fruit("狩猎出行图之二", R.drawable.p45);
            fruitList.add(p5);
            Fruit p6 = new Fruit("东罗马金币", R.drawable.p46);
            fruitList.add(p6);
            Fruit p7 = new Fruit("盛放丹砂的银盒", R.drawable.p47);
            fruitList.add(p7);
            Fruit p8 = new Fruit("莲瓣纹提梁银罐", R.drawable.p48);
            fruitList.add(p8);
            Fruit p9 = new Fruit("和同开珎银币", R.drawable.p49);
            fruitList.add(p9);
            Fruit p10 = new Fruit("仪卫图", R.drawable.p50);
            fruitList.add(p10);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_fruit_button:
                String inputText = editText.getText().toString();
                Intent intent = new Intent(ThirdActivity.this, SearchActivity.class);
                intent.putExtra("extra_data",inputText);
                intent.putExtra("room_num",Rnum);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
