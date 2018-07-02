package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.widget.TextView
import app.bootcamp.com.recruitapp.R

class ShowToCmpanyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    internal var name: TextView? = null
    internal var age: TextView? = null
    internal var qualif: TextView? = null
    internal var email: TextView? = null
    internal var city: TextView? = null
    private val selectedItems = SparseBooleanArray()

    init {
        name=itemView.findViewById(R.id.namec)
        age=itemView.findViewById(R.id.agec)
        qualif=itemView.findViewById(R.id.qualifc)
        email=itemView.findViewById(R.id.emailc)
        city=itemView.findViewById(R.id.cityc)
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


