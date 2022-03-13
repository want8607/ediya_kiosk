package com.example.stage.database

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class DatabaseControl{
    //[[condition,value],[TEXT,condition,value]]
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
                var type = result.getType(j)
                if(type == Cursor.FIELD_TYPE_INTEGER){
                    row.add(result.getInt(j).toString())
                }else if(type == Cursor.FIELD_TYPE_STRING){
                    row.add(result.getString(j))
                }else if(type == Cursor.FIELD_TYPE_FLOAT){
                    row.add(result.getFloat(j).toString())
                }
            }
            dataList.add(row)
        }
        result.close()
        Log.d("message",dataList.toString())
        return dataList
    }
//    [[menuname,"아메리카노","TEXT"],[menuCost,3500,"INTEGER"]]
    fun createData(database: SQLiteDatabase, table:String, value: ArrayList<ArrayList<String>>){
        var sql = "INSERT INTO $table("

        for( i in value.indices){
            sql += value[i][0]
            if(i != value.size-1){
                sql+=","
            }
        }
        sql += ") VALUES ("

        for( j in value.indices){
            if(value[j][2]=="TEXT"){
                sql += "'${value[j][1]}'"
            }else if(value[j][2]=="INTEGER"){
                sql += value[j][1]
            }

            if(j != value.size-1){
                sql+=","
            }
        }
        sql +=")"

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


