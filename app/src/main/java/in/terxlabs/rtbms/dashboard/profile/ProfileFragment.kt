package `in`.terxlabs.rtbms.dashboard.profile

import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.dashboard.hospital_pharmacy.HospitalFragment
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(){

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)


        return view
    }
companion object{
    private var instance: ProfileFragment?= null
    fun getInstance(): Fragment {
        if (instance == null) {
            instance = ProfileFragment()
        }
        return instance!!
    }
}

}