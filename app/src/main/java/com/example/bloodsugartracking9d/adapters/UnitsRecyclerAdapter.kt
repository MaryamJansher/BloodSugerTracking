/*
package com.example.bloodsugartracking9d.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.all_interface.RecyclerViewclicks

class UnitsRecyclerAdapter(private val value_list: List<String> , val recyclerviewClicks: RecyclerViewclicks) :
    RecyclerView.Adapter<UnitsRecyclerAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.`row_view_picker_item.xml`, parent, false)

        return UnitsRecyclerAdapter.MyViewHolder(view)


    }


    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


        val textView: TextView = itemView.findViewById(R.id.tv_measurements)


    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.textView.text = value_list[position].toString()
        holder.itemView.setOnClickListener {

            recyclerviewClicks.recyclerview_itemclick(position)
        }


    }


    override fun getItemCount(): Int {

        return value_list.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

   */
/* fun stop_scrolling(position: Int) {

        unitsRecyclerStopScrolling.stopscrollingunitsrv()

    }*//*


}
*/
