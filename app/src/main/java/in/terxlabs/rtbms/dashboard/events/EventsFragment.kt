package `in`.terxlabs.rtbms.dashboard.events

import `in`.terxlabs.rtbms.base.BaseFragment
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.dashboard.events.bean.Events
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.dialog_please_wait.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EventsFragment : BaseFragment(), LocationListener {
    var tv: TextView? = null
    var criteria: Criteria? = null
    var bestProvider: String? = null
    var locationManager: LocationManager? = null
    private var adapter: FirebaseRecyclerAdapter<Events, Holder>? =
        null
    var city: String? = null
    var date: String? = null
    var finalise: Query? = null
    var cl: CalendarView? = null
    var obj_hospital: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (context == null) {
            return
        }
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                request
            )
            return
        }
        locationManager =
            context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        criteria = Criteria()
        bestProvider = locationManager!!.getBestProvider(criteria, true).toString()
        locationManager =
            activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        assert(locationManager != null)
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
               context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_events, container, false)
        obj_hospital = view.findViewById(R.id.events_recycle)
        cl = view.findViewById(R.id.calender)
        createDialogForFragmentWithLayout(context!!, R.layout.dialog_please_wait)
        cl?.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            // display the selected date by using a toast
            date = "$year-$month-$dayOfMonth"
            Toast.makeText(
                getContext(),
                "$dayOfMonth/$month/$year",
                Toast.LENGTH_LONG
            ).show()
        })
        val datarefer =
            FirebaseDatabase.getInstance().getReference("web").child("notifyPeople")
        datarefer.keepSynced(true)
        ShowDialog()
        dialog.progress
        Glide.with(context!!).asGif().load(R.raw.loading_spiral).into(dialog.progress)
        val ref =
            FirebaseDatabase.getInstance().getReference("web").child("notifyPeople")
        val state = ref.orderByChild("state")
        val events_state = state.equalTo("Maharashtra")
        finalise = events_state
        events_state.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) { // startActivity( new Intent( getContext()(), EventNotFound.class ) );
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        obj_hospital?.hasFixedSize()
        obj_hospital?.setLayoutManager(LinearLayoutManager(getContext()))
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
               context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                101
            )
        }
        val firebaseRecycleOptions: FirebaseRecyclerOptions<*> =
            FirebaseRecyclerOptions.Builder<Events>()
                .setQuery(finalise!!, Events::class.java).build()
        adapter = object :
            FirebaseRecyclerAdapter<Events, Holder>(
                firebaseRecycleOptions as FirebaseRecyclerOptions<Events>
            ) {
            override fun onBindViewHolder(
                holder: Holder,
                position: Int,
                model: Events
            ) {
                HideDialog()
                holder.setName(model.name)
                holder.setDate(model.date)
                holder.setTime(model.time)
                holder.setVenue(model.venue, model.cityPincode)
            }

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_events, parent, false)
                return Holder(
                    view
                )
            }
        }
        obj_hospital?.setAdapter(adapter)
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onLocationChanged(location: Location) {
        try {
            val geoCoder = Geocoder(context, Locale.getDefault())
            val addresses =
                geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            city = addresses[0].adminArea
        } catch (e: Exception) {
        }
    }

    override fun onStatusChanged(
        provider: String,
        status: Int,
        extras: Bundle
    ) {
    }

    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
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
        var comingDateFormat = SimpleDateFormat("yyyy-MM-dd")
        var setDateFormat = SimpleDateFormat("dd MMM,yyyy")
        @SuppressLint("SetTextI18n")
        fun setName(evname: String?) {
            val tname = v.findViewById<TextView>(R.id.event_name)
            tname.text = evname
        }

        @SuppressLint("SetTextI18n")
        fun setVenue(venue: String, pincode: String) {
            val tname = v.findViewById<TextView>(R.id.event_venue)
            tname.text = "$venue,$pincode"
        }

        @SuppressLint("SetTextI18n")
        fun setDate(date: String?) {
            try {
                val date_ = comingDateFormat.parse(date)
                val tname = v.findViewById<TextView>(R.id.event_date)
                assert(date_ != null)
                val setdate = setDateFormat.format(date_)
                tname.text = setdate
            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }

        @SuppressLint("SetTextI18n")
        fun setTime(time: String?) {
            val tname = v.findViewById<TextView>(R.id.event_time)
            tname.text = time
        }

    }

    companion object {
        private const val request = 1
        const val REQUEST_LOCATION = 1
        private var instance: EventsFragment?= null
        fun getInstance(): Fragment {
            if (instance == null) {
                instance = EventsFragment()
            }
            return instance!!
        }
    }
}