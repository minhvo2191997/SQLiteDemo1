package com.example.sqlitedemo1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.sqlitedemo1.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="students_manager";
    private static final String TABLE="students";
    private static final String ID="id";
    private static final String NAME="name";
    private static final String ADDRESS="address";
    private static final String PHONE_NUMBER="phone";
    private static final String EMAIL="email";
    private static final int VERSION=1;
    private  final  String TAG="DB";
    private Context context;
    private  String SQLQuery="CREATE TABLE "+TABLE+"("+
            ID+" integer primary key, "+
            NAME+ " TEXT, "+
            EMAIL+ " TEXT, "+
            PHONE_NUMBER+" TEXT, "+
            ADDRESS+" TEXT )";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
        Log.d(TAG,"DBManager");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d(TAG,"On Create");
    }

    public void addStudent(Student student){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(NAME,student.getmName());
        values.put(ADDRESS,student.getmAddress());
        values.put(PHONE_NUMBER,student.getmPhoneNumber());
        values.put(EMAIL,student.getnEmail());
        db.insert(TABLE,null,values);
        db.close();
        Log.d(TAG,"addStudent Successfuly");


    }
    public Boolean removeStudent(String name){
        SQLiteDatabase db=this.getReadableDatabase();
        return db.delete(TABLE,NAME+" =?",new String[]{String.valueOf(name)})>0;
    }
    public Boolean updateStudent(Student student){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
      String name=student.getmName().toString();
        values.put(ID,""+student.getmID());
        values.put(NAME,""+student.getmName());
        values.put(ADDRESS,""+student.getmAddress());
        values.put(PHONE_NUMBER,""+student.getmPhoneNumber());
        values.put(EMAIL,""+student.getnEmail());
        return db.update(TABLE,values,NAME+" =?",new String[]{String.valueOf(name)})>0;
    }
    public List<Student> getAllStudent(){
        List<Student> listStudent=new ArrayList<>();
        String selectQuery=" SELECT * FROM "+TABLE;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Student student=new Student();
                student.setmID(cursor.getInt(0));
                student.setmName(cursor.getString(1));
                student.setmAddress(cursor.getString(2));
                student.setmPhoneNumber(cursor.getString(3));
                student.setnEmail(cursor.getString(4));
                listStudent.add(student);
            }while(cursor.moveToNext());
        }
        db.close();
        return  listStudent;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG,"On Upgrade");

    }
}
