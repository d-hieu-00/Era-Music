package com.era.eramusic.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.era.eramusic.R
import com.era.eramusic.entitys.Song

class AllSongAdapter(
    context: Activity,
    layoutID: Int,
    objects: ArrayList<Song>
) : ArrayAdapter<Song>(context, layoutID, objects) {


    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cvView: View? = convertView
        if (cvView == null) {
            cvView = LayoutInflater.from(context).inflate(
                R.layout.menutest_allsong_item_song,
                null,
                false)
        }

        val iSong = getItem(position) as Song
        val iName = cvView?.findViewById(R.id.item_name) as TextView
        val iImg = cvView.findViewById(R.id.item_img) as ImageView

        iName.text = iSong.name
        //iName.setSelected(true)
        Glide.with(context).load(iSong.linkPic).into(iImg)
        return cvView
    }

}