package com.purpleshade.pms.fragment.appLock.createLock

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast

class CreateLockViewModel(val context: Context, val view: View, val box1: EditText, val box2: EditText, val box3: EditText, val box4: EditText) : ViewModel() {

    var lockStatus: String? = null
    val sb = StringBuilder()
    val listData = BaseActivity.INSTANCE!!.myDao().user

    fun createPinBoxes() {
        if (listData.lockAppStatus == "on")
            lockStatus = context.getString(R.string.please_enter_your_pin)
        else
            lockStatus = context.getString(R.string.create_your_lock_screen_pin)


        val edit = arrayOf<EditText>(box1, box2, box3, box4)

        box1.addTextChangedListener(GenericTextWatcher(box1, edit))
        box2.addTextChangedListener(GenericTextWatcher(box2, edit))
        box3.addTextChangedListener(GenericTextWatcher(box3, edit))
        box4.addTextChangedListener(GenericTextWatcher(box4, edit))

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
                    if (text.length == 1 && listData.lockAppStatus == "off") {
                        Flag.appPassword = sb.toString()
                        editText.isEmpty()
                        editText[3].requestFocus()
                        view.findNavController().navigate(R.id.action_createLockFragment_to_confirmLockFragment)
                    } else if (text.isEmpty())
                        editText[2].requestFocus()
                    else if (text.length == 1 && listData.lockAppStatus == "on") {
                        if (listData.appPassword != sb.toString()) {
                            context.toast(context.getString(R.string.incorrect_pin))
                            sb.clear()
                            return
                        }
                        view.snackbar(context, context.getString(R.string.disable_screen_lock), R.color.colorBlackGrey)
                        BaseActivity.INSTANCE!!.myDao().lockAppStatus("off", 1)
                        view.findNavController().navigate(R.id.profileFragment)
                    }
            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}