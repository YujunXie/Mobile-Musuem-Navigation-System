package com.example.yujun.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yujun.myapplication.control.Fruit;
import com.example.yujun.myapplication.utils.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    private String RoomNum=null;

    private List<Fruit> fruitList2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search1);

        Intent intent = getIntent();
        String data1 = intent.getStringExtra("extra_data");
        RoomNum = intent.getStringExtra("room_num");

        Log.d("SearchActivity",data1);
        Log.d("SearchActivity",RoomNum);
        initFruits(data1);

        ListAdapter adapter = new ListAdapter(SearchActivity.this, R.layout.searchlist, fruitList2);
        ListView listView = (ListView) findViewById(R.id.searchlist_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList2.get(position);

                if(RoomNum.equals("first")) {
                    if (fruit.getName().equals("阙楼图(东壁)")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("阙楼图(西壁)")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("葡萄花鸟纹银香囊")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("杜虎符")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("天鹅穿莲纹玉佩")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                }
               else  if(RoomNum.equals("second")) {
                    if (fruit.getName().equals("镂空铜豆")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity3.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("彩绘骑马乐俑")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity3.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("琉璃围棋子")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity3.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("蟠虺纹提梁铜盉")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity3.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("二人抬箱图")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity3.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }

                }
               else  if(RoomNum.equals("third")) {
                    if (fruit.getName().equals("陶楼")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity4.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("陶簋")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity4.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("鱼纹葫芦瓶")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity4.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("玄武图")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity4.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("绿琉璃瓶")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity4.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                }
                else if(RoomNum.equals("fourth")) {
                    if (fruit.getName().equals("乐舞图")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity5.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("高昌吉利铜钱")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity5.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("鎏金飞狮宝相花纹")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity5.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("鎏金石榴花纹银盒")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity5.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("拊狻猊罗汉")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity5.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                }
                else if(RoomNum.equals("fifth")) {
                    if (fruit.getName().equals("树下侍女图屏风")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity6.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    } else if (fruit.getName().equals("鎏金折枝花纹银盖")) {
                    Intent intent = new Intent(SearchActivity.this, DisplayActivity6.class);
                    intent.putExtra("item_data", fruit.getName());
                    startActivity(intent);
                }
                    else if (fruit.getName().equals("鎏金花鸟纹银碗")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity6.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("贝币")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity6.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                    else if (fruit.getName().equals("狩猎出行图之二")) {
                        Intent intent = new Intent(SearchActivity.this, DisplayActivity6.class);
                        intent.putExtra("item_data", fruit.getName());
                        startActivity(intent);
                    }
                }
                else
                    Toast.makeText(SearchActivity.this,"无搜索结果",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void initFruits(String string1){
        if(RoomNum.equals("first")) {
            if (string1.startsWith("阙")) {
                Fruit item1 = new Fruit("阙楼图(东壁)", R.drawable.p1);
                fruitList2.add(item1);
                Fruit item2 = new Fruit("阙楼图(西壁)", R.drawable.p2);
                fruitList2.add(item2);
            } else if (string1.startsWith("葡")) {
                Fruit item1 = new Fruit("葡萄花鸟纹银香囊", R.drawable.p3);
                fruitList2.add(item1);
            } else if (string1.startsWith("杜")) {
                Fruit item1 = new Fruit("杜虎符", R.drawable.p4);
                fruitList2.add(item1);
            } else if (string1.startsWith("天")) {
                Fruit item1 = new Fruit("天鹅穿莲纹玉佩", R.drawable.p5);
                fruitList2.add(item1);
            }
        }
        if(RoomNum.equals("second")) {
            if (string1.startsWith("镂")) {
                Fruit item1 = new Fruit("镂空铜豆", R.drawable.p11);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("彩")) {
                Fruit item1 = new Fruit("彩绘骑马乐俑", R.drawable.p12);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("琉")) {
                Fruit item1 = new Fruit("琉璃围棋子", R.drawable.p13);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("蟠")) {
                Fruit item1 = new Fruit("蟠虺纹提梁铜盉", R.drawable.p14);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("二")) {
                Fruit item1 = new Fruit("二人抬箱图", R.drawable.p15);
                fruitList2.add(item1);
            }
        }
        if(RoomNum.equals("third")) {
            if (string1.startsWith("陶")) {
                Fruit item1 = new Fruit("陶楼", R.drawable.p21);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("彩")) {
                Fruit item1 = new Fruit("彩绘陶钫", R.drawable.p22);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("鱼")) {
                Fruit item1 = new Fruit("鱼纹葫芦瓶", R.drawable.p23);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("玄")) {
                Fruit item1 = new Fruit("玄武图", R.drawable.p24);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("绿")) {
                Fruit item1 = new Fruit("绿琉璃瓶", R.drawable.p25);
                fruitList2.add(item1);
            }
        }
        if(RoomNum.equals("fourth")) {
            if (string1.startsWith("乐")) {
                Fruit item1 = new Fruit("乐舞图", R.drawable.p32);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("高")) {
                Fruit item1 = new Fruit("高昌吉利铜钱", R.drawable.p33);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("鎏")) {
                Fruit item1 = new Fruit("鎏金飞狮宝相花纹", R.drawable.p31);
                fruitList2.add(item1);
                Fruit item2 = new Fruit("鎏金石榴花纹银盒", R.drawable.p34);
                fruitList2.add(item2);
            }
            else if (string1.startsWith("拊")) {
                Fruit item1 = new Fruit("拊狻猊罗汉", R.drawable.p35);
                fruitList2.add(item1);
            }

        }
        if(RoomNum.equals("fifth")) {
            if (string1.startsWith("树")) {
                Fruit item1 = new Fruit("树下侍女图屏风", R.drawable.p41);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("贝")) {
                Fruit item1 = new Fruit("贝币", R.drawable.p43);
                fruitList2.add(item1);
            }
            else if (string1.startsWith("鎏")) {
                Fruit item1 = new Fruit("鎏金折枝花纹银盖", R.drawable.p42);
                fruitList2.add(item1);
                Fruit item2 = new Fruit("鎏金花鸟纹银碗", R.drawable.p44);
                fruitList2.add(item2);
            }
            else if (string1.startsWith("狩")) {
                Fruit item1 = new Fruit("狩猎出行图之二", R.drawable.p45);
                fruitList2.add(item1);
            }
        }

    }
}
