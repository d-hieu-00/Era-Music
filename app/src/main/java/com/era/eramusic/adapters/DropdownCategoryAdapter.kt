package com.era.eramusic.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.era.eramusic.R
import com.era.eramusic.entitys.Catergory

class DropdownCategoryAdapter (context: Activity, layoutID: Int, objects: List<Catergory>) :
    ArrayAdapter<Catergory>(context, layoutID, objects) {

    @SuppressLint("InflateParams")
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cvView: View? = convertView
        if (cvView == null) {
            cvView = LayoutInflater.from(context).inflate(R.layout.menutest_allsong_item_category,null, false)
        }

        val iCategory = getItem(position) as Catergory
        val iName = cvView?.findViewById(R.id.item_name) as TextView
        iName.text = iCategory.name
        return cvView
    }

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cvView: View? = convertView
        if (cvView == null) {
            cvView = LayoutInflater.from(context).inflate(R.layout.menutest_allsong_item_category,null, false)
        }

        val iCategory = getItem(position) as Catergory
        val iName = cvView?.findViewById(R.id.item_name) as TextView
        iName.text = iCategory.name
        return cvView
    }
}