package com.example.todoapp
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemRowBinding

class RecyclerViewAdapter(private val items: ArrayList<ToDo>): RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            cbItem.isChecked = item.isSelected
            cbItem.text = item.content
            cbItem.setOnCheckedChangeListener { _, isChecked ->
                if(cbItem.isChecked){
                    cbItem.setTextColor(Color.GRAY)
                    items[position].isSelected = true
                }else {
                    cbItem.setTextColor(Color.BLACK)
                    items[position].isSelected = false
                }
            }
        }
    }

    override fun getItemCount() = items.size

    fun deleteItems(){
        items.removeAll{ item -> item.isSelected }
        notifyDataSetChanged()
    }
}

