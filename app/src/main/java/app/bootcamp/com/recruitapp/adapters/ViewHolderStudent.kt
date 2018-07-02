package app.bootcamp.com.recruitapp.adapters


import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import app.bootcamp.com.recruitapp.R

class ViewHolderStudent (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    internal var postShow: TextView? = null
    internal var expShow: TextView? = null
    internal var comShow: TextView? = null
    internal var showType: TextView? = null
    internal var showIndstry: TextView? = null
    internal var showDesc: TextView? = null
    internal var shoSal: TextView? = null
    internal var apply: Button? = null

    var visible: Boolean=false

    internal var img: ImageButton? = null

    private val selectedItems = SparseBooleanArray()

    init {
        apply=itemView.findViewById<Button>(R.id.apply)
        img=itemView.findViewById(R.id.imagText)
        postShow=itemView.findViewById(R.id.jobText)
        expShow=itemView.findViewById(R.id.expText)
        comShow=itemView.findViewById(R.id.comText)
        showType=itemView.findViewById(R.id.typText)
        showIndstry=itemView.findViewById(R.id.indText)
        shoSal=itemView.findViewById(R.id.salText)
        showDesc=itemView.findViewById(R.id.jobdescText)

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



