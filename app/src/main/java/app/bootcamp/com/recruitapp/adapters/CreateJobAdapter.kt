package app.bootcamp.com.recruitapp.adapters


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import app.bootcamp.com.recruitapp.R
import app.bootcamp.com.recruitapp.models.Post
import com.daimajia.swipe.SwipeLayout


import java.util.ArrayList

class CreateJobAdapter(private val mPosts: ArrayList<Post>,val onItemDelClick:(Int)->Unit) : RecyclerView.Adapter<JobPostViewHolder>() {
    override fun getItemCount(): Int {
        return mPosts.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobPostViewHolder {
        val myItemView = LayoutInflater.from(parent.context).inflate(R.layout.showpostdata, parent, false)
        return JobPostViewHolder(myItemView)
    }

    override fun onBindViewHolder(holder: JobPostViewHolder, position: Int) {
        val post = mPosts[position]
        holder.jobName.setText(post.jobCom)
        holder.samp!!.setShowMode(SwipeLayout.ShowMode.LayDown);
        holder.Del!!.setOnClickListener {
            onItemDelClick(position);
        }
    }
}


