package com.example.appmarveldesafio.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmarveldesafio.R
import com.example.appmarveldesafio.entities.Images
import com.example.appmarveldesafio.entities.Quadrinho
import kotlinx.android.synthetic.main.item_quadrinho.view.*

class AdapterQuadrinhos(val listener : OnClickQuadrinhoListener) : RecyclerView.Adapter<AdapterQuadrinhos.ResultViewHolder>() {
    var listQuadrinhos = ArrayList<Quadrinho>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quadrinho, parent,false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        var item = listQuadrinhos[position]
        Glide.with(holder.itemView.context).asBitmap()
                .load("${item.thumbnail.path}.${item.thumbnail.extension}")
                .into(holder.ivThumbQuad)
        holder.tvIssueNumber.text = "#${item.issueNumber}"
    }

    override fun getItemCount() = listQuadrinhos.size

    fun addList(list: ArrayList<Quadrinho>){
        listQuadrinhos.addAll(list)
        notifyDataSetChanged()
    }

    interface OnClickQuadrinhoListener{
        fun onClickQuadrinho(position: Int)
    }

    inner class ResultViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var ivThumbQuad: ImageView = view.ivThumbQuad
        var tvIssueNumber : TextView = view.tvIssueNumber

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION)
                listener.onClickQuadrinho(position)
        }
    }
}