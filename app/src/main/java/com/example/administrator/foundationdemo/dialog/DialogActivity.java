package com.example.administrator.foundationdemo.dialog;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.brothers.fly.aichebao.R;

public class DialogActivity extends AppCompatActivity {

    private GameDialog gameDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        gameDialog = new GameDialog(this);
        gameDialog.setShutImg(BitmapFactory.decodeResource(getResources(),R.drawable.ic_highlight_remove_24dp));
        gameDialog.setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.game));
        gameDialog.setDownloadOnOnclickListener("下载",new GameDialog.onDownloadOnclickListener() {
            @Override
            public void onDownloadClick() {
                Log.d("FLY","1111111111111");
            }
        });
        gameDialog.show();

        findViewById(R.id.download_img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameDialog.show();
            }
        });


    }
}
