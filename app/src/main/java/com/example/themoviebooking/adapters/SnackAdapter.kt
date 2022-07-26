package com.example.themoviebooking.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.viewholders.SnackViewHolder

class SnackAdapter: RecyclerView.Adapter<SnackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_snack, parent, false)
        return SnackViewHolder(view)
    }

    override fun onBindViewHolder(holder: SnackViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 3
    }
}