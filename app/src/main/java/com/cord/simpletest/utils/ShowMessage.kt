package com.cord.simpletest.utils

import android.app.Dialog
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.cord.simpletest.R


class ShowMessage private constructor() {
    private lateinit var dialog: Dialog

    companion object {

        private var showMessage: ShowMessage? = null
        fun getInstance(): ShowMessage {
            synchronized(this) {
                showMessage = ShowMessage()
            }
            return showMessage!!
        }
    }

    fun Show(contaxt: Context, message: String?, time: Int): Dialog {
        dialog = Dialog(contaxt)
        dialog.setContentView(R.layout.error_message_show_ui)
        dialog.window!!.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        dialog.setCancelable(true)
        val error_msg_okey: TextView by lazy { dialog.findViewById(R.id.error_msg_okey) }
        val msg_icon: ImageView by lazy { dialog.findViewById(R.id.msg_icon) }
        val msg_title: TextView by lazy { dialog.findViewById(R.id.msg_title) }
        val msg: TextView by lazy { dialog.findViewById(R.id.message) }
        msg.setText(message)
        error_msg_okey.setOnClickListener {
            dialog.dismiss()
        }

        if (time > 0) {
            if (dialog.isShowing) {
                Handler(Looper.getMainLooper()).postDelayed({
                    dialog.dismiss()
                }, time.toLong())
            }
        }

        return dialog
    }


}