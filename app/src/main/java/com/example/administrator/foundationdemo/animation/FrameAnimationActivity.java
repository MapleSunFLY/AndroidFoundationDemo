package com.example.administrator.foundationdemo.animation;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.administrator.foundationdemo.R;

public class FrameAnimationActivity extends AppCompatActivity {

    /** Called when the activity is first created. */
    private Button button1,button2;
    private RadioGroup radioGroup;
    private RadioButton radioButton1,radioButton2;
    private SeekBar seekBar;
    private ImageView imageView1;
    private AnimationDrawable animationDrawable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_animation);

        button1=(Button) this.findViewById(R.id.button1);
        button2=(Button) this.findViewById(R.id.button2);
        radioGroup=(RadioGroup) this.findViewById(R.id.radioGroup1);
        radioButton1=(RadioButton) this.findViewById(R.id.radioButton1);
        radioButton2=(RadioButton) this.findViewById(R.id.radioButton2);
        seekBar=(SeekBar) this.findViewById(R.id.seekBar1);
        imageView1=(ImageView) this.findViewById(R.id.imageView1);

        //通过ImageView对象拿到背景显示的AnimationDrawable
        animationDrawable=(AnimationDrawable) imageView1.getBackground();

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(!animationDrawable.isRunning()){
                    animationDrawable.start();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(animationDrawable.isRunning()){
                    animationDrawable.stop();
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(checkedId==radioButton1.getId()){
                    //设置单次播放
                    animationDrawable.setOneShot(true);
                }else if(checkedId==radioButton2.getId()){
                    //设置循环播放
                    animationDrawable.setOneShot(false);
                }
                //设置播放后重新启动
                animationDrawable.stop();
                animationDrawable.start();
            }
        });
        //监听的进度条修改透明度
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {


            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                //设置动画Alpha值
                animationDrawable.setAlpha(progress);
                //通知imageView 刷新屏幕
                imageView1.postInvalidate();
            }
        });
    }
}
