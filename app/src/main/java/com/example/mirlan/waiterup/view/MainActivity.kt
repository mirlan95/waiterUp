package com.example.mirlan.waiterup.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import com.example.mirlan.waiterup.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mNewOrderBtn = findViewById<Button>(R.id.btnNewOrder)
        val mCloseOrderBtn = findViewById<Button>(R.id.btnClosedOrder)
        val mOpenOrderBtn = findViewById<Button>(R.id.btnOpenOrder)
        val mClockOrderkBtn = findViewById<Button>(R.id.btnClockOrder)
        val mLogOutBtn = findViewById<Button>(R.id.btnLogOut)

        mNewOrderBtn.setOnClickListener {
            //val intent = Intent(this,)
        }

        mClockOrderkBtn.setOnClickListener {

        }

        mOpenOrderBtn.setOnClickListener {

        }

        mCloseOrderBtn.setOnClickListener {

        }

        mLogOutBtn.setOnClickListener {

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
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
