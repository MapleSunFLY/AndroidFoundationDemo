package com.example.administrator.foundationdemo.myfileobserver;

import android.os.FileObserver;
import android.util.Log;

/**
 * Created by "sinlov" on 2017/3/21.
 */
public class MyFileObserver extends FileObserver {
    /**
     path 是所监听的文件夹或者文件名。
     */
    public MyFileObserver (String path) {
        super(path);
    }


    @Override
    public void onEvent(int event, String path) {

        switch(event){
            case android.os.FileObserver.ALL_EVENTS:
                //所有事件 相当于default的功能
                Log.d("FLY","文件名称：  "+path);
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.CREATE:
                //文件被创建
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.OPEN :
                //文件被打开
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.ACCESS:
                //打开文件后，读文件内容操作 文件或目录被访问
                Log.d("FLY","文件名称：  "+path);
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.MODIFY:
                //文件被修改
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.ATTRIB:
                //未明操作
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.CLOSE_NOWRITE:
                //没有编辑文件，关闭
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.CLOSE_WRITE:
                //编辑完文件，关闭
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.DELETE:
                //文件被删除
                /**
                 * 相关操作
                 */
                break;
            case android.os.FileObserver.MOVED_FROM:
                //文件被移动
                /**
                 * 相关操作
                 */
                break;

        }
    }
}
