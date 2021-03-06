package `in`.terxlabs.rtbms.dashboard.does.fragment.adapter

import `in`.terxlabs.rtbms.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoAdapter( var does:Array<String>) :RecyclerView.Adapter<DoAdapter.ViewHolder>(){
    var size=does.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_do,null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return does.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text=does[position]
        holder.imageView.setImageResource(R.drawable.ic_completed)
    }

    class ViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
      var textView=itemView.findViewById<TextView>(R.id.tvDoDont)
        var imageView=itemView.findViewById<ImageView>(R.id.imageView)
    }
}
