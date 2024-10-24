package com.example.cquest

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user_data.db"
        private const val DATABASE_VERSION = 2 // Incrementa la versión si has cambiado la estructura de la base de datos
        private const val TABLE_NAME = "user_info"
        private const val COLUMN_ID = "id"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_AGE TEXT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_EMAIL TEXT UNIQUE, " +  // Asegura que el correo sea único
                "$COLUMN_PASSWORD TEXT)"
        db.execSQL(createTable)
    }
    fun emailExists(email: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    @SuppressLint("Range")
    fun getUserNameById(id: Long): String? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_NAME FROM $TABLE_NAME WHERE $COLUMN_ID = ?", arrayOf(id.toString()))
        var name: String? = null
        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
        }
        cursor.close()
        return name
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertAge(age: String): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_AGE, age)
        }
        return db.insert(TABLE_NAME, null, values)
    }

    fun updateName(id: Long, name: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
        }
        val rowsAffected = db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        if (rowsAffected == 0) {
            // Log or handle the error as needed
        }
    }

    fun updateEmail(id: Long, email: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_EMAIL, email)
        }
        val rowsAffected = db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        return rowsAffected > 0 // Devuelve true si se actualizaron filas
    }

    fun updatePassword(id: Long, password: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_PASSWORD, password)
        }
        val rowsAffected = db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(id.toString()))
        return rowsAffected > 0 // Return true if rows were updated
    }
    fun checkUserCredentials(email: String, password: String): Boolean {
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?",
            arrayOf(email, password)
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    @SuppressLint("Range")
    fun getLastInsertedId(): Long {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_ID DESC LIMIT 1", null)
        cursor.use {
            if (cursor.moveToFirst()) {
                return cursor.getLong(cursor.getColumnIndex(COLUMN_ID))
            }
        }
        return -1
    }
}
