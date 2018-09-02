package com.example.mirlan.waiterup.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import butterknife.ButterKnife
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.Network
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.User
import com.example.mirlan.waiterup.data.network.Waiter
import com.example.mirlan.waiterup.data.preferences.SaveSharedPreference
import com.example.mirlan.waiterup.utils.LocaleManager
import com.example.mirlan.waiterup.utils.ProgressDialog
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: LoginActivity? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    private var mUserId: Int = 0
    private var mUserName: String? = null
    private var mUserList: ArrayList<User>? = null
    private var arrayListUsers: ArrayList<String> = ArrayList()
    private lateinit var mUserSpinner: Spinner
  //  private lateinit var mLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        btnLogIn.setOnClickListener { logIn() }

        mUserSpinner = findViewById(R.id.spinner)
        mUserSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 if(position != 0){
                    mUserId = mUserList!![position-1].userId
                    mUserName = mUserList!![position-1].name
                }else
                     mUserId = 0

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
                        init()
                    }
                    error = {

                    }
                })
    }

    private fun init() {

        arrayListUsers.clear()
        arrayListUsers.add(0,resources.getString(R.string.change_spinner_name))
        for(i in 0 until mUserList!!.size)
            arrayListUsers.add(i+1, mUserList!![i].name!!)
        val spinnerList = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListUsers)
        spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mUserSpinner.adapter = spinnerList

    }
    private fun logIn(){

        val dialog = ProgressDialog.progressDialog(this)

        if(!userPassword!!.text.toString().isEmpty()) {

            dialog.show()
            validateUser(dialog)

        }else {
            Toast.makeText(this,resources.getString(R.string.errorPassword),Toast.LENGTH_LONG).show()
        }
    }

    private fun validateUser(dialog: Dialog) {

        Network.request(Api.provideApi().logIn(mUserId, userPassword!!.text.toString()),
                NetworkCallback<Waiter>().apply {
                    success = {
                        setToken(it.mToken,it.auth,dialog)
                    }
                    error = {
                        dialog.dismiss()
                        msgToUser(false)
                    }
                }
                )

    }
    private fun setToken(mToken: String?,auth:Boolean?, dialog: Dialog) {

        dialog.dismiss()

        if (auth!! && mToken != null) {

            saveUserData(mToken)
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("USER_NAME",mUserName)
            startActivity(intent)

        }else {
            Toast.makeText(this,resources.getString(R.string.errorPassword),Toast.LENGTH_LONG).show()
        }
    }
    private fun msgToUser(b: Boolean) {
        if(b) {
            Toast.makeText(this,R.string.success,Toast.LENGTH_LONG).show()
            super.onBackPressed()
        }else
            Toast.makeText(this,R.string.errorInt,Toast.LENGTH_LONG).show()

    }
    private fun saveUserData(mToken: String) {
        SaveSharedPreference.setAccessToken(this,mToken)
        SaveSharedPreference.setUserName(this, this.mUserName!!)
        SaveSharedPreference.setUserId(this,this.mUserId)
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
