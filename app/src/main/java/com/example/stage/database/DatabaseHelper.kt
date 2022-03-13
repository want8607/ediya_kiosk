package com.example.stage.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context?, name: String, factory: SQLiteDatabase.CursorFactory?, version: Int )
    : SQLiteOpenHelper(context,name, factory, version){

    override fun onCreate(database: SQLiteDatabase) {

        var accountSql = "CREATE TABLE IF NOT EXISTS account(id TEXT PRIMARY KEY, pw TEXT)"
        database.execSQL(accountSql)
        var categorySql = "CREATE TABLE IF NOT EXISTS category(drinkOrBakery TEXT,categoryName TEXT PRIMARY KEY, categoryEnglishName TEXT, photo TEXT)"
        database.execSQL(categorySql)
        var menuSql = "CREATE TABLE IF NOT EXISTS menu(categoryName TEXT, menuName TEXT ,menuEnglishName TEXT,cost INTEGER, photo TEXT)"
        database.execSQL(menuSql)
        var ordersSql = "CREATE TABLE IF NOT EXISTS orders(seq INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, orderTime TEXT)"
        database.execSQL(ordersSql)
        var orderMenusSql = "CREATE TABLE IF NOT EXISTS orderMenus(seq INTEGER, menuName TEXT,menuCost INTEGER, menuNum INTEGER)"
        database.execSQL(orderMenusSql)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //droptable
    }

}