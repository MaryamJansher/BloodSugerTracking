package com.sazib.mypicker.picker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodsugartracking9d.R

class PickerItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

  val tvItem: TextView? = itemView?.findViewById(R.id.tv_item)
}