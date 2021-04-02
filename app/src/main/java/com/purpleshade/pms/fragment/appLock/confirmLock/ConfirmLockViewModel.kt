package com.purpleshade.pms.fragment.appLock.confirmLock

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.activity.BaseActivity
import com.purpleshade.pms.db.RoomUser
import com.purpleshade.pms.repository.ConfirmLockRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.gone
import com.purpleshade.pms.utils.show
import com.purpleshade.pms.utils.snackbar
import com.purpleshade.pms.utils.toast
import java.lang.StringBuilder

class ConfirmLockViewModel(val context: Context, val view: View, val box1: EditText, val box2: EditText, val box3: EditText, val box4: EditText, val button: Button, val progressBar: ProgressBar, val repository: ConfirmLockRepository) : ViewModel() {

    val sb = StringBuilder()
    val roomUser = RoomUser()
    var authListener: AuthListener? = null

    fun confirmPinBoxes() {
        button.isEnabled = false
        val edit = arrayOf<EditText>(box1, box2, box3, box4)

        box1.addTextChangedListener(GenericTextWatcher(box1, edit))
        box2.addTextChangedListener(GenericTextWatcher(box2, edit))
        box3.addTextChangedListener(GenericTextWatcher(box3, edit))
        box4.addTextChangedListener(GenericTextWatcher(box4, edit))

    }

    fun onCreatePinButtonClick(view: View) {

        progressBar.show()

        if (Flag.appPassword != sb.toString()) {
            //view.snackbar(context, "PIN not matched", R.color.colorAccent)
            context.toast("PIN not matched")
            sb.clear()
            progressBar.gone()
            return
        }

        if (!Flag.networkProblem) {
            repository.confirmPin = sb.toString()
            val repo = repository.updatePin(context, progressBar)
            authListener!!.onSuccess(repo)
            repository.view = view

            roomUser.appPassword = sb.toString()
            //update pin into table
            BaseActivity.INSTANCE!!.myDao().pinUpdate(sb.toString(), 1)
            BaseActivity.INSTANCE!!.myDao().lockAppStatus("on", 1)


        }else{
            context.toast("Internet connection required")
        }


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