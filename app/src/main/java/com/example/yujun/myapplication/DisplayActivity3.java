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
//展厅2号里展品

public class DisplayActivity3 extends Activity {
    String[] textword ={
            "\t\t镂空铜豆——西周，铜豆为直口，浅盘，盘底微凹，高坐呈喇叭形。盘壁装饰一周顾首卷尾的花冠夔龙纹，夔龙身体曲折，两两相对。高座铸成镂空的环带纹，环带纹上阴线刻细纹。这种形制的豆,有自名为'簠'、'铺'、'甫'的。西周中期开始出现，首先是出现在宝鸡，逐渐向中原渐次传播。",
            "\t\t彩绘骑马乐俑——南北朝,乐俑上身着左衽翻领，外披套衣。头发涂有红色颜料，结成发辫垂于肩上。双手半握拳置于胸腹间，中皆有一孔，原持物已失。",
            "\t\t琉璃围棋子——隋代 一级文物,琉璃棋子8个，均为平底，尖顶，通体绿色，形制规整，一个略小,其它大小相同。",
        "\t\t蟠虺纹提梁铜盉——战国 一级文物,铜盉直口、矮颈、扁球体腹。足部为三立鹰形，盉盖上有环形钮，钮上有大小相次的套环连接在提梁上。器腹一侧有一半身蹲形流与器腹相通，器盖四周及颈部饰以变形云纹和叶纹，腹部饰有云雷纹衬底的蟠螭纹，连接盖链的两大铜环上均饰云雷纹。整体纹饰纤细密集。",
        "\t\t二人抬箱图——唐代。7这幅画绘制于甬道西壁，高77厘米、宽123厘米。图中两位男侍从，面色红润、表情安然。他们头戴黑色幞头，上身穿土红色圆领袍衫，下身穿浅色长裤，足蹬黑靴，抬着一个长方形的箱子，正向着墓主人所在的墓室大步前行。为了方便行动，还将前衣襟掖在腰带上，极富生活气息。",
            "\t\t玉串——西周。通长32cm，串饰一组共32件，由玛瑙管4个，绿松石珠13个组成。组玉佩在西周是贵族身份在服饰上的体现之一，身份愈复杂，佩饰愈长。这组玉佩，色彩绚丽，装饰华美。",
            "\t\t越式鼎——战国。通高40cm，口径21.7cm，腹深11.3cm。越式鼎敛口，浅腹，三高足外撇，平底，最大径在最底部，双附耳微外撇。鼎盖为母口，盖上有三小附钮扁平，中间有一桥钮。盖上有纹饰三圈，内圈为绹索纹，中间一圈和外圈为蟠虺纹，附耳上装饰有羽状纹",
            "\t\t蟠龙纹提梁铜盉——战国。高23.2cm,口径10cm,腹径19.6cm,腹深14.2cm，虎形提梁,鸟形流,球腹,圆底,足为兽面人身,上托展翅欲翔的三鹰,肩腹部饰规整的弦纹、蟠螭纹带,流口部可张可合。",
            "\t\t安邑二釿币——战国。 长6.7cm，宽4cm。 圆肩方足,短銎,圆档,布背素平,钱面书“安邑二釿”篆体。背部书“安”字。",
            "\t\t鎏金蔓草纹银羽觞——唐代。高3.2cm，长10.6cm，宽7.5cm，银羽觞侈口，双耳椭圆形平底，内壁有折枝莲四株，空白处填以流云纹。底部饰以团花，器外长边两侧各饰一只鸿雁和鸳鸯站在莲瓣上，短边两侧分别饰一对鸳鸯和鸿雁，以蔓草将器体分为四个区域，外底部饰以与内底相同的团花，通体饰花纹，纹样鎏金，鱼子纹地。",

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
        DisplayAdapter adapter = new DisplayAdapter(DisplayActivity3.this, R.layout.activity_display2, fruitList1);
        ListView listView = (ListView) findViewById(R.id.searchlist_view);
        listView.setAdapter(adapter);

        // 语音讲解模块
        initView();
        initPermission();
        initTTs();
    }

    private void initFruits(String string1){
        if(string1.equals("镂空铜豆")){
        Fruit item1 = new Fruit("镂空铜豆",R.drawable.p11,textword[0]);
        fruitList1.add(item1);
        }

        else  if(string1.equals("彩绘骑马乐俑")){
        Fruit item1 = new Fruit("彩绘骑马乐俑",R.drawable.p12,textword[1]);
        fruitList1.add(item1);
        }

        else if(string1.equals("琉璃围棋子")){
                Fruit item1 = new Fruit("琉璃围棋子",R.drawable.p13,textword[2]);
                fruitList1.add(item1);
        }

        else if(string1.equals("蟠虺纹提梁铜盉")){
        Fruit item1 = new Fruit("蟠虺纹提梁铜盉",R.drawable.p14,textword[3]);
        fruitList1.add(item1);
        }

        else if(string1.equals("二人抬箱图")){
        Fruit item1 = new Fruit("二人抬箱图",R.drawable.p15,textword[4]);
        fruitList1.add(item1);
        }

        else if(string1.equals("玉串")){
        Fruit item1 = new Fruit("玉串",R.drawable.p16,textword[5]);
        fruitList1.add(item1);
        }

        else if(string1.equals("越式鼎")){
                Fruit item1 = new Fruit("越式鼎",R.drawable.p17,textword[6]);
                fruitList1.add(item1);
        }

        else if(string1.equals("蟠龙纹提梁铜盉")){
                Fruit item1 = new Fruit("蟠龙纹提梁铜盉",R.drawable.p18,textword[7]);
                fruitList1.add(item1);
        }

        else if(string1.equals("安邑二釿币")){
                Fruit item1 = new Fruit("安邑二釿币",R.drawable.p19,textword[8]);
                fruitList1.add(item1);
        }

        else if(string1.equals("鎏金蔓草纹银羽觞")){
                Fruit item1 = new Fruit("鎏金蔓草纹银羽觞",R.drawable.p20,textword[9]);
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
                            Toast.makeText(DisplayActivity3.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        // 暂停
                        else if(speakFlag == 1){
                            mSpeechSynthesizer.pause();
                            mSpeak.setBackgroundResource(R.drawable.audio_start);
                            Toast.makeText(DisplayActivity3.this, "语音讲解已暂停", Toast.LENGTH_SHORT).show();
                            speakFlag = 2;
                        }
                        // 继续
                        else if(speakFlag == 2){
                            mSpeechSynthesizer.resume();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(DisplayActivity3.this, "语音讲解继续", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        break;
                    case R.id.respeak1:
                        speak();
                        speakFlag = 1;
                        mSpeak.setBackgroundResource(R.drawable.audio_on);
                        Toast.makeText(DisplayActivity3.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
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