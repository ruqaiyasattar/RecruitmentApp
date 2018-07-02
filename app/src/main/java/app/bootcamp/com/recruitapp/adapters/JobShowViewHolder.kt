package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.widget.TextView
import app.bootcamp.com.recruitapp.R

class JobShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    internal var postShow: TextView? = null
    internal var expShow: TextView? = null
    internal var comShow: TextView? = null
    internal var showType: TextView? = null
    internal var showIndstry: TextView? = null
    internal var showDesc: TextView? = null
    internal var shoSal:TextView? = null

    private val selectedItems = SparseBooleanArray()

    init {
        postShow=itemView.findViewById(R.id.showpst)
        expShow=itemView.findViewById(R.id.showexp)
        comShow=itemView.findViewById(R.id.showjob)
        showType=itemView.findViewById(R.id.showtyp)
        showIndstry=itemView.findViewById(R.id.showinds)
        shoSal=itemView.findViewById(R.id.showsal)
        showDesc=itemView.findViewById(R.id.showdesc)

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


