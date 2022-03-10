package com.example.stage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int )
    : SQLiteOpenHelper(context,name, factory, version){
    override fun onCreate(database: SQLiteDatabase) {
        //category-categoryNameP,categoryEnglishName,photo
        //menu-categoryName,menuNameP,menuEnglishName,cost,photo
        //idP,password
        //seqPI,id,orderNum,orderTime;
        //seq,menu,menucost,menuNum
        var sql = "CREATE TABLE IF NOT EXISTS account(id TEXT PRIMARY KEY, pw TEXT)"
        database.execSQL(sql)
        var second_sql = "CREATE TABLE IF NOT EXISTS order_history(id TEXT PRIMARY KEY, pw TEXT)"
        database.execSQL(second_sql)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //droptable
    }

}