package com.era.eramusic.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.era.eramusic.R
import com.era.eramusic.entitys.Playlist

class PlaylistAdapter (context: Activity, layoutID: Int, objects: List<Playlist>) :
    ArrayAdapter<Playlist>(context, layoutID, objects) {

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var cvtView: View? = convertView
        if (cvtView == null) {
            cvtView = LayoutInflater.from(context)
                .inflate(R.layout.menutest_playlist_item_playlist,null)
        }

        // Get item
        val iPlaylist: Playlist? = getItem(position)
        val tvFullName = cvtView?.findViewById(R.id.NamePlaylist) as TextView

        tvFullName.text = iPlaylist?.name

        return cvtView
    }
}