package com.example.roma2;

import android.database.Cursor;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GameStatsDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game_stats.db";
    private static final int DATABASE_VERSION = 2;  // Збільште версію при оновленні схеми

    public static final String TABLE_NAME = "game_stats";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_DATE = "date";

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_SCORE + " INTEGER, " +
            COLUMN_DATE + " TEXT);";

    public GameStatsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);  // Створення таблиці
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Якщо версія бази даних змінилася, видаляємо старі таблиці і створюємо нові
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);  // Створення нової таблиці
    }

    // Метод для вставки статистики гри в базу даних
    public void insertGameStats(int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_DATE, System.currentTimeMillis()); // Зберігаємо поточний час як дату
        db.insert(TABLE_NAME, null, values);
    }

    // Отримуємо статистику гри
    public Cursor getGameStats() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null, null, null, null, null, COLUMN_DATE + " DESC"); // Сортуємо за датою
    }
}
