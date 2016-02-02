package com.example.dhiraj.project1;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.Console;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    float[] values = new float[10];
    String[] verlabels = new String[]{"7", "6", "5", "4", "3", "2","1","0"};
    String[] horlabels = new String[]{"0", "1", "2", "3", "4", "5", "6","7"};
    GraphView g;
    private Handler mHandler;
    LinearLayout l;
    boolean blGraphNeedsToRun;
    Button btnRun;
    Button btnStop;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = (LinearLayout) findViewById(R.id.lay);
        g = new GraphView(this, values, "Running data", horlabels, verlabels, GraphView.LINE);
        btnRun = (Button)findViewById(R.id.btnRun);
        btnStop = (Button)findViewById(R.id.btnStop);
        btnRun.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        l.addView(g);

        mHandler = new Handler();
        mHandler.post(mUpdate);


    }


    private Runnable mUpdate = new Runnable() {
        public void run() {
                for (int i = 0; i < 10; i++) {
                    values[i] = (float) 0 + (int) (Math.random() * 7);
                }

                g.setValues(values);
                l.removeView(g);
                l.addView(g);
                mHandler.postDelayed(this, 1000);

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.btnRun: {
                // do something for button 1 click
                g.blGraphNeedsToRun=true;
                System.out.println("Run");
                break;
            }

            case R.id.btnStop: {
                // do something for button 2 click
                g.blGraphNeedsToRun=false;
                System.out.println("Stop");
                break;
            }
        }
    }
}
