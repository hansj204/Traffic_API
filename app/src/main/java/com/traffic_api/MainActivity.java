package com.traffic_api;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            // 21 버전 이상일 때
            getWindow().setStatusBarColor(Color.BLACK);
        }

        ImageButton Dawnbusbutton = (ImageButton) findViewById(R.id.dawnbus);
        Dawnbusbutton.setOnClickListener(btnHandler);
    }

    View.OnClickListener btnHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.dawnbus:
                    intent = new Intent(MainActivity.this, Bus.class);
                    startActivity(intent);
                    break;
            }


        }
    };

    private long pressedTime = 0;

    @Override
    public void onBackPressed() {

        if ( pressedTime == 0 ) {
            Toast.makeText(MainActivity.this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                pressedTime = 0 ;
            }
            else {
                super.onBackPressed();
                finish();
            }
        }
    }




}

