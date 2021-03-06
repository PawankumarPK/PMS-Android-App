package com.purpleshade.pms.fragment.appLock.confirmLock

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.purpleshade.pms.utils.GenericTextWatcher

class ConfirmLockViewModel(val view: View, val box1: EditText, val box2: EditText, val box3: EditText, val box4: EditText) : ViewModel(), TextWatcher {


    private val editText: Array<EditText>? = null

    fun confirmPinBoxes() {
        val edit = arrayOf<EditText>(box1, box2, box3, box4)

        box1.addTextChangedListener(GenericTextWatcher(box1, edit))
        box2.addTextChangedListener(GenericTextWatcher(box2, edit))
        box3.addTextChangedListener(GenericTextWatcher(box3, edit))
        box4.addTextChangedListener(GenericTextWatcher(box4, edit))

    }


    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(editable: Editable?) {
        val text = editable.toString()
        when (view) {
            box1 -> if (text.length == 1) editText!![1].requestFocus()
            box2 -> if (text.length == 1) editText!![2].requestFocus() else if (text.isEmpty()) editText!![0].requestFocus()
            box3 -> if (text.length == 1) editText!![3].requestFocus() else if (text.isEmpty()) editText!![1].requestFocus()
            box4 -> if (text.isEmpty()) editText!![2].requestFocus()
        }
    }

}