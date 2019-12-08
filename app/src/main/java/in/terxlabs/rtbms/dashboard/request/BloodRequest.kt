package `in`.terxlabs.rtbms.dashboard.request

import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.base.BaseActivity
import `in`.terxlabs.rtbms.dashboard.request.bean.Hospital
import `in`.terxlabs.rtbms.dashboard.request.hospitalNotAvailable.HospitalNotfound
import `in`.terxlabs.rtbms.dashboard.request.hospitaldetail.HospitalDetail
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.firebase.database.Query

import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_blood_request.*
import kotlinx.android.synthetic.main.dialog_please_wait.*


class BloodRequest : BaseActivity(), View.OnClickListener {
    lateinit var state: String
    lateinit var city: String
    lateinit var s: String
    lateinit var dis: String
    private var adapter: FirebaseRecyclerAdapter<Hospital, Holder>? = null
    lateinit var bloodgroup: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_request)
        createDialogWithLayout(this, R.layout.dialog_please_wait)
        Glide.with(this).asGif().load(R.raw.loading_spiral).into(dialog.progress)
        ivBack.setOnClickListener(this)
        state = intent.getStringExtra("state")
        city = intent.getStringExtra("city")
        bloodgroup = intent.getStringExtra("blood")
        if (bloodgroup == "A+") {
            bloodgroup = "aPCount"
        }
        if (bloodgroup == "A-") {
            bloodgroup = "aNCount"
        }
        if (bloodgroup == "B+") {
            bloodgroup = "bPCount"
        }
        if (bloodgroup == "B-") {
            bloodgroup = "bNCount"
        }
        if (bloodgroup == "AB+") {
            bloodgroup = "abPCount"
        }
        if (bloodgroup == "AB-") {
            bloodgroup = "abNCount"
        }
        if (bloodgroup == "O+") {
            bloodgroup = "oPCount"
        }
        if (bloodgroup == "O-") {
            bloodgroup = "oNCount"
        }
        val datarefer =
            FirebaseDatabase.getInstance().reference.child("web").child("hospitals").child(state)
                .child(city)
        datarefer.keepSynced(true)
        val ref =
            FirebaseDatabase.getInstance().reference.child("web").child("hospitals").child(state)
                .child(city)
        Log.i("destination", java.lang.String.valueOf(bloodgroup))
        val bloodquery:Query = ref.orderByChild(bloodgroup)
        val bloodcount :Query= bloodquery.startAt("5")
        rvHospitals.hasFixedSize()
        rvHospitals.layoutManager = LinearLayoutManager(this)
        bloodcount.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    rvHospitals.visibility=View.GONE
                    loadFragment(HospitalNotfound())
                    dialog.hide()
                   /* intent.putExtra("blood_part", bloodgroup)
                    intent.putExtra("district", city)
                    intent.putExtra("state", state)
                    startActivity(intent)*/
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i("oncancelled ", "Exception", databaseError.toException())
            }
        })
        val firerecycleoptions: FirebaseRecyclerOptions<Hospital> =
            FirebaseRecyclerOptions.Builder<Hospital>().setQuery(
                bloodcount,
                Hospital::class.java
            ).build()

        adapter = object : FirebaseRecyclerAdapter<Hospital, Holder>(firerecycleoptions) {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
             override fun onBindViewHolder(holder: Holder, position: Int, model: Hospital) {
                holder.setName(model.name)
                holder.setAddress(model.address)
                rvHospitals.visibility=View.VISIBLE
                dialog.hide()
                holder.v.setOnClickListener {
                    val id: String = model.hid
                    s = id
                    dis = city
                    val intent = Intent(applicationContext, HospitalDetail::class.java)
                    intent.putExtra("district", dis)
                    intent.putExtra("HID", id)
                    intent.putExtra("state", state)
                    startActivity(intent)
                }
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_hospitalsavailable, parent, false)
                return Holder(view)
            }
        }

        rvHospitals.adapter = adapter
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.navigation, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter!!.stopListening()
    }
    class Holder internal constructor(var v: View) :
        RecyclerView.ViewHolder(v) {
        @SuppressLint("SetTextI18n")
        fun setName(title: String) {
            val tname = v.findViewById<TextView>(R.id.tvHospital)
            tname.text = "Name :- $title"
        }

        @SuppressLint("SetTextI18n")
        fun setAddress(desc: String) {
            val taddr = v.findViewById<TextView>(R.id.tvAddress)
            taddr.text = "Address :- $desc"
        }

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.ivBack -> onBackPressed()

        }
    }
}
