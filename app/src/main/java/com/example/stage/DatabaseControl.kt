package com.example.stage

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DatabaseControl{
    fun readData(database: SQLiteDatabase,table:String, id: String, pw: String): ArrayList<ArrayList<String>> {
        val sql = "SELECT * FROM $table WHERE id='${id}' and pw='${pw}'"
        var result : Cursor = database.rawQuery(sql,null)

        val dataList = ArrayList<ArrayList<String>>()

        while (result.moveToNext()) {
            val id = result.getString(0)
            val pw = result.getString(1)
            val row = arrayListOf(id,pw)
            dataList.add(row)
        }

        result.close()
        Log.d("message",dataList.toString())
        return dataList
    }

    fun createData(database: SQLiteDatabase, table:String, id: String, pw: String){
        val sql = "INSERT INTO $table('id','pw') VALUES('${id}','${pw}')"
        database.execSQL(sql)
    }

    fun updateData(database: SQLiteDatabase,table:String,column : String, value: String, primaryColumn:String, whereValue:String){
        val sql = "UPDATE $table SET $column = '${value}' WHERE $primaryColumn = '$whereValue'"
        database.execSQL(sql)
    }

    fun deleteData(database: SQLiteDatabase,table:String, primaryColumn:String, value: String){
        val sql = "DELETE FROM $table WHERE $primaryColumn = '$value'"
        database.execSQL(sql)
    }
}