package com.example.stage

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DatabaseControl{
    //[[condition,value],[condition,value],[condition,value],[condition,value]]
    fun readData(database: SQLiteDatabase,table:String, value:ArrayList<ArrayList<String>>): ArrayList<ArrayList<String>> {

        var sql = if(value.isNullOrEmpty()){
            "SELECT * FROM $table"
        }else {
            "SELECT * FROM $table WHERE"
        }

        for( i in value.indices){
            sql += " ${value[i][0]}= '${value[i][1]}'"
            if(i != value.size-1){
                sql+=" AND"
            }
        }
        Log.d("text",sql)

        var result : Cursor = database.rawQuery(sql,null)
        var columnSize = result.columnCount-1
        val dataList = ArrayList<ArrayList<String>>()

        while (result.moveToNext()) {
            val row = arrayListOf<String>()
            for(j in 0..columnSize){
                row.add(result.getString(j))
            }
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


