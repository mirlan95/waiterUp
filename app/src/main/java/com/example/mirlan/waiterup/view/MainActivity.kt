package com.example.mirlan.waiterup.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtWaiterName.text = SaveSharedPreference.getUserName(this)

        btnNewOrder.setOnClickListener {toOrderActivity(1)}

        btnClockOrder.setOnClickListener {toOrderActivity(0)}

        btnOpenOrder.setOnClickListener { toActivity(1) }

        btnClosedOrder.setOnClickListener { toActivity(0) }

        btnLogOut.setOnClickListener { alertDialogShow() }

    }

    private fun toActivity(i: Int) {
        val intent = Intent(this,OrderHistoryActivity::class.java)
        intent.putExtra("HISTORY",i)
        startActivity(intent)
    }
    private  fun toOrderActivity(i: Int) {

        val intent = Intent(this,NewOrderActivity::class.java)
        intent.putExtra("MAIN",i)
        startActivity(intent)
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
                    finishAffinity()
                }
        alert.show()
    }

    private fun clearToken() {
        SaveSharedPreference.setAccessToken(this,"")
    }

    override fun onBackPressed() {
                    super.onBackPressed()
                    finishAffinity()
                    //System.exit(0)
    }

}
