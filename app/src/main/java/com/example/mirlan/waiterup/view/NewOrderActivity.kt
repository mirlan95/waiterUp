package com.example.mirlan.waiterup.view

import android.net.Network
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.menu.MenuAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.*
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.R.layout.menu_raw
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.Categories
import com.example.mirlan.waiterup.data.network.Waiter
import kotlinx.android.synthetic.main.activity_new_order.*
import java.util.Arrays.asList
import org.angmarch.views.NiceSpinner
import java.util.*
import kotlin.collections.ArrayList


class NewOrderActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {

    private lateinit var mCheckBox:CheckBox
    private var layoutManager: LinearLayoutManager? = null
    private var menuAdapter: MenuAdapter? = null
    private var mCategoryList: ArrayList<Categories>? = null
    private var niceSpinner: NiceSpinner? = null
    private var mCategoryId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)

        val mUpdateBtn = findViewById<Button>(R.id.btn_update)
        val mOrderBtn = findViewById<Button>(R.id.btn_order)
        niceSpinner = findViewById(R.id.category_spinner)
         mCheckBox = findViewById(R.id.checkBox)

        getMenu()

        mOrderBtn.setOnClickListener {

            orderingFood()

        }
        mUpdateBtn.setOnClickListener {
            clearOrders()
        }
    }

    private fun getMenu() {

        com.example.mirlan.waiterup.api.Network.request(Api.provideApi().getCategories(),
                NetworkCallback<List<Categories>>().apply {
                    success = {
                        mCategoryList = ArrayList(it)
                        initSpinner()
                    }
                    error = {

                        //ProgressDialog.myToast(LoginActivity.applicationContext(),R.string.errorPassword.toString())
                    }
                }
        )

    }

    private fun initSpinner(){
        val spinnerList = ArrayAdapter(this, android.R.layout.simple_spinner_item, mCategoryList)
        niceSpinner!!.setAdapter(spinnerList)
    }
    private fun initRecylerView(){

        menu_recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        menu_recyclerview.layoutManager = layoutManager

    }

    private fun orderingFood() {

    }

    private fun clearOrders() {

    }
    override fun onNothingSelected(parent: AdapterView<*>?) {

        mCategoryId = mCategoryList!![0].id

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        mCategoryId = mCategoryList!![position].id
    }
}
