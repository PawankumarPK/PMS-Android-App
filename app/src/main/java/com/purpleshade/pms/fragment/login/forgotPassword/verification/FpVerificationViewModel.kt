package com.purpleshade.pms.fragment.login.forgotPassword.verification

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.FpVerificationRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.hide
import com.purpleshade.pms.utils.show
import java.lang.StringBuilder

class FpVerificationViewModel(
    val context: Context,
    private val box1: EditText,
    private val box2: EditText,
    private val box3: EditText,
    private val box4: EditText,
    private val box5: EditText,
    private val box6: EditText,
    var progressBar: ProgressBar,
    val repository: FpVerificationRepository
) : ViewModel() {

    var authListener: AuthListener? = null
    val sb = StringBuilder()


    fun verifyOnClick(view: View) {
        progressBar.show()
        Flag.forgotPassToken = sb.toString()
        val repo = repository.verifyForgetPasswordCode(context, progressBar)
        authListener!!.onSuccess(repo)
        repository.view = view
    }

    fun createPinBoxes() {
        val edit = arrayOf<EditText>(box1, box2, box3, box4, box5, box6)

        box1.addTextChangedListener(GenericTextWatcher(box1, edit))
        box2.addTextChangedListener(GenericTextWatcher(box2, edit))
        box3.addTextChangedListener(GenericTextWatcher(box3, edit))
        box4.addTextChangedListener(GenericTextWatcher(box4, edit))
        box5.addTextChangedListener(GenericTextWatcher(box5, edit))
        box6.addTextChangedListener(GenericTextWatcher(box6, edit))


    }


    inner class GenericTextWatcher(private val view: View, private val editText: Array<EditText>) : TextWatcher {
        override fun afterTextChanged(editable: Editable) {
            val text = editable.toString()
            sb.append(text)

            when (view.id) {
                R.id.mEditTextBoxOne -> if (text.length == 1) editText[1].requestFocus()
                R.id.mEditTextBoxTwo -> if (text.length == 1) editText[2].requestFocus() else if (text.isEmpty()) editText[0].requestFocus()
                R.id.mEditTextBoxThree -> if (text.length == 1) editText[3].requestFocus() else if (text.isEmpty()) editText[1].requestFocus()
                R.id.mEditTextBoxFour -> if (text.length == 1) editText[4].requestFocus() else if (text.isEmpty()) editText[2].requestFocus()
                R.id.mEditTextBoxFive -> if (text.length == 1) editText[5].requestFocus() else if (text.isEmpty()) editText[3].requestFocus()
                R.id.mEditTextBoxSix -> if (text.length == 1) editText[5].requestFocus() else if (text.isEmpty()) editText[4].requestFocus()


                // view.findNavController().navigate(R.id.profileFragment)


            }
        }

        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    }
}