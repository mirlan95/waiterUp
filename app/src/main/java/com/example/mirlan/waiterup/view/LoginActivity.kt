package com.example.mirlan.waiterup.view

import android.content.Intent
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.mirlan.waiterup.R
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val mButtonLoginIn = findViewById<Button>(R.id.btnLogIn)
        mButtonLoginIn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when(item.itemId){
                R.id.settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    return true
                }
                R.id.kg -> {
                    var res:Resources = applicationContext.resources
                    var dm:DisplayMetrics = res.displayMetrics
                    var conf:android.content.res.Configuration = res.configuration
                    conf.setLocale(Locale("KG"))
                    res.updateConfiguration(conf,dm)

                }
                R.id.ru -> {

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
