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
import com.example.yujun.myapplication.control.InitConfig;
import com.example.yujun.myapplication.control.ShowItem;
import com.example.yujun.myapplication.listener.UiMessageListener;
import com.example.yujun.myapplication.utils.Adapter;
import com.example.yujun.myapplication.utils.AutoCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class secondActivity extends Activity {

    private List<ShowItem> ShowList = new ArrayList<>();

    // 语音讲解相关
    protected String appId = "10614542";
    protected String appKey = "YyvqArNlMygI6dF6GMWKlvGFn3oVgspe";
    protected String secretKey = "lFOHaYXoGQbRvKNAk38ghnzEMqyZeNTn";
    // TtsMode.ONLINE 纯在线, 没有纯离线
    protected TtsMode ttsMode = TtsMode.ONLINE;
    // 主控制类，所有合成控制方法从这个类开始
    protected SpeechSynthesizer mSpeechSynthesizer;
    private static final String TAG = "secondActivity";

    private Button mSpeak;
    private Button mReSpeak;
    private TextView mShowText;
    protected Handler mainHandler;

    private int speakFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exhibition);

        initActivity();

        // 语音讲解模块
        initView();
        initPermission();
        initTTs();
    }

    public void initActivity(){

        Intent intent = getIntent();
        final String data = intent.getStringExtra("list");

        //initExhRoom();
        String[] name = new String[11];
        name[0] = (String) this.getResources().getText(R.string.text1);
        name[1] = (String) this.getResources().getText(R.string.text2);
        name[2] = (String) this.getResources().getText(R.string.text3);
        name[3] = (String) this.getResources().getText(R.string.text4);
        name[4] = (String) this.getResources().getText(R.string.text5);
        name[5] = (String) this.getResources().getText(R.string.text6);
        name[6] = (String) this.getResources().getText(R.string.text7);
        name[7] = (String) this.getResources().getText(R.string.text8);
        name[8] = (String) this.getResources().getText(R.string.text9);
        name[9] = (String) this.getResources().getText(R.string.text10);
        //name[10] = (String) this.getResources().getText(R.string.things_button);
        if(data.equals("first")) {
            final ShowItem first = new ShowItem(name[0], name[1],R.drawable.first_1);
            ShowList.add(first);
//            Adapter adapter = new Adapter(this, R.layout.exhibit_item, ShowList);
//            ListView listView = (ListView) findViewById(R.id.exhibition_list);
//            listView.setAdapter(adapter);
//
//            Button button1= (Button) findViewById(R.id.things_button);
//            button1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(secondActivity.this, ThirdActivity.class);
//                    intent.putExtra("button", data);
//                    secondActivity.this.startActivity(intent);
//                }
//            });
        }
        if(data.equals("second")) {
            final ShowItem second = new ShowItem(name[2],name[3],R.drawable.first_2);
            ShowList.add(second);
//            Adapter adapter = new Adapter(this,R.layout.exhibit_item,ShowList);
//            ListView listView = (ListView) findViewById(R.id.exhibition_list);
//            listView.setAdapter(adapter);
//
//            Button button1= (Button) findViewById(R.id.things_button);
//            button1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(secondActivity.this, ThirdActivity.class);
//                    intent.putExtra("button",data);
//                    secondActivity.this.startActivity(intent);
//                }
//            });
        }
        if(data.equals("third")) {
            final ShowItem third = new ShowItem(name[4],name[5],R.drawable.first_3);
            ShowList.add(third);
//            Adapter adapter = new Adapter(this,R.layout.exhibit_item,ShowList);
//            ListView listView = (ListView) findViewById(R.id.exhibition_list);
//            listView.setAdapter(adapter);
//
//            Button button1= (Button) findViewById(R.id.things_button);
//            button1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(secondActivity.this, ThirdActivity.class);
//                    intent.putExtra("button",data);
//                    secondActivity.this.startActivity(intent);
//                }
//            });
        }
        if(data.equals("fourth")) {
            final ShowItem fourth = new ShowItem(name[6],name[7], R.drawable.first_4);
            ShowList.add(fourth);
//            Adapter adapter = new Adapter(this,R.layout.exhibit_item,ShowList);
//            ListView listView = (ListView) findViewById(R.id.exhibition_list);
//            listView.setAdapter(adapter);
//
//            Button button1= (Button) findViewById(R.id.things_button);
//            button1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(secondActivity.this, ThirdActivity.class);
//                    intent.putExtra("button",data);
//                    secondActivity.this.startActivity(intent);
//                }
//            });
        }
        if(data.equals("fifth")) {
            final ShowItem fifth = new ShowItem(name[8], name[9], R.drawable.first_5);
            ShowList.add(fifth);
//            Adapter adapter = new Adapter(this,R.layout.exhibit_item,ShowList);
//            ListView listView = (ListView) findViewById(R.id.exhibition_list);
//            listView.setAdapter(adapter);
//
//            Button button1 = (Button) findViewById(R.id.things_button);
//            button1.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent();
//                    intent.setClass(secondActivity.this, ThirdActivity.class);
//                    intent.putExtra("button",data);
//                    secondActivity.this.startActivity(intent);
//                }
//            });
        }
        Adapter adapter = new Adapter(this,R.layout.exhibit_item,ShowList);
        ListView listView = (ListView) findViewById(R.id.exhibition_list);
        listView.setAdapter(adapter);

        Button button1 = (Button) findViewById(R.id.things_button);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mSpeechSynthesizer.stop();
                Intent intent = new Intent();
                intent.setClass(secondActivity.this, ThirdActivity.class);
                intent.putExtra("button",data);
                secondActivity.this.startActivity(intent);
            }
        });
    }

    // 语音讲解模块
    public void initView(){

        mSpeak = (Button) this.findViewById(R.id.speak);
        mReSpeak = (Button) this.findViewById(R.id.respeak);

        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v) {
                int id = v.getId();
                mShowText = (TextView) findViewById(R.id.text3_view);
                switch (id) {
                    case R.id.speak:
                        // 第一次播放
                        if(speakFlag == 0){
                            speak();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(secondActivity.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        // 暂停
                        else if(speakFlag == 1){
                            mSpeechSynthesizer.pause();
                            mSpeak.setBackgroundResource(R.drawable.audio_start);
                            Toast.makeText(secondActivity.this, "语音讲解已暂停", Toast.LENGTH_SHORT).show();
                            speakFlag = 2;
                        }
                        // 继续
                        else if(speakFlag == 2){
                            mSpeechSynthesizer.resume();
                            mSpeak.setBackgroundResource(R.drawable.audio_on);
                            Toast.makeText(secondActivity.this, "语音讲解继续", Toast.LENGTH_SHORT).show();
                            speakFlag = 1;
                        }
                        break;
                    case R.id.respeak:
                        speak();
                        speakFlag = 1;
                        mSpeak.setBackgroundResource(R.drawable.audio_on);
                        Toast.makeText(secondActivity.this, "语音讲解开始", Toast.LENGTH_SHORT).show();
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
        // 设置在线合成模式
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

    /**
     * speak 实际上是调用 synthesize 后，获取音频流，然后播放。
     * 获取音频流的方式见SaveFileActivity及FileSaveListener
     * 需要合成的文本text的长度不能超过1024个GBK字节。
     */
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

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initPermission();
        initTTs();
    }
}
