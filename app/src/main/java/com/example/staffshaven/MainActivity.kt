package com.example.staffshaven

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.staffshaven.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.Manifest
import android.app.AlarmManager
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import java.util.Calendar
import java.util.TimeZone

class MainActivity : AppCompatActivity(), MyActivityInterface {
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var binding: ActivityMainBinding
    private var CHANNEL_ID = "com.example.staffshaven.notifications.meditations"

    override fun onCreate(savedInstanceState: Bundle?) {
        // Retrieve the theme from SharedPreferences
        val sharedPrefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val selectedTheme =
            sharedPrefs.getInt("selectedTheme", R.style.Base_Theme_StaffsHaven) // Default theme
        val selectedNav = sharedPrefs.getString("selectedNav", "Swipe")
        setTheme(selectedTheme)

        super.onCreate(savedInstanceState)

        // store the theme in Firestore
        val db = Firebase.firestore
        val user = Firebase.auth.currentUser

        // Retrieve the theme preference on app launch
        if (user != null) {
            val userEmail = user.email
            db.collection("userThemes").document(userEmail!!) // Use email as document ID
                .get(Source.SERVER)
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val selectedThemeId = document.getLong("selectedTheme")?.toInt()
                            ?: R.style.Base_Theme_StaffsHaven
                        setTheme(selectedThemeId)
                    } else {
                        // Handle case where theme is not found for the email
                        setTheme(R.style.Base_Theme_StaffsHaven) // Set a default theme
                    }
                    binding = ActivityMainBinding.inflate(layoutInflater)
                    setContentView(binding.root)

                    if (selectedNav != null) {
                        buttonListener(selectedNav)
                    } else {
                        buttonListener("Swipe")
                    }
                }
                .addOnFailureListener { e ->
                    setTheme(R.style.Base_Theme_StaffsHaven) // Set a default theme in case of error
                    binding = ActivityMainBinding.inflate(layoutInflater)
                    setContentView(binding.root)
                    if (selectedNav != null) {
                        buttonListener(selectedNav)
                    } else {
                        buttonListener("Swipe")
                    }
                }
        } else {
            // Handle case where user is not logged in
            setTheme(R.style.Base_Theme_StaffsHaven) // Set a default theme
            // Inflate layout and set content view after setting the theme
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            if (selectedNav != null) {
                buttonListener(selectedNav)
            } else {
                buttonListener("Swipe")
            }
        }
    }

    private fun buttonListener(selectedNav : String) {
        val btn : FloatingActionButton = findViewById(R.id.btnJournaling)
        val emergencyCallBtn : ImageButton = findViewById<ImageButton>(R.id.emergencyBtn)
        val profileBtn : ImageButton = findViewById<ImageButton>(R.id.profileBtn)

        // Replace the fragments when bottom menu clicked
        bottomNavView = findViewById(R.id.bottomNavigationView)
        bottomNavView.setOnItemSelectedListener{
            when (it.itemId){
                R.id.navigation_dashboard -> {replaceFragment(Dashboard())
                    emergencyCallBtn.visibility = View.VISIBLE}
                R.id.navigation_goals -> {replaceFragment(Goals())
                    emergencyCallBtn.visibility = View.VISIBLE}
                R.id.navigation_healthyHabits -> {replaceFragment(Health())
                    emergencyCallBtn.visibility = View.VISIBLE}
                R.id.navigation_relaxation -> {replaceFragment(Relaxation())
                    emergencyCallBtn.visibility = View.VISIBLE}
                else -> {
                }
            }
            true
        }

        btn.setOnClickListener{
            when (selectedNav) {
                "Click" -> {
                    when (it.id) {
                        R.id.btnJournaling -> replaceFragment(Journal_click1())
                        else -> {}
                    }
                    true
                }
                "Slide" -> {
                    when (it.id) {
                        R.id.btnJournaling -> replaceFragment(JournalSlideMain())
                        else -> {}
                    }
                    true
                }
                "Swipe" -> {
                    when (it.id) {
                        R.id.btnJournaling -> replaceFragment(Journaling()) // CHANGE THIS TO THE GOOD FRAGMENT
                        else -> {}
                    }
                    true
                }
                else -> {
                    when (it.id) {
                        R.id.btnJournaling -> replaceFragment(Journaling())
                        else -> {}
                    }
                    true
                }
            }


        }

        emergencyCallBtn.setOnClickListener{
            when (it.id){
                R.id.emergencyBtn -> {replaceFragment(Emergency())
                    emergencyCallBtn.visibility = View.GONE}
            }
        }

        profileBtn.setOnClickListener{
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }

    override fun showNotifications(){
        val NOTIFICATION_ID = 1

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system.
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Create an explicit intent for the MainActivity.
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Set the parameters of the notification
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.self_improvement)
            .setContentTitle("Please take care of yourself!")
            .setContentText("This is your reminder to meditate today!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@with
            }
            notify(NOTIFICATION_ID, builder.build())
        }
    }

}

// Interface to communicate with the Goals activity
interface MyActivityInterface {
    fun showNotifications()
}
