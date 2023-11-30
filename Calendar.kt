package com.example.finalproject

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Calendar {

    private lateinit var list : HashMap<String, ArrayList<String>>

    constructor(){
        list = hashMapOf()
    }
    constructor(context: Context) {
        var pref: SharedPreferences = context.getSharedPreferences(
            context.packageName +
                    "_preferences", Context.MODE_PRIVATE
        )
        val defValue = Gson().toJson(HashMap<String, ArrayList<String>>())
        val json = pref.getString(PREFERENCE_TODO, defValue)
        val token: TypeToken<HashMap<String, ArrayList<String>>> =
            object :
                TypeToken<HashMap<String, ArrayList<String>>>() {}
        setList(Gson().fromJson(json, token.type))

    }

    fun getList() : HashMap<String, ArrayList<String>>{
        return list
    }
    fun getListForDay(day : String) : ArrayList<String>?{
        return list.get(day)
    }
    fun setList(list : HashMap<String, ArrayList<String>>){
        this.list = list
    }
    fun setListForDay(day: String, l: ArrayList<String>){
        list.set(day, l)
    }
    fun addItem(day: String, l: String){
        var oldList : ArrayList<String>? = list.get(day)
        if (oldList == null){
            var newList : ArrayList<String> = arrayListOf(l)
            list.set(day, newList)
        } else {
            oldList.add(l)
            list.set(day, oldList)
        }

    }
    fun removeDay(day: String){
        list.remove(day)
    }
    fun removeItem(day : String, l: String){
        var oldList : ArrayList<String>? = list.get(day)
        if (oldList != null){
            oldList.remove(l)
        }
    }
    fun setPreferences(context: Context) {
        val jsonString: String = Gson().toJson(list)
        var pref : SharedPreferences = context.getSharedPreferences( context.packageName +
                "_preferences", Context.MODE_PRIVATE )
        var editor : SharedPreferences.Editor = pref.edit()
        editor.putString( PREFERENCE_TODO, jsonString )
        editor.commit()
    }
    companion object {
        private const val PREFERENCE_TODO : String = "current level"

    }

}



}
