
package com.example.mirlan.waiterup.view
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference


class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val mApiEdittext  = findViewById<EditText>(R.id.apiEditText)
        val mIpBtn = findViewById<Button>(R.id.ipBtn)

        mIpBtn.setOnClickListener {


            if(!mApiEdittext.text.toString().isEmpty()){
             SaveSharedPreference.setApiAddress(this, mApiEdittext.text.toString())
             Toast.makeText(this, " Тиркеменин ip-адреси өзгөртүрүлдү.",Toast.LENGTH_LONG).show()
            }else {
               Toast.makeText(this, "Талааны толтуруңуз!",Toast.LENGTH_LONG).show()

            }

        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home ->{
            onBackPressed()
            true
        }
        else -> false

    }
}
