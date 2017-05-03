package com.example.administrator.foundationdemo.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.brothers.fly.aichebao.R;


public class AnimationActivity extends AppCompatActivity {

    private Button tweened_rotate_btn;
    private Button tweened_scale_btn;
    private Button tweened_alpha_btn;
    private Button tweened_translate_btn;
    private ImageView tweened_img;
    private Button tween_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        init();
        viewListener();
    }

    private void init() {

        tweened_rotate_btn = (Button) findViewById(R.id.tweened_rotate_btn);
        tweened_scale_btn = (Button) findViewById(R.id.tweened_scale_btn);
        tweened_alpha_btn = (Button) findViewById(R.id.tweened_alpha_btn);
        tweened_translate_btn = (Button) findViewById(R.id.tweened_translate_btn);
        tweened_img = (ImageView) findViewById(R.id.tweened_img);
        tween_animation = (Button) findViewById(R.id.Tween_Animations);


    }

    private void viewListener() {
        tweened_rotate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //创建一个AnimationSet对象 参数为boolean型

                //true表示使用Animation的interpolation，false则是使用自己的
                AnimationSet animationSet = new AnimationSet(true);

                //参数1：从哪个旋转角度开始

                //参数2：转到什么角度

                //后4个参数用于设置围绕着旋转的圆的圆心在哪里

                //参数3：确定x轴坐标的类型，有ABSOLUT绝对坐标、RELATIVE_TO_SELF相对于自身坐标、RELATIVE_TO_PARENT相对于父控件的坐标

                //参数4：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                //参数5：确定y轴坐标的类型

                //参数6：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                RotateAnimation rotateAnimation = new RotateAnimation(0, 360,

                        Animation.RELATIVE_TO_SELF, 0.5f,

                        Animation.RELATIVE_TO_SELF, 0.5f);
                //设置执行时间,单位ms
                rotateAnimation.setDuration(1000);
                //将动画对象添加到序列中
                animationSet.addAnimation(rotateAnimation);

                tweened_img.startAnimation(animationSet);

            }
        });

        tweened_scale_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(true);

                //参数1：x轴的初始值

                //参数2：x轴收缩后的值

                //参数3：y轴的初始值

                //参数4：y轴收缩后的值

                //参数5：确定x轴坐标的类型

                //参数6：x轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                //参数7：确定y轴坐标的类型

                //参数8：y轴的值，0.5f表明是以自身这个控件的一半长度为x轴

                ScaleAnimation scaleAnimation = new ScaleAnimation(

                        0, 0.1f,0,0.1f,

                        Animation.RELATIVE_TO_SELF,0.5f,

                        Animation.RELATIVE_TO_SELF,0.5f);

                scaleAnimation.setDuration(1000);

                animationSet.addAnimation(scaleAnimation);

                tweened_img.startAnimation(animationSet);
            }
        });

        tweened_alpha_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建一个AnimationSet对象 参数为boolean型
                //true表示使用Animation的interpolation，false则是使用自己的
                AnimationSet animationSet = new AnimationSet(true);
                //创建一个AlphaAnimation对象，参数透明度，1完全透明，0不透明
                AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                //设置执行时间,单位ms
                alphaAnimation.setDuration(1000);
                //将动画对象添加到序列中
                animationSet.addAnimation(alphaAnimation);
                tweened_img.startAnimation(animationSet);
            }
        });

        tweened_translate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimationSet animationSet = new AnimationSet(true);

                //参数1～2：x轴的开始位置

                //参数3～4：y轴的开始位置

                //参数5～6：x轴的结束位置

                //参数7～8：x轴的结束位置

                TranslateAnimation translateAnimation =

                        new TranslateAnimation(

                                Animation.RELATIVE_TO_SELF,0f,

                                Animation.RELATIVE_TO_SELF,0.5f,

                                Animation.RELATIVE_TO_SELF,0f,

                                Animation.RELATIVE_TO_SELF,0.5f);

                translateAnimation.setDuration(1000);

                animationSet.addAnimation(translateAnimation);

                tweened_img.startAnimation(animationSet);

            }
        });

        tween_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(AnimationActivity.this,FrameAnimationActivity.class);
                startActivity(mIntent);
            }
        });
    }


}
