package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

    fun switchScreen (v : View){
        // update
        var intent:Intent = Intent(this, DataActivity::class.java)
        startActivity(intent)

    }
    companion object{
        lateinit var calendar: Calendar
    }
}