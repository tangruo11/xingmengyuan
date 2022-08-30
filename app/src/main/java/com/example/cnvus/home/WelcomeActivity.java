package com.example.cnvus.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.cnvus.Mainactivity;
import com.example.cnvus.R;

public class WelcomeActivity extends AppCompatActivity {
    TextView tv;
    int count=5;
    Handler handler=new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    count--;
                    if(count==0){
                        Intent intent=new Intent(WelcomeActivity.this, GuideActivity.class);
                        Boolean isFirst = pref.getBoolean("isFirst", true);
                        if (isFirst) {
                            intent.setClass(WelcomeActivity.this, GuideActivity.class);
                            startActivity(intent);
                            pref.edit().putBoolean("isFirst",false).apply();

                        }else {
                            intent.setClass(  WelcomeActivity.this, Mainactivity.class);
                            startActivity(intent);
                        }
                        finish();
                    }else {
                        tv.setText(""+count);
                    }
                    break;
            }

            handler.sendEmptyMessageDelayed(1,1000);
        }
    };
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv=findViewById(R.id.welcome_tv);
        handler.sendEmptyMessageDelayed(1,1000);
        pref = getSharedPreferences("first_pref", MODE_PRIVATE);

    }
}