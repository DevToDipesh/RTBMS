package `in`.terxlabs.rtbms.dashboard.does.fragment


import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.dashboard.does.fragment.adapter.DoAdapter
import `in`.terxlabs.rtbms.dashboard.does.fragment.adapter.DontAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView


class DoDoesFragment : Fragment() {
 lateinit  var doRecyclerView:RecyclerView
    lateinit var dontRecyclerView: RecyclerView
    var do_list = arrayOf(
        "You are between age group of 18-60 years.",
        "Your weight is 45 kgs or more.",
        "Your haemoglobin is 12.5 gm% minimum.",
        "Your last blood donation was 3 months earlier.",
        "You are healthy and have not suffered from malaria, typhoid or other transmissible disease in the recent past.",
        "There are many, many people who meet these parameters of health and fitness!",
        "Do abide by our rules - be truthful about your health status!",
        "You have to be healthy to give 'safe blood'"
    )
    var dont_list = arrayOf(
        "Cold / fever in the past 1 week.",
        "Under treatment with antibiotics or any other medication.",
        "Cardiac problems, hypertension, epilepsy, diabetes (on insulin therapy), history of cancer,chronic kidney or liver disease, bleeding tendencies, venereal disease etc.",
        "Major surgery in the last 6 months.",
        "Vaccination in the last 24 hours.",
        "Had a miscarriage in the last 6 months or have been pregnant / lactating in the last one year.",
        "Had fainting attacks during last donation.",
        "Have regularly received treatment with blood products.",
        "Shared a needle to inject drugs/ have history of drug addiction.",
        "Had sexual relations with different partners or with a high risk individual.",
        "Been tested positive for antibodies to HIV.",
        "Pregnancy And Menstrual Cycle",
        "Females should not donate blood during pregnancy.",
        "They can donate after 6 months following a normal delivery and when they are not breast feeding.",
        "Females should not donate blood if they are having heavy menstrual flow or menstrual cramps."
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_do_does, container, false)
        doRecyclerView=view.findViewById(R.id.rvDoes)
        dontRecyclerView=view.findViewById(R.id.rvDont)
        doRecyclerView.adapter=DoAdapter(do_list)
        dontRecyclerView.adapter=DontAdapter(dont_list)
        return view
    }

    companion object {

        private var instance: DoDoesFragment?= null
        fun getInstance(): Fragment {
            if (instance == null) {
                instance = DoDoesFragment()
            }
            return instance!!
        }
    }
}
