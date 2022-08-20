package com.example.themoviebooking.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themoviebooking.R
import com.example.themoviebooking.data.vos.ActorVO
import com.example.themoviebooking.viewholders.CastViewHolder

class CastAdapter: RecyclerView.Adapter<CastViewHolder>() {

    private var mActorList: List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bindData(mActorList[position])
    }

    override fun getItemCount(): Int {
        return mActorList.count()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(actorList: List<ActorVO>) {
        mActorList = actorList
        notifyDataSetChanged()
    }
}