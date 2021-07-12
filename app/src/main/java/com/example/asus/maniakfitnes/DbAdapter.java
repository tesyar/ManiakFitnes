package com.example.asus.maniakfitnes;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbAdapter {
    public static int dbversion =6;
    //kita ambil db versi 6
    public static String dbname = "FitnesDB";
    //memasukkan nama database sesuai dengan yg kita buat
    public static String dbTable = "fitnes";
    //lalu kita tampilkan pada database tablenya dengan id yang kita buat

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context,dbname,null, dbversion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE IF NOT EXISTS "+dbTable+" (_id INTEGER PRIMARY KEY autoincrement,name, number, nomoru, address, gender, masa, UNIQUE(number))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+dbTable);
            onCreate(db);
        }
    }
    //diatas adalah kode agar db dapat terbuat

    //establsh connection with SQLiteDataBase
    private final Context c;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlDb;

    public DbAdapter(Context context) {
        this.c = context;
    }
    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(c);
        sqlDb = dbHelper.getWritableDatabase();
        return this;
    }
    //disini adalah kode untuk pemanggilan databasenya

    //insert data
    public void insert(String text2,String text3,String text4,String text5,String text6,String text7) {
        if(!isExist(text3)) {
            sqlDb.execSQL("INSERT INTO fitnes (name,number,nomoru,address,gender,masa) VALUES('" + text2 + "','" + text3 + "','" + text4 + "','" + text5 + "','" + text6 + "','" + text7 + "')");
        }
    }
    //diatas adalah kode untuk insert data ke DB

    //check entry already in database or not
    public boolean isExist(String num){
        String query = "SELECT number FROM fitnes WHERE number='"+num+"' LIMIT 1";
        Cursor row = sqlDb.rawQuery(query, null);
        return row.moveToFirst();
    }
    //diatas adalah kode untuk insert nomor ke DB

    //edit data
    public void update(int id,String text2,String text3,String text4,String text5,String text6,String text7) {
        sqlDb.execSQL("UPDATE " + dbTable + " SET name='" + text2 + "', number='" + text3 + "', nomoru='" + text4 + "', address='" + text5 + "', gender='" + text6 + "', masa='" + text7 + "'   WHERE _id=" + id);
    }
    // /delete data
    public void delete(int id) {
        sqlDb.execSQL("DELETE FROM "+dbTable+" WHERE _id="+id);
    }
    //diatas adalah kode untuk delete data ke DB

    //fetch data
    public Cursor fetchAllData() {
        String query = "SELECT * FROM "+dbTable;
        Cursor row = sqlDb.rawQuery(query, null);
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
    //mengambil datanya dari DB agar tampil ke APK

    //fetch data by filter
    public Cursor fetchdatabyfilter(String inputText,String filtercolumn) throws SQLException {
        Cursor row = null;
        String query = "SELECT * FROM "+dbTable;
        if (inputText == null  ||  inputText.length () == 0)  {
            row = sqlDb.rawQuery(query, null);
        }else {
            query = "SELECT * FROM "+dbTable+" WHERE "+filtercolumn+" like '%"+inputText+"%'";
            row = sqlDb.rawQuery(query, null);
        }
        if (row != null) {
            row.moveToFirst();
        }
        return row;
    }
}
//kode tsb untuk pemfilteran databasenya, jika sudah dihapus atau update data