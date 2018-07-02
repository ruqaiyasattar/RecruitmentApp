package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.widget.Button
import android.widget.TextView
import app.bootcamp.com.recruitapp.R
import kotlinx.android.synthetic.main.showstudent.view.*

class StudentViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var stuName: TextView
    var stuAge: TextView
    var stuLocation: TextView
    var stuEmail: TextView
    var stuQualif: TextView
    internal var Delete: Button? =null

    private val selectedItems = SparseBooleanArray()

    init {
        stuName = itemView.name as TextView
        stuAge = itemView.age as TextView
        stuEmail = itemView.email as TextView
        stuLocation = itemView.city as TextView
        stuQualif = itemView.qualif as TextView
        Delete=itemView.findViewById(R.id.DelStu)
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


