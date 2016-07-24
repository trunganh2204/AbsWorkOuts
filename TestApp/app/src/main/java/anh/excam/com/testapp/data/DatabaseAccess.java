package anh.excam.com.testapp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import anh.excam.com.testapp.Model.DataWorkOut;
import anh.excam.com.testapp.Model.ExecisesOfDay;
import anh.excam.com.testapp.Model.ExecisesPlay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 09/07/2016.
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }


    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
//
//    public String getImage( String title) {
//        String image = null;
//
//        Cursor cursor = database.rawQuery("SELECT Image FROM Exercises where Name = '"+title+"' ;", null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            image = cursor.getString(0);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return image;
//    }

    public List<ExecisesOfDay> getExecises() {
        List<ExecisesOfDay> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Name,ImageView FROM Exercises order by ID ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExecisesOfDay exercises=new ExecisesOfDay();
            exercises.setNameEx(cursor.getString(0));
            exercises.setImage(cursor.getString(1));
            list.add(exercises);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<DataWorkOut> getDataWorkOut(int position) {
        List<DataWorkOut> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select Position,Name from WEEK where ID = "+position+" ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            DataWorkOut workOut = new DataWorkOut();
            workOut.setPosition(cursor.getString(0));
            workOut.setName(cursor.getString(1));
            list.add(workOut);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<ExecisesOfDay> getExecisesDay(int position, int pos) {
        List<ExecisesOfDay> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select NAME, ImageView from Exercises,Week_Day_Exercises where Exercises.ID = Week_Day_Exercises.ID_Exercises and ID_Week="+position+" and ID_Day = "+pos+" ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExecisesOfDay workOut = new ExecisesOfDay();
            workOut.setNameEx(cursor.getString(0));
            workOut.setImage(cursor.getString(1));
            list.add(workOut);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<ExecisesPlay> getEXST(String a) {

        List<ExecisesPlay> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Name,Description,Image FROM Exercises where NAME='"+a+"' ", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ExecisesPlay execisesPlay= new ExecisesPlay();
            execisesPlay.setTitle(cursor.getString(0));
            execisesPlay.setDescription(cursor.getString(1));
            execisesPlay.setImage(cursor.getString(2));
            list.add(execisesPlay);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}