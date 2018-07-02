package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import app.bootcamp.com.recruitapp.R

class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var comName: TextView
    var comAbout: TextView
    var comContact: TextView
    var comLocation: TextView
    var comEmail: TextView
    var imageView: ImageView
    internal var Del: Button? =null
    private val selectedItems = SparseBooleanArray()

    init {
        comName = itemView.findViewById(R.id.namedata) as TextView
        comAbout = itemView.findViewById(R.id.aboutdata) as TextView
        comContact = itemView.findViewById(R.id.contactdata) as TextView
        comLocation = itemView.findViewById(R.id.locationdata) as TextView
        comEmail = itemView.findViewById(R.id.emaildata) as TextView
        imageView = itemView.findViewById(R.id.showimg) as ImageView
        Del=itemView.findViewById(R.id.DelPro)
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

