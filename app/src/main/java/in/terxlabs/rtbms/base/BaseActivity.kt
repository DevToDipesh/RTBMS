package `in`.terxlabs.rtbms.base

import `in`.terxlabs.rtbms.R
import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import `in`.terxlabs.rtbms.others.ResourceUtils

abstract class BaseActivity :AppCompatActivity(){
    lateinit var dialog: Dialog
    lateinit var snackbar: Snackbar
    fun createDialogWithLayout(context: Context, @LayoutRes layout: Int) {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ResourceUtils.getDrawable(R.color.transparent))
        dialog.setContentView(layout)
        dialog.show()
    }
    fun ShowDialog() {
        if (dialog != null && !dialog.isShowing) {
            dialog.show()
        }

    }

    fun HideDialog() {
        if (dialog != null && dialog.isShowing) {
            dialog.hide()
        }
    }
}