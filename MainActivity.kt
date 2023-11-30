package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    private lateinit var listView : ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>( R.id.todo )
        calendar = Calendar()

        calendar.addItem("11302023", "Finish Project")
        displayList("11302023")


    }
    fun displayList(day : String) {
        if( day != null ) {
            if(calendar.getListForDay(day) != null){
                var adapter : ArrayAdapter<String> =
                    ArrayAdapter<String>( this, android.R.layout.simple_list_item_multiple_choice , calendar.getListForDay(day)!! )
                listView.setAdapter( adapter )
                var lih : ListItemHandler = ListItemHandler( )
                listView.setOnItemClickListener( lih )
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    // should probably show a screen where it tells them to enter the date in a specific way
    // and the activity they want to enter
    fun addTask(v : View){

    }

    fun monthlyList (v : View){
        // update
        var intent:Intent = Intent(this, DataActivity::class.java)
        startActivity(intent)

    }
    fun weeklyList (v : View){
        // update
        // need to change to a different activity class
        var intent:Intent = Intent(this, DataActivity::class.java)
        startActivity(intent)
    }
    // need to deal with event handling when the user checks that they have finished a task
    inner class ListItemHandler : AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            Log.w("MainActivity", "check if this is the right listener ")
        }
    }
    companion object{
        lateinit var calendar: Calendar
    }
}
