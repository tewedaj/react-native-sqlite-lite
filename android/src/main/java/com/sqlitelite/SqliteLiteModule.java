package com.sqlitelite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


@ReactModule(name = SqliteLiteModule.NAME)
public class SqliteLiteModule extends ReactContextBaseJavaModule {
  public static final String NAME = "SqliteLite";
  public Context context;
  public SqliteLiteModule(ReactApplicationContext reactContext) {
    super(reactContext);
    context = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }


  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  public void multiply(double a, double b, Promise promise) {
    promise.resolve(a * b);
  }

  @ReactMethod
  public void test(Callback callback) {
    callback.invoke("test");

  }


//  @ReactMethod
//  public void prepopulatedQuery(String dbName ,String query, Callback cb) throws JSONException {
//
//    String dbPath = reactContext.getFilesDir().getPath() + File.separator + dbName;
//
//    // Check if the database file already exists
//    if (!new File(dbPath).exists()) {
//      try {
//        InputStream inputStream = reactContext.getAssets().open(dbName);
//        OutputStream outputStream = new FileOutputStream(dbPath);
//        byte[] buffer = new byte[1024];
//        int length;
//        while ((length = inputStream.read(buffer)) > 0) {
//          outputStream.write(buffer, 0, length);
//        }
//        outputStream.flush();
//        outputStream.close();
//        inputStream.close();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//
//// Open the database
//    SQLiteDatabase db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
//    try{
//      Cursor answer = db.rawQuery(query,null);
//      JSONArray response = cursorToJson(answer);
//      cb.invoke("",response.toString());
//
//    }catch(Exception e){
//      cb.invoke(e.toString(),"");
//
//    }
//  }

  @ReactMethod
  public void createDatabase(String dbName,Callback callback){
   // String dbPath = getReactApplicationContext().getFilesDir().getPath() + File.separator + dbName;

    SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbName,null);
    callback.invoke(db.isOpen());
  }

  @ReactMethod
  public void createTable(String databaseName,String tableName,String query,Callback callback){
      DbHelper db = new DbHelper(context,databaseName);

     boolean response =  db.createTable(tableName,query);
     callback.invoke(response);
  }

  @ReactMethod
  public void insertQuery(String dbName,String query,Callback callback){
    DbHelper db = new DbHelper(context,dbName);
    Boolean response = db.insertQuery(query);
    callback.invoke(response);

  }
  @ReactMethod
  public void selectQuery(String dbName,String query,Callback callback){
    DbHelper db = new DbHelper(context,dbName);
    String response = db.selectQuery(query);
    callback.invoke(response);
  }


  JSONArray cursorToJson(Cursor cursor) throws JSONException {
    String[] columnNames = cursor.getColumnNames();
    JSONArray jsonArray = new JSONArray();

    while (cursor.moveToNext()) {
      JSONObject rowObject = new JSONObject();

      for (String columnName : columnNames) {
        int columnIndex = cursor.getColumnIndex(columnName);
        String columnValue = cursor.getString(columnIndex);
        rowObject.put(columnName, columnValue);
      }

      jsonArray.put(rowObject);
    }
    return jsonArray;
  }



}
