package com.purpleshade.pms.fragment.signup.verification

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.purpleshade.pms.R
import com.purpleshade.pms.repository.SignupVerificationRepository
import com.purpleshade.pms.utils.customInterface.AuthListener
import com.purpleshade.pms.utils.customObject.Flag
import com.purpleshade.pms.utils.show
import kotlinx.android.synthetic.main.verified_dialog.*
import java.lang.StringBuilder

class VerificationViewModel(
    val context: Context,
    private val box1: EditText,
    private val box2: EditText,
    private val box3: EditText,
    private val box4: EditText,
    private val box5: EditText,
    private val box6: EditText,
    var progressBar: ProgressBar,
    val repository: SignupVerificationRepository
) : ViewModel() {

    var authListener: AuthListener? = null
    val sb = StringBuilder()
    lateinit var mDialog: Dialog


    fun createPinBoxes() {
        val edit = arrayOf<EditText>(box1, box2, box3, box4, box5, box6)

        box1.addTextChangedListener(GenericTextWatcher(box1, edit))
        box2.addTextChangedListener(GenericTextWatcher(box2, edit))
        box3.addTextChangedListener(GenericTextWatcher(box3, edit))
        box4.addTextChangedListener(GenericTextWatcher(box4, edit))
        box5.addTextChangedListener(GenericTextWatcher(box5, edit))
        box6.addTextChangedListener(GenericTextWatcher(box6, edit))


    }

    fun changeEmailOnClick(view: View) {
        view.findNavController().navigate(R.id.signUpFragment)

    }

    fun onConfirmButtonClick(view: View) {
        Flag.signUpToken = sb.toString()
        sb.clear()
        val repo = repository.verifySignUp(context)
        authListener!!.onSuccess(repo)
        successfullyVerifiedDialog(view)

        repository.view = view

    }

    private fun successfullyVerifiedDialog(view: View) {
        mDialog = Dialog(context)
        val layout = LayoutInflater.from(context).inflate(R.layout.verified_dialog, null, false)
        mDialog.setContentView(layout)

        mDialog.mGoToLogin.setOnClickListener {
            mDialog.dismiss()
            view.findNavController().navigate(R.id.loginFragment)

        }
        mDialog.setCanceledOnTouchOutside(false)
        mDialog.show()
        mDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    }

    fun resendVerificationCodeOnClick(view:View){
        progressBar.show()

        val removeSignUpFieldRepo = repository.removeSignUp(context, progressBar)
        authListener!!.onSuccess(removeSignUpFieldRepo)

        view.findNavController().navigate(R.id.signUpFragment)
        repository.view = view

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