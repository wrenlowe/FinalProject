package com.example.finalproject

class Calendar {
      private lateinit var list : HashMap<String, ArrayList<String>>
    
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





}
