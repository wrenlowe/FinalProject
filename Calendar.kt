package com.example.finalproject

class Calendar {
    private var numItems : Int = 0
    private lateinit var list : HashMap<String, ArrayList<String>>

    fun getNumItems() : Int {
        return numItems
    }
    fun getList() : HashMap<String, ArrayList<String>>{
        return list
    }
    fun getListForDay(day : String) : ArrayList<String>?{
        return list.get(day)
    }
    fun setNumItems(numItems : Int){
        this.numItems = numItems
    }





}