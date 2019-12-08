package `in`.terxlabs.rtbms.dashboard.hospital_pharmacy

import `in`.terxlabs.rtbms.R
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions



class PharmacyFragment : Fragment(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private var mMap: GoogleMap? = null
    private var client: GoogleApiClient? = null
    private var locationRequest: LocationRequest? = null
    private var lastLocation: Location? = null
    private var currentLocationMarker: Marker? = null
    internal var proximityRadius = 10000
    internal var latitude: Double = 0.toDouble()
    internal var longitude: Double = 0.toDouble()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_pharmacies, container, false)

        val dataTransfer = arrayOfNulls<Any>(2)
        val pharmacy = "pharmacy"
        val url = getUrl(latitude, longitude, pharmacy)
        dataTransfer[0] = mMap
        dataTransfer[1] = url
        val nearby =
            GetNearbyPlacesDataHospital()
        nearby.execute(*dataTransfer)
        // Toast.makeText(getContext(), "Showing Nearby Pharmacy", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        (childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?)!!.getMapAsync(this)

        return v
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(
                        activity!!,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    if (client == null) {
                        bulidGoogleApiClient()
                    }
                    mMap!!.isMyLocationEnabled = true
                }
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            bulidGoogleApiClient()
            mMap!!.isMyLocationEnabled = true
        }
    }


    @Synchronized
    protected fun bulidGoogleApiClient() {
        client = GoogleApiClient.Builder(context!!).addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this).addApi(LocationServices.API).build()
        client!!.connect()

    }

    override fun onLocationChanged(location: Location) {

        latitude = location.latitude
        longitude = location.longitude
        lastLocation = location
        if (currentLocationMarker != null) {
            currentLocationMarker!!.remove()

        }
        Log.d("lat = ", "" + latitude)
        val latLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Location")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        currentLocationMarker = mMap!!.addMarker(markerOptions)
        //  pharmacy_name.setText( nearby.url );
        //pharmacy_extra_info.setText( markerOptions.getSnippet() );
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap!!.animateCamera(CameraUpdateFactory.zoomBy(15f))
        val dataTransfer = arrayOfNulls<Any>(2)
        val getNearbyPlacesDataHospital =
            GetNearbyPlacesDataHospital()
        mMap!!.clear()
        val pharmacyString = "pharmacy"
        val url = getUrl(latitude, longitude, pharmacyString)
        dataTransfer[0] = mMap
        dataTransfer[1] = url
        getNearbyPlacesDataHospital.execute(*dataTransfer)

        if (client != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(client, this)
        }
    }


    private fun getUrl(latitude: Double, longitude: Double, nearbyPlace: String): String {


        val googlePlaceUrl =
            StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?")
        googlePlaceUrl.append("location=$latitude,$longitude")
        googlePlaceUrl.append("&radius=$proximityRadius")
        googlePlaceUrl.append("&type=$nearbyPlace")
        googlePlaceUrl.append("&sensor=true")
        googlePlaceUrl.append("&key=" + "AIzaSyBPcmYW7T0Mj8VbSmMXIR6JRfj68UcUPAM")

        Log.d("MapsActivity", "url = $googlePlaceUrl")

        return googlePlaceUrl.toString()
    }

    override fun onConnected(bundle: Bundle?) {

        locationRequest = LocationRequest()
        locationRequest!!.interval = 100
        locationRequest!!.fastestInterval = 1000
        locationRequest!!.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY


        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                client,
                locationRequest,
                this as LocationListener
            )
        }
    }

    fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity!!,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_CODE
                )
            } else {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_CODE
                )
            }

        }
    }


    override fun onConnectionSuspended(i: Int) {}

    override fun onConnectionFailed(connectionResult: ConnectionResult) {}

    companion object {
        val REQUEST_LOCATION_CODE = 99
        private var instance: PharmacyFragment?= null
        fun getInstance(): Fragment {
            if (instance == null) {
                instance = PharmacyFragment()
            }
            return instance!!
        }
    }
}
