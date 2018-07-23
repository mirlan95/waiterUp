package com.example.mirlan.waiterup.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.Network
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.User
import com.example.mirlan.waiterup.data.network.Waiter
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import com.example.mirlan.waiterup.utils.LocaleManager
import com.example.mirlan.waiterup.utils.ProgressDialog

class LoginActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: LoginActivity? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    private lateinit var mUserPassword:EditText
    private var mUserSpinner: Spinner? = null
    private var mUserId: Int = 0
    private var mUserName: String? = null
    private var mUserList: ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

         mUserPassword = findViewById(R.id.userPassword)
         mUserSpinner = findViewById(R.id.spinner)

        mUserSpinner!!.onItemSelectedListener = this

        val mButtonLoginIn = findViewById<Button>(R.id.btnLogIn)

        mButtonLoginIn.setOnClickListener {
            if(!mUserPassword.text.isEmpty()) {
                val intent = Intent(this,MainActivity::class.java)
                intent.putExtra("USER_NAME",mUserName)
                startActivity(intent)
                //var dialog = ProgressDialog.progressDialog(this)
              //  dialog.show()
               // validateUser(dialog)

            }else {

                Toast.makeText(this,resources.getString(R.string.errorPassword),Toast.LENGTH_LONG).show()

            }


        }
    }

    override fun onStart() {
        super.onStart()

        getUsers()

    }
    private fun getUsers(){

        Network.request(Api.provideApi().getWaiters(),
                NetworkCallback<List<User>>().apply {
                    success = {

                        mUserList = ArrayList(it)
                       // mUserId = it[0].userId
                       // mUserName = it[0].name
                        Log.e("EEEE",it[1].name)
                        init()

                    }
                    error = {
                      // ProgressDialog.myToast(this,R.string.errorInt.toString())
                        Log.e("ERROOOR",it.localizedMessage) // it.  (Throwable)
                    }
                })
    }

    private fun init() {

        val spinnerList = ArrayAdapter(this, android.R.layout.simple_spinner_item, mUserList)
        spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mUserSpinner!!.adapter = spinnerList

    }
    private fun validateUser(dialog: Dialog) {

        Network.request(Api.provideApi().logIn(mUserId,mUserPassword.text.toString()),
                NetworkCallback<Waiter>().apply {
                    success = {
                        setToken(it.mToken,dialog)
                    }
                    error = {
                        dialog.dismiss()
                        //ProgressDialog.myToast(LoginActivity.applicationContext(),R.string.errorPassword.toString())
                    }
                }
                )

    }
    private fun setToken(mToken: String?, dialog: Dialog) {
        if (mToken != null) {

            dialog.dismiss()
            SaveSharedPreference.setAccessToken(this,mToken)
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("USER_NAME",mUserName)
            startActivity(intent)
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        mUserId = mUserList!![0].userId
        mUserName = mUserList!![0].name
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        mUserId = mUserList!![position].userId
        mUserName = mUserList!![position].name

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when(item.itemId){
                R.id.kg -> {
                    LocaleManager.setLocale(this, "kg")
                    recreate()

                }
                R.id.ru -> {
                    LocaleManager.setLocale(this, "ru")
                    recreate()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
