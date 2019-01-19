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
//展厅4号里展品

public class DisplayActivity5 extends Activity {
    String[] textword ={
            "\t\t鎏金飞狮宝相花纹——银盒出土于唐代何家村窖藏，高5.6厘米，重425克。银盒上下以子母口相扣合，捶打成型后又经切削打磨处理修整得光滑圆润。盒内面以鱼子纹为底，盖面在麦穗纹圆形框架中，錾刻出一只飞扬双翼的狮子，周围绕以折枝花，盖底中心平錾出一朵团花及石榴花结等。纹饰全部鎏金，黄白辉映，煜煜夺目。",
            "\t\t乐舞图——这幅画绘制于苏思勖墓中。乐舞图绘制于墓室东壁，全长4.1米、高147厘米，由于画面篇幅巨大，揭取时将整幅画面分割为三个部分。中间一幅为舞蹈者，两边两幅绘制的是乐队。",
            "\t\t高昌吉利铜钱——“高昌吉利”钱为高昌国（今新疆地区）在唐朝贞观年间铸行的一种流通货币，此钱直径2.6厘米，重约12克，轮郭规整，制作精良。正面为汉文隶书“高昌吉利”四字，背面无文。隶书端庄凝重，古朴苍劲，既体现出高昌国高超的铸钱水平，也是汉文化在西域的充分展现。",
            "\t\t鎏金石榴花纹银盒——这件银盒纹饰錾刻流畅，一气呵成，表现了唐代高超的工艺，并将石榴花与本土花色有机组合，是中国同中西亚文化交流的产物。是研究中西方文化重要的史学资料。",
            "\t\t拊狻猊罗汉——这件石雕罗汉是1980年在陕西富县直罗镇柏山寺塔内第四层龛洞中发现的，均由黄砂石雕刻而成，罗汉双手相握禅盘坐于佛台上，头向右下方微侧，表情含蓄文静，右侧立有一狻猊，正抬头撒娇，二者十分和谐。",
            "\t\t裸体俑——汉代出土的陶俑从形制上分为塑衣式陶俑和着衣式陶俑两种类型。塑衣式陶俑是指人物形象及服饰同时塑成，并施以彩绘；着衣式陶俑在随葬时身上穿着真正的丝绸、棉麻或铠甲类等服饰，但随着时间的推移，昔日的衣装已糟朽、碳化，成为今天所能看见的裸体俑。",
            "\t\t托果盘侍女图——这幅画绘制于房陵公主墓中。房龄公主是唐高祖李渊的第六个女儿，咸亨四年（公元673年）逝世，陪葬高祖献陵，终年五十五岁。房陵公主墓位于陕西省富平县，1975年发掘。",
            "\t\t仪仗出行图之四——这幅画绘制于李寿墓中。李寿是唐高祖李渊的从弟，唐朝开国功臣之一，后被封为淮安王。李寿墓位于陕西省三原县，1973年发掘。",
            "\t\t双狮纹金铛——狮纹金铛是何家村窖藏发现的金银铛中最华丽的一件。在铛的外底部中心分出九条水波纹曲线，将外壁分成九个“S”形区间，内錾刻出双鸟衔绶、狮子及花卉等纹饰，底部饰满鱼子纹。铛内底还饰有高浮雕式的双狮在奔跑嬉戏。整体构图协调华美，体现了唐代金银器富丽华美的特点。",
            "\t\t鎏金熊纹六曲银盘——银盘为六曲形，制作相当考究。银盘锤揲制成，盘底中心装饰有熊纹饰，熊纹通体鎏金，光亮如新。熊仰首作咆哮状，形态生动、自然逼真。",


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
        DisplayAdapter adapter = new DisplayAdapter(DisplayActivity5.this, R.layout.activity_display2, fruitList1);
        ListView listView = (ListView) findViewById(R.id.searchlist_view);
        listView.setAdapter(adapter);

        // 语音讲解模块
        initView();
        initPermission();
        initTTs();
    }

    private void initFruits(String string1){
        if(string1.equals("鎏金飞狮宝相花纹")){
            Fruit item1 = new Fruit("鎏金飞狮宝相花纹",R.drawable.p31,textword[0]);
            fruitList1.add(item1);
        }
        else  if(string1.equals("乐舞图")){
            Fruit item1 = new Fruit("乐舞图",R.drawable.p32,textword[1]);
            fruitList1.add(item1);
        }
        else if(string1.equals("高昌吉利铜钱")){
            Fruit item1 = new Fruit("高昌吉利铜钱",R.drawable.p33,textword[2]);
            fruitList1.add(item1);
        }
        else if(string1.equals("鎏金石榴花纹银盒")){
            Fruit item1 = new Fruit("鎏金石榴花纹银盒",R.drawable.p34,textword[3]);
            fruitList1.add(item1);
        }
        else if(string1.equals("拊狻猊罗汉")){
            Fruit item1 = new Fruit("拊狻猊罗汉",R.drawable.p35,textword[4]);
            fruitList1.add(item1);
        }
        else if(string1.equals("裸体俑")){
            Fruit item1 = new Fruit("裸体俑",R.drawable.p36,textword[5]);
            fruitList1.add(item1);
        }
        else if(string1.equals("托果盘侍女图")){
            Fruit item1 = new Fruit("托果盘侍女图",R.drawable.p37,textword[6]);
            fruitList1.add(item1);
        }
        else if(string1.equals("仪仗出行图之四")){
            Fruit item1 = new Fruit("仪仗出行图之四",R.drawable.p38,textword[7]);
            fruitList1.add(item1);
        }
        else if(string1.equals("双狮纹金铛")){
            Fruit item1 = new Fruit("双狮纹金铛",R.drawable.p39,textword[8]);
            fruitList1.add(item1);
        }
        else if(string1.equals("鎏金熊纹六曲银盘")){
            Fruit item1 = new Fruit("鎏金熊纹六曲银盘",R.drawable.p40,textword[9]);
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
                            Toast.makeText(DisplayActivity5.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        // 暂停
                        else if(speakFlag == 1){
                            mSpeechSynthesizer.pause();
                            mSpeak.setBackgroundResource(R.drawable.audio_start);
                            Toast.makeText(DisplayActivity5.this, "语音讲解已暂停", Toast.LENGTH_SHORT).show();
                            speakFlag = 2;
                        }
                        // 继续
                        else if(speakFlag == 2){
                            mSpeechSynthesizer.resume();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(DisplayActivity5.this, "语音讲解继续", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        break;
                    case R.id.respeak1:
                        speak();
                        speakFlag = 1;
                        mSpeak.setBackgroundResource(R.drawable.audio_on);
                        Toast.makeText(DisplayActivity5.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
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