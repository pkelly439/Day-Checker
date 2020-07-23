package com.example.daychecker.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SpinnerAdapter
import android.widget.TextView
import java.util.ArrayList

class CustomAdapter(private val activity: Context, private val asr: ArrayList<*>) : BaseAdapter(), SpinnerAdapter {
    override fun getCount(): Int {
        return asr.size
    }

    override fun getItem(i: Int): Any {
        return asr[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val txt = TextView(activity)
        txt.setPadding(16, 16, 16, 16)
        txt.textSize = 18f
        txt.text = asr[position].toString()
        return txt
    }

    override fun getView(i: Int, view: View?, viewgroup: ViewGroup): View {
        val txt = TextView(activity)
        txt.textSize = 18f
        //        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_circle, 0);
        txt.text = asr[i].toString()
        return txt
    }

}