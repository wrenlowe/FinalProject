package com.example.finalproject

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TaskActivity : AppCompatActivity(){

    private lateinit var dateEditText: EditText
    private lateinit var taskEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        dateEditText = findViewById(R.id.dateEntered)
        taskEditText = findViewById(R.id.taskEntered)

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
    fun updateList(){
        var dateEntered : String = dateEditText.text.toString()
        var taskEntered : String = taskEditText.text.toString()
        MainActivity.calendar.addItem(dateEntered, taskEntered)
    }
    fun goBack(v: View){
        updateList()
        finish()
    }
}
