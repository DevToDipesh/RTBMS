@file:Suppress("DEPRECATION")

package `in`.terxlabs.rtbms.dashboard.science

import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.base.BaseFragment
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.app.ProgressDialog
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog_please_wait.*
@Suppress("DEPRECATION")
 class ScienceFragment : BaseFragment() {

lateinit var webView:WebView
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_science, container, false)
        webView =  v.findViewById(R.id.webView)
        createDialogForFragmentWithLayout(context!!,R.layout.dialog_please_wait)
        Glide.with(context!!).asGif().load(R.raw.loading_spiral).into(dialog.progress)
        ShowDialog()
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true
        webView.loadUrl(  "https://drive.google.com/file/d/17MoyDLjboWI47lt1CBeOTtI3HzL4MfO1/view")
        webView.isVerticalScrollBarEnabled = true
        webView.isHorizontalScrollBarEnabled = true
        webView.webViewClient =
           object :WebViewClient(){
               override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                   view?.loadUrl(url)
                   return true
               }

               override fun onPageFinished(view: WebView?, url: String?) {
                HideDialog()
               }
           }

        return  v
    }


}
