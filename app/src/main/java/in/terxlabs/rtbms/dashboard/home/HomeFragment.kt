package `in`.terxlabs.rtbms.dashboard.home

import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.dashboard.request.BloodRequest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment


class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener,View.OnClickListener {
    lateinit var stateSpinner: Spinner
    lateinit var bloodSpinner: Spinner
    lateinit var districtSpinner: Spinner
    var districtArrayList: Array<String>? = null

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        stateSpinner = view.findViewById(R.id.state)
        bloodSpinner = view.findViewById(R.id.blood)
        view.findViewById<Button>(R.id.btSearch).setOnClickListener(this)
        districtSpinner = view.findViewById(R.id.city)
        val stateAdapter = ArrayAdapter(context!!, R.layout.item_spinneritem, resources.getStringArray(R.array.india_states))
        stateSpinner.adapter = stateAdapter
        val bloodAdapter = ArrayAdapter(context!!, R.layout.item_spinneritem, resources.getStringArray(R.array.SelectBloodGroup))
        bloodSpinner.adapter = bloodAdapter
        stateSpinner.onItemSelectedListener = this
        return view
    }

    companion object {
        private var instance: HomeFragment? = null
        fun getInstance(): Fragment {
            if (instance == null) {
                instance = HomeFragment()
            }
            return instance!!
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, arg1: View?, arg2: Int, p3: Long) {
        if (arg2 == 0) {
            districtArrayList =
                arrayOf("Nicobar", "North and Middle Andaman", "South Andaman")
            //Andaman and Nicobar
        }
        if (arg2 == 1) {
            districtArrayList = arrayOf(
                "Anantapur",
                "Chittoor",
                "East Godavari",
                "Guntur",
                "Kadapa",
                "Kurnool",
                "Sri Potti Sriramula",
                "Sri Kakulam",
                "Vishakhapatnam",
                "Viziangaram",
                "West Godavari"
            )
            //Andhra Pradesh
        }
        if (arg2 == 2) {
            districtArrayList = arrayOf(
                "Anjaw",
                "Changlong",
                "Dibang Valley",
                "East Kameng",
                "East Siang",
                "Kamle",
                "Kra Daadi",
                "Kurung Kumey",
                "Lohit",
                "Longding",
                "Lower Dibang Valley",
                "Lower Siang",
                "Lower Subansiri",
                "Namasai",
                "Papum Pare",
                "Siang",
                "Tawang",
                "Tirap",
                "Upper Siang",
                "Upper Subansiri",
                "Upper Kameng",
                "West Kameng",
                "West Siang"
            )
            //Arunachal Pradesh
        }
        if (arg2 == 3) {
            districtArrayList = arrayOf(
                "Baksa",
                "Barpeta",
                "Bishwanath",
                "Bongaigaon",
                "Cachar",
                "Charaideo",
                "Chirang",
                "Darrang",
                "Dhemaji",
                "Dhubri",
                " Dibrugarh",
                "Dima Hasao",
                "Goalpara",
                "Golaghat",
                "Hailakandi",
                "Hojai",
                "Jorhat",
                "Kamrup",
                "Kamrup Metropolitian",
                "Karbi Anglong",
                "Karmiganj",
                "Kokrajhar",
                "Lakhimpur",
                "Majuli",
                "Morigaon",
                "Nagaon",
                "Nalbari",
                "Sivasagar",
                "South Salmara-Mankachar",
                "Sonitpur",
                "Tinsukia",
                "Udalguri",
                "West Karbi Anglong"
            )
            //Assam
        }
        if (arg2 == 4) {
            districtArrayList = arrayOf(
                "Araria",
                "Arwal",
                "Aurangabad",
                "Banka",
                "Begusarai",
                "Bhagalpur",
                "Bhojpur",
                "Buxar",
                "Darbhanga",
                "East Champarah",
                "Gaya",
                "Gopalganj",
                "Jamui",
                "Jehanabad",
                "Kaimur",
                "Katihar",
                "Khargaria",
                "Kishanganj",
                "Lakhisarai",
                "Madhepura",
                "Madhubani",
                "Munger",
                "Muzaffarpur",
                "Nalanda",
                "Nawada",
                "Patna",
                "Purnia",
                "Rohtas",
                "Saharsa",
                "Samastipur",
                "Saran",
                "Sheikhpura",
                "Sheohar",
                "Sitamarhi",
                "Siwan",
                "Supaul",
                "Vaishal",
                "West Champaran"
            )
            //Bihar
        }
        if (arg2 == 5) {
            districtArrayList = arrayOf("Chandigarh")
            //Chandigarh
        }
        if (arg2 == 6) {
            districtArrayList = arrayOf(
                "Balod",
                "Baloda Bazar",
                "Balrampur",
                "Bastar",
                "Bemetara",
                "Bijapur",
                "Bilaspur",
                "Dantewada",
                "Dhamtari",
                "Durg",
                "Gariaband",
                "Janjgir-Champa",
                "Jashpur",
                "Kabirdham",
                "Kanker",
                "Kondagaon",
                "Korba",
                "Koriya",
                "Mahesamund",
                "Mungeli",
                "Narayanpur",
                "Raigarh",
                "Raipur",
                "Rajnandgaon",
                "Sukma",
                "Surajpur",
                "Surguja"
            )
            //Chattisgarh
        }
        if (arg2 == 7) {
            districtArrayList = arrayOf("Dadra and Nagar Haveli")
            //Dadra and Nagar Haveli
        }
        if (arg2 == 8) {
            districtArrayList = arrayOf("Daman", "Daman and Diu", "Diu")
            //Daman and diu
        }

        if (arg2 == 9) {
            districtArrayList = arrayOf(
                "Central Delhi",
                "East Delhi",
                "New Delhi",
                "North Delhi",
                "North East Delhi",
                "North West Delhi",
                "Shahdara",
                "South Delhi",
                "South East Delhi",
                "South West Delhi",
                "West Delhi"
            )
            //Delhi
        }
        if (arg2 == 10) {
            districtArrayList = arrayOf("North Goa", "South Goa")
            //Goa
        }
        if (arg2 == 11) {
            districtArrayList = arrayOf(
                "Ahmedabad",
                "Amreli",
                "Anand",
                "Banaskantha",
                "Bharuch",
                "Bhavnagar",
                "Botad",
                "Chotta Udaipur",
                "Dahod",
                "Dang",
                "Devbhoomi Dwarka",
                "Gandhinagar",
                "Gir Somnath",
                "Jamnagar",
                "Junagar",
                "Kheda",
                "Kutch",
                "Mahisagar",
                "Mehsana",
                "Morbi",
                "Narmada",
                "Navsari",
                "Panchmahal",
                "Patan",
                "Porbandar",
                "Rajkot",
                "Sabarkantha",
                "Surat",
                "Surendranagar",
                "Tapi",
                "Vadodara",
                "Vyara"
            )
            //Gujarat
        }
        if (arg2 == 12) {
            districtArrayList = arrayOf(
                "Ambala",
                "Bhiwani",
                "Charkhi Dadri",
                "Faridabad",
                "Fatehabad",
                "Gurgaon",
                "Hisar",
                "Jhajjar",
                "JindKaithal",
                "Karnal",
                "Mahendragarh",
                "NuhPalwai",
                "Panchkula",
                "Panipat",
                "Rewari",
                "Rohtak",
                "Sirsa",
                "Sonipat",
                "Yamunanagar"
            )
            //Haryana
        }
        if (arg2 == 13) {
            districtArrayList = arrayOf(
                "Bilaspur",
                "Chamba",
                "Himarpur",
                "Kangra",
                "Kinnaur",
                "Kullu",
                "Lahaul and Spiti",
                "Mandi",
                "Shimla",
                "Sirmaur",
                "Solan",
                "Una"
            )
            //Himachal Pradesh
        }
        if (arg2 == 14) {
            districtArrayList = arrayOf(
                "Anantnag",
                "Badgam",
                "Bandipora",
                "Baramulla",
                "Doda",
                "Ganderbal",
                "Jammu",
                "Kargil",
                "Kathua",
                "Kishtwar",
                "Kupwara",
                "Kulgam",
                "Leh",
                "Poonch",
                "Pulwana",
                "Rajauri",
                "Ramban",
                "Reasi",
                "Samba",
                "Shopian",
                "Srinagar",
                "Udhampur"
            )
            //Jammmu and Kashmir
        }
        if (arg2 == 15) {
            districtArrayList = arrayOf(
                "Bokaro",
                "Chatra",
                "Deoghar",
                "Dhanbad",
                "Dumka",
                "East Singhbhum",
                "Garhwa",
                "Giridih",
                "Godda",
                "Gumla",
                "Hazaribag",
                "Jamtara",
                "Khunti",
                "Koderma",
                "Latehar",
                "Lohardaga",
                "Pakur",
                "Palamu",
                "Ramgarh",
                "Ranchi",
                "Sahibganj",
                "Seraikele Kharsawan",
                "Simdega",
                "West Singhbum"
            )
            //Jharkhand
        }
        if (arg2 == 16) {
            districtArrayList = arrayOf(
                "Bagalkot",
                "Bangalore Rural",
                "Belgaum",
                "Bellary",
                "Bidar",
                "Bijapur",
                "Chamarajnagar",
                "Chikkamagaluru",
                "Chikkaballapur",
                "Chitradurga",
                "Davanagere",
                "Dharwad",
                "Dakshina Kannada",
                "Gadag",
                "Gulbarga",
                "Hassan",
                "Haveri District",
                "Kodagu",
                "Kolar",
                "Koppal",
                "Mandya",
                "Mysore ",
                "Ramanagara",
                "Raichur",
                "Shimoga",
                "Tumkur",
                "Udupi",
                "Uttara Kannada",
                "Vijayapura",
                "Yadgir"
            )
            //Karnataka
        }
        if (arg2 == 17) {
            districtArrayList = arrayOf(
                "Alappuzha",
                "Ernakulam",
                "Idukki",
                "Kannur",
                "Kasaragod",
                "Kollam",
                "Kottayam",
                "Kozhikode",
                "Malappuram",
                "Palakkad",
                "Pathanamthitta",
                "Thrissur",
                "Thiruvananthapuram",
                "Wayanad"
            )
            //Kerala
        }
        if (arg2 == 18) {
            districtArrayList = arrayOf("Lakshadweep")
            //Lakshadweep
        }
        if (arg2 == 19) {
            districtArrayList = arrayOf(
                "Agar Malwa",
                "Alirajpur",
                "Anuppur",
                "Ashok Nagar",
                "Balaghat",
                "Barwani",
                "Betul",
                "Bhind",
                "Bhopal",
                "Burhanpur",
                "Chhatarpur",
                "Chhindwara",
                "Damoh",
                "Datia",
                "Dewas",
                "Dhar",
                "Dindori",
                "Guna",
                "Gwalior",
                "Harda",
                "Hoshangabad",
                "Indore",
                "Jabalpur",
                "Jhabua",
                "Katni",
                "Khandwa (East Nimar)",
                "Khargone (West Nimar)",
                "Mandla",
                "Mandsaur",
                "Morena",
                "Narsinghpur",
                "Neemuch",
                "Panna",
                "Rewa",
                "Rajgarh",
                "Ratlam",
                "Raisen",
                "Sagar",
                "Satna",
                "Sehore",
                "Seoni",
                "Shahdol",
                "Shajapur",
                "Sheopur",
                "Shivpuri",
                "Sidhi",
                "Singrauli",
                "Tikamgarh",
                "Ujjain",
                "Umaria",
                "Vidisha"
            )
            //Madhya Pradesh
        }
        if (arg2 == 20) {
            districtArrayList = arrayOf(
                "Ahmednagar",
                "Akola",
                "Amravati",
                "Aurangabad",
                "Bhandara",
                "Beed",
                "Buldhana",
                "Chandrapur",
                "Dhule",
                "Gadchiroli",
                "Gondia",
                "Hingoli",
                "Jalgaon",
                "Jalna",
                "Kolhapur",
                "Latur",
                "Mumbai City",
                "Mumbai Suburban",
                "Nandurbar",
                "Nanded",
                "Nagpur",
                "Nashik",
                "Osmanabad",
                "Parbhani",
                "Pune",
                "Raigad",
                "Ratnagiri",
                "Sangli",
                "Sindhudurg",
                "Solapur",
                "Satara",
                "Thane",
                "Wardha",
                "Washim",
                "Yavatmal"
            )
            //Maharashtra
        }
        if (arg2 == 21) {
            districtArrayList = arrayOf(
                "Bishnupur",
                "Churachandpur",
                "Chandel",
                "Imphal East",
                "Imphal West",
                "Senepati",
                "Tamenglong",
                "Thoubal",
                "Ukhrul"
            )
            //Manipur
        }
        if (arg2 == 22) {
            districtArrayList = arrayOf(
                "East Garo Hills",
                "East Khasi Hills",
                "East Jaintia Hills",
                "North Garo Hills",
                "Ri Bhoi",
                "South Garo Hills",
                "South West Garo Hills",
                "South West Khasi Hills",
                "West Jaintia Hills",
                "West Garo Hills",
                "West Khasi Hills"
            )
            //Meghalaya
        }
        if (arg2 == 23) {
            districtArrayList = arrayOf(
                "Aizawl",
                "Champhai",
                "Kolasib",
                "Lawngtlai",
                "Lunglei",
                "Mamit",
                "Saiha",
                "Serchhip"
            )
            //Mizoram
        }
        if (arg2 == 24) {
            districtArrayList = arrayOf(
                "Dimapur",
                "Kiphir",
                "Kohima",
                "Longeleng",
                "Mokokchung",
                "Mon",
                "Peren",
                "Phek",
                "Tuesang",
                "Wokha",
                "Zunheboto"
            )
            //Nagaland
        }
        if (arg2 == 25) {
            districtArrayList = arrayOf(
                "Angul",
                "Boudh",
                "Bhadrak",
                "Balangir",
                "Bargarh",
                "Balasore",
                "Cuttack",
                "Debagarh",
                "Dhenkanal",
                "Ganjam",
                "Gajapati",
                "Jharsugudu",
                "Jajpur",
                "Jagatsinghpur",
                "Khordha",
                "Kendujhar",
                "Kalahandi",
                "Kandhamal",
                "Koraput",
                "Kendrapara",
                "Malkangiri",
                "Mayurbhanj",
                "Nabarangpur",
                "Nuapada",
                "Nayagarh",
                "Puri",
                "Rayagada",
                "Sambalpur",
                "Sundargarh"
            )
            //Odhisa
        }
        if (arg2 == 26) {
            districtArrayList = arrayOf("Karaikal", "Mahe", "Pondicherry", "Yanam")
            //Puducherry
        }
        if (arg2 == 27) {
            districtArrayList = arrayOf(
                "Amritsar",
                "Barnala",
                "Faridkot",
                "Firozpur",
                "Fatehgarh Sahib",
                "Fazilka",
                "Gurdaspur",
                "Hoshiarpur",
                "Jalandhar",
                "apurthala",
                "Ludhiana",
                "Mansa",
                "Moga",
                "Patiala",
                "Pathankot",
                "Rupnagar",
                "Sahibzada Ajit Singh Nagar",
                "Sahid Bhagat Singh Nagar",
                "Sangrur",
                "Sri Muktsar Sahib",
                "Tarn Taran"
            )
            //Punjab
        }
        if (arg2 == 28) {
            districtArrayList = arrayOf(
                "Ajmer",
                "Alwar",
                "Bikaner",
                "Barmer",
                "Banswara",
                "Bharatpur",
                "Baran",
                "Bundi",
                "Bhilwara",
                "Churu",
                "Chittorgarh",
                "Dausa",
                "Dholpur",
                "Dungapur",
                "Ganganagar",
                "Hanumangarh",
                "Jhunjhunu",
                "Jalore",
                "Jaipur",
                "Jaisalmer",
                "Jhalawar",
                "Karauli",
                "Kota",
                "Nagaur",
                "Pali",
                "Pratapgarh",
                "Rajsamand",
                "Sikar",
                "Sawai Madhopur",
                "Sirohi",
                "Tonk",
                "Udaipur"
            )
            //Rajasthan
        }
        if (arg2 == 29) {
            districtArrayList =
                arrayOf("East Sikkim", "North Sikkim", "South Sikkim", "West Sikkim")
            //Sikkim
        }
        if (arg2 == 30) {
            districtArrayList = arrayOf(
                "Ariyalur",
                "Chennai",
                "Coimbatore",
                "Cuddalore",
                "Dharmapuri",
                "Dindigul",
                "Erode",
                "Kanchipuram",
                "Kanyakumari",
                "Karur",
                "Madurai",
                "Nagapattinam",
                "Nilgiris",
                "Namakkal",
                "Perambalur",
                "Pudukkottai",
                "Ramanathapuram",
                "Salem",
                "Sivaganga",
                "Tiruchirappalli",
                "Theni",
                "Tirunelveli",
                "Thanjavur",
                "Thoothukudi",
                "Tiruvarur",
                "Tiruvannamalai",
                "Vellore",
                "Viluppuram",
                "Virudhunagar"
            )
            //Tamil Nadu
        }
        if (arg2 == 31) {
            districtArrayList = arrayOf(
                "Adilabad",
                "Bhadradri Kothagudem",
                "Hyderabad",
                "Jagtial",
                "Jagaon",
                "Jayashankar Bhupalpally",
                "Jogulamba Gadwal",
                "Kamareddy",
                "Karimnagar",
                "Khammam",
                "Komaram Bheem Asifabad",
                "Mahabubabad",
                "Mancherial",
                "Medak",
                "Medchal",
                "Nalgonda",
                "Nagarkurnool",
                "Nirmal",
                "Nizamabad",
                "Peddapalli",
                "Ranjana Sircilla",
                "Ranga Reddy",
                "Sangareddy",
                "Siddipet",
                "Suryapet",
                "Vikarabad",
                "Wanaparthy",
                "Warangal(Rural)",
                "Warangal(Urban)",
                "Yadadri"
            )
            //Telegana
        }
        if (arg2 == 32) {
            districtArrayList = arrayOf(
                "Dhalai",
                "Gomati",
                "Khowali",
                "North Tripura",
                "Sepahijali",
                "South Tripura",
                "Unokoti",
                "West Tripura"
            )
            //Tripura
        }
        if (arg2 == 33) {
            districtArrayList = arrayOf(
                "Almora",
                "Bageshwar",
                "Chamoli",
                "Champawat",
                "Dehradun",
                "Haridwar",
                "Nainital",
                "Pauri Garhwal",
                "Pithoragarh",
                "Rudraprayag",
                "Tehri Garhwal",
                "Udham Singh Nagar",
                "Uttarkashi"
            )
            //Uttarakhand
        }
        if (arg2 == 34) {
            districtArrayList = arrayOf(
                "Agra",
                "Aligarh",
                "Allahabad",
                "Ambedkar Nagar",
                "Amethi",
                "Amroha",
                "Auraiya",
                "Azamgarh",
                "Bagpat",
                "Bahraich",
                "Ballia",
                "Balrampur",
                "Banda",
                "Barabanki",
                "Bareilly",
                "Basti",
                "Bhadoli",
                "Bijnor",
                "Budaun",
                "Bulandshahr",
                "Chandaulli",
                "Chitrakoot",
                "Deoria",
                "Etah",
                "Etawah",
                "Firozabad",
                "Farrukhabad",
                "Fatehpur",
                "firozabad",
                "Gautam Buddh Nagar",
                "Ghaziabad",
                "Ghazipur",
                "Gonda",
                "Gorakhpur",
                "Hamirpur",
                "Hardoi",
                "Hathras",
                "Jalaun",
                "Jaunpur",
                "Jhansi",
                "Kannauj",
                "Kanpur Dehat",
                "Kanpur Nagar",
                "Kasganj",
                "Kushambi",
                "Kushinagar",
                "Lakhimpur Kheri",
                "Lalitpur",
                "Lucknow",
                "Maharajganj",
                "Mohoba",
                "Mainpuri",
                "Mathura",
                "Mau",
                "Meerut",
                "Mirzapur",
                "Moradabaad",
                "Muzaffarnagar",
                "Pilibhit",
                "Pratapgarh",
                "Raebarelli",
                "Rampur",
                "Saharanpur",
                "Sambhal",
                "Sant Kabir Nagar",
                "Shamli",
                "Sharavasti",
                "Siddharthnagar",
                "Sitapur",
                "Sultanpur",
                "Sultanpur",
                "Unnao",
                "Varanasi"
            )
            //uttar pradesh
        }
        if (arg2 == 35) {
            districtArrayList = arrayOf(
                "Alipurduar",
                "Bankura",
                "Birbhum",
                "Cooch Behar",
                "Darjeeling",
                "Hooghly",
                "Howrah",
                "Jalpaiguri",
                "Jhargram",
                "Kalimpong",
                "Kolkata",
                "Maldah",
                "Murshidabad",
                "Nadia",
                "North 24 Parganas",
                "Paschim Medinipur",
                "Purulia",
                "South 24 Parganas",
                "Uttar Dinajpur"
            )
            //West Bengal

        }

        val districtAdapter =
            ArrayAdapter(context!!, R.layout.item_spinneritem, districtArrayList as Array<String>)

        districtSpinner.adapter = districtAdapter
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btSearch->{
                var intent=Intent(context,BloodRequest::class.java)
                intent.putExtra("state",stateSpinner.selectedItem.toString())
                intent.putExtra("city",districtSpinner.selectedItem.toString())
                intent.putExtra("blood",bloodSpinner.selectedItem.toString())
                startActivity(intent)
            }
        }
    }


}



