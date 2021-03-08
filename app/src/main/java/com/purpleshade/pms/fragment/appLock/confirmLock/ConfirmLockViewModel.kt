package com.purpleshade.pms.fragment.appLock.confirmLock

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.snackbar
import java.lang.StringBuilder

class ConfirmLockViewModel(val context: Context, val view: View, val box1: EditText, val box2: EditText, val box3: EditText, val box4: EditText, val button: Button) : ViewModel() {

    val sb = StringBuilder()
    val roomUser = RoomUser()

    fun confirmPinBoxes() {
        button.isEnabled = false
        val edit = arrayOf<EditText>(box1, box2, box3, box4)

        box1.addTextChangedListener(GenericTextWatcher(box1, edit))
        box2.addTextChangedListener(GenericTextWatcher(box2, edit))
        box3.addTextChangedListener(GenericTextWatcher(box3, edit))
        box4.addTextChangedListener(GenericTextWatcher(box4, edit))

    }

    fun onCreatePinButtonClick(view: View) {
        view.snackbar(context, "PIN is created", R.color.colorGreen)
        Flag.enableScreenLock = true
        roomUser.appPassword = sb.toString()
        //update pin into table
        BaseActivity.INSTANCE!!.myDao().pinUpdate(sb.toString(),1)
        view.findNavController().navigate(R.id.profileFragment)
    }

    inner class GenericTextWatcher(private val view: View, private val editText: Array<EditText>) : TextWatcher {
        override fun afterTextChanged(editable: Editable) {

            val text = editable.toString()
            sb.append(text)
            when (view.id) {
                R.id.mEditTextBoxOne -> if (text.length == 1) editText[1].requestFocus()
                R.id.mEditTextBoxTwo -> if (text.length == 1) editText[2].requestFocus() else if (text.isEmpty()) editText[0].requestFocus()
                R.id.mEditTextBoxThree -> if (text.length == 1) editText[3].requestFocus() else if (text.isEmpty()) editText[1].requestFocus()
                R.id.mEditTextBoxFour ->
                    if (text.length == 1 || text.isNotEmpty())
                        button.isEnabled = true
                    else if (text.isEmpty()) {
                        button.isEnabled = false
                        editText[2].requestFocus()
                    }
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}