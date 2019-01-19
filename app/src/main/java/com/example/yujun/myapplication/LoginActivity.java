package com.example.yujun.myapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yujun.myapplication.utils.MyDBOpenHelper;

import org.json.JSONObject;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

        EditText mEtPhoneNumber;
        EditText mEtCode;
        Button mBtnSendMsg;
        Button mBtnSubmitCode;

        private MyDBOpenHelper myDBHelper;
        private SQLiteDatabase db;
        private StringBuilder sb;

        private int i;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            mEtPhoneNumber = (EditText)findViewById(R.id.phone);
            mEtCode = (EditText)findViewById(R.id.password);
            mBtnSendMsg = (Button)findViewById(R.id.btn_getcode_sendsms);
            mBtnSubmitCode = (Button)findViewById(R.id.sign_in_button);

            myDBHelper = new MyDBOpenHelper(LoginActivity.this, "system.db", null, 1);
            db = myDBHelper.getWritableDatabase();

            SMSSDK.registerEventHandler(eventHandler);
            mBtnSendMsg.setOnClickListener(this);
            mBtnSubmitCode.setOnClickListener(this);

           /* mBtnSubmitCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String phone = mEtPhoneNumber.getText().toString().trim();

                    int uid = queryDB(phone);
                    if(uid != 0){
                        saveDB(uid, phone);
                        Toast.makeText(LoginActivity.this, "欢迎用户，" + phone + "uid" + uid, Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(LoginActivity.this, "欢迎回来，用户" + phone, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    intent.putExtra("phone", phone);
                    LoginActivity.this.startActivity(intent);
                }
            });*/
        }

        @Override
        public void onClick(View v) {
            String phoneNum = mEtPhoneNumber.getText().toString().trim();
            switch (v.getId()) {
                case R.id.btn_getcode_sendsms:
                    if (TextUtils.isEmpty(phoneNum)) {
                        Toast.makeText(getApplicationContext(), "手机号码不能为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SMSSDK.getVerificationCode("86", phoneNum);
                    mBtnSendMsg.setClickable(false);
                    //开始倒计时
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i > 0; i--) {
                                handler.sendEmptyMessage(-1);
                                if (i <= 0) {
                                    break;
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            //倒计时结束执行
                            handler.sendEmptyMessage(-2);
                        }
                    }).start();
                    break;
                case R.id.sign_in_button:
                    String code = mEtCode.getText().toString().trim();
                    if (TextUtils.isEmpty(phoneNum)) {
                        Toast.makeText(getApplicationContext(), "手机号码不能为空",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(code)) {
                        Toast.makeText(getApplicationContext(), "验证码不能为空",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }
                    SMSSDK.submitVerificationCode("86", phoneNum, code);
                    break;
                default:
                    break;
            }
        }

        Handler handler = new Handler() {

            public void handleMessage(Message msg) {

                if (msg.what == -1) {
                    //修改控件文本进行倒计时  i 以60秒倒计时为例
                    //修改控件文本，进行重新发送验证码
                    mBtnSendMsg.setText("重新发送");
                    mBtnSendMsg.setClickable(true);
                    i = 60;
                } else {
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;

                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                        String country = (String) phoneMap.get("country");
                        String phone = (String) phoneMap.get("phone");

                        int uid = queryDB(phone);
                        if(uid != 0){
                            saveDB(uid, phone);
                            Toast.makeText(LoginActivity.this, "欢迎用户，" + phone + "uid" + uid, Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(LoginActivity.this, "欢迎回来，用户" + phone, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        intent.putExtra("phone", phone);
                        LoginActivity.this.startActivity(intent);
                        // 提交验证码成功,调用注册接口，之后直接登录
                        //当号码来自短信注册页面时调用登录注册接口
                        //当号码来自绑定页面时调用绑定手机号码接口
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                    } else if (result == SMSSDK.RESULT_ERROR) {
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(LoginActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            //do something
                        }
                    }else {
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        };

        public void saveDB(int uid, String phone){
            ContentValues values1 = new ContentValues();
            values1.put("phone", phone);
            //参数依次是：表名，强行插入null值得数据列的列名，一行记录的数据
            db.insert("user", null, values1);
            Toast.makeText(LoginActivity.this, "新用户已插入库中", Toast.LENGTH_SHORT).show();
        }

        public int queryDB(String phone){
            boolean flag = false;
            int i = 0;
            sb = new StringBuilder();
            //参数依次是:表名，列名，where约束条件，where中占位符提供具体的值，指定group by的列，进一步约束
            //指定查询结果的排序方式
            Cursor query_cursor =  db.rawQuery("SELECT * FROM user WHERE phone = ?",
                    new String[]{phone});
            while(query_cursor.moveToNext())
            {
                flag = true;
                break;
            }
            query_cursor.close();
            if (!flag) {
                Cursor count_cursor =  db.rawQuery("SELECT * FROM user",null);
                i = count_cursor.getCount();
                count_cursor.close();
            }
            return i;
        }

        // .registerEventHandler是用来往SMSSDK中注册一个事件接收器。
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };

        //在完成短信验证之后，需要销毁回调监听接口。
        @Override
        protected void onDestroy() {
            super.onDestroy();
            SMSSDK.unregisterAllEventHandler();
        }
    }

