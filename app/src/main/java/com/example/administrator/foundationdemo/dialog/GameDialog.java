package com.example.administrator.foundationdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.brothers.fly.aichebao.R;

/**
 * Created by android on 2017/5/3.
 */
public class GameDialog extends Dialog {

    private ImageButton shutBtn;
    private Button downloadBtn;
    private ImageView backgroundImg;

    private Bitmap shutImg, background;
    private String downloadbtnText;

    private String messageStr; //从外界设置的消息文本
    private onShutOnclickListener shutOnclickListener;//关闭按钮被点击了的监听器  
    private onDownloadOnclickListener downloadOnclickListener;//下载按钮被点击了的监听器


    public GameDialog(Context context) {
        super(context, R.style.AppHomepageDialog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dawnload_game);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        initView();
        initData();
        initListener();
    }

    /**
     * 初始化界面控件    
     */
    private void initView() {
        shutBtn = (ImageButton) findViewById(R.id.dialog_shut_btn);
        downloadBtn = (Button) findViewById(R.id.dialog_download_btn);
        backgroundImg = (ImageView) findViewById(R.id.dialog_background_img);
    }

    /**
     *  初始化界面控件的显示数据 
     */
    private void initData() {
        if (shutBtn != null) {
            shutBtn.setImageBitmap(shutImg);
        }

        if (downloadBtn != null) {
            downloadBtn.setText(downloadbtnText);
        }

        if (backgroundImg != null) {
            backgroundImg.setImageBitmap(background);
        }
    }

    /**
     *  
     *   * 初始化界面按钮监听器 
     *   
     */
    private void initListener() {
        shutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                shutOnclickListener.onShutClick();
                dismiss();
            }
        });

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadOnclickListener.onDownloadClick();
                dismiss();
            }
        });
    }

    /**
     * 设置dialog背景
     *
     * @param background
     */
    public void setBackground(Bitmap background) {
        this.background = background;
    }

    /**
     * 设置关闭图片
     *
     * @param shutImg
     */
    public void setShutImg(Bitmap shutImg) {
        this.shutImg = shutImg;
    }

    /**
     * 设置按钮文本
     *
     * @param text
     */
    public void setDownloadbtnTest(String text) {
        downloadbtnText = text;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    /**
     * 退出按钮监听
     *
     * @param shutOnclickListener
     */
    public void setShutOnOnclickListener(onShutOnclickListener shutOnclickListener) {
        this.shutOnclickListener = shutOnclickListener;
    }

    /**
     * 设置下载按钮的显示内容和监听
     *
     * @param text
     * @param downloadOnclickListener
     */
    public void setDownloadOnOnclickListener(String text, onDownloadOnclickListener downloadOnclickListener) {
        if (null != text && !"".equals(text)) {
            downloadbtnText = text;
        }
        this.downloadOnclickListener = downloadOnclickListener;
    }

    public void setDownloadOnOnclickListener(onDownloadOnclickListener downloadOnclickListener) {
        this.setDownloadOnOnclickListener(null, downloadOnclickListener);
    }


    /**
     *  
     *  设置确定按钮和取消被点击的接口 
     */
    public interface onShutOnclickListener {
        public void onShutClick();
    }

    public interface onDownloadOnclickListener {
        public void onDownloadClick();
    }
}
