package com.purpleshade.pms.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.navigation.findNavController
import com.purpleshade.pms.R

/**
 * Created by pawan on 05,March,2021
 */
class GenericTextWatcher(private val view: View, private val editText: Array<EditText>) : TextWatcher {
    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        when (view.id) {
            R.id.mEditTextBoxOne -> if (text.length == 1) editText[1].requestFocus()
            R.id.mEditTextBoxTwo -> if (text.length == 1) editText[2].requestFocus() else if (text.isEmpty()) editText[0].requestFocus()
            R.id.mEditTextBoxThree -> if (text.length == 1) editText[3].requestFocus() else if (text.isEmpty()) editText[1].requestFocus()
            R.id.mEditTextBoxFour -> if (text.length == 1 || text.isNotEmpty()) {
                Log.d("=====>>", "====>>>")
                view.findNavController().navigate(R.id.action_createLockFragment_to_confirmLockFragment)
            } else if (text.isEmpty())
                editText[2].requestFocus()
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
}