package com.example.bloodsugartracking9d.adapters

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodsugartracking9d.R
import com.sazib.mypicker.picker.PickerItemViewHolder


class PickerAdapter : RecyclerView.Adapter<PickerItemViewHolder>() {

  private val data: ArrayList<String> = ArrayList()

  var callback: Callback_interface? = null
  private val clickListener = View.OnClickListener { v -> v?.let { callback?.onItemClicked(it) } }
  private var selectedItem: Int? = -1
  private var ctx: Context? = null

   var selected_item : Int? = null

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickerItemViewHolder {

    ctx = parent.context
    val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.row_view_picker_item, parent, false)
    itemView.setOnClickListener(clickListener)
    return PickerItemViewHolder(itemView)
  }

  override fun getItemCount(): Int {
    return data.size
  }

  override fun onBindViewHolder(holder: PickerItemViewHolder, position: Int) {
    holder.tvItem?.text = data[position]

    when (selectedItem) { position -> {

        holder.tvItem?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F);
        holder.tvItem?.setTextColor(ContextCompat.getColor(ctx!!, R.color.white))
        holder.tvItem?.setBackground(ContextCompat.getDrawable(ctx!!, R.drawable.ic_circle));
        selectedItem = -1
      }
      else -> {

          holder.tvItem?.setTextColor(ContextCompat.getColor(ctx!!, R.color.light_blue))
          holder.tvItem?.setBackgroundColor(Color.TRANSPARENT);
      }
    }
  }

  fun setSelectedItem(position: Int) {
    selectedItem = position
    notifyDataSetChanged()
  }

  fun setData(data: ArrayList<String>) {
    this.data.clear()
    this.data.addAll(data)
    notifyDataSetChanged()
  }



  interface Callback_interface {
    fun onItemClicked(view: View)
  }
}