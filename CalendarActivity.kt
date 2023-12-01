package com.example.finalproject

import android.content.Intent
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalendarActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var calendarView : CalendarView
    private val formatter = DateTimeFormatter.ofPattern("MMddyyyy")
    private lateinit var clh : CalendarListHandler
    private  var current : String = LocalDateTime.now().format(formatter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        listView = findViewById( R.id.list )
        calendarView = findViewById(R.id.simpleCalendarView)
        clh = CalendarListHandler()
        calendarView.setOnDateChangeListener(clh)
        displayList(current)
    }
    fun displayList(day : String) {

        if( day != null ) {
            if(MainActivity.calendar.getListForDay(day) != null){
                var adapter : ArrayAdapter<String> =
                    ArrayAdapter<String>( this, android.R.layout.simple_list_item_multiple_choice , MainActivity.calendar.getListForDay(day)!! )
                listView.setAdapter( adapter )
                var lih : ListItemHandler = ListItemHandler( )
                listView.setOnItemClickListener( lih )
            }
        }

    }
    fun addTask(v : View){
        var intent: Intent = Intent(this, TaskActivity::class.java)
        startActivity(intent)
    }
    inner class CalendarListHandler : CalendarView.OnDateChangeListener {
        override fun onSelectedDayChange(view: CalendarView, year: Int, month: Int, dayOfMonth: Int) {
            var newDay : String = (month + 1).toString() + dayOfMonth.toString() + year.toString()
            current = newDay
            displayList(newDay)
        }

    }
    inner class ListItemHandler : AdapterView.OnItemClickListener {
        override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
            // need to store the day currently showing
            var oldList : ArrayList<String> = MainActivity.calendar.getListForDay(current)!!
            oldList.removeAt(position)
            MainActivity.calendar.setPreferences(this@CalendarActivity)
            displayList(current)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        displayList(current)
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
