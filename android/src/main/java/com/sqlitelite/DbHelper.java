package com.sqlitelite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DbHelper extends SQLiteOpenHelper {

  private static final int DB_VERSION = 1;
  private String db_name = "";
  public DbHelper(Context context,String DB_NAME) {
    super(context, DB_NAME, null, DB_VERSION);
    this.db_name = DB_NAME;
  }

  // below method is for creating a database by running a sqlite query
  @Override
  public void onCreate(SQLiteDatabase db) {

  }


  public void createDatabase(){
    SQLiteDatabase db = this.getWritableDatabase();

  }

  public boolean createTable(String tableName , String query){
    SQLiteDatabase db = this.getWritableDatabase();

    if(exists(tableName,db) < 1){
      JSONArray response = new JSONArray();

      try{
        response = cursorToJson(db.rawQuery(query,null));
      }catch(Exception e){
        return false;
      }
      return true;

    }else{
      return true;
    }


  }

  public boolean insertQuery(String query){
    SQLiteDatabase db = getWritableDatabase();
    try{
      db.execSQL(query);
      return true;
    }catch(Exception e){
      return false;
    }
  }

  public String selectQuery(String query){
    SQLiteDatabase db = getWritableDatabase();
    try{
     Cursor dbResponse =  db.rawQuery(query, null);
     JSONArray response = cursorToJson(dbResponse);
      return response.toString();
    }catch(Exception e) {
      return e.toString();
    }
  }

  // this method is use to add new course to our sqlite database.

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // this method is called to check if the table exists already.

    onCreate(db);
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

  public int exists(String TableName,SQLiteDatabase sql){
    String query = "SELECT * FROM sqlite_master WHERE type='table' AND name = '"+ TableName +"';";
    Cursor response= sql.rawQuery(query,null);
    int responseCast = response.getCount();

    return responseCast;
  }
}
