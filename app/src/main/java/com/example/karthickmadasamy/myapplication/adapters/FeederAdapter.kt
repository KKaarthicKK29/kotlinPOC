package com.example.karthickmadasamy.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.example.karthickmadasamy.myapplication.R
import com.example.karthickmadasamy.myapplication.db.FeederEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.karthickmadasamy.myapplication.utils.SquareImageView

import java.util.ArrayList

/**
 * Created by Karthick.Madasamy on 12/4/2019.
 */

class FeederAdapter
//    OnItemClickListener onItemClickListener;



(private val context: Context) : RecyclerView.Adapter<FeederAdapter.FeederHolder>() {

    var feederList: List<FeederEntity> = ArrayList()
        set(feederList) {
            field = feederList
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeederHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.feeder_rows, parent, false)
        return FeederHolder(v)
    }

    override fun onBindViewHolder(holder: FeederHolder, position: Int) {
        holder.tvTitle.text = this.feederList[position].title
        holder.tvDescription.text = this.feederList[position].description
        if (this.feederList[position].description != null && !this.feederList[position].description!!.equals("", ignoreCase = true))
        // set description values if values is not null or not empty
            holder.tvDescription.text = this.feederList[position].description
        else
            holder.tvDescription.text = context.resources.getString(R.string.description_not_available)
        //        holder.click(rowsList.get(position),onItemClickListener);
        Glide.with(context).load(this.feederList[position].imageHref).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.drawable.lazyimage).into(holder.imageViewFeeder)
    }

    override fun getItemCount(): Int {
        return this.feederList.size
    }

   /* interface OnItemClickListener {
        fun onClick(rows: DBRowModel)
    }*/

    inner class FeederHolder(v: View) : RecyclerView.ViewHolder(v) {
        val tvTitle: TextView
        val tvDescription: TextView
        val imageViewFeeder: SquareImageView

        init {
            tvTitle = v.findViewById(R.id.tv_title)
            tvDescription = v.findViewById(R.id.tv_description)
            imageViewFeeder = v.findViewById(R.id.imageView_Feeder)
        }

       /* fun click(rows: DBRowModel, onItemClickListener: OnItemClickListener) {
            itemView.setOnClickListener { onItemClickListener.onClick(rows) }
        }*/
    }

}

