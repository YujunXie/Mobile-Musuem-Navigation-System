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
//展厅3号里展品

public class DisplayActivity4 extends Activity {
    String[] textword ={
            "\t\t陶楼——陶楼，魏晋，高31cm，长36cm。通体施绿釉，有剥蚀，屋顶正中有方形口，四角饰小熊，两侧有孔，四周有墙，瓦顶有瓦垄，前有瓦当，屋檐四角各有六云纹瓦当，中间一层一周有五人，前边有门，后边有方窗，两侧有圆窗，四周有围栏，最下方形底座四面有窗棱。",
            "\t\t彩绘陶钫—— 汉代，高47.5cm，口径12cm。 方口,束颈,鼓腹,圈足呈方形,带盖,盖上有四凤鸟纹钮。腹部两侧贴塑对称的兽面衔环铺首,器身有3道红色弦纹将器身分为四部分,第一部分,肩部绘三角纹,颈部绘云气纹;第二部分腹部用红、白彩绘云气纹。器形个体较大,纹饰精致,色彩鲜艳,是同类器物中难得的精品。 " ,
            "\t\t鱼纹葫芦瓶——新石器时代。瓶高25.4cm，口径3.4cm，底径8.8cm。泥质红陶，短直口，束颈，颈腹分界明显，鼓腹，最大径偏下，口至腹部全饰黑彩，下腹绘两组黑彩变体鱼纹。",
            "\t\t玄武图——这幅玄武图绘制于墓室北壁，高188厘米、宽175厘米。图中描绘了一龟一蛇交缠在一起的场景。龟直立爬行，引颈回首，而全身布满花斑鳞甲的巨蛇，在龟身上缠绕了三圈，头尾相绕，形成环形。在中部，蛇头和龟首张口吐信，怒目相对。整个画面显得非常生动，很有感染力。",
            "\t\t绿琉璃瓶——西安东郊隋墓出土，瓶高8.4厘米。瓶体呈球形，绿色透明。瓶口为管状，中空与瓶身相接，颈部下有一圆台，肩部对称凸起四个三角形，腹部装饰四个突出的圆饼形。底有圈足。",
            "\t\t鎏金蔓草蝴蝶纹银——唐代。长35.5CM，钗柄扁平,分为两股,长短基本相等,钗托为花蕾形,其上錾刻纹饰,上连有挽成“倒8”字形交花,钗面各有一片花叶,每片花叶上镂刻出一只展翅的蝴蝶,构成具有灵动感的主题纹饰,其下衬以蔓草纹.通体鎏金。这件银钗装饰考究,工艺精湛,是研究唐代妇女服饰重要的实物资料.",
            "\t\t青铜双耳杯——西周。高14.4cm，口径8.4cm，最宽22cm，腹深11cm，底径7cm，长安张家坡窖藏出土。杯身为觚形，两侧有镂空双柄，柄上端作鸟尾状，矮圈足，中腰饰有一道凸弦纹",
            "\t\t陶簋——新石器时代。细泥红陶制成，敛口，腹壁平直，腹底部内折并出棱，喇叭型高圈足，圈足中部有三个等距离的圆孔，腹壁外施白色陶衣，用黑彩绘出三层纹饰，上层为连续的编索纹，中间为间隔的树叶纹，下层为斜线纹，是研究黄河流域仰韶文化发展的宝贵资料。",
            "\t\t捧盆景侍女图—— 这幅画绘制于章怀太子墓中。章怀太子李贤（公元654年—公元684年），唐高宗第六子。文明元年，章怀太子墓发掘于1971年。地下全长71米，由墓道、4个过洞、4 个天井、6个小龛、甬道及前后墓室组成。墓中出土了600多件随葬品及50多组壁画。",
            "\t\t金箔——这件文物出土于唐代何家村窖藏。自古皇室就有追求长生不老之风，宫廷内供养方士仙人，为皇室炼制仙丹。唐代不仅盛行服用丹药，还服食黄金，当时认为黄金具有“镇心安神、定惊除痫，辟恶去邪”神奇疗效。"
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
        DisplayAdapter adapter = new DisplayAdapter(DisplayActivity4.this, R.layout.activity_display2, fruitList1);
        ListView listView = (ListView) findViewById(R.id.searchlist_view);
        listView.setAdapter(adapter);

        // 语音讲解模块
        initView();
        initPermission();
        initTTs();
    }
    private void initFruits(String string1){
        if(string1.equals("陶楼")){
            Fruit item1 = new Fruit("陶楼",R.drawable.p21,textword[0]);
            fruitList1.add(item1);
        }
        else  if(string1.equals("彩绘陶钫")){
            Fruit item1 = new Fruit("彩绘陶钫",R.drawable.p22,textword[1]);
            fruitList1.add(item1);
        }
        else if(string1.equals("鱼纹葫芦瓶")){
            Fruit item1 = new Fruit("鱼纹葫芦瓶",R.drawable.p23,textword[2]);
            fruitList1.add(item1);
        }
        else if(string1.equals("玄武图")){
            Fruit item1 = new Fruit("玄武图",R.drawable.p24,textword[3]);
            fruitList1.add(item1);
        }
        else if(string1.equals("绿琉璃瓶")){
            Fruit item1 = new Fruit("绿琉璃瓶",R.drawable.p25,textword[4]);
            fruitList1.add(item1);
        }
        else if(string1.equals("鎏金蔓草蝴蝶纹银")){
            Fruit item1 = new Fruit("鎏金蔓草蝴蝶纹银",R.drawable.p26,textword[5]);
            fruitList1.add(item1);
        }
        else if(string1.equals("青铜双耳杯")){
            Fruit item1 = new Fruit("青铜双耳杯",R.drawable.p27,textword[6]);
            fruitList1.add(item1);
        }
        else if(string1.equals("陶簋")){
            Fruit item1 = new Fruit("陶簋",R.drawable.p28,textword[7]);
            fruitList1.add(item1);
        }
        else if(string1.equals("捧盆景侍女图")){
            Fruit item1 = new Fruit("捧盆景侍女图",R.drawable.p29,textword[8]);
            fruitList1.add(item1);
        }
        else if(string1.equals("金箔")){
            Fruit item1 = new Fruit("金箔",R.drawable.p30,textword[9]);
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
                            Toast.makeText(DisplayActivity4.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        // 暂停
                        else if(speakFlag == 1){
                            mSpeechSynthesizer.pause();
                            mSpeak.setBackgroundResource(R.drawable.audio_start);
                            Toast.makeText(DisplayActivity4.this, "语音讲解已暂停", Toast.LENGTH_SHORT).show();
                            speakFlag = 2;
                        }
                        // 继续
                        else if(speakFlag == 2){
                            mSpeechSynthesizer.resume();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(DisplayActivity4.this, "语音讲解继续", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        break;
                    case R.id.respeak1:
                        speak();
                        speakFlag = 1;
                        mSpeak.setBackgroundResource(R.drawable.audio_on);
                        Toast.makeText(DisplayActivity4.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
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