package com.kteam.cardenglishgamer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mo on 2017/7/7.
 * 本地数据库函数封装类。
 */

public class CEGDatebase {
    private SQLiteDatabase db;
    private static CEGDatebase cegDatabase;
    public static final String DB_NAME = "db";
    public static final int DB_VERSION = 1;

    /**
     * 数据库函数封装类的构造函数
     * @param context 上下文
     */
    private  CEGDatebase(Context context) {
        CEGOpenHelper cegOpenHelper = new CEGOpenHelper(context,DB_NAME,null,DB_VERSION);
        db = cegOpenHelper.getWritableDatabase();
    }

    /**
     * 获取CEGDatebase单例
     * @param context 上下文
     * @return 全局数据库的唯一对象。
     * 备注：synchronized标注意义：处理多线程同时获取单例时，能采用优先级排队的策略解决冲突。
     */
    public synchronized CEGDatebase getInstance(Context context){
        if(cegDatabase == null)
            cegDatabase = new CEGDatebase(context);
        return cegDatabase;
    }
}
