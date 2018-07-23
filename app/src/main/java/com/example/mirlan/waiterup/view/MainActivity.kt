package com.example.mirlan.waiterup.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.internal.Internal.instance

class MainActivity : AppCompatActivity() {

    private var mUserName:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mUserName = intent.getStringExtra("USER_NAME")

        val mNewOrderBtn = findViewById<Button>(R.id.btnNewOrder)
        val mCloseOrderBtn = findViewById<Button>(R.id.btnClosedOrder)
        val mOpenOrderBtn = findViewById<Button>(R.id.btnOpenOrder)
        val mClockOrderkBtn = findViewById<Button>(R.id.btnClockOrder)
        val mLogOutBtn = findViewById<Button>(R.id.btnLogOut)
        val mUsertxt = findViewById<TextView>(R.id.txtWaiterName)
        mUsertxt.text = mUserName


        mNewOrderBtn.setOnClickListener {
            val intent = Intent(this,NewOrderActivity::class.java)
            startActivity(intent)
        }

        mClockOrderkBtn.setOnClickListener {

        }

        mOpenOrderBtn.setOnClickListener {

        }

        mCloseOrderBtn.setOnClickListener {

        }

        mLogOutBtn.setOnClickListener {

            alertDialogShow()

        }
    }

    private fun alertDialogShow(){
        val alert =  AlertDialog.Builder(this)
                .setTitle(resources.getString(R.string.logOutTxt))
                .setMessage(resources.getString(R.string.logSure))
                .setNegativeButton(resources.getString(R.string.no), null)
                .setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                    clearToken()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }
        alert.show()
    }

    private fun clearToken() {
        SaveSharedPreference.setAccessToken(this,"")
    }

    override fun onBackPressed() {
                    super.onBackPressed()
                    finishAffinity()
                    System.exit(0)
    }

}
