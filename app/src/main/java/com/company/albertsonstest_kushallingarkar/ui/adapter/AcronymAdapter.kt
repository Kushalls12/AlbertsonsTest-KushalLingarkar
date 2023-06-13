package com.company.albertsonstest_kushallingarkar.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.company.albertsonstest_kushallingarkar.model.data.Acronym
import com.company.albertsonstest_kushallingarkar.R

class AcronymAdapter(private val meanings: List<Acronym>) : BaseAdapter() {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.acronym_item, parent, false)
        }

        val acronymTextView = view?.findViewById<TextView>(R.id.acronymTextView)
        val meaningTextView = view?.findViewById<TextView>(R.id.meaningTextView)

        val acronym = meanings[position].sf
        val meaning = meanings[position].lfs.joinToString(", ") { longForm ->
            longForm.lf
        }

        acronymTextView?.text = acronym
        meaningTextView?.text = meaning

        return view!!
    }
}
