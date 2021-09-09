package com.example.atividade3mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Thread timer = new Thread(){
            public void run(){
                try{
                    // display for 3 seconds
                    sleep(3000);
                }
                catch (InterruptedException e) {
                    // TODO: handle Exception
                    e.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        //start thread
        timer.start();
    }
}