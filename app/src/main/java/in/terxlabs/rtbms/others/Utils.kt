package com.socreates.others

import `in`.terxlabs.rtbms.R
import android.text.TextUtils
import android.view.View


class Utils {


    public fun viewGone(view: View) {
        if (view != null) {
            view.visibility = View.GONE

        }
    }

    fun viewInvisible(view: View) {
        if (view != null && view.visibility == View.VISIBLE) {
            view.visibility = View.INVISIBLE
        }
    }

    fun viewVisible(view: View) {
        if (view != null && (view.visibility == View.INVISIBLE || view.visibility == View.GONE)) {
            view.visibility = View.VISIBLE

        }
    }

    fun isEmptyString(value: String?): Boolean {
        return TextUtils.isEmpty(value) || TextUtils.isEmpty(value?.trim())
    }



}
