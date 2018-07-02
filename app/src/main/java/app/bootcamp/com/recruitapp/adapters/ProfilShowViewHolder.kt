package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import app.bootcamp.com.recruitapp.R


class ProfilShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var aboutInfo: TextView
    var namInf: TextView
    var imageView: ImageView
    private val selectedItems = SparseBooleanArray()

    init {
        imageView = itemView.findViewById(R.id.namedata)
        namInf = itemView.findViewById(R.id.namedata)
        aboutInfo = itemView.findViewById(R.id.namedata)
    }

    override fun onClick(v: View) {
        if (selectedItems.get(adapterPosition, false)) {
            selectedItems.delete(adapterPosition)
            v.isSelected = false
        } else {
            selectedItems.put(adapterPosition, true)
            v.isSelected = true
        }
    }
}


