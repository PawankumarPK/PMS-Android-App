package com.purpleshade.pms.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.purpleshade.pms.R

/**
 * Created by pawan on 05,March,2021
 */
 class GenericTextWatcher(private val view: View, private val editText: Array<EditText>) : TextWatcher {
    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (view.id) {
            R.id.otp_edit_box1 -> if (text.length == 1) editText[1].requestFocus()
            R.id.otp_edit_box2 -> if (text.length == 1) editText[2].requestFocus() else if (text.isEmpty()) editText[0].requestFocus()
            R.id.otp_edit_box3 -> if (text.length == 1) editText[3].requestFocus() else if (text.isEmpty()) editText[1].requestFocus()
            R.id.otp_edit_box4 -> if (text.isEmpty()) editText[2].requestFocus()
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
}