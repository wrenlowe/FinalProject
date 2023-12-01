package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CalendarView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class CalendarActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var calendarView : CalendarView
    private val formatter = DateTimeFormatter.ofPattern("MMddyyyy")
    private lateinit var clh : CalendarListHandler
    private  var current : String = LocalDateTime.now().format(formatter)
    private var ad : InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        var adUnitId : String = // "ca-app-pub-3940256099942544/6300978111"
            "ca-app-pub-3940256099942544/1033173712"
        var adRequest : AdRequest = (AdRequest.Builder( )).build( )
        var adLoad : AdLoad = AdLoad( )
        InterstitialAd.load( this, adUnitId, adRequest, adLoad )

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
    inner class AdLoad : InterstitialAdLoadCallback() {
        override fun onAdFailedToLoad(p0: LoadAdError) {
            super.onAdFailedToLoad(p0)
            Log.w( "MainActivity", "ad failed to load" )
        }

        override fun onAdLoaded(p0: InterstitialAd) {
            super.onAdLoaded(p0)
            Log.w( "MainActivity", "ad loaded" )
            // show the ad
            ad = p0
            ad!!.show( this@CalendarActivity )

            // handle user interaction with the ad
            var manageAd : ManageAdShowing = ManageAdShowing()
            ad!!.fullScreenContentCallback = manageAd
        }
    }

    inner class ManageAdShowing : FullScreenContentCallback( ) {
        override fun onAdDismissedFullScreenContent() {
            super.onAdDismissedFullScreenContent()
            Log.w( "MainActivity", "user closed the ad" )
            // some code here to continue the app
        }

        override fun onAdClicked() {
            super.onAdClicked()
            Log.w( "MainActivity", "User clicked on the ad" )
        }

        override fun onAdImpression() {
            super.onAdImpression()
            Log.w( "MainActivity", "user has seen the ad" )
        }

        override fun onAdShowedFullScreenContent() {
            super.onAdShowedFullScreenContent()
            Log.w( "MainActivity", "ad has been shown" )
        }

        override fun onAdFailedToShowFullScreenContent(p0: AdError) {
            super.onAdFailedToShowFullScreenContent(p0)
            Log.w( "MainActivity", "ad failed to show" )
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
