package com.kuri.rabbitduh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    RabbitView rabbitView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout)findViewById(R.id.graphics);
        rabbitView = new RabbitView(this);
        frameLayout.addView(rabbitView);
    }

    @Override
    protected void onStop() {
        super.onStop();
        rabbitView.soundPool.release();
    }
}
