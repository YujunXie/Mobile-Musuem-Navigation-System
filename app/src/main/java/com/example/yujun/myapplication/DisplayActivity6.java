package com.example.yujun.myapplication;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.tts.chainofresponsibility.logger.LoggerProxy;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;
import com.example.yujun.myapplication.control.Fruit;
import com.example.yujun.myapplication.control.InitConfig;
import com.example.yujun.myapplication.listener.UiMessageListener;
import com.example.yujun.myapplication.utils.AutoCheck;
import com.example.yujun.myapplication.utils.DisplayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/5.
 */
//展厅5号里展品

public class DisplayActivity6 extends Activity {
    String[] textword ={
            "树下侍女图（屏风）——这幅壁画出土于陕西省长安县南里王村一座唐代竖井砖墓中，总计六条，屏条之间用宽约10厘米的红框相隔。由于面积较大，揭取时将其按屏条分割为六块。六合屏风每条的主人都是一位装束相同的仕女",
            "鎏金折枝花纹银盖——带盖银碗在何家村窖藏共出土三件，这三件盖碗在属窖藏中时代较晚的器物，大体属于唐德宗时期。此银盖碗重1380克，盖足内有莲叶组成的六出团花一朵，盖周围散点排列出六朵忍冬结，腹部錾刻六株形像葡萄石榴的折枝花，盖面和底部的外沿均有六朵小花，纹饰鎏金，圈足内沿还錾刻有“进”字。",
            "贝币——在西周时的内陆地区，这些产自海边的贝壳是不多见的，物以稀为贵，加之由于好携带、易于储存、不易变质等因素，成为了物物交换之后的流通货币",
            "鎏金花鸟纹银碗——银碗出土于唐代何家村窖藏，高3.1厘米，重136克。碗内外錾刻花纹，纹饰鎏金，通体以鱼子纹为底。碗心及足底各有一朵宝相花，内腹壁装饰有折枝花及流云，外腹壁折枝花草间錾刻出一对鸳鸯，两只鹦鹉。",
            "狩猎出行图之二——从这部分画中，我们可以看到中间大队人马共有六排，束腰佩剑，驾鹰抱犬，前呼后应。行走在大队人马最前列的是一位身穿紫袍、体态雍容的男子。",
            "东罗马金币——该金币直径2厘米，重4.6克，正面为头戴王冠，身披甲袍的国王半身像，左侧为希拉克略，右侧为他的儿子，背面四级台座上有末端刻有西字形的十字架图案，周围边缘有铭文。",
            "盛放丹砂的银盒——何家村出土的丹砂均盛放于有墨书标记的银盒内。唐代质量上乘的丹砂是很难得到的。而何家村出土丹砂不仅质量好，而且数量多，总重达两百余两。",
            "莲瓣纹提梁银罐——何家村窖藏出土的金银器中，有很多器物上的莲瓣纹饰内都雕刻有唐代流行的各种花鸟纹、珍禽异兽纹等。这件银罐的莲瓣内没有装饰任何图案，莲瓣彼此相连突起，增加了纹饰立体感，显得素雅大方。出土时罐内有玻璃杯等器物。",
            "“和同开珎”银币——“和同开珎”银币是日本奈良王朝元明天皇和铜元年（相当于唐中宗景龙二年，公元708年）仿效中国唐代“开元通宝”钱铸造的货币。它从始铸到停废仅一年三个月，所以铸量不多，现在日本国内也存量有限。何家村窖藏中一次出土了五枚银质“和同开珎”。经考证是唐玄宗开元四年（716年）日本第七次遣唐使带入中国的。",
            "仪卫图——章怀太子墓道东、西壁《客使图》之后，第一过洞廊柱建筑之前，是一组位于东、西壁的《仪卫图》。这一幅是东壁北段的《仪卫图》，较为完整，高222厘米，宽227.5厘米。",
    };

    private List<Fruit> fruitList1 = new ArrayList<>();

    // 语音讲解相关
    protected String appId = "10614542";
    protected String appKey = "YyvqArNlMygI6dF6GMWKlvGFn3oVgspe";
    protected String secretKey = "lFOHaYXoGQbRvKNAk38ghnzEMqyZeNTn";
    // TtsMode.ONLINE 纯在线, 没有纯离线
    protected TtsMode ttsMode = TtsMode.ONLINE;
    // 主控制类，所有合成控制方法从这个类开始
    protected SpeechSynthesizer mSpeechSynthesizer;
    private static final String TAG = "DisplayActivity";

    private Button mSpeak;
    private Button mReSpeak;
    private TextView mShowText;
    protected Handler mainHandler;

    private int speakFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String itemname = intent.getStringExtra("item_data");

        initFruits(itemname);
        DisplayAdapter adapter = new DisplayAdapter(DisplayActivity6.this, R.layout.activity_display2, fruitList1);
        ListView listView = (ListView) findViewById(R.id.searchlist_view);
        listView.setAdapter(adapter);

        // 语音讲解模块
        initView();
        initPermission();
        initTTs();
    }

    private void initFruits(String string1){
        if(string1.equals("树下侍女图屏风")){
            Fruit item1 = new Fruit("树下侍女图屏风",R.drawable.p41,textword[0]);
            fruitList1.add(item1);
        }
        else  if(string1.equals("鎏金折枝花纹银盖")){
            Fruit item1 = new Fruit("鎏金折枝花纹银盖",R.drawable.p42,textword[1]);
            fruitList1.add(item1);
        }
        else if(string1.equals("贝币")){
            Fruit item1 = new Fruit("贝币",R.drawable.p43,textword[2]);
            fruitList1.add(item1);
        }
        else if(string1.equals("鎏金花鸟纹银碗")){
            Fruit item1 = new Fruit("鎏金花鸟纹银碗",R.drawable.p44,textword[3]);
            fruitList1.add(item1);
        }
        else if(string1.equals("狩猎出行图之二")){
            Fruit item1 = new Fruit("狩猎出行图之二",R.drawable.p45,textword[4]);
            fruitList1.add(item1);
        }
        else if(string1.equals("东罗马金币")){
            Fruit item1 = new Fruit("东罗马金币",R.drawable.p46,textword[5]);
            fruitList1.add(item1);
        }
        else if(string1.equals("盛放丹砂的银盒")){
            Fruit item1 = new Fruit("盛放丹砂的银盒",R.drawable.p47,textword[6]);
            fruitList1.add(item1);
        }
        else if(string1.equals("莲瓣纹提梁银罐")){
            Fruit item1 = new Fruit("莲瓣纹提梁银罐",R.drawable.p48,textword[7]);
            fruitList1.add(item1);
        }
        else if(string1.equals("和同开珎银币")){
            Fruit item1 = new Fruit("和同开珎银币",R.drawable.p49,textword[8]);
            fruitList1.add(item1);
        }
        else if(string1.equals("仪卫图")){
            Fruit item1 = new Fruit("仪卫图",R.drawable.p50,textword[9]);
            fruitList1.add(item1);
        }


    }

    // 语音讲解模块
    public void initView(){

        mSpeak = (Button) this.findViewById(R.id.speak1);
        mReSpeak = (Button) this.findViewById(R.id.respeak1);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                int id = v.getId();
                mShowText = (TextView) findViewById(R.id.fruit_text2);
                switch (id) {
                    case R.id.speak1:
                        // 第一次播放
                        if(speakFlag == 0){
                            speak();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(DisplayActivity6.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        // 暂停
                        else if(speakFlag == 1){
                            mSpeechSynthesizer.pause();
                            mSpeak.setBackgroundResource(R.drawable.audio_start);
                            Toast.makeText(DisplayActivity6.this, "语音讲解已暂停", Toast.LENGTH_SHORT).show();
                            speakFlag = 2;
                        }
                        // 继续
                        else if(speakFlag == 2){
                            mSpeechSynthesizer.resume();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(DisplayActivity6.this, "语音讲解继续", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        break;
                    case R.id.respeak1:
                        speak();
                        speakFlag = 1;
                        mSpeak.setBackgroundResource(R.drawable.audio_on);
                        Toast.makeText(DisplayActivity6.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        speakFlag = 0;
                        break;
                }
            }
        };
        mSpeak.setOnClickListener(listener);
        mReSpeak.setOnClickListener(listener);
        mainHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.obj != null) {
                    print(msg.obj.toString());
                }
            }

        };
    }

    private void initTTs() {
        LoggerProxy.printable(true); // 日志打印在logcat中

        // 日志更新在UI中，可以换成MessageListener，在logcat中查看日志
        SpeechSynthesizerListener listener = new UiMessageListener(mainHandler);

        // 获取实例
        mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        mSpeechSynthesizer.setContext(this);

        // 设置listener
        mSpeechSynthesizer.setSpeechSynthesizerListener(listener);

        // 设置appId，appKey.secretKey
        int result = mSpeechSynthesizer.setAppId(appId);
        checkResult(result, "setAppId");
        result = mSpeechSynthesizer.setApiKey(appKey, secretKey);
        checkResult(result, "setApiKey");

        // 以下setParam 参数选填。不填写则默认值生效
        // 设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
        // 设置合成的音量，0-9 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "5");
        // 设置合成的语速，0-9 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "6");
        // 设置合成的语调，0-9 ，默认 5
        mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "3");

        //mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_HIGH_SPEED_SYNTHESIZE);
        // 该参数设置为TtsMode.MIX生效。即纯在线模式不生效。
        // MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
        // MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线

        mSpeechSynthesizer.setAudioStreamType(AudioManager.MODE_IN_CALL);

        // 额外: 自动so文件是否复制正确及上面设置的参数
        Map<String, String> params = new HashMap<>();
        // 复制下上面的 mSpeechSynthesizer.setParam参数
        // 上线时请删除AutoCheck的调用

        InitConfig initConfig =  new InitConfig(appId, appKey, secretKey, ttsMode, params, listener);
        AutoCheck.getInstance(getApplicationContext()).check(initConfig, new Handler() {
            @Override
            /**
             * 开新线程检查，成功后回调
             */
            public void handleMessage(Message msg) {
                if (msg.what == 100) {
                    AutoCheck autoCheck = (AutoCheck) msg.obj;
                    synchronized (autoCheck) {
                        String message = autoCheck.obtainDebugMessage();
                        print(message);
                    }
                }
            }

        });

        // 初始化
        result = mSpeechSynthesizer.initTts(ttsMode);
        checkResult(result, "initTts");
    }

    private void speak() {
        /* 以下参数每次合成时都可以修改
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "0");
         *  设置在线发声音人： 0 普通女声（默认） 1 普通男声 2 特别男声 3 情感男声<度逍遥> 4 情感儿童声<度丫丫>
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "5"); 设置合成的音量，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "5"); 设置合成的语速，0-9 ，默认 5
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_PITCH, "5"); 设置合成的语调，0-9 ，默认 5
         *
         *  mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_DEFAULT);
         *  MIX_MODE_DEFAULT 默认 ，wifi状态下使用在线，非wifi离线。在线状态下，请求超时6s自动转离线
         *  MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI wifi状态下使用在线，非wifi离线。在线状态下， 请求超时1.2s自动转离线
         *  MIX_MODE_HIGH_SPEED_NETWORK ， 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
         *  MIX_MODE_HIGH_SPEED_SYNTHESIZE, 2G 3G 4G wifi状态下使用在线，其它状态离线。在线状态下，请求超时1.2s自动转离线
         */

        if (mSpeechSynthesizer == null) {
            print("[ERROR], 初始化失败");
            return;
        }
        int result = mSpeechSynthesizer.speak(mShowText.getText().toString());
        print("合成并播放 按钮已经点击");
        checkResult(result, "speak");
    }

    private void stop() {
        print("停止合成引擎 按钮已经点击");
        int result = mSpeechSynthesizer.stop();
        checkResult(result, "stop");
    }

    private void print(String message) {
        Log.i(TAG, message);
//        mShowText.append(message + "\n");
    }

    @Override
    protected void onDestroy() {
        if (mSpeechSynthesizer != null) {
            mSpeechSynthesizer.stop();
            mSpeechSynthesizer.release();
            mSpeechSynthesizer = null;
            print("释放资源成功");
        }
        super.onDestroy();
    }

    private void checkResult(int result, String method) {
        if (result != 0) {
            print("error code :" + result + " method:" + method + ", 错误码文档:http://yuyin.baidu.com/docs/tts/122 ");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // 此处为android 6.0以上动态授权的回调，用户自行实现。
    }

    /**
     * android 6.0 以上需要动态申请权限
     */
    private void initPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.MODIFY_AUDIO_SETTINGS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
                // 进入到这里代表没有权限.
            }
        }
        String[] tmpList = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);
        }

    }
}