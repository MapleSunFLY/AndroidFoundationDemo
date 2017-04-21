package com.example.administrator.foundationdemo.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.foundationdemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {


    private int SECOND_DURATION = 3;
    private int SECOND_CONUTER = 0;

    private boolean immediatelyStop= false;


    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initView();
        setupCountDownTimer();
    }

    private void initView() {

        final Button button = (Button) findViewById(R.id.timer_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    immediatelyStop = true;
            }
        });

    }

    /**
     * Timer和TimerTask是util包中两个与工作排程的类，
     * Timer是计时器，可以设定成特定时间或特定的时间周期产生信号，
     * 不过这里只有Timer是没有用的，必须配合TimerTask才有作用。Timer一旦与某个TimerTask产生关联，
     * 就会在产生信号的同时，连带一起执行TimerTask所定义的工作。
     * TimerTask的实现只需要继承TimerTask类就并实现其run()方法就可以了。
     * run()方法是由我们自己来编写的，把你想做的工作放在里面，一旦Timer在特定时间内或周期产生信号，run()方法就会执行，
     * 我们通会Timer的schdeule()方法来设定特定时间或特定的周期。schdeule()有两种形式，一个是两个参数的，一个是三个参数的。
     * 二种参数的第一个参数是TimerTask的对象，第二个是时间也可是以Date对象。
     * 具有三个参数的schedule方法可以使一个task在某一个时间后，根据一定的间隔时间运行多次，具有周期性。
     * 最后，可以使用Timer的cancel()方法来停止Timer，调用cancel()之后，两者就会脱离关系。TimerTask本身也有cancel()方法。
     */

    private void setupCountDownTimer() {
        timer.schedule(timerTask,0,1000);
    }

    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {

            if (immediatelyStop){
                timer.cancel();
                return;
            }

            if (SECOND_CONUTER == SECOND_DURATION*1000){
                timer.cancel();
                TimerActivity.this.finish();
            }
            SECOND_CONUTER +=1000;
        }
    };
}
