package com.example.stage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context?, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int )
    : SQLiteOpenHelper(context,name, factory, version){

    override fun onCreate(database: SQLiteDatabase) {

        var accountSql = "CREATE TABLE IF NOT EXISTS account(id TEXT PRIMARY KEY, pw TEXT)"
        database.execSQL(accountSql)
        var categorySql = "CREATE TABLE IF NOT EXISTS category(categoryName TEXT PRIMARY KEY, categoryEnglishName TEXT, photo TEXT)"
        database.execSQL(categorySql)
        var menuSql = "CREATE TABLE IF NOT EXISTS menu(categoryName TEXT, menuName TEXT ,menuEnglishName TEXT, photo TEXT)"
        database.execSQL(menuSql)
        var ordersSql = "CREATE TABLE IF NOT EXISTS orders(seq INTEGER PRIMARY KEY , id TEXT,orderNumber INT, orderTime TEXT)"
        database.execSQL(ordersSql)
        var orderMenusSql = "CREATE TABLE IF NOT EXISTS orderMenus(seq INTEGER, menuName TEXT,menuCost INT, menuNum INT)"
        database.execSQL(orderMenusSql)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //droptable
    }

}