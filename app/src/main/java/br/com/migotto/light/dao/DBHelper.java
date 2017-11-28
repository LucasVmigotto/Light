package br.com.migotto.light.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by appCivico on 23/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME="Light2.db";
    private static final int DB_VERSION=1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    private static final String SQL_LIGHT="create table if not exists light(" +
            "id integer primary key autoincrement," +
            "description text not null" +
            ");";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_LIGHT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
