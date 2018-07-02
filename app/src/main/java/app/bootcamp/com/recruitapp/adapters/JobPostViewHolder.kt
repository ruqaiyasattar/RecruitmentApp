package app.bootcamp.com.recruitapp.adapters

import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

import app.bootcamp.com.recruitapp.R
import com.daimajia.swipe.SwipeLayout


class JobPostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    internal var jobName: TextView
    internal var postTyp: TextView? = null
    internal var expernc: TextView? = null
    internal var comSalry: TextView? = null
    internal var comType: TextView? = null
    internal var comIndstry: TextView? = null
    internal var jobDesc: TextView? = null
    internal  var samp: SwipeLayout? = null
    internal var bottom: LinearLayout? = null
    internal var surface:LinearLayout? = null
    internal var Del:Button? =null

    private val selectedItems = SparseBooleanArray()

    init {
        jobName = itemView.findViewById(R.id.jobbb)
        samp=itemView.findViewById(R.id.sample)
        bottom=itemView.findViewById(R.id.bottomview)
        Del=itemView.findViewById(R.id.DelBtn)
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


