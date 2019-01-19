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

//展厅1号里展品

public class DisplayActivity extends Activity {
    String[] textword ={
            "\t\t阙楼图(东壁)——唐代，阙楼图绘制于懿德太子墓。懿德太子李重润是唐中宗长子（公元682年—公元701年），也是中宗李显与韦皇后所生的唯一的儿子。大足元年被武则天处死，公元705年中宗重新即帝位后，追赠其为懿德太子，将其灵柩从洛阳迁回乾陵陪葬，并给予“号墓为陵”的最高礼遇。1971年发掘。墓全长100.8米，由墓道、6个过洞、7个天井、8个小龛、前甬道、后甬道、前墓室、后墓室八部分组成。如此规模宏大的墓葬中，随葬品十分丰富，出土各类文物多达1000余件，壁画近400平米。这些壁画堪称初唐至盛唐具有代表性的绘画流派杰作，在唐代绘画真品中极为罕见墓道东西两侧绘制有两幅阙楼图，阙楼是宫门前的标志性建筑。它由高到低共分三层，表明此阙楼为三出阙，这超出了太子本应使用二重阙的标准，显然是由于“号墓为陵”而使用了皇帝的阙楼规格。在三出阙之后是一座角楼，与侧面的城墙相连。唐代的城墙为夯土筑成，城墙上“凸”字形的建筑称为“马面”，是城墙上的防御工事。画面颜色以赭色（艳红色）为主，绿色为辅，红、黄、青色点缀其间，体现了盛唐时期绘画技巧的高超水平。",
            "\t\t阙楼图(西壁)——唐代，阙楼图绘制于懿德太子墓。懿德太子李重润是唐中宗长子（公元682年—公元701年），也是中宗李显与韦皇后所生的唯一的儿子。大足元年被武则天处死，公元705年中宗重新即帝位后，追赠其为懿德太子，将其灵柩从洛阳迁回乾陵陪葬，并给予“号墓为陵”的最高礼遇。1971年发掘。墓全长100.8米，由墓道、6个过洞、7个天井、8个小龛、前甬道、后甬道、前墓室、后墓室八部分组成。如此规模宏大的墓葬中，随葬品十分丰富，出土各类文物多达1000余件，壁画近400平米。这些壁画堪称初唐至盛唐具有代表性的绘画流派杰作，在唐代绘画真品中极为罕见。墓道东西两侧绘制有两幅阙楼图，阙楼是宫门前的标志性建筑。它由高到低共分三层，表明此阙楼为三出阙，这超出了太子本应使用二重阙的标准，显然是由于“号墓为陵”而使用了皇帝的阙楼规格。在三出阙之后是一座角楼，与侧面的城墙相连。唐代的城墙为夯土筑成，城墙上“凸”字形的建筑称为“马面”，是城墙上的防御工事。画面颜色以赭色（艳红色）为主，绿色为辅，红、黄、青色点缀其间，体现了盛唐时期绘画技巧的高超水平。",
            "\t\t葡萄花鸟纹银香囊——1970年西安何家村唐代窖藏出土了一件葡萄花鸟纹银香囊，香囊外壁用银制，呈圆球形，通体镂空，以中部水平线为界平均分割形成两个半球形，上下球体之间，一侧以钩链相勾合，一侧以活轴相套合，下部球体内又设两层银质的双轴相连的同心圆机环，外层机环与球壁相连，内层机环分别与外层机环和金盂相连，内层机环内安放半圆形金香盂，外壁、机环、金盂之间，用银质铆钉铆接，可以自由转动。这样无论外壁球体怎样转动，由于机环和金盂重力的作用，香盂始终保持重心向下，里面的香料不致撒落于外。尽管已经经历了一千多年，其仍然玲珑剔透，转动起来灵活自如，平衡不倒，其设计之科学与巧妙，令现代人叹绝。唐代，香囊还可用于佛事。人们认为将佛经盛放在香囊之中，随身携带，能起到消灾辟邪的作用。",
            "\t\t杜虎符——我国古代兵符多制成虎形。1973年，西安南郊杜城村附近一位农民犁地时发现了一枚形似虎的秦国兵符，即我们要认识的这件“杜虎符”。杜虎符为左半符，虎作行走状，昂首，尾巴蜷曲。背面有槽，颈上有一小孔。虎符上有错金铭文9行共40字，字体为小篆，内容大意是：右半符掌握在国君手中，左半符在杜地军事长官手中，凡要调动50人以上的带甲兵士，杜地的左符就要与君王的右符相合，才能行动。但遇上烽火报警的紧急情况，不必会君王的右符。铭文反映出秦以“右”为尊，秦国的军权高度集中，凡征调50人以上的兵士必须经国君认可。",
            "\t\t天鹅穿莲纹玉佩——天鹅穿莲纹玉佩是明代配饰的精品，在椭圆形环内，天鹅穿花飞翔，象征在莲花池塘内飞行穿越。",
            "\t\t彩绘持果盘女立俑——唐,陕西省长安县出土;女俑头梳回鹘髻，面庞丰满，细目小口，身着男式圆领袍衫，腰带系于髋部，脚穿尖头鞋，右手持果盘，左手抬于胸前，头和身躯微微右转，重心落在左脚上，身姿婀娜，好似在细步前行等候主人的吩咐。",
            "\t\t侍女与侏儒图——这幅侍女与侏儒图绘制于墓前室东侧，高168厘米，宽102厘米。本图为大家了解唐代侏儒的生活打开了一扇窗户",
            "\t\t执马球杆男侍图——这幅画绘制于唐安公主墓中。唐安公主（公元762年—公元784年），唐德宗李适的女儿。执马球杆男侍图绘制于甬道东壁，高119厘米，宽103厘米。图中两男侍均头戴黑幞头，身穿圆领袍衫，前面的一位双手举于胸前，一副若有所思的神情。后面那位双手举着马球杆，毕恭毕敬地尾随其后，身份较低。",
            "\t\t彩绘持镜女立俑——唐代，女俑头梳乌蛮髻，鬓发抱面，脸庞丰腴，身着嫩绿色长袖衫，下穿红色高腰曳地长裙，右手持一面带柄铜镜，侧头似要照面，身姿婀娜，衣裙线条自然流畅，显露出唐代女性秀美洒脱的气质。",
            "\t\t陶井——汉代，井栏为井字形，四边各出两头，中间为方形井，井身为瓶状束颈，肩部为多道弦纹，井架的顶为四阿式顶，有瓦脊"
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
        Log.d("DisplayActivity", itemname);

        initFruits(itemname);
        DisplayAdapter adapter = new DisplayAdapter(DisplayActivity.this, R.layout.activity_display2, fruitList1);
        ListView listView = (ListView) findViewById(R.id.searchlist_view);
        listView.setAdapter(adapter);

        // 语音讲解模块
        initView();
        initPermission();
        initTTs();
    }

    private void initFruits(String string1){
        if(string1.equals("阙楼图(东壁)")){
            Fruit item1 = new Fruit("阙楼图(东壁)",R.drawable.p1,textword[0]);
            fruitList1.add(item1);
        }
        else  if(string1.equals("阙楼图(西壁)")){
            Fruit item1 = new Fruit("阙楼图(西壁)",R.drawable.p2,textword[1]);
            fruitList1.add(item1);
        }
        else if(string1.equals("葡萄花鸟纹银香囊")){
            Fruit item1 = new Fruit("葡萄花鸟纹银香囊",R.drawable.p3,textword[2]);
            fruitList1.add(item1);
        }
        else if(string1.equals("杜虎符")){
            Fruit item1 = new Fruit("杜虎符",R.drawable.p4,textword[3]);
            fruitList1.add(item1);
        }
        else if(string1.equals("天鹅穿莲纹玉佩")){
            Fruit item1 = new Fruit("天鹅穿莲纹玉佩",R.drawable.p5,textword[4]);
            fruitList1.add(item1);
        }
        else if(string1.equals("彩绘持果盘女立俑")){
            Fruit item1 = new Fruit("彩绘持果盘女立俑",R.drawable.p6,textword[5]);
            fruitList1.add(item1);
        }
        else if(string1.equals("侍女与侏儒图")){
            Fruit item1 = new Fruit("侍女与侏儒图",R.drawable.p7,textword[6]);
            fruitList1.add(item1);
        }
        else if(string1.equals("执马球杆男侍图")){
            Fruit item1 = new Fruit("执马球杆男侍图",R.drawable.p8,textword[7]);
            fruitList1.add(item1);
        }
        else if(string1.equals("彩绘持镜女立俑")){
            Fruit item1 = new Fruit("天鹅穿莲纹玉佩",R.drawable.p9,textword[8]);
            fruitList1.add(item1);
        }
        else if(string1.equals("陶井")){
            Fruit item1 = new Fruit("陶井",R.drawable.p10,textword[9]);
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
                            Toast.makeText(DisplayActivity.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        // 暂停
                        else if(speakFlag == 1){
                            mSpeechSynthesizer.pause();
                            mSpeak.setBackgroundResource(R.drawable.audio_start);
                            Toast.makeText(DisplayActivity.this, "语音讲解已暂停", Toast.LENGTH_SHORT).show();
                            speakFlag = 2;
                        }
                        // 继续
                        else if(speakFlag == 2){
                            mSpeechSynthesizer.resume();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(DisplayActivity.this, "语音讲解继续", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        break;
                    case R.id.respeak1:
                        speak();
                        speakFlag = 1;
                        mSpeak.setBackgroundResource(R.drawable.audio_on);
                        Toast.makeText(DisplayActivity.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
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
