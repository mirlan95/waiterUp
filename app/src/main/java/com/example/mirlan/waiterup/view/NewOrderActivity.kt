package com.example.mirlan.waiterup.view

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.mirlan.waiterup.R
import com.example.mirlan.waiterup.api.Api
import com.example.mirlan.waiterup.api.NetworkCallback
import com.example.mirlan.waiterup.data.network.Categories
import kotlinx.android.synthetic.main.activity_new_order.*
import com.example.mirlan.waiterup.api.Network
import com.example.mirlan.waiterup.data.network.Food
import com.example.mirlan.waiterup.utils.ProgressDialog
import com.example.mirlan.waiterup.view.adapter.FoodAdapter
import kotlin.collections.ArrayList
import android.widget.ArrayAdapter
import com.example.mirlan.waiterup.data.database.FoodDataBase
import com.example.mirlan.waiterup.data.entity.FoodData
import android.app.Activity


class NewOrderActivity : AppCompatActivity() {

    private lateinit var mCheckBox:CheckBox

    private var layoutManager: LinearLayoutManager? = null

    private var arrayListCategory: ArrayList<String> = ArrayList()
    private var mFoodAdapter:FoodAdapter? = null
    private var mFoodList:ArrayList<Food>? = null
    private var mCategoryList: ArrayList<Categories>? = null
    var isAction: Int = 0

    companion object {
        var mCategoryId: Int = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_order)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mCheckBox = findViewById(R.id.checkBox)

        val extra = intent.extras
        if (extra != null) isAction = extra.get("MAIN") as Int
        when (isAction) {
            0 -> {
                //to clock
            }
            1 -> {
                //to order
            }
            else -> {
                //to about order
            }
        }
        getMenu()

        category_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                mCategoryId = if(position == 0){
                    0
                }else{
                    mCategoryList!![position-1].id
                }
                sortList()
            }
        }
    }

    fun btnOrder(v:View){
        if(isAction == 1 || isAction == 0)
            orderingFood()
        else
            backToAboutActivity()
    }
    fun btnUpdate(v:View){
        clearOrders()
    }
    private fun getMenu() {
        val dialog = ProgressDialog.progressDialog(this)
        dialog.show()
       Network.request(Api.provideApi().getCategories(),
                NetworkCallback<List<Categories>>().apply {
                    success = {

                        mCategoryList = ArrayList(it)
                        initSpinner()
                        getFood(dialog)
                    }
                    error = {
                        dialog.dismiss()
                    }
                }
        )

    }
    private fun getFood(dialog: Dialog) {

        Network.request(Api.provideApi().getFoods(),
                NetworkCallback<List<Food>>().apply {
                    success = {

                        mFoodList = ArrayList(it)
                        initRecylerView(dialog)
                    }
                    error = {
                        dialog.dismiss()
                    }
                }
        )
    }

    private fun initSpinner() {

        arrayListCategory.clear()
        arrayListCategory.add(0,resources.getString(R.string.alltxt))

        for(i in 0 until mCategoryList!!.size)
            arrayListCategory.add(i+1, mCategoryList!![i].name!!)

        val spinnerList = ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayListCategory)
        spinnerList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        category_spinner.adapter = spinnerList


    }
    private fun initRecylerView(dialog: Dialog) {

        menu_recyclerview.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        menu_recyclerview.layoutManager = layoutManager
        mFoodAdapter = FoodAdapter(this.mFoodList!!)
        menu_recyclerview.adapter = mFoodAdapter
        dialog.dismiss()
    }

    private fun orderingFood() {

        val intent = Intent(this,OrderLastControlActivity::class.java)
        intent.putExtra("CLOCK",isAction)
        intent.putParcelableArrayListExtra("list", mFoodList)//?.filter { it -> it.quantity > 0 })
        startActivity(intent)
    }

    private fun backToAboutActivity(){

        val returnIntent = Intent()
        returnIntent.putParcelableArrayListExtra("list", mFoodList)//?.filter { it -> it.quantity>0 })
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
    private fun sortList() {

        if(mCategoryId == 0)
            mFoodAdapter?.update(mFoodList)
        else
            mFoodAdapter?.update(mFoodList?.filter { it -> it.catId == mCategoryId })
        mFoodAdapter?.notifyDataSetChanged()
    }


    private fun clearOrders() {
        for(i in 0 until mFoodList!!.size)
            mFoodList!![i].quantity = 0
        mFoodAdapter?.notifyDataSetChanged()

    }


    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        android.R.id.home ->{
            onBackPressed()
            true
        }
        else -> false

    }


}



