package com.example.mirlan.waiterup.view

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference

class MainEmptyActivity : AppCompatActivity() {

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent:Intent
        if(SaveSharedPreference.getAccessToken(this) != "")
            intent = Intent(this,MainActivity::class.java)
        else
            intent = Intent(this,LoginActivity::class.java)

        startActivity(intent)
        finish()
    }

}