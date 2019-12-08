package `in`.terxlabs.rtbms.base

import `in`.terxlabs.rtbms.R
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import `in`.terxlabs.rtbms.others.ResourceUtils

abstract class BaseFragment :Fragment(){
    lateinit var dialog: Dialog
    lateinit var snackbar: Snackbar

    @SuppressLint("ResourceType")
    protected fun createDialogForFragmentWithLayout(context: Context, @LayoutRes v: Int) {
        dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(v)
        dialog.setCancelable(true)
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