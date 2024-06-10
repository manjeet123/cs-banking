package com.cs.banking

import android.app.Dialog
import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

private var dialog: Dialog? = null

class AppUtils {

    companion object {

        fun getNewProgressDialog(context: Context?, msg: String?, color: Int) {
            if (dialog != null) {
                dismissProgressDialog()
            }
            dialog = Dialog(context!!)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog?.setContentView(R.layout.progress_dialog)
            dialog?.setCanceledOnTouchOutside(false)
            dialog?.setCancelable(false)
            if (dialog?.window != null) {
                dialog!!.window?.setBackgroundDrawableResource(android.R.color.transparent)
            }
            if (msg != null && !msg.isEmpty()) {
                val tvMessage = dialog?.findViewById<View>(R.id.status_message) as TextView
                tvMessage.visibility = View.VISIBLE
                tvMessage.text = msg
                tvMessage.setTextColor(ContextCompat.getColor(context, color))
            }
            val mProgressBar = dialog?.findViewById<View>(R.id.progressBar) as ProgressBar
            // fixes pre-Lollipop progressBar indeterminateDrawable tinting
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                val wrapDrawable = DrawableCompat.wrap(mProgressBar.indeterminateDrawable)
                DrawableCompat.setTint(
                    wrapDrawable,
                    ContextCompat.getColor(context, R.color.primary_color)
                )
                mProgressBar.indeterminateDrawable = DrawableCompat.unwrap(wrapDrawable)
            } else {
                mProgressBar.indeterminateDrawable.setColorFilter(
                    ContextCompat.getColor(
                        context,
                        R.color.primary_color
                    ), PorterDuff.Mode.SRC_IN
                )
            }
            dialog?.show()
        }

        fun dismissProgressDialog() {
            if (dialog != null && dialog?.isShowing == true) {
                try {
                    dialog?.dismiss()
                } catch (e: Exception) {

                } finally {
                    dialog = null
                }
            }
        }
    }

}