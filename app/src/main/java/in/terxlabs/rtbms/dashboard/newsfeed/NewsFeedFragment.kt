package `in`.terxlabs.rtbms.dashboard.newsfeed
import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.base.BaseFragment
import `in`.terxlabs.rtbms.dashboard.newsfeed.bean.NewsFeed
import android.content.Context
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.dialog_please_wait.*


class NewsFeedFragment : BaseFragment() {
    private var adapter: FirebaseRecyclerAdapter<NewsFeed, NewsViewHolder>? = null
   lateinit var newsfeedRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_newsfeed, container, false)
        val mDatabase = FirebaseDatabase.getInstance().getReference("web").child("newsfeed")
        mDatabase.keepSynced(true)
        newsfeedRecyclerView = view.findViewById(R.id.rlNewsFeed)
        createDialogForFragmentWithLayout(context!!, R.layout.dialog_please_wait)
        ShowDialog()
        Glide.with(context!!).asGif().load(R.raw.loading_spiral).into(dialog.progress)
        val datarefer =FirebaseDatabase.getInstance().getReference("web").child("newsfeed")
        val personsQuery: Query = datarefer.orderByKey()
        newsfeedRecyclerView.hasFixedSize()
        newsfeedRecyclerView.isNestedScrollingEnabled = true
        newsfeedRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val personsOptions: FirebaseRecyclerOptions<NewsFeed> = FirebaseRecyclerOptions.Builder<NewsFeed>().setQuery(personsQuery, NewsFeed::class.java).build()
        adapter = object : FirebaseRecyclerAdapter<NewsFeed, NewsViewHolder>(personsOptions) {
                 override fun onBindViewHolder(holder: NewsViewHolder, position: Int, model: NewsFeed) {
                    HideDialog()
                    holder.setDate(model.date)
                    holder.setDescription(model.description)

                    holder.setImage(context!!, model.image)
                    holder.setHead(model.head)
                }

                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
                    val v: View = LayoutInflater.from(parent.context).inflate(R.layout.item_newsfeed, parent, false)
                    return NewsViewHolder(v)
                }
            }
        newsfeedRecyclerView.adapter = adapter
        return view
    }


    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }
    class NewsViewHolder internal constructor(private var mView: View) :
        RecyclerView.ViewHolder(mView) {
        fun setDescription(title: String?) {
            val posttitle = mView.findViewById<TextView>(R.id.tvDescription)
            posttitle.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD)
            posttitle.text = title
        }

        fun setDate(desc: String?) {
            val postdesc = mView.findViewById<TextView>(R.id.tvNewsDate)
            postdesc.text = desc
        }

        fun setHead(date: String?) {
            val head = mView.findViewById<TextView>(R.id.tvNewsName)
            head.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD)
            head.text = date
        }

        fun setImage(ctx: Context?, image: String?) {
            val postimage: ImageView = mView.findViewById(R.id.imNewsImage)
            Glide.with(ctx!!).asDrawable().load(image).into(postimage)
        }

    }

    companion object {
        private var instance: NewsFeedFragment?= null
        fun getInstance(): Fragment {
            if (instance == null) {
                instance = NewsFeedFragment()
            }
            return instance!!
        }
    }
}
