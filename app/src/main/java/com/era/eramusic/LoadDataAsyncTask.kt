package com.era.eramusic

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.widget.Toast
import com.era.eramusic.handletest.TestMenu
import com.era.eramusic.resources.Resource
import java.util.*

@SuppressLint("StaticFieldLeak")
class LoadDataAsyncTask(context: Context) : AsyncTask<Void, Void, Void? >() {
    private val con = context

    override fun onPreExecute() {
        super.onPreExecute()
        Toast.makeText(con, "Đang lấy dữ liệu từ máy ...", Toast.LENGTH_SHORT).show()
        Resource.GetSharedPreferences(con)
        Toast.makeText(con, "Đang tải dữ liệu ...", Toast.LENGTH_SHORT).show()
    }

    override fun doInBackground(vararg params: Void?): Void? {
        Resource.LoadData(con as Activity)
        return null;
    }

    override fun onCancelled() {
        super.onCancelled()
        Toast.makeText(con, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show()
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
        //Toast.makeText(con, "Đang tải dữ liệu ...", Toast.LENGTH_SHORT).show()
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        Toast.makeText(con, "Tải thành công", Toast.LENGTH_SHORT).show()
        con.startActivity(Intent(con, MainActivity::class.java))
    }
}