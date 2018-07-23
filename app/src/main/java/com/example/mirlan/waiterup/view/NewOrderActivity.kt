package com.example.mirlan.waiterup.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.view.menu.MenuAdapter
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.*
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.Categories
import com.example.mirlan.waiterup.data.network.Waiter
import kotlinx.android.synthetic.main.activity_new_order.*
import com.example.mirlan.waiterup.api.Network
import com.example.mirlan.waiterup.data.network.Food
import com.example.mirlan.waiterup.view.adapter.FoodAdapter
import org.angmarch.views.NiceSpinner
import kotlin.collections.ArrayList
class NewOrderActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener,FoodAdapter.Listener {

    private lateinit var mCheckBox:CheckBox
    private var layoutManager: LinearLayoutManager? = null
    private var mCategoryList: ArrayList<Categories>? = null
    private var mFoodList:ArrayList<Food>? = null

    private var niceSpinner: NiceSpinner? = null
    private var mCategoryId: Int = 0
    private var arrayListCategory: ArrayList<String> = ArrayList()

    private var mFoodAdapter:FoodAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)

        val mUpdateBtn = findViewById<Button>(R.id.btn_update)
        val mOrderBtn = findViewById<Button>(R.id.btn_order)
        niceSpinner = findViewById(R.id.category_spinner)
        mCheckBox = findViewById(R.id.checkBox)

        getMenu()
        getFood()

        mOrderBtn.setOnClickListener {

            orderingFood()

        }
        mUpdateBtn.setOnClickListener {
            clearOrders()
        }
    }
    override fun onItemClick(mFood: Food) {

        Toast.makeText(this, " hello ",Toast.LENGTH_LONG).show()
    }

    private fun getMenu() {

       Network.request(Api.provideApi().getCategories(),
                NetworkCallback<List<Categories>>().apply {
                    success = {

                        mCategoryList = ArrayList(it)
                        arrayListCategory.add(0,resources.getString(R.string.alltxt))
                        for(i in 0 until mCategoryList!!.size)
                        arrayListCategory.add(i+1, it[i].name!!)
                        initSpinner()
                    }
                    error = {
                    Log.e("ERORRRO",it.localizedMessage)
                        //ProgressDialog.myToast(LoginActivity.applicationContext(),R.string.errorPassword.toString())
                    }
                }
        )

    }
    private fun getFood(){

        Network.request(Api.provideApi().getFoods(),
                NetworkCallback<List<Food>>().apply {
                    success = {

                        mFoodList = ArrayList(it)
                        initRecylerView()
                    }
                    error = {
                        Log.e("ERORRRO",it.localizedMessage)
                        //ProgressDialog.myToast(LoginActivity.applicationContext(),R.string.errorPassword.toString())
                    }
                }
        )
    }

    private fun initSpinner(){
        val spinnerList = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListCategory)
         niceSpinner!!.setAdapter(spinnerList)
    }
    private fun initRecylerView(){

        menu_recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        menu_recyclerview.layoutManager = layoutManager
        mFoodAdapter = FoodAdapter(mFoodList!!,this)
        menu_recyclerview.adapter = mFoodAdapter

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

