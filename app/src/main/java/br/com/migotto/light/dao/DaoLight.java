package br.com.migotto.light.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import br.com.migotto.light.model.Light;

/**
 * Created by appCivico on 23/10/2017.
 */

public class DaoLight {
    private SQLiteDatabase sqLiteDatabase;
    private Context context;
    private DBHelper dbHelper;

    public DaoLight(Context context){
        this.context=context;
        this.dbHelper=new DBHelper(context);
    }

    public long save(Light l){
        if(sqLiteDatabase==null){
            sqLiteDatabase=dbHelper.getWritableDatabase();
        }
        try{
            ContentValues values=new ContentValues();
            values.put("description", l.getDescription());
            if(l.getId()!=null){
                return sqLiteDatabase.update("light", values, "id=?", new String[]{String.valueOf(l.getId())});
            }else{
                return sqLiteDatabase.insertOrThrow("light", "", values);
            }
        }finally {
            sqLiteDatabase.close();
        }
    }

    public long delete(Light l){
        if(sqLiteDatabase==null){
            sqLiteDatabase=dbHelper.getWritableDatabase();
        }
        try{
            return sqLiteDatabase.delete("light", "id=?", new String[]{String.valueOf(l.getId())});
        }finally {
            sqLiteDatabase.close();
        }
    }

    public List<Light> listAll(){
        if(sqLiteDatabase==null){
            sqLiteDatabase=dbHelper.getWritableDatabase();
        }
        try{
            Cursor c=sqLiteDatabase.rawQuery("SELECT * FROM light",null);
            return toList(c);
        }finally {
            sqLiteDatabase.close();
        }
    }

    private List<Light> toList(Cursor c){
        if(sqLiteDatabase==null){
            sqLiteDatabase=dbHelper.getWritableDatabase();
        }
        List<Light> list=new ArrayList<>();
        if(c.moveToFirst()){
            do {
                Light l=new Light();
                l.setId(c.getLong(c.getColumnIndex("id")));
                l.setDescription(c.getString(c.getColumnIndex("description")));
                list.add(l);
            }while(c.moveToNext());
        }
        return list;
    }
}
