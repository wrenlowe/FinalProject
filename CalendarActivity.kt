package com.example.finalproject

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var calendarView : CalendarView
    private val formatter = DateTimeFormatter.ofPattern("MMddyyyy")
    private lateinit var clh : CalendarListHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        listView = findViewById( R.id.todo )
        calendarView = findViewById(R.id.simpleCalendarView)
        clh = CalendarListHandler()
        calendarView.setOnDateChangeListener(clh)
    }
    fun displayList(day : String) {
        var list : ArrayList<String> = arrayListOf()
        if( day != null ) {
            if(MainActivity.calendar.getListForDay(day) != null){
                list = MainActivity.calendar.getListForDay(day)!!
            }
        }
        var adapter : ArrayAdapter<String> =
            ArrayAdapter<String>( this, android.R.layout.simple_list_item_multiple_choice , list )
        listView.setAdapter( adapter )
        var lih : ListItemHandler = ListItemHandler( )
        listView.setOnItemClickListener( lih )
    }
    inner class CalendarListHandler : CalendarView.OnDateChangeListener {
        override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
            var newDay : String = (month + 1).toString() + dayOfMonth.toString() + year.toString()
            Log.w("MainActivity", "listener works" + newDay)
            displayList(newDay)
        }

    }
    inner class ListItemHandler : AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // need to store the day currently showing
            var oldList : ArrayList<String> = MainActivity.calendar.getListForDay("current")!!
            oldList.removeAt(position)
            MainActivity.calendar.setPreferences(this@CalendarActivity)
            displayList("current")
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

    fun update(){
        //amountTextView.text = (MainActivity.travel.currency(MainActivity.travel.getAmount(enterAmountEditText)))

    }
    fun goBack(v: View){
        update()
        finish()
    }
}
