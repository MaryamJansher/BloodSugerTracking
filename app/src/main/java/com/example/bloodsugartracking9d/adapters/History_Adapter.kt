package com.example.bloodsugartracking9d.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.all_interface.RecyclerViewclicks
import com.example.bloodsugartracking9d.room.UserDetail

class History_Adapter (val mylist: List<UserDetail> , val recyclerviewClicks: RecyclerViewclicks  ) : RecyclerView.Adapter<History_Adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return MyViewHolder(view)
    }

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        //val imageView : ImageView = itemView.findViewById(R.id.imageview)
       // val textView: TextView = itemView.findViewById(R.id.textView)


    }

     override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

      ///   holder.textView.text =mylist.get(position).time.toString()

         holder.itemView.setOnClickListener {
             recyclerviewClicks.recyclerview_itemclick(position)
         }
     }



    override fun getItemCount(): Int {

        return mylist.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

     override fun getItemViewType(position: Int): Int {
         return super.getItemViewType(position)
     }





 }