package com.huhuijia.progressdialogdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mStartBtn;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mStartBtn = (Button) findViewById(R.id.startBtn);
        mStartBtn.setOnClickListener(this);
        dialog=new ProgressDialog(MainActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置进度条为方形的，默认为圆形
        dialog.setMax(100);
        dialog.setProgress(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBtn:
                dialog.show();
                doProgress();
                break;
        }
    }
    public void doProgress(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int progressMax = dialog.getMax();
                    while(progressMax != dialog.getProgress()){

                        int step = progressMax / 10;
                        dialog.setProgress(dialog.getProgress() + step);
                        Thread.sleep(1000);
                    }
                    dialog.dismiss();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }
}
